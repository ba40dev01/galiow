import React from "react";
import { Outlet } from "react-router-dom";
import { Container, CssBaseline } from "@mui/material";

const Layout = () => {
  return (
    <>
      <CssBaseline />
      <Container maxWidth="lg">
        <Outlet />
      </Container>
    </>
  );
};

export default Layout;
