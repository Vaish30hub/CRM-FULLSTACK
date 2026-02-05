import { useEffect, useState } from "react";
import api from "../services/api";

export default function Tasks() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    api.get("/tasks").then(res => setTasks(res.data));
  }, []);

  return (
    <div className="container">
      <h2>Tasks</h2>

      {tasks.length === 0 ? (
        <p style={{ textAlign: "center" }}>No tasks assigned</p>
      ) : (
        <ul className="list">
          {tasks.map(t => (
            <li key={t.id}>
              {t.title} — <strong>{t.status}</strong>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}