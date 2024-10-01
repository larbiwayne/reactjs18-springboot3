import React, { useEffect, useState } from "react";
import axios from "axios";

const AdminPage = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    const CNAS = "CNAS";
    axios.get(`http://localhost:8084/api/admin/customquery/${CNAS}`, {  
 
      headers: {  
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    })
    .then((response) => {
      setUsers(response.data);
    });
  }, []);

  const deleteUser = (id) => {
    axios.delete(`http://localhost:8084/api/admin/users/${id}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    }).then(() => {
      setUsers(users.filter(user => user.id !== id));
    });
  };

  return (
    <div>
      <h1>Admin Page</h1>
      <ul>
        {users.map((user) => (
          <li key={user.id}>
            {user.username} <button onClick={() => deleteUser(user.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AdminPage;
