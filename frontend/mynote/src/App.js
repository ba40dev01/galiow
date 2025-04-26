import React from "react";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import AppRoute from "./routes/AppRoute";

const theme = createTheme();

const router = createBrowserRouter(AppRoute, {
  future: {
    v7_startTransition: true,
    v7_relativeSplatPath: true,
  },
});

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <RouterProvider router={router} />
    </ThemeProvider>
  );
}

export default App;
