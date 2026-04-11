import { useEffect, useState } from "react";
import axios from "axios";

function StudentList({ onEdit, refreshFlag }) {
  const [students, setStudents] = useState([]);

  const fetchStudents = async () => {
    const res = await axios.get("http://localhost:8080/students");
    setStudents(res.data);
  };

  useEffect(() => {
    fetchStudents();
  }, [refreshFlag]);

  const deleteStudent = async (id) => {
    await axios.delete(`http://localhost:8080/students/${id}`);
    fetchStudents();
  };

  return (
    <div>
      <h2>Student List</h2>

      <table border="1" style={{ margin: "auto" }}>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {students.map((s) => (
            <tr key={s.id}>
              <td>{s.name}</td>
              <td>{s.email}</td>
              <td>{s.course}</td>
              <td>
                <button onClick={() => onEdit(s)}>Edit</button>
                <button onClick={() => deleteStudent(s.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default StudentList;