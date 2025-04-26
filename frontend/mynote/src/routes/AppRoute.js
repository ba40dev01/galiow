import React from "react";
import Layout from "../components/Layout";
import LoginPage from "../pages/LoginPage";
import DashboardPage from "../pages/DashboardPage";
import NotFoundPage from "../pages/NotFoundPage";
import RegisterPage from "../pages/RegisterPage";
import NoteFormPage from "../pages/NoteFormPage";
import PrivateRoute from "../components/PrivateRoute";
import { AuthProvider } from "../context/AuthContext";

const AppRoute = [
  {
    path: "/",
    element: (
      <AuthProvider>
        <Layout />
      </AuthProvider>
    ),
    errorElement: <NotFoundPage />,
    children: [
      {
        index: true,
        element: <LoginPage />,
      },
      {
        path: "login",
        element: <LoginPage />,
      },
      {
        path: "register",
        element: <RegisterPage />,
      },
      {
        path: "dashboard",
        element: (
          <PrivateRoute>
            <DashboardPage />
          </PrivateRoute>
        ),
      },
      {
        path: "note/new",
        element: (
          <PrivateRoute>
            <NoteFormPage />
          </PrivateRoute>
        ),
      },
      {
        path: "note/:noteId/edit",
        element: (
          <PrivateRoute>
            <NoteFormPage />
          </PrivateRoute>
        ),
      },
      {
        path: "*",
        element: <NotFoundPage />,
      },
    ],
  },
];

export default AppRoute;
