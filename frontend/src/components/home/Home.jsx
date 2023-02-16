import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Outlet } from "react-router-dom";
import { Button, Space, Typography } from 'antd';
import { Layout } from 'antd';
import TopNavBar from '../NavBar';

import "./home.css"

function Home() {

  return (
    <Layout>
    <Layout.Header>
      <TopNavBar />
    </Layout.Header>
    <Layout.Content>
    <div>
      <section className="home" >
        <div className="container" >
          <div className="row" >
            <Typography className="title">WELLCOME TO BEST INSTITUTION MANAGEMENT SYSTEM</Typography>
            <p className="text">Lorem ipsum dolor sit amet consectetur adipisicing elit. Consectetur dolorum dolore placeat, inventore, commodi quis illo eius amet corporis voluptate cupiditate, odio expedita! Id aspernatur omnis, ut aliquam neque in.</p>
            <div>
              <Link to="/login/">
                <Button className="home-button" type="primary" size="large">
                  <i>LOG IN</i>
                </Button>
              </Link>
              <Link to="/login/user-register">
                <Button className="home-button" type="primary" size="large">
                  <i>CREATE AN ACCOUNT</i>
                </Button>
              </Link>
            </div>
          </div>
        </div>
      </section>
      <div className="margin"></div>
      <Outlet />
    </div>
    </Layout.Content>
    </Layout>
  );
}

export default Home;
