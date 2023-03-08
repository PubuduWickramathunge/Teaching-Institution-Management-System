import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Table, Layout, Card } from "antd";

import { isTokenExpired } from "../../utils/TokenUtils";

import "./Dashboard";
import "./dashboard.css";

import TopNavBar from "../NavBar";
import ClassCard from "./ClassCard";

const columns = [
  {
    title: "Course",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "Description",
    dataIndex: "description",
    key: "description",
  },
  {
    title: "Materials",
    dataIndex: "",
    key: "",
  },
];

const TeacherDashboard = () => {
  const [classes, setClasses] = useState([]);

  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const teacherId = localStorage.getItem("id");

  useEffect(() => {
    console.log("teacherId:", teacherId);
    if (!token || isTokenExpired(token)) {
      navigate("/login");
    }
    const options = {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    };
    fetch(`http://localhost:8080/teachers/${teacherId}/courses`, options)
      .then((response) => response.json())
      .then((data) => setClasses(data));
  }, [teacherId, navigate, token]);

  return (
    <Layout>
      <Layout.Header>
        <TopNavBar />
      </Layout.Header>
      <Layout.Content>
        <div className="dashboard" style={{ padding: "50px" }}>
          <br />
          <br />
          <h2 style={{ color: "#1eb2a6" }}>My Classes</h2>
          <div className="dashboard-cards-container">
            {classes.map((course) => (
              <ClassCard key={course.id} course={course} />
            ))}
          </div>
          <br />
          <br />
          <h2 style={{ color: "#1eb2a6" }}>Overview</h2>
          <Table
            style={{ borderBlockEndWidth: "5px", marginTop: 50 }}
            dataSource={classes.map((classes) => ({
              ...classes,
            }))}
            columns={columns}
            rowKey="id"
          />
          <br />
          <div className="dashboard-cards-container">
            <Card title="Courses" className="dashboard-card">
              <p className="dashboard-card-data">{classes.length}</p>
              <p className="dashboard-card-label">Total Classes</p>
            </Card>
          </div>
        </div>
      </Layout.Content>
    </Layout>
  );
};

export default TeacherDashboard;
