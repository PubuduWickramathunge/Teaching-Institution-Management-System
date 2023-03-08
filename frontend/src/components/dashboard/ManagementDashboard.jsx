import React, { useState, useEffect } from "react";
import { Table, Layout, Button } from "antd";
import TopNavBar from "../NavBar";
import { Link, useNavigate } from "react-router-dom";
import "./dashboard.css";
import { isTokenExpired } from "../../utils/TokenUtils";
import CourseTableSwitcher from "../course/admin/CourseTableSwitcher";

const columns = [
  {
    title: "ID",
    dataIndex: "id",
    key: "id",
  },
  {
    title: "First Name",
    dataIndex: "firstName",
    key: "firstName",
  },
  {
    title: "Last Name",
    dataIndex: "lastName",
    key: "lastName",
  },
  {
    title: "Email",
    dataIndex: "email",
    key: "email",
  },
];

const ManagementDashboard = () => {
  const [students, setStudents] = useState([]);
  const [teachers, setTeachers] = useState([]);
  const [managers, setManagers] = useState([]);
  const [users, setUsers] = useState([]);
  const [selected, setSelected] = useState("users");
  const navigate = useNavigate();

  const token = localStorage.getItem("token");

  useEffect(() => {
    if (!token || isTokenExpired(token)) {
      navigate("/login");
    } else {
      const options = {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      };

      fetch("http://localhost:8080/getAll/students", options)
        .then((response) => response.json())
        .then((data) => setStudents(data));

      fetch("http://localhost:8080/getAll/teachers", options)
        .then((response) => response.json())
        .then((data) => setTeachers(data));

      fetch("http://localhost:8080/getAll/managers", options)
        .then((response) => response.json())
        .then((data) => setManagers(data));

      fetch("http://localhost:8080/getAll/users", options)
        .then((response) => response.json())
        .then((data) => setUsers(data));
    }
  }, [token, navigate]);

  const handleStudentButtonClick = () => {
    setSelected("students");
  };

  const handleTeacherButtonClick = () => {
    setSelected("teachers");
  };

  const handleManagerButtonClick = () => {
    setSelected("managers");
  };

  const handleUserButtonClick = () => {
    setSelected("users");
  };
  let dataSource;
  let tableTitle;
  if (selected === "students") {
    dataSource = students;
    tableTitle = "List of Students";
  } else if (selected === "teachers") {
    dataSource = teachers;
    tableTitle = "List of Teachers";
  } else if (selected === "managers") {
    dataSource = managers;
    tableTitle = "List of Managers";
  } else if (selected === "users") {
    dataSource = users;
    tableTitle = "List of Users";
  }

  return (
    <Layout>
      <Layout.Header>
        <TopNavBar />
      </Layout.Header>
      <Layout.Content>
        <div style={{ padding: " 50px" }}>
          <div>
            <CourseTableSwitcher />
          </div>
          <Button
            className="home-button"
            type="primary"
            size="large"
            onClick={handleStudentButtonClick}
          >
            View All Students
          </Button>
          <Button
            className="home-button"
            type="primary"
            size="large"
            onClick={handleTeacherButtonClick}
          >
            View All Teachers
          </Button>
          <Button
            className="home-button"
            type="primary"
            size="large"
            onClick={handleManagerButtonClick}
          >
            View All Managers
          </Button>
          <Button
            className="home-button"
            type="primary"
            size="large"
            onClick={handleUserButtonClick}
          >
            View All Users
          </Button>

          <Link to="/add-manager">
            <Button className="home-button" type="default" size="large">
              <i>Add New Manager</i>
            </Button>
          </Link>

          <br />
          <br />
          <h2 style={{ color: "#1eb2a6" }}>{tableTitle}</h2>
          <Table dataSource={dataSource} columns={columns} />
        </div>
      </Layout.Content>
    </Layout>
  );
};

export default ManagementDashboard;
