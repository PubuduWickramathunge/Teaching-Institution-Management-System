import React from "react";
import { Button, message } from "antd";
import axios from "axios";

const EnrollButton = ({ courseId }) => {
  const enrollCourse = () => {
    const options = {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    };
    axios
      .post(`http://localhost:8080/enrollment/${courseId}`, null, options)
      .then((response) => {
        console.log(response);
        message.success("You have successfully enrolled in the course.");
      })
      .catch((error) => {
        console.log(error);
        if (error.response) {
          const errorMessage = error.response.data.message;
          message.error(errorMessage);
        } else {
          message.error("An error occurred while enrolling in the course.");
        }
      });
  };

  return (
    <Button
      type="default"
      style={{
        color: "#1eb2a6",
        border: "1px solid #1eb2a6",
      }}
      onClick={enrollCourse}
    >
      Enroll
    </Button>
  );
};

export default EnrollButton;
