import React, { useEffect, useState } from 'react';
import { Avatar, Input, Row, Col, Layout } from 'antd';

import "./dashboard/dashboard.css"

import TopNavBar from './NavBar';

const Profile = () => {
  
  const firstName = localStorage.getItem("firstName");
  const lastName = localStorage.getItem("lastName");
  const email = localStorage.getItem("email");
  const role = localStorage.getItem("role");
  



  return (
    <Layout>
        <Layout.Header>
          <TopNavBar />
        </Layout.Header>
        <Layout.Content>
    <div className="student-dashboard" style={{ padding: '100px' }} >
    <Row gutter={24}>
      <Col span={4}>
      <Avatar size={200} icon="user" />
      </Col>
      <Col span={6}>
        
        <Input  value={firstName+" " + lastName} disabled />
        <Input value={email} disabled />
        
        <Input value={role} disabled />
        <Input value={""} disabled />
        <Input value={""} disabled />
        <Input value={""} disabled />
      </Col>
    </Row>
    </div>
    </Layout.Content>
    </Layout>
  );
};

export default Profile;