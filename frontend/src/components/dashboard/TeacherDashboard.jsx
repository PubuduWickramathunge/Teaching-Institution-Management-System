import React, { useEffect, useState } from "react";
import { Table, Tag, Space, Layout, Card, Alert, Calendar } from "antd";
import dayjs from "dayjs";

import "./Dashboard";
import "./dashboard.css";

import TopNavBar from "../NavBar";

const columns = [
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
  {
    title: "Subject",
    dataIndex: "null",
    key: "null",
  },
];

const TeacherDashboard = () => {
  const [value, setValue] = useState(() => dayjs("2017-01-25"));
  const [selectedValue, setSelectedValue] = useState(() => dayjs("2017-01-25"));
  const onSelect = (newValue) => {
    setValue(newValue);
    setSelectedValue(newValue);
  };
  const onPanelChange = (newValue) => {
    setValue(newValue);
  };

  return (
    <Layout>
      <Layout.Header>
        <TopNavBar />
      </Layout.Header>
      <Layout.Content>
        <div style={{ padding: "50px" }}>
          <Table
            style={{ borderBlockEndWidth: "5px", marginTop: 50 }}
            dataSource={""}
            columns={columns}
          />
          <h2 style={{ color: "#1eb2a6" }}>Overview</h2>
          <div className="dashboard-cards-container">
            <Card title="Subjects" className="dashboard-card">
              <p className="dashboard-card-data">
                Pure Maths <br /> Applied Maths
              </p>
              <p className="dashboard-card-label">subjects</p>
            </Card>
            <Card title="Classes" className="dashboard-card">
              <p className="dashboard-card-data">4</p>
              <p className="dashboard-card-label">total classes</p>
            </Card>
            <Card title="Assistant" className="dashboard-card">
              <p className="dashboard-card-data">Dr. Nihal</p>
              <p className="dashboard-card-label">Subject - Pure Maths</p>
            </Card>
          </div>
          <Alert
            message={`You selected date: ${selectedValue?.format(
              "YYYY-MM-DD"
            )}`}
          />
          <Calendar
            value={value}
            onSelect={onSelect}
            onPanelChange={onPanelChange}
          />
        </div>
      </Layout.Content>
    </Layout>
  );
};

export default TeacherDashboard;
