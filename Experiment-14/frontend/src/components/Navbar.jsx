import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Navbar() {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem('user'));

  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/login');
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/" className="navbar-brand">
          AuthSystem
        </Link>
        
        {user && (
          <div className="navbar-links">
            <Link to="/">Home</Link>
            <Link to="/profile">Profile</Link>
            <button onClick={handleLogout} className="logout-btn">
              Logout
            </button>
          </div>
        )}
      </div>
    </nav>
  );
}

export default Navbar;