import { Link, useNavigate } from "react-router-dom";

export default function Dashboard() {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  return (
    <div className="container">
      <button className="logout" onClick={logout}>Logout</button>

      <h1>CRM Dashboard</h1>

      <ul className="list">
        <li><Link to="/customers">Customers</Link></li>
        <li><Link to="/leads">Leads</Link></li>
        <li><Link to="/tasks">Tasks</Link></li>
        <li><Link to="/sales">Sales</Link></li>
      </ul>
    </div>
  );
}