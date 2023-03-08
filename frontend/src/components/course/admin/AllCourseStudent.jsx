import React, { useState, useEffect } from "react";
import { Table } from "antd";

const columns = [
  {
    title: "Student ID",
    dataIndex: "id",
    key: "id",
  },
  {
    title: "Student Name",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "Student Email",
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

const ViewAllCoursesStudent = () => {
  const [data, setData] = useState([]);
  const token = localStorage.getItem("token");

  useEffect(() => {
    const options = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };

    fetch("http://localhost:8080/getAll/students", options)
      .then((response) => response.json())
      .then((students) => {
        const promises = students.map((student) =>
          fetch(
            `http://localhost:8080/enrollment/${student.id}/courses`,
            options
          )
            .then((response) => response.json())
            .then((courses) => ({
              id: student.id,
              name: `${student.firstName} ${student.lastName}`,
              email: student.email,
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
      <h2 style={{ color: "#1eb2a6" }}>List of All Courses of Students</h2>
      <Table dataSource={data} columns={columns} />
    </div>
  );
};

export default ViewAllCoursesStudent;
