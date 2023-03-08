import React from "react";
import { Route, Routes } from "react-router-dom";
import UserRegister from "./components/signup/UserRegister";
import Login from "./components/login/login";
import Home from "./components/home/Home";
import Dashboard from "./components/dashboard/Dashboard";
import Profile from "./components/Profile";
import AddManager from "./components/AddManager";
import ForbiddenPage from "./components/forbidden";
import AddCourse from "./components/course/AddCourse";
import CourseViewStudent from "./components/course/ViewCourseStudent";
import AllCoursesPage from "./components/course/ViewAllCourses";
import ViewClassTeacher from "./components/course/ViewClassTeacher";

function RouterComponent() {
  return (
    <Routes>
      <Route exact path="/" element={<Dashboard />} />
      <Route path="/login/user-register" element={<UserRegister />} />
      <Route path="/login" element={<Login />} />
      <Route path="/home" element={<Home />} />
      <Route path="/" element={<Profile />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/add-manager" element={<AddManager />} />
      <Route path="/forbidden" element={<ForbiddenPage />} />
      <Route path="/add-course" element={<AddCourse />} />
      <Route path="/courses/:id" element={<CourseViewStudent />} />
      <Route path="/all-courses" element={<AllCoursesPage />} />
      <Route path="/class/:id" element={<ViewClassTeacher />} />
    </Routes>
  );
}

export default RouterComponent;
