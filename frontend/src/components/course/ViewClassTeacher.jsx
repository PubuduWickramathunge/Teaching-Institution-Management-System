import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Card, Divider, List, Typography, Layout, Table } from "antd";
import TopNavBar from "../NavBar";
import { isTokenExpired } from "../../utils/TokenUtils";

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
];

const { Title, Text } = Typography;

const ViewClassTeacher = () => {
  const [students, setStudents] = useState([]);
  const location = useLocation();
  const courseId = location.pathname.split("/")[2];
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const teacherId = localStorage.getItem("id");

  useEffect(() => {
    if (!token || isTokenExpired(token)) {
      navigate("/login");
    }
    const options = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    fetch(
      `http://localhost:8080/teachers/${teacherId}/${courseId}/students`,
      options
    )
      .then((response) => response.json())
      .then((data) => setStudents(data));
  }, [courseId, navigate, teacherId, token]);

  console.log(students);

  return (
    <Layout>
      <Layout.Header>
        <TopNavBar />
      </Layout.Header>
      <Layout.Content>
        <div style={{ padding: " 50px" }}>
          <br />
          <br />
          <br />
          <h2 style={{ color: "#1eb2a6" }}>Students of This Class</h2>
          <Table
            style={{ borderBlockEndWidth: "5px", marginTop: 50 }}
            dataSource={students.map((students) => ({
              ...students,
            }))}
            columns={columns}
            rowKey="id"
          />
          <h4>Total Students of This Class: {students.length}</h4>
          <br />
          <Card>
            <br />
            <br />
            <br />
            <Divider />
            <Title style={{ color: "#1eb2a6" }} level={3}>
              Course Materials
            </Title>
            <List />
          </Card>
        </div>
      </Layout.Content>
    </Layout>
  );
};

export default ViewClassTeacher;
