import React, { useEffect, useState } from "react";
import { Table, Tag, Space, Layout, Card } from "antd";
import "./Dashboard";
import "./dashboard.css";

import TopNavBar from "../NavBar";

const columns = [
  {
    title: "Course",
    dataIndex: "",
    key: "",
  },
  {
    title: "Instructor",
    dataIndex: "",
    key: "",
  },
  {
    title: "Assignments",
    dataIndex: "",
    key: "",
  },
  {
    title: "Current Grade",
    dataIndex: "",
    key: "",
  },
];

const StudentDashboard = () => {
  return (
    <Layout>
      <Layout.Header>
        <TopNavBar />
      </Layout.Header>
      <Layout.Content>
        <div style={{ padding: "50px" }}>
          <div className="dashboard-cards-container">
            <Card title="Courses" className="dashboard-card">
              <p className="dashboard-card-data">5</p>
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

          <h2 style={{ color: "#1eb2a6" }}>Overview</h2>
          <Table
            style={{ borderBlockEndWidth: "5px", marginTop: 50 }}
            dataSource={""}
            columns={columns}
          />
        </div>
      </Layout.Content>
    </Layout>
  );
};

export default StudentDashboard;
