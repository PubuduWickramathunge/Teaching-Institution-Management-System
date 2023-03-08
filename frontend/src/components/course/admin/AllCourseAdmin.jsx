import React, { useState, useEffect } from "react";
import { Table } from "antd";

const columns = [
  {
    title: "Course Id",
    dataIndex: "id",
    key: "id",
  },
  {
    title: "Name",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "Description",
    dataIndex: "description",
    key: "description",
  },
  {
    title: "Teacher",
    dataIndex: "teacher",
    key: "teacher",
    render: (teacher) => `${teacher.firstName} ${teacher.lastName}`,
  },

  {
    title: "Number of Students",
    key: "students",
    render: (_, record) => record.students.length,
  },
];

const ViewAllCoursesAdmin = () => {
  const [courses, setCourses] = useState([]);

  const token = localStorage.getItem("token");

  useEffect(() => {
    const options = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };

    fetch("http://localhost:8080/courses/all", options)
      .then((response) => response.json())
      .then((data) => {
        const promises = data.map((course) =>
          fetch(
            `http://localhost:8080/teachers/${course.teacher.id}/${course.id}/students`,
            options
          )
            .then((response) => response.json())
            .then((students) => ({ ...course, students }))
        );
        Promise.all(promises).then(setCourses);
      });
  }, [token]);

  return (
    <div>
      <h2 style={{ color: "#1eb2a6" }}>List of All Courses</h2>
      <Table dataSource={courses} columns={columns} />
    </div>
  );
};

export default ViewAllCoursesAdmin;
