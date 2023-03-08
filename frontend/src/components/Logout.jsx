import React, { useState } from "react";
import { Button, message, Modal } from "antd";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function LogoutButton() {
  const [loggedOut, setLoggedOut] = useState(false);
  const navigate = useNavigate();

  const handleLogout = async () => {
    try {
    const token = localStorage.getItem("token");
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
      await axios.put("http://localhost:8080/auth/logout",null, config);
      localStorage.removeItem("token");
      localStorage.removeItem("email");
      localStorage.removeItem("role");
      localStorage.removeItem("lastName");
      localStorage.removeItem("firstName");
      setLoggedOut(true);
      message.success("You have successfully logged out");
      navigate("/login/");
    } catch (error) {
      console.log(error);
      message.error("Failed to logout");
      
    }
  };
  const handleConfirm = () => {
    Modal.confirm({
      title: "Are you sure you want to logout?",
      onOk: handleLogout,
      onCancel: () => {},
    });
  };
  return (
    <>
      {loggedOut ? (
        <p>You have successfully logged out</p>
      ) : (
        <Button type="default" style={{color: "red",
                  border: '1px solid red'}}  onClick={handleConfirm}>
    Log Out
  </Button>
      )}
    </>
  );
  
}

export default LogoutButton;
