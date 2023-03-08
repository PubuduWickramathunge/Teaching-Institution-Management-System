import React, { useEffect, useState } from "react";
import { Table, Layout, Card, Button } from "antd";
import { useNavigate, Link } from "react-router-dom";
import "./Dashboard";
import "./dashboard.css";
import { isTokenExpired } from "../../utils/TokenUtils";

import TopNavBar from "../NavBar";
import CourseCard from "./CourseCard";

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
    title: "Teacher",
    dataIndex: "teacherName",
    key: "teacherName",
  },
  {
    title: "Contact Teacher via Email",
    dataIndex: "teacherEmail",
    key: "teacherEmail",
  },

  {
    title: "Assignments",
    dataIndex: "assignments",
    key: "assignments",
  },
  {
    title: "Current Grade",
    dataIndex: "grade",
    key: "grade",
  },
];

const StudentDashboard = () => {
  const [courses, setCourses] = useState([]);
  const role = localStorage.getItem("role");
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const studentId = localStorage.getItem("id");

  useEffect(() => {
    if (role !== "STUDENT") {
      navigate("/forbidden");
    }

    if (!token || isTokenExpired(token)) {
      navigate("/login");
    } else {
      const options = {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      };
      fetch(`http://localhost:8080/enrollment/${studentId}/courses`, options)
        .then((response) => response.json())
        .then((data) => setCourses(data));
    }
  }, [token, navigate]);

  return (
    <Layout>
      <Layout.Header>
        <TopNavBar />
      </Layout.Header>
      <Layout.Content>
        <div style={{ padding: "50px" }}>
          <div className="dashboard-cards-container">
            <Card title="Courses" className="dashboard-card">
              <p className="dashboard-card-data">{courses.length}</p>
              <p className="dashboard-card-label">Total Courses</p>
            </Card>
            <Card title="Assignments" className="dashboard-card">
              <p className="dashboard-card-data">7</p>
              <p className="dashboard-card-label">Due Dates</p>
            </Card>
            <Card title="Grades" className="dashboard-card">
              <p className="dashboard-card-data">3.8</p>
              <p className="dashboard-card-label">GPA</p>
            </Card>
          </div>
          <br />
          <br />
          <h2 style={{ color: "#1eb2a6" }}>My Courses</h2>
          <div className="dashboard-cards-container">
            {courses.map((course) => (
              <CourseCard key={course.id} course={course} />
            ))}
          </div>
          <br />
          <br />
          <h2 style={{ color: "#1eb2a6" }}>Overview</h2>
          <Table
            style={{ borderBlockEndWidth: "5px", marginTop: 50 }}
            dataSource={courses.map((course) => ({
              ...course,
              description: course.description,
              teacherName: `${course.teacher.firstName} ${course.teacher.lastName}`,
              teacherEmail: course.teacher.email,
            }))}
            columns={columns}
            rowKey="id"
          />
          <br />
          <br />
          <div className="view-all-courses-button-container">
            <Link to="/all-courses">
              <Button
                className="view-all-courses-button"
                size="large"
                type="default"
                block
              >
                Find a Course
              </Button>
            </Link>
          </div>
          <br />
          <br />
        </div>
      </Layout.Content>
    </Layout>
  );
};

export default StudentDashboard;
