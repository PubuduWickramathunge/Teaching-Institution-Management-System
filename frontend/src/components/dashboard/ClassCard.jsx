import React from "react";
import { Card } from "antd";
import { Link } from "react-router-dom";
import "./dashboard.css";

const ClassCard = ({ course }) => {
  return (
    <Link
      to={`/class/${course.id}`}
      state={{ courseName: course.name, courseDescription: course.description }}
    >
      <Card title={course.name} className="course-card">
        <p className="dashboard-card-label">{course.description}</p>
      </Card>
    </Link>
  );
};

export default ClassCard;
