import React, { useEffect, useState } from "react";
import { Form, Button, Input, Radio, Typography, Divider, message, Select } from "antd";
import { Link, useNavigate } from "react-router-dom";
import { validationRules } from "./CoourseValidation";
import { Layout } from "antd";
import TopNavBar from "../NavBar";
import axios from "axios";

import "../login/style.css";

const AddCourse = () => {
  const [form] = Form.useForm();
  const [teachers, setTeachers] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();
  const role = localStorage.getItem("role");

  useEffect(() => {
    if (role !== "MANAGEMENT") {
      navigate("/forbidden");
    }
  
    const token = localStorage.getItem("token");
    const options = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    axios.get("http://localhost:8080/getAll/teachers", options)
      .then(response => setTeachers(response.data))
      .catch(error => console.log(error));
  }, []);

  const onFinish = (values) => {
    const selectedTeacher = teachers.find(teacher => teacher.id === parseInt(values.teacher));
    const token = localStorage.getItem("token");
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    const simplifiedTeacher = {
      id: selectedTeacher.id,
      email: selectedTeacher.email
    };
    axios.post('http://localhost:8080/courses/add-course', {
      name: values.name,
      description: values.description,
      teacher: simplifiedTeacher
    }, config)
      .then(response => {
        console.log(response);
        message.success("Course added successfully");
        form.resetFields();
      })
      .catch((error) => {
        console.log(error.response);
        if (error.response.status === 409) {
          setErrorMessage("Course name already exists.");
        } else {
          setErrorMessage("Failed to add course.");
        }
      });  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <Layout>
    <Layout.Header>
      <TopNavBar />
    </Layout.Header>
    <Layout.Content>
      <div className="form">
        <header className="normal-form">
          <div className="form-container">
            
    <Form
      form={form}
      autoComplete="off"
      labelCol={{ span: 8 }}
      wrapperCol={{ span: 20 }}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
    >
      <Typography.Title
                style={{ color: "#1eb2a6", fontSize: "50px" }}
              >
                Add Course
              </Typography.Title>
              {errorMessage && (
                  <div className="error-message">
                    <p>{errorMessage}</p>
                  </div>
                )}
      <Form.Item
        label="Name"
        name="name"
        rules={validationRules.name}
      >
        <Input size="large" className="form-input"/>
      </Form.Item>

      <Form.Item
        label="Description"
        name="description"
        
      >
        <Input className="form-input" size="large"/>
      </Form.Item>

      <Form.Item
        label="Teacher"
        name="teacher"
        size="large"
        rules={validationRules.teacher}
      >
        <Select placeholder="Select a teacher">
          {teachers.map(teacher => (
            <Select.Option key={teacher.id} value={teacher.id}>{teacher.firstName} {teacher.lastName}</Select.Option>
          ))}
        </Select>
      </Form.Item>

      <Form.Item wrapperCol={{ span: 24 }}>
        <Button type="primary" htmlType="submit" className="submit-button" size="large"block>
          Add Course
        </Button>
      </Form.Item>
    </Form>
    </div>
          </header>
        </div>
      </Layout.Content>
    </Layout>
  );
};

export default AddCourse;
