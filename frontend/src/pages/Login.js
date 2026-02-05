import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = () => {
    api.post("/login", { email, password })
      .then(res => {
        localStorage.setItem("token", res.data.token);
        navigate("/dashboard");
      })
      .catch(() => {
        setError("Invalid credentials");
      });
  };

  return (
    <div className="center-screen">
      <div className="card">
        <h2 style={{ textAlign: "center" }}>CRM Login</h2>

        {error && <p style={{ color: "red" }}>{error}</p>}

        <input
          placeholder="Email"
          value={email}
          onChange={e => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={e => setPassword(e.target.value)}
        />

        <button onClick={handleLogin}>Login</button>
      </div>
    </div>
  );
}