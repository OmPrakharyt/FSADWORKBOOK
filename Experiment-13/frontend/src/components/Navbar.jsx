import React from 'react';
import { FaGraduationCap, FaUserGraduate } from 'react-icons/fa';

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-content">
        <div className="navbar-left">
          <FaGraduationCap className="navbar-icon" />
          <div className="navbar-title">
            <h1>Student Management System</h1>
            <p>Manage student records efficiently</p>
          </div>
        </div>
        <div className="navbar-right">
          <FaUserGraduate />
          <span>Admin Portal</span>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;