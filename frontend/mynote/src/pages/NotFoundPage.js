import { useNavigate } from "react-router-dom";
import { Box, Button, Container, Typography } from "@mui/material";
import { useAuth } from "../context/AuthContext";

const NotFoundPage = () => {
  const navigate = useNavigate();

  // Safely get auth context with default values
  const auth = useAuth?.() || {};
  const isAuthenticated = auth.isAuthenticated ? auth.isAuthenticated() : false;

  return (
    <Container maxWidth="md">
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
          minHeight: "80vh",
          textAlign: "center",
        }}
      >
        <Typography variant="h1" sx={{ fontSize: "6rem", mb: 2 }}>
          404
        </Typography>
        <Typography variant="h4" sx={{ mb: 3 }}>
          Oops! Page Not Found
        </Typography>
        <Typography variant="body1" sx={{ mb: 4 }}>
          The page you're looking for doesn't exist or has been moved.
        </Typography>
        <Button
          variant="contained"
          size="large"
          onClick={() => navigate(isAuthenticated ? "/dashboard" : "/login")}
        >
          Go to {isAuthenticated ? "Dashboard" : "Login"}
        </Button>
      </Box>
    </Container>
  );
};

export default NotFoundPage;
