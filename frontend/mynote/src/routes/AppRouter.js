import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import LoginPage from "../pages/LoginPage";
import RegisterPage from "../pages/RegisterPage";
import DashboardPage from "../pages/DashboardPage";
import TaskFormPage from "../pages/TaskFormPage";
import NotFoundPage from "../pages/NotFoundPage";

const AppRouter = () => {
  const { isAuthenticated, loading } = useAuth();

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <Routes>
      <Route
        path="/"
        element={
          isAuthenticated() ? (
            <Navigate to="/dashboard" replace />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />
      <Route
        path="/login"
        element={
          !isAuthenticated() ? <LoginPage /> : <Navigate to="/login" replace />
        }
      />
      <Route
        path="/register"
        element={
          !isAuthenticated() ? (
            <RegisterPage />
          ) : (
            <Navigate to="/dashboard" replace />
          )
        }
      />
      <Route
        path="/dashboard"
        element={
          isAuthenticated() ? (
            <DashboardPage />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />
      <Route
        path="/tasks/new"
        element={
          isAuthenticated() ? (
            <TaskFormPage />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />
      <Route
        path="/tasks/:id/edit"
        element={
          isAuthenticated() ? (
            <TaskFormPage />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />
      <Route path="*" element={<NotFoundPage />} />
    </Routes>
  );
};

export default AppRouter;
