import React, { useState } from "react";
import { Button } from "antd";
import { Link } from "react-router-dom";

import "../../dashboard/dashboard.css";

import ViewAllCoursesAdmin from "./AllCourseAdmin";
import TeacherCoursesTable from "./AllCourseTeacher";
import ViewAllCoursesStudent from "./AllCourseStudent";

function CourseTableSwitcher() {
  const [activeTable, setActiveTable] = useState("admin");

  const handleSwitchTable = (table) => {
    setActiveTable(table);
  };

  let tableToRender;

  switch (activeTable) {
    case "admin":
      tableToRender = <ViewAllCoursesAdmin />;
      break;
    case "teacher":
      tableToRender = <TeacherCoursesTable />;
      break;
    case "student":
      tableToRender = <ViewAllCoursesStudent />;
      break;
    default:
      tableToRender = <ViewAllCoursesAdmin />;
      break;
  }

  return (
    <div>
      <div>
        <Button
          className="home-button"
          type="primary"
          size="large"
          onClick={() => handleSwitchTable("admin")}
        >
          All Courses
        </Button>
        <Button
          className="home-button"
          type="primary"
          size="large"
          onClick={() => handleSwitchTable("teacher")}
        >
          Courses of Teachers
        </Button>
        <Button
          className="home-button"
          type="primary"
          size="large"
          onClick={() => handleSwitchTable("student")}
        >
          Courses of Students
        </Button>
        <Link to="/add-course">
          <Button className="home-button" type="default" size="large">
            <i>Add New course</i>
          </Button>
        </Link>
        <br />
      </div>
      <br />
      {tableToRender}
    </div>
  );
}

export default CourseTableSwitcher;
