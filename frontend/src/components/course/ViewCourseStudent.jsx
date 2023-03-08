import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Card, Descriptions, Divider, List, Typography, Layout } from "antd";
import TopNavBar from "../NavBar";
import { isTokenExpired } from "../../utils/TokenUtils";
import UnenrollButton from "./UnenrollCourse";

const { Title } = Typography;

const CourseViewStudent = () => {
  const [course, setCourse] = useState({});
  const location = useLocation();
  const courseId = location.pathname.split("/")[2];
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  useEffect(() => {
    if (!token || isTokenExpired(token)) {
      navigate("/login");
    }
    const options = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    fetch(`http://localhost:8080/enrollment/${courseId}`, options)
      .then((response) => response.json())
      .then((data) => setCourse(data));
  }, [courseId, navigate, token]);

  return (
    <Layout>
      <Layout.Header>
        <TopNavBar />
      </Layout.Header>
      <Layout.Content>
        <div style={{ padding: " 50px" }}>
          <Card
            title={
              <Title style={{ color: "#1eb2a6" }} level={2}>
                {course.name}
              </Title>
            }
          >
            <Descriptions bordered>
              <Descriptions.Item label="Description" span={3}>
                {course.description}
              </Descriptions.Item>
              <Descriptions.Item label="Teacher">
                {`${course.teacher?.firstName} ${course.teacher?.lastName}`}
              </Descriptions.Item>
              <Descriptions.Item label="Email">
                {course.teacher?.email}
              </Descriptions.Item>
            </Descriptions>
            <Divider />
            <Title style={{ color: "#1eb2a6" }} level={3}>
              Course Materials
            </Title>
            <List />
            <div
              style={{
                position: "fixed",
                bottom: "0",
                right: "0",
                padding: "50px",
              }}
            >
              <UnenrollButton courseId={courseId} />
            </div>
          </Card>
        </div>
      </Layout.Content>
    </Layout>
  );
};

export default CourseViewStudent;
