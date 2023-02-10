import React, { useState, useEffect } from "react";
import ManagementDashboard from "./ManagementDashboard";
import StudentDashboard from "./StudentDashboard";
import TeacherDashboard from "./TeacherDashboard";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const navigate = useNavigate();
  const [role, setRole] = useState("");

  useEffect(() => {
    // Get the role of the logged-in user from local storage
    const token = localStorage.getItem("token");
    console.log(token)
    if (!token) {
      navigate("/login");
      return;
    }

    const role = localStorage.getItem("role");
    if (!role) {
      navigate("/login");
      return;
    }
    setRole(role);
    console.log(role);
  }, []);

  if (!role) {
    return <div>Loading...</div>;
  }

  // Render the appropriate home page based on the user's role
  switch (role) {
    case "MANAGEMENT":
      return <ManagementDashboard />;
    case "STUDENT":
      return <StudentDashboard />;
    case "TEACHER":
      return <TeacherDashboard />;
    default:
      return <div>Invalid role</div>;
  
};
}

export default Dashboard;
