import React, { useEffect, useState } from "react";
import { Avatar, Layout, Card, Typography } from "antd";
import { useNavigate } from "react-router-dom";
import "./profile/profile.css";

import TopNavBar from "./NavBar";
import LogoutButton from "./Logout";
import { isTokenExpired } from "../utils/TokenUtils";

const Profile = () => {
  const firstName = localStorage.getItem("firstName");
  const lastName = localStorage.getItem("lastName");
  const email = localStorage.getItem("email");
  const role = localStorage.getItem("role");
  const [authenticated, setAuthenticated] = useState(false);
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const { Meta } = Card;
  const { Text } = Typography;
  const fullName = `${firstName} ${lastName}`;

  useEffect(() => {
    if (!token || isTokenExpired(token)) {
      navigate("/login");
    }
    if (!token) {
      setAuthenticated(false);
      navigate("/login/");
    } else {
      setAuthenticated(true);
    }
  }, []);

  if (!authenticated) {
    return navigate("/login/");
  }

  return (
    <Layout>
      <Layout.Header>
        <TopNavBar />
      </Layout.Header>
      <Layout.Content>
        <Card className="card">
          <Typography.Title style={{ color: "#1eb2a6", fontSize: "50px" }}>
            Profile
          </Typography.Title>
          <Avatar className="avatar" size={200} icon="User" />
          <Meta
            className="meta"
            title={<Text strong>{fullName}</Text>}
            description={<Text type="secondary">{email}</Text>}
          />
          <Text style={{ marginTop: 10 }} type="secondary">
            Role: {role}
          </Text>
          <br />
          <br />
          <br />
          <LogoutButton
            style={{ position: "absolute", bottom: 10, right: 10 }}
            type="danger"
          />
        </Card>
      </Layout.Content>
    </Layout>
  );
};

export default Profile;
