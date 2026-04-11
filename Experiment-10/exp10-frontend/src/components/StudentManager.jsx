import { useState, useEffect } from "react";
import axios from "axios";
import "./StudentManager.css";

function StudentManager() {

  const [students, setStudents] = useState([]);
  const [newStudent, setNewStudent] = useState({
    id: "",
    name: "",
    course: ""
  });

  const API = "http://localhost:8080/students";

  useEffect(() => {
    fetchStudents();
  }, []);

  const fetchStudents = async () => {
    const res = await axios.get(API);
    setStudents(res.data);
  };

  const handleChange = (e) => {
    setNewStudent({
      ...newStudent,
      [e.target.name]: e.target.value
    });
  };

  const addStudent = async () => {

    if (!newStudent.id || !newStudent.name || !newStudent.course) {
      alert("Please fill all fields");
      return;
    }

    await axios.post(API, newStudent);
    fetchStudents();

    setNewStudent({
      id: "",
      name: "",
      course: ""
    });
  };

  const deleteStudent = async (id) => {
    await axios.delete(`${API}/${id}`);
    fetchStudents();
  };

  return (
    <div className="container">

      <h2>Student Manager</h2>

      <div className="form">

        <input
          name="id"
          placeholder="Student ID"
          value={newStudent.id}
          onChange={handleChange}
        />

        <input
          name="name"
          placeholder="Student Name"
          value={newStudent.name}
          onChange={handleChange}
        />

        <input
          name="course"
          placeholder="Course"
          value={newStudent.course}
          onChange={handleChange}
        />

        <button onClick={addStudent}>Add Student</button>

      </div>

      {students.length === 0 ? (
        <p>No students available</p>
      ) : (

        <table>

          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Course</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>

            {students.map((s) => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.name}</td>
                <td>{s.course}</td>
                <td>
                  <button onClick={() => deleteStudent(s.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}

          </tbody>

        </table>
      )}

    </div>
  );
}

export default StudentManager;