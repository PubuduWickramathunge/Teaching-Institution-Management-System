import React, { useState, useEffect } from "react";
import { Table, Tag, Space, Layout } from "antd";
import TopNavBar from '../NavBar';

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

const AdminPage = () => {
  const [students, setStudents] = useState([]);
  const [teachers, setTeachers] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/students")
      .then((response) => response.json())
      .then((data) => setStudents(data));

      fetch("http://localhost:8080/teachers")
      .then((response) => response.json())
      .then((data) => setTeachers(data));
  }, []);

  return (
    <Layout>
        <Layout.Header>
          <TopNavBar />
        </Layout.Header>
        <Layout.Content>
    <div style={{padding:' 50px'}}>
      <h2 style={{color: "#1eb2a6"}}>List of Students</h2>
      <Table style={{borderBlockEndWidth:'5px'}} dataSource={students} columns={columns} />
      <h2 style={{color: "#1eb2a6"}}>List of Teachers</h2>
      <Table dataSource={teachers} columns={columns} />
    </div>
    </Layout.Content>
    </Layout>
  );
};

export default AdminPage;