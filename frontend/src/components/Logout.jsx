import React, { useState } from "react";
import { Button, message } from "antd";
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

  return (
    <>
      {loggedOut ? (
        <p>You have successfully logged out</p>
      ) : (
        <Button danger onClick={handleLogout}>
          Logout
        </Button>
      )}
    </>
  );
}

export default LogoutButton;
