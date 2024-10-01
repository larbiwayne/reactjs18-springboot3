import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate(); 

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
          const response = await axios.post('http://localhost:8084/api/login', {
            username,
            password,
          });
              // Store the token in localStorage
          localStorage.setItem('token', response.data.token);
          console.log("token");
          console.log(response.data.token);
          // Check if user is admin or normal user
          if (response.data.role === 'ADMIN') {
            navigate('/admin');
          } else {
            navigate('/user');
          }
        } catch (error) {
          console.error('Login error:', error);
        }
      };

      return (
        <div>
          <h2>Login</h2>
          <form onSubmit={handleLogin}>
            <div>
              <label>Username:</label>
              <input
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
            <div>
              <label>Password:</label>
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <button type="submit">Login</button>
          </form>
        </div>
      );
    }
    
    export default LoginPage;




