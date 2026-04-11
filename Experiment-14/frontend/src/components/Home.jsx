import React from 'react';
import { useNavigate } from 'react-router-dom';

function Home() {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem('user'));

  // Redirect if no logged-in user
  if (!user) {
    navigate('/login');
    return null;
  }

  return (
    <div className="container">
      <div className="home-container">
        <h1>Welcome, {user.fullName || user.username}!</h1>
        <p>You have successfully logged into the Authentication System.</p>
        <p>This is your dashboard. Use the navigation menu to view your profile.</p>
      </div>
    </div>
  );
}

export default Home;