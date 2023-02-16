import React from 'react';
import { Layout, Menu, Avatar, Typography } from 'antd';
import { UserOutlined, BookOutlined } from '@ant-design/icons';
import { Link } from 'react-router-dom';
const { Header } = Layout;

function TopNavBar() {
  return (
    <Header className="header" style={{ display: 'flex', justifyContent: 'space-between', boxShadow: ' -28px 28px 25px rgba(0, 0, 0, 0.2)' }}>
      <div className="logo" style={{ textAlign: 'left' }}>
        <Typography style={{ color: '#1eb2a6' , fontSize: 'xx-large',fontWeight:'bold'}} >ISA TEACHING INSTITUTION</Typography>
      </div>
      <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['2']} style={{ textAlign: 'right' }}>
        <Menu.Item key="1" icon={<UserOutlined />} style={{ backgroundColor: '#1eb2a6' }}>
        <Link to="/dashboard">Dashboard</Link>
        </Menu.Item>
        <Menu.Item key="2" icon={<BookOutlined />} style={{ backgroundColor: '#1eb2a6' }}>
        <Link to="/home">Home</Link>
        </Menu.Item>
        <Menu.Item key="3" style={{ float: 'right' }}>
          
          <Link to="/profile"><Avatar style={{ backgroundColor: '#1eb2a6' }} icon={<UserOutlined />} /></Link>
        </Menu.Item>
      </Menu>
    </Header>
  );
}

export default TopNavBar;
