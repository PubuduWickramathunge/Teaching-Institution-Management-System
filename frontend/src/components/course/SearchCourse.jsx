import React, { useState, useEffect } from 'react';
import { Input } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import axios from 'axios';

const SearchCourses = ({ setCourses, setError }) => {
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    const options = {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
    };
    axios
      .get(`http://localhost:8080/courses/search?name=${searchTerm}`, options)
      .then((response) => {
        setCourses(response.data);
      })
      .catch((error) => {
        setError(error.response.data.message);
        setCourses([]);
      });
  }, [searchTerm, setCourses, setError]);

  return (
    <div style={{ marginBottom: '20px' }}>
      <Input
        placeholder="Search courses"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        style={{ width: '300px', marginRight: '10px' }}
        suffix={<SearchOutlined />}
      />
    </div>
  );
};

export default SearchCourses;
