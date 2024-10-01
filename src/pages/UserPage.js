import React, { useState, useEffect } from 'react';
import axios from 'axios';

function UserPage() {
  const [userInfo, setUserInfo] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8084/api/me')
      .then(response => setUserInfo(response.data))
      .catch(error => console.error('Error fetching user info:', error));
  }, []);

  return (
    <div>
      <h2>User Information</h2>
      {userInfo ? (
        <div>
          <p>Username: {userInfo.username}</p>
          <p>Role: {userInfo.role}</p>
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
}

export default UserPage;
