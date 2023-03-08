import React, { useState, useEffect } from "react";
import { Table } from "antd";

const columns = [
  {
    title: "Teacher ID",
    dataIndex: "id",
    key: "id",
  },
  {
    title: "Teacher Name",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "Teacher Email",
    dataIndex: "email",
    key: "email",
  },
  {
    title: "Number of Courses",
    dataIndex: "numCourses",
    key: "numCourses",
  },
  {
    title: "Course Names",
    dataIndex: "courses",
    key: "courses",
    render: (courses) => (
      <ul>
        {courses.map((course) => (
          <li key={course.id}>{course.name}</li>
        ))}
      </ul>
    ),
  },
];

const TeacherCoursesTable = () => {
  const [data, setData] = useState([]);
  const token = localStorage.getItem("token");

  useEffect(() => {
    const options = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };

    fetch("http://localhost:8080/getAll/teachers", options)
      .then((response) => response.json())
      .then((teachers) => {
        const promises = teachers.map((teacher) =>
          fetch(`http://localhost:8080/teachers/${teacher.id}/courses`, options)
            .then((response) => response.json())
            .then((courses) => ({
              id: teacher.id,
              name: `${teacher.firstName} ${teacher.lastName}`,
              email: teacher.email,
              numCourses: courses.length,
              courses,
            }))
        );
        Promise.all(promises).then(setData);
      });
  }, [token]);
  console.log(data);

  return (
    <div>
      <h2 style={{ color: "#1eb2a6" }}>List of All Courses of Teachers</h2>
      <Table dataSource={data} columns={columns} />
    </div>
  );
};

export default TeacherCoursesTable;
