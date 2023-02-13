import React from "react";
import { Route, Routes } from "react-router-dom";
import UserRegister from './components/signup/UserRegister';
import Login from "./components/login/login";
import Home from "./components/home/Home";
import Dashboard from "./components/dashboard/Dashboard";
import Profile from "./components/Profile";

function RouterComponent() {
  return (
    <Routes>
      <Route exact path="/" element={<Dashboard/>} />
      <Route path="/login/UserRegister" element={<UserRegister/>} />
      <Route path="/login" element={<Login/>} />
      <Route path="/Home" element={<Home/>} />
      <Route path="/" element={<Profile/>} />
      <Route path="/profile" element={<Profile/>} />
      <Route path="/dashboard" element={<Dashboard/>} />

      




    </Routes>
  );
}

export default RouterComponent;
