import React, { useEffect, useState } from "react";
import { Card, List, Typography, Layout } from "antd";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import TopNavBar from "../NavBar";
import { isTokenExpired } from "../../utils/TokenUtils";
import EnrollButton from "./EnrollButton";
import SearchCourses from "./SearchCourse";

const { Title } = Typography;

const AllCoursesPage = () => {
  const [courses, setCourses] = useState([]);
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    if (!token || isTokenExpired(token)) {
      navigate("/login");
    }
    const options = {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    };
    axios
      .get("http://localhost:8080/courses/all", options)
      .then((response) => {
        setCourses(response.data);
        setIsLoading(false);
      })
      .catch((error) => console.log("Error while fetching courses:", error));
  }, []);

  return (
    <Layout>
      <Layout.Header>
        <TopNavBar />
      </Layout.Header>
      <Layout.Content>
        <div style={{ padding: "50px" }}>
          <Title style={{ color: "#1eb2a6" }} level={2}>
            All Courses
          </Title>
          <SearchCourses setCourses={setCourses} setError={setError} />
          {error && <p style={{ color: "red" }}>{error}</p>}
          <br />
          <br />
          <br />
          {courses.length > 0 ? (
            <List
              grid={{
                gutter: 16,
                xs: 1,
                sm: 2,
                md: 3,
                lg: 4,
                xl: 4,
                xxl: 4,
              }}
              dataSource={courses}
              renderItem={(course) => (
                <List.Item>
                  <Card
                    title={course.name}
                    actions={[<EnrollButton courseId={course.id} />]}
                  >
                    <p>{course.description}</p>
                    <p>
                      Teacher:{" "}
                      {course.teacher
                        ? `${course.teacher.firstName} ${course.teacher.lastName}`
                        : "Unknown"}
                    </p>
                  </Card>
                </List.Item>
              )}
            />
          ) : isLoading ? (
            <p>Loading courses...</p>
          ) : (
            <p>No courses found.</p>
          )}
        </div>
      </Layout.Content>
    </Layout>
  );
};

export default AllCoursesPage;
