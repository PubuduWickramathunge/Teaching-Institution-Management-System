import React, { useEffect, useState } from 'react';
import { Table, Tag, Space, Layout } from "antd";
import "./Dashboard";
import "./dashboard.css"

import TopNavBar from '../NavBar';

const columns = [
  {
    title: "Day",
    dataIndex: "",
    key: "",
  },
  {
    title: "Time",
    dataIndex: "",
    key: "",
  },
  {
    title: "Lecture Hall",
    dataIndex: "",
    key: "",
  },
  {
    title: "Assistant",
    dataIndex: "",
    key: "",
  },
];

const TeacherDashboard = () => {
  


  return (
    <Layout>
        <Layout.Header>
          <TopNavBar />
        </Layout.Header>
        <Layout.Content>
    <div style={{padding:' 50px'}}>
      <h2 style={{color: "#1eb2a6"}}>Time Table</h2>
      <Table style={{borderBlockEndWidth:'5px'}} dataSource={""} columns={columns} />

    </div>
    </Layout.Content>
    </Layout>
  );
};

export default TeacherDashboard;