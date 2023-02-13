import React, { useEffect, useState } from 'react';
import { Avatar, Input, Row, Col, Layout,Card, Typography } from 'antd';
import { useNavigate } from "react-router-dom";
import "./profile/profile.css"



import TopNavBar from './NavBar';
import LogoutButton from './Logout';

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
    if (!token) {
      setAuthenticated(false);
      navigate("/Login/");

    } else {
      setAuthenticated(true);
    }
  }, [])


  if (!authenticated) {
    return navigate("/Login/");
  }



  return (
    <Layout>
      <Layout.Header>
        <TopNavBar />
      </Layout.Header>
      <Layout.Content>
      <Card className='card'
      
    ><Avatar className='avatar' size={200} icon="User" />
      <Meta className='meta'
      
        title={<Text strong>{fullName}</Text>}
        description={<Text type="secondary">{email}</Text>}
      />
      <Text style={{ marginTop: 10 }} type="secondary">
        Role: {role}
      </Text>
      <br />
      <br /><br />
      <LogoutButton
        style={{ position: "absolute", bottom: 10, right: 10 }}
        type="danger"
        />
    </Card>
        {/* <div className='container'>
          <div  style={{ padding: '100px' }} >
            <Row className='profile-content' gutter={24}>
              <Col span={4}>
                <Avatar size={200} icon="user" />
              </Col>
              <Col span={6}>

                <Input value={firstName + " " + lastName} disabled />
                <Input value={email} disabled />

                <Input value={role} disabled />
                <Input value={""} disabled />
                <Input value={""} disabled />
                <Input value={""} disabled />
              </Col>
            </Row>
          </div>
        </div> */}
      </Layout.Content>
      {/* <Layout.Footer style={{ textAlign: "right" }}>
        <LogoutButton />
      </Layout.Footer> */}
    </Layout>
  );
};

export default Profile;

// import React from "react";
// import { Card, Avatar, Typography, Button } from "antd";

// const { Meta } = Card;
// const { Text } = Typography;

// const ProfileCard = ({ firstName, lastName, email, role, onDelete }) => {
//   const fullName = `${firstName} ${lastName}`;

//   return (
//     <Card
//       style={{ width: 300, textAlign: "center", position: "relative" }}
//       cover={<Avatar size={100} src="https://i.pravatar.cc/300" />}
//     >
//       <Meta
//         title={<Text strong>{fullName}</Text>}
//         description={<Text type="secondary">{email}</Text>}
//       />
//       <Text style={{ marginTop: 10 }} type="secondary">
//         Role: {role}
//       </Text>
//       <Button
//         style={{ position: "absolute", bottom: 10, right: 10 }}
//         type="danger"
//         onClick={onDelete}
//       >
//         Delete
//       </Button>
//     </Card>
//   );
// };

// export default ProfileCard;
