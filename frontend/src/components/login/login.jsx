import"./style.css";
import {Form, Button, Input, Typography, Divider, } from "antd";
import React, { useEffect, useState } from "react";
import"../../../src/App.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleEmail = (event) => {
        setEmail(event.target.value)
    }
    const handlepassword = (event) => {
        setPassword(event.target.value)
    }
    useEffect(() => {
        if(!localStorage.getItem('token')){
            navigate('/login')

        }
    }, [])

    const login = () => {
        console.log({email, password})
        axios.post("http://localhost:8080/auth/login", {
            email: email,
            password: password
        })
        .then(result => {
            
            console.log(result.data)
            alert("success")
            localStorage.setItem('token', result.data.token)
            navigate('/login')
        })
        .catch(error => {
            alert('service error')
            console.log(error)
        })
    }

    // async function login(event){

    //     event.preventDefault();
    
    //     try{
    //         console.log(email);
    //         // await axios.post("http://localhost:8080/auth/register",
    //         // {
              
    //         //     firstname: firstName,
    //         //     lastname: lastName,
    //         //     email: email,
    //         //     password: password,
    //         //     role: role
    //         // });
    //         // alert("user registered succesfully");
    //         // setId("");
    //         // setFirstName("");
    //         // setLastName("");
    //         // setEmail("");
    //         // setPassword("");
    //         // setRole("");

    //     }
    //     catch(err){
    //         alert("user registration failed");
    //     }
    // }


    return(
        <div className="login">
            <Form className="loginForm" labelCol={{span: 6}} wrapperCol = {{span: 18}}>
                <Typography.Title className="title" style={{color: "royalblue"}}>Log In</Typography.Title>
                <Form.Item className="label" name="Email" label="Email" >
                    <Input  className="form-input" placeholder="Enter Your Email Address" value={email}
                                onChange = {(event) =>
                                {
                                    setEmail(event.target.value);
                                }}/>
                </Form.Item>
                <Form.Item className="form-item-label" name="Password" label="Password" style={{fontSize: 16}}>
                    <Input.Password className="form-input" placeholder="Enter Your Password" value={password}
                                onChange = {(event) =>
                                {
                                    setPassword(event.target.value);
                                }}/>
                </Form.Item>
                <Form.Item wrapperCol={{span: 24}}>
                    <Button size="large" type="primary" htmlType="submit" block onClick={login}>
                        Log In
                    </Button>
                </Form.Item>
                <Divider className="divider" >
                    <span style={{color: "royalblue"}}>
                        Don't have an account? <span style={{fontWeight: "bold"}}>Sign Up</span>
                    </span>
                </Divider>

            </Form>
            
        </div>
    );
}

export default Login;