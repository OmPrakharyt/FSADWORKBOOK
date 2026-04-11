import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api';
import { FaUserCircle } from 'react-icons/fa';

function Profile() {
  const navigate = useNavigate();
  const [userProfile, setUserProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const user = JSON.parse(localStorage.getItem('user'));

  useEffect(() => {
    // Redirect if no logged-in user
    if (!user) {
      navigate('/login');
      return;
    }

    // Fetch user profile from backend
    const fetchProfile = async () => {
      try {
        const response = await api.get(`/users/profile/${user.userId}`);
        setUserProfile(response.data);
      } catch (err) {
        setError('Failed to load profile');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, [user, navigate]);

  if (loading) {
    return (
      <div className="container">
        <div className="loading">
          <div className="spinner"></div>
          <p>Loading profile...</p>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="container">
        <div className="auth-card">
          <div className="error-message">{error}</div>
        </div>
      </div>
    );
  }

  return (
    <div className="container">
      <div className="profile-card">
        <div className="profile-header">
          <div className="profile-avatar">
            <FaUserCircle />
          </div>
          <h2>User Profile</h2>
        </div>
        
        <div className="profile-info">
          <div className="info-row">
            <div className="info-label">Username:</div>
            <div className="info-value">{userProfile.username}</div>
          </div>
          
          <div className="info-row">
            <div className="info-label">Full Name:</div>
            <div className="info-value">{userProfile.fullName || 'Not provided'}</div>
          </div>
          
          <div className="info-row">
            <div className="info-label">Email:</div>
            <div className="info-value">{userProfile.email}</div>
          </div>
          
          <div className="info-row">
            <div className="info-label">Phone:</div>
            <div className="info-value">{userProfile.phoneNumber || 'Not provided'}</div>
          </div>
          
          <div className="info-row">
            <div className="info-label">Address:</div>
            <div className="info-value">{userProfile.address || 'Not provided'}</div>
          </div>
          
          <div className="info-row">
            <div className="info-label">Member Since:</div>
            <div className="info-value">
              {new Date(userProfile.createdAt).toLocaleDateString()}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Profile;