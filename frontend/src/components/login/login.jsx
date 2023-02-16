import React, { useEffect, useState, useRef } from "react";
import { Link } from "react-router-dom";
import {Form, Button, Input, Typography, Divider, Layout, message} from "antd";
import { validationRules } from "./ValidationLogIn";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import"./style.css";
import"../../../src/App.css";
import TopNavBar from '../NavBar';


const Login = () => {
    
    
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const passwordRef = useRef(null);
    const navigate = useNavigate();
    const [form] = Form.useForm();


    const handleEmail = (event) => {
        setEmail(event.target.value)
    };
    const handlePassword = (event) => {
        setPassword(event.target.value)
    };
    useEffect(() => {
        if(!localStorage.getItem('token')){
            navigate('/login');

        }
    }, []);

    const submit = async () => {
      try {
          form.validateFields().then(async values => {
            const result = await axios.post("http://localhost:8080/auth/login", {
              email,
              password,
            });
        
            localStorage.setItem("token", result.data.token);
            localStorage.setItem("role", result.data.user.role);
            localStorage.setItem("email", result.data.user.email);
            localStorage.setItem("firstName", result.data.user.firstName);
            localStorage.setItem("lastName", result.data.user.lastName);
            
            message.success("You have successfully logged in!");
            passwordRef.current.value = '';
            navigate("/dashboard/");
            setErrorMessage("");
            
          }).catch((error) => {
            setErrorMessage("Failed to log in. Please check your details and try again.");
          });
        } catch (error) {
          message.error("log in failed")
          passwordRef.current.value = '';
          console.error(error);
        }
      };
    
      return (
        <Layout>
        <Layout.Header>
          <TopNavBar />
        </Layout.Header>
        <Layout.Content>
        <div className="login">
          
            
          <Form className="login-form" labelCol={{ span: 6 }} wrapperCol={{ span: 18 }}>
            <Typography.Title style={{ color: "#1eb2a6", fontSize: "50px" }}>
              Log In
            </Typography.Title>
            {errorMessage && (
        <div className="error-message">
          <p>{errorMessage}</p>
        </div>)}
            <Form.Item
              name="email"
              label="Email"
              className="form-item"
              rules={validationRules.email}

              
            >
              <Input
                className="form-input"
                placeholder="Enter Your Email Address"
                value={email}
                onChange={handleEmail}
                
              />
            </Form.Item>
            <Form.Item
              name="password"
              label="Password"
              className="form-item"
              rules={validationRules.password}
            >
              <Input.Password
                className="form-input"
                placeholder="Enter Your Password"
                value={password}
                onChange={handlePassword}
                ref={passwordRef}
                
              />
            </Form.Item>
            <Form.Item wrapperCol={{ span: 24 }}>
              <Button
                className="submit-button"
                size="large"
                type="primary"
                block
                onClick={submit}
              >
                Log In
              </Button>
            </Form.Item>
            <Divider className="divider">
              <span style={{ color: "#1eb2a6" }}>
                Don't have an account?{" "}
                <Link to="/login/user-register"> <span style={{ fontWeight: "bold", color: "#1eb2a6" }}>Sign Up</span> </Link>
              </span>
            </Divider>
          </Form>
        </div>
        </Layout.Content>
    </Layout>
      );
    };
    
    export default Login;
    