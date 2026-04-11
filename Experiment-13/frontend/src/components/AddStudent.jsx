import React, { useState, useEffect } from 'react';
import { FaSave, FaTimes, FaUserPlus, FaEdit } from 'react-icons/fa';

function AddStudent({ onAdd, onUpdate, editingStudent, setEditingStudent }) {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    course: '',
    phoneNumber: ''
  });
  const [errors, setErrors] = useState({});
  const [submitting, setSubmitting] = useState(false);

  const courses = ['Computer Science', 'Engineering', 'Business', 'Mathematics', 'Physics', 'Chemistry', 'Biology'];

  useEffect(() => {
    if (editingStudent) {
      setFormData({
        firstName: editingStudent.firstName || '',
        lastName: editingStudent.lastName || '',
        email: editingStudent.email || '',
        course: editingStudent.course || '',
        phoneNumber: editingStudent.phoneNumber || ''
      });
    } else {
      resetForm();
    }
  }, [editingStudent]);

  const resetForm = () => {
    setFormData({
      firstName: '',
      lastName: '',
      email: '',
      course: '',
      phoneNumber: ''
    });
    setErrors({});
  };

  const validateForm = () => {
    const newErrors = {};
    
    if (!formData.firstName.trim()) {
      newErrors.firstName = 'First name is required';
    } else if (formData.firstName.length < 2) {
      newErrors.firstName = 'First name must be at least 2 characters';
    }
    
    if (!formData.lastName.trim()) {
      newErrors.lastName = 'Last name is required';
    } else if (formData.lastName.length < 2) {
      newErrors.lastName = 'Last name must be at least 2 characters';
    }
    
    if (!formData.email.trim()) {
      newErrors.email = 'Email is required';
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = 'Email is invalid (e.g., name@domain.com)';
    }
    
    if (!formData.course) {
      newErrors.course = 'Please select a course';
    }
    
    if (formData.phoneNumber && !/^\d{10}$/.test(formData.phoneNumber.replace(/\D/g, ''))) {
      newErrors.phoneNumber = 'Phone number must be 10 digits';
    }
    
    return newErrors;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
    if (errors[name]) {
      setErrors(prev => ({
        ...prev,
        [name]: ''
      }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const validationErrors = validateForm();
    
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }

    setSubmitting(true);
    let result;
    
    if (editingStudent) {
      result = await onUpdate(editingStudent.id, formData);
    } else {
      result = await onAdd(formData);
    }
    
    setSubmitting(false);
    
    if (result.success) {
      resetForm();
      if (editingStudent) {
        setEditingStudent(null);
      }
    } else {
      alert(result.error || 'An error occurred. Please try again.');
    }
  };

  const handleCancel = () => {
    resetForm();
    setEditingStudent(null);
  };

  return (
    <div className="form-card">
      <div className="form-header">
        {editingStudent ? (
          <>
            <FaEdit className="form-header-icon" style={{ color: '#eab308' }} />
            <h2>Edit Student</h2>
          </>
        ) : (
          <>
            <FaUserPlus className="form-header-icon" />
            <h2>Add New Student</h2>
          </>
        )}
      </div>
      
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <div className="form-group">
            <label>First Name <span className="required">*</span></label>
            <input
              type="text"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
              placeholder="Enter first name"
            />
            {errors.firstName && <div className="error-message">{errors.firstName}</div>}
          </div>
          
          <div className="form-group">
            <label>Last Name <span className="required">*</span></label>
            <input
              type="text"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
              placeholder="Enter last name"
            />
            {errors.lastName && <div className="error-message">{errors.lastName}</div>}
          </div>
        </div>
        
        <div className="form-group">
          <label>Email Address <span className="required">*</span></label>
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="student@example.com"
          />
          {errors.email && <div className="error-message">{errors.email}</div>}
        </div>
        
        <div className="form-group">
          <label>Course <span className="required">*</span></label>
          <select
            name="course"
            value={formData.course}
            onChange={handleChange}
          >
            <option value="">Select a course</option>
            {courses.map(course => (
              <option key={course} value={course}>{course}</option>
            ))}
          </select>
          {errors.course && <div className="error-message">{errors.course}</div>}
        </div>
        
        <div className="form-group">
          <label>Phone Number</label>
          <input
            type="tel"
            name="phoneNumber"
            value={formData.phoneNumber}
            onChange={handleChange}
            placeholder="10-digit mobile number"
          />
          {errors.phoneNumber && <div className="error-message">{errors.phoneNumber}</div>}
        </div>
        
        <div className="button-group">
          <button type="submit" disabled={submitting} className="btn-save">
            <FaSave /> {submitting ? 'Saving...' : (editingStudent ? 'Update Student' : 'Save Student')}
          </button>
          
          {editingStudent && (
            <button type="button" onClick={handleCancel} className="btn-cancel">
              <FaTimes /> Cancel
            </button>
          )}
        </div>
      </form>
      
      <div className="info-box">
        <p>📝 <strong>Note:</strong> Fields marked with <span style={{color: '#ef4444'}}>*</span> are required. All student data is saved in the database.</p>
      </div>
    </div>
  );
}

export default AddStudent;