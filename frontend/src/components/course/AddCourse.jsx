import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Password from 'antd/es/input/Password';

const AddCourse = () => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [teacher, setTeacher] = useState(null);
  const [teachers, setTeachers] = useState([]);

  useEffect(() => {
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

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleTeacherChange = (event) => {
    const selectedTeacher = teachers.find(teacher => teacher.id === parseInt(event.target.value));
    setTeacher(selectedTeacher);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!teacher) {
      console.log("Please select a teacher");
      return;
    }
    const token = localStorage.getItem("token");
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    const simplifiedTeacher = {
      id: teacher.id,
      email: teacher.email

    };
    axios.post('http://localhost:8080/courses/add-course', {
      name,
      description,
      teacher: simplifiedTeacher
    }, config)
      .then(response => console.log(response))
      .catch(error => console.log(error));
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Name:</label>
        <input type="text" value={name} onChange={handleNameChange} />
      </div>
      <div>
        <label>Description:</label>
        <input type="text" value={description} onChange={handleDescriptionChange} />
      </div>
      <div>
        <label>Teacher:</label>
        <select value={teacher?.id} onChange={handleTeacherChange}>
          <option value="">Select a teacher</option>
          {teachers.map(teacher => (
            <option key={teacher.id} value={teacher.id}>{teacher.firstName} {teacher.lastName}</option>
          ))}
        </select>
      </div>
      <button type="submit">Add Course</button>
    </form>
  );
};

export default AddCourse;
