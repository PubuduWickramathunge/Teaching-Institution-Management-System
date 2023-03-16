import React from "react";
import { Card } from "antd";
import { Link } from "react-router-dom";
import "./dashboard.css";
const CourseCard = ({ course }) => {
  const teacherFirstName = course.teacher.firstName;
  const teacherLastName = course.teacher.lastName;

  return (
    <Link to={`/courses/${course.id}`}>
      <Card title={course.name} className="course-card">
        {/* <p className="dashboard-card-label">{course.description}</p> */}
        <p className="dashboard-card-label">
          Teacher: {teacherFirstName} {teacherLastName}
        </p>
      </Card>
    </Link>
  );
};

export default CourseCard;
