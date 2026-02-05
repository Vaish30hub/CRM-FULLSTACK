import { useEffect, useState } from "react";
import api from "../services/api";

export default function Customers() {
  const [customers, setCustomers] = useState([]);
  const [name, setName] = useState("");

  useEffect(() => {
    api.get("/customers").then(res => setCustomers(res.data));
  }, []);

  const addCustomer = () => {
    if (!name) return;
    api.post("/customers", { name }).then(() => {
      setName("");
      api.get("/customers").then(res => setCustomers(res.data));
    });
  };

  return (
    <div className="container">
      <h2>Customers</h2>

      <div>
        <input
          placeholder="Customer Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <button onClick={addCustomer}>Add</button>
      </div>

      <ul className="list">
        {customers.map(c => (
          <li key={c.id}>{c.name}</li>
        ))}
      </ul>
    </div>
  );
}