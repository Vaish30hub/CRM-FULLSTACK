import { useEffect, useState } from "react";
import api from "../services/api";

export default function Sales() {
  const [sales, setSales] = useState([]);

  useEffect(() => {
    api.get("/sales").then(res => setSales(res.data));
  }, []);

  return (
    <div className="container">
      <h2>Sales</h2>

      <ul className="list">
        {sales.map(s => (
          <li key={s.id}>
            Customer ID: {s.customerId} | Amount: {s.amount} | 
            <strong> {s.status}</strong>
          </li>
        ))}
      </ul>
    </div>
  );
}