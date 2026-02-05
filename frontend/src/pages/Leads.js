import { useEffect, useState } from "react";
import api from "../services/api";

export default function Leads() {
  const [leads, setLeads] = useState([]);

  useEffect(() => {
    api.get("/leads").then(res => setLeads(res.data));
  }, []);

  return (
    <div className="container">
      <h2>Leads</h2>

      {leads.length === 0 ? (
        <p style={{ textAlign: "center" }}>No leads available</p>
      ) : (
        <ul className="list">
          {leads.map(l => (
            <li key={l.id}>
              {l.name} — <strong>{l.status}</strong>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}