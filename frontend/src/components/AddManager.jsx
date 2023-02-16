import React, { useEffect, useState } from "react";
import {Form, Button, Input, Radio, Typography, Divider, message} from "antd";
import { Link, useNavigate } from "react-router-dom";
import { validationRules } from "./signup/ValidationSignUp";
import { Layout } from 'antd';
import TopNavBar from './NavBar';
import axios from "axios";


import "./login/style.css";




const AddManager = () => {
    

    const [form] = Form.useForm();
    const[id, setId] = useState('');
    const[firstName, setFirstName] = useState("");
    const[lastName, setLastName] = useState("");
    const[email, setEmail] = useState("");
    const[password, setPassword] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();

    const role = localStorage.getItem("role");
    console.log(role);
    
    

    useEffect(() => {
        if (role !== "MANAGEMENT") {
            navigate("/forbidden"); // redirect the user to the login page
        }
    }, []);
    console.log(role);

   
  
    const handleSave = async event => {
    event.preventDefault();
    const token = localStorage.getItem("token");
    console.log(role);
    
    
    try {
        form
          .validateFields()
          .then(async values => {
           
            const result = await axios.post("http://localhost:8080/manager/addmanager", {
              firstname: firstName,
              lastname: lastName,
              email: email,
              password: password,
              role: "MANAGEMENT"
            },
            // Pass the token as a header
            { headers: { Authorization: `Bearer ${token}` } }
          );
  
            message.success("Manager Added Successfully!")
            setFirstName("");
            setLastName("");
            setEmail("");
            setPassword("");
            form.resetFields();
            setErrorMessage("");
            navigate("/dashboard");
          })
          .catch(error => {
            if (error.response.status === 409) {
                setErrorMessage("A user with this email address already exists.");
              } else {
                 setErrorMessage("Failed to register the manager. Please check your details and try again.");
                console.log(error)
              }
          });
      } catch (error) {
        message.error("manager registration failed!");
        console.log(error)
      }
    };
    

    return(
        <Layout>
        <Layout.Header>
          <TopNavBar />
        </Layout.Header>
        <Layout.Content>
        <div className="login">
            <header className = "login-form">
                <div className="form-container"  >
                    <Form autoComplete="off" labelCol={{span: 8}} wrapperCol = {{span: 20}} >
                    <Typography.Title style={{ color: "#1eb2a6", fontSize: "50px" }}>
             Add Manager
            </Typography.Title>
                    {errorMessage && (
                        <div className="error-message">
                        <p>{errorMessage}</p>
                        </div>)}
                            <Form.Item name = "firstName" label = "First Name" rules={validationRules.firstName} hasFeedback>
                                <Input className="form-input"  placeholder="Enter Your First Name" value={firstName}
                                onChange = {(event) =>
                                {
                                    setFirstName(event.target.value);
                                }}
                                
                                />
                                
                            </Form.Item>

                            <Form.Item name = "lastName" label = "Last Name" rules={validationRules.lastName} hasFeedback>
                                <Input className="form-input"  placeholder="Enter Your Last Name" 
                                
                                value={lastName}
                                onChange = {(event) =>
                                {
                                    setLastName(event.target.value);
                                }}

                                />
                            </Form.Item>

                            <Form.Item  name = "email" label = "Email" rules={validationRules.email} hasFeedback>
                                <Input className="form-input" placeholder="Enter Your Email" 
                                
                                value={email}
                                onChange = {(event) =>
                                {
                                    setEmail(event.target.value);
                                }}
                                
                                />
                            </Form.Item>

                            <Form.Item name = "password" label = "Password" rules={validationRules.password} hasFeedback>
                                <Input.Password className="form-input" placeholder="Enter Your Password"
                                
                                value={password}
                                onChange = {(event) =>
                                {
                                    setPassword(event.target.value);
                                }}
                                
                                />
                            </Form.Item>

                            <Form.Item name = "confirmPassword" label = "Confirm Password" rules={validationRules.confirmPassword} hasFeedback>
                                <Input.Password size="large" placeholder="Confirm Your Password" />
                            </Form.Item>

                           

                            <Form.Item wrapperCol={{span: 24}}><Link to="/login">
                                <Button className="submit-button" size="large" type="primary" htmlType="submit" onClick={handleSave} block>
                                    Add New Manager
                                </Button></Link>
                            </Form.Item>
                            
                        </Form>
                    </div>
            </header>
        </div>
        </Layout.Content>
    </Layout>
    );
}
export default AddManager;