import React from "react";
import { Route, Routes } from "react-router-dom";
import UserRegister from './components/signup/UserRegister';
import Login from "./components/login/login";
import Home from "./components/home/Home";
import Dashboard from "./components/dashboard/Dashboard";
import Profile from "./components/Profile";
import AddManager from "./components/AddManager";
import ForbiddenPage from "./components/forbidden";


function RouterComponent() {
  return (
    <Routes>
      <Route exact path="/" element={<Dashboard/>} />
      <Route path="/login/user-register" element={<UserRegister/>} />
      <Route path="/login" element={<Login/>} />
      <Route path="/home" element={<Home/>} />
      <Route path="/" element={<Profile/>} />
      <Route path="/profile" element={<Profile/>} />
      <Route path="/dashboard" element={<Dashboard/>} />
      <Route path="/add-manager" element={<AddManager/>} />
      <Route path="/forbidden" element={<ForbiddenPage/>} />


      




    </Routes>
  );
}

export default RouterComponent;
