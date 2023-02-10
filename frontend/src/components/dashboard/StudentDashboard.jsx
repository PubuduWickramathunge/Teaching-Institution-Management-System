import React, { useEffect, useState } from 'react';
import { Table, Tag, Space, Layout } from "antd";
import "./Dashboard";
import "./dashboard.css"

import TopNavBar from '../NavBar';

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
    <div style={{padding:' 50px'}}>
      <h2 style={{color: "#1eb2a6"}}>Overview</h2>
      <Table style={{borderBlockEndWidth:'5px'}} dataSource={""} columns={columns} />

    </div>
    </Layout.Content>
    </Layout>
  );
};

export default StudentDashboard;