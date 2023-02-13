import React, { useState } from "react";
import { Button, message } from "antd";
import { useNavigate } from "react-router-dom";

function LogoutButton() {
  const [loggedOut, setLoggedOut] = useState(false);
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    setLoggedOut(true);
    message.success("You have successfully logged out");
    navigate("/login/")
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
