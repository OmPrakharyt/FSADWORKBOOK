import { useState } from "react";
import StudentList from "./components/StudentList";
import AddStudent from "./components/AddStudent";

function App() {
  const [selectedStudent, setSelectedStudent] = useState(null);
  const [refreshFlag, setRefreshFlag] = useState(false);

  const refresh = () => {
    setRefreshFlag(!refreshFlag); // triggers re-fetch
    setSelectedStudent(null);
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h1>Student Management System</h1>

      <AddStudent
        selectedStudent={selectedStudent}
        refresh={refresh}
      />

      <StudentList
        onEdit={setSelectedStudent}
        refreshFlag={refreshFlag}
      />
    </div>
  );
}

export default App;