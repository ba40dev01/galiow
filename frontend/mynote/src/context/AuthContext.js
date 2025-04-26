import { createContext, useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../utils/axiosConfig"; // Use the configured axios instance

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(localStorage.getItem("token"));
  const navigate = useNavigate();

  const login = async (username, password) => {
    try {
      if (localStorage.getItem("token") != null) {
        logout();
      }

      const response = await api.post("/auth/login", {
        username,
        password,
      });
      const { token, user } = response.data;

      localStorage.setItem("token", token);
      setToken(token);
      setUser(user);
      navigate("/dashboard");
    } catch (error) {
      console.error("Login failed:", error);
      throw error;
    }
  };

  const register = async (username, name, email, phone, password) => {
    try {
      if (localStorage.getItem("token") != null) {
        logout();
      }
      const response = await api.post("/auth/register", {
        username,
        name,
        email,
        phone,
        password,
      });
      const { token, user } = response.data;

      localStorage.setItem("token", token);
      setToken(token);
      setUser(user);
      navigate("/login");
    } catch (error) {
      console.error("Registration failed:", error);
      throw error;
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    setToken(null);
    setUser(null);
    navigate("/login");
  };
  const isAuthenticated = () => {
    return !!token;
  };
  return (
    <AuthContext.Provider
      value={{ user, token, isAuthenticated, login, register, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
