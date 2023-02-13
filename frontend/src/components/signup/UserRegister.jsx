import React, { useEffect, useState } from "react";
import {Form, Button, Input, Radio, Typography, Divider, message} from "antd";
import { Link, useNavigate } from "react-router-dom";
import { validationRules } from "./ValidationSignUp";
import { Layout } from 'antd';
import TopNavBar from '../NavBar';
import axios from "axios";
import"../../../src/App.css";
import "../login/style.css";




const UserRegister = () => {

    const [form] = Form.useForm();
    const[id, setId] = useState('');
    const[firstName, setFirstName] = useState("");
    const[lastName, setLastName] = useState("");
    const[email, setEmail] = useState("");
    const[password, setPassword] = useState("");
    const[role, setRole] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();
    

     const handleSave = async event => {
    event.preventDefault();

    try {
        form
          .validateFields()
          .then(async values => {
            const result = await axios.post("http://localhost:8080/auth/register", {
              firstname: firstName,
              lastname: lastName,
              email: email,
              password: password,
              role: role
            });
            message.success("Registration complete. Please sign in")
            setFirstName("");
            setLastName("");
            setEmail("");
            setPassword("");
            setRole("");
            form.resetFields();
            setErrorMessage("");
            navigate("/login");
          })
          .catch(error => {
            setErrorMessage("Failed to register. Please check your details and try again.");
          });
      } catch (error) {
        message.error("user registration failed!")
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
                    <Typography.Title style={{ color: "#1eb2a6", fontSize: "50px" }} >Register</Typography.Title>
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

                            <Form.Item name = "role" label = "User Type" rules={validationRules.role} hasFeedback>
                            <Radio.Group value={role}
                                onChange = {(event) =>
                                {
                                    setRole(event.target.value);
                                }}>
                                <Radio value = "STUDENT"> Student </Radio>
                                <Radio value = "TEACHER">Teacher </Radio>
                                <Radio value = "MANAGEMENT">Management </Radio>
                            </Radio.Group>
                            </Form.Item>

                            <Form.Item wrapperCol={{span: 24}}><Link to="/login">
                                <Button className="submit-button" size="large" type="primary" htmlType="submit" onClick={handleSave} block>
                                    Next
                                </Button></Link>
                            </Form.Item>
                            <Divider className="divider">
              <span style={{ color: "#1eb2a6" }}>
                Already have an account?{" "}
                <Link to="/login/"> <span style={{ fontWeight: "bold", color: "#1eb2a6" }}>Sign In</span> </Link>
              </span>
            </Divider>
                        </Form>
                    </div>
            </header>
        </div>
        </Layout.Content>
    </Layout>
    );
}
export default UserRegister;