import React, { useState, useEffect } from 'react';
import StudentList from './components/StudentList';
import AddStudent from './components/AddStudent';
import Navbar from './components/Navbar';
import axios from 'axios';

const API_URL = import.meta.env.VITE_API_URL;

function App() {
  const [students, setStudents] = useState([]);
  const [editingStudent, setEditingStudent] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState('');

  useEffect(() => {
    fetchStudents();
  }, []);

  useEffect(() => {
    if (successMessage) {
      const timer = setTimeout(() => setSuccessMessage(''), 3000);
      return () => clearTimeout(timer);
    }
  }, [successMessage]);

  const fetchStudents = async () => {
    try {
      setLoading(true);
      setError(null);
      const response = await axios.get(`${API_URL}/students`);
      setStudents(response.data);
    } catch (err) {
      console.error('Error fetching students:', err);
      setError('Failed to fetch students. Please make sure the backend server is running.');
    } finally {
      setLoading(false);
    }
  };

  const addStudent = async (student) => {
    try {
      const response = await axios.post(`${API_URL}/students`, student);
      setStudents([...students, response.data]);
      setSuccessMessage('Student added successfully!');
      return { success: true };
    } catch (err) {
      console.error('Error adding student:', err);
      const errorMsg = err.response?.data?.message || 'Failed to add student';
      return { success: false, error: errorMsg };
    }
  };

  const updateStudent = async (id, student) => {
    try {
      const response = await axios.put(`${API_URL}/students/${id}`, student);
      setStudents(students.map(s => s.id === id ? response.data : s));
      setEditingStudent(null);
      setSuccessMessage('Student updated successfully!');
      return { success: true };
    } catch (err) {
      console.error('Error updating student:', err);
      const errorMsg = err.response?.data?.message || 'Failed to update student';
      return { success: false, error: errorMsg };
    }
  };

  const deleteStudent = async (id) => {
    if (window.confirm('Are you sure you want to delete this student?')) {
      try {
        await axios.delete(`${API_URL}/students/${id}`);
        setStudents(students.filter(s => s.id !== id));
        setSuccessMessage('Student deleted successfully!');
      } catch (err) {
        console.error('Error deleting student:', err);
        alert('Failed to delete student. Please try again.');
      }
    }
  };

  return (
    <div>
      <Navbar />
      
      <div className="container">
        {successMessage && (
          <div className="success-toast">
            {successMessage}
          </div>
        )}
        
        <div className="main-layout">
          <AddStudent
            onAdd={addStudent}
            onUpdate={updateStudent}
            editingStudent={editingStudent}
            setEditingStudent={setEditingStudent}
          />
          
          <StudentList
            students={students}
            onEdit={setEditingStudent}
            onDelete={deleteStudent}
            loading={loading}
            error={error}
            onRefresh={fetchStudents}
          />
        </div>
      </div>
    </div>
  );
}

export default App;