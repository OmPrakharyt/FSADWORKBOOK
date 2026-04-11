import { useState, useEffect } from "react";
import axios from "axios";

function AddStudent({ selectedStudent, refresh }) {
  const [form, setForm] = useState({
    name: "",
    email: "",
    course: "",
  });

  useEffect(() => {
    if (selectedStudent) {
      setForm(selectedStudent);
    }
  }, [selectedStudent]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (form.id) {
      await axios.put(
        `http://localhost:8080/students/${form.id}`,
        form
      );
    } else {
      await axios.post(
        "http://localhost:8080/students",
        form
      );
    }

    setForm({ name: "", email: "", course: "" });
    refresh();
  };

  return (
    <div>
      <h2>{form.id ? "Update Student" : "Add Student"}</h2>

      <form onSubmit={handleSubmit}>
        <input
          name="name"
          value={form.name}
          onChange={handleChange}
          placeholder="Name"
          required
        />
        <br /><br />

        <input
          name="email"
          value={form.email}
          onChange={handleChange}
          placeholder="Email"
          required
        />
        <br /><br />

        <input
          name="course"
          value={form.course}
          onChange={handleChange}
          placeholder="Course"
          required
        />
        <br /><br />

        <button type="submit">
          {form.id ? "Update" : "Add"}
        </button>
      </form>
    </div>
  );
}

export default AddStudent;