import React, { useEffect, useState } from "react";
import"../../../src/App.css";
import "./style.css";
import {Form, Button, Input, Radio, Typography} from "antd";
import { validationRules } from "./Validation";
import axios from "axios";




function UserRegister() {

    const[id, setId] = useState('');
    const[firstName, setFirstName] = useState("");
    const[lastName, setLastName] = useState("");
    const[email, setEmail] = useState("");
    const[password, setPassword] = useState("");
    const[role, setRole] = useState("");

    async function save(event){

        event.preventDefault();
    
        try{
            console.log(firstName);
            await axios.post("http://localhost:8080/auth/register",
            {
              
                firstname: firstName,
                lastname: lastName,
                email: email,
                password: password,
                role: role
            });
            alert("user registered succesfully");
            setId("");
            setFirstName("");
            setLastName("");
            setEmail("");
            setPassword("");
            setRole("");

        }
        catch(err){
            alert("user registration failed");
        }
    }


    return(
        <div className="login">
            <header className = "loginForm">
                <div className="form-container"  >
                    <Form autoComplete="off" labelCol={{span: 8}} wrapperCol = {{span: 20}} >
                    <Typography.Title className="title" style={{color: "royalblue"}}>Register</Typography.Title>
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

                            <Form.Item wrapperCol={{span: 24}}>
                                <Button size="large" type="primary" htmlType="submit" onClick={save} block>
                                    Next
                                </Button>
                            </Form.Item>
                        </Form>
                    </div>
            </header>
        </div>
    );
}
export default UserRegister;