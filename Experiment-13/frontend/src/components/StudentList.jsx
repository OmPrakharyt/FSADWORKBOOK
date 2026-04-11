import React from 'react';
import { FaEdit, FaTrash, FaUserGraduate, FaSync, FaEnvelope, FaPhone, FaBook } from 'react-icons/fa';

function StudentList({ students, onEdit, onDelete, loading, error, onRefresh }) {
  if (loading) {
    return (
      <div className="list-card">
        <div className="loading-container">
          <div className="spinner"></div>
          <p>Loading students...</p>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="list-card">
        <div className="error-container">
          <div className="error-icon">⚠️</div>
          <p>{error}</p>
          <button onClick={onRefresh} className="retry-btn">
            <FaSync /> Retry
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="list-card">
      <div className="list-header">
        <div>
          <h2>Student Directory</h2>
          <p className="student-count">
            <FaUserGraduate style={{ display: 'inline', marginRight: '4px' }} />
            Total Students: <strong>{students.length}</strong>
          </p>
        </div>
        <button onClick={onRefresh} className="refresh-btn">
          <FaSync />
        </button>
      </div>
      
      {students.length === 0 ? (
        <div className="empty-state">
          <FaUserGraduate className="empty-icon" />
          <p>No students added yet</p>
          <small>Use the form to add your first student</small>
        </div>
      ) : (
        <div className="table-container">
          <table>
            <thead>
              <tr>
                <th>#</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Course</th>
                <th>Phone</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {students.map((student, index) => (
                <tr key={student.id}>
                  <td>{index + 1}</td>
                  <td>
                    <div className="student-name">
                      {student.firstName} {student.lastName}
                    </div>
                  </td>
                  <td>
                    <div className="email-cell">
                      <FaEnvelope /> {student.email}
                    </div>
                  </td>
                  <td>
                    <div className="course-cell">
                      <FaBook /> {student.course}
                    </div>
                  </td>
                  <td>
                    <div className="phone-cell">
                      <FaPhone /> {student.phoneNumber || 'N/A'}
                    </div>
                  </td>
                  <td>
                    <div className="action-buttons">
                      <button onClick={() => onEdit(student)} className="edit-btn">
                        <FaEdit /> Edit
                      </button>
                      <button onClick={() => onDelete(student.id)} className="delete-btn">
                        <FaTrash /> Delete
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default StudentList;