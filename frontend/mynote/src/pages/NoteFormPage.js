import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import {
  TextField,
  Button,
  Typography,
  Container,
  Box,
  Alert,
  CircularProgress,
} from "@mui/material";
import noteService from "../api/noteService";

const NoteFormPage = () => {
  const { noteId } = useParams();
  const { token } = useAuth();
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    title: "",
    note: "",
  });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    const fetchNote = async () => {
      try {
        setLoading(true);
        const note = await noteService.getNoteById(noteId);
        setFormData({
          title: note.title,
          note: note.note,
        });
      } catch (error) {
        console.error("Error fetching note:", error);
        setError("Failed to load note. Please try again.");
      } finally {
        setLoading(false);
      }
    };

    if (noteId) {
      setIsEditing(true);
      fetchNote();
    }
  }, [noteId, token]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
    if (error) setError("");
  };

  const validateForm = () => {
    if (!formData.title.trim()) {
      setError("Title is required");
      return false;
    }
    if (!formData.note.trim()) {
      setError("Note note is required");
      return false;
    }
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    setLoading(true);
    try {
      if (isEditing) {
        await noteService.updateNote(noteId, formData);
      } else {
        await noteService.createNote(formData);
      }
      navigate("/dashboard");
    } catch (error) {
      console.error("Error saving note:", error);
      setError(
        error.response?.data?.message ||
          "Failed to save note. Please try again."
      );
    } finally {
      setLoading(false);
    }
  };

  if (loading && isEditing) {
    return (
      <Container maxWidth="md">
        <Box display="flex" justifyContent="center" mt={4}>
          <CircularProgress />
        </Box>
      </Container>
    );
  }

  return (
    <Container maxWidth="md">
      <Box
        sx={{
          mt: 4,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Typography component="h1" variant="h4" sx={{ mb: 3 }}>
          {isEditing ? "Edit Note" : "Create New Note"}
        </Typography>

        {error && (
          <Alert severity="error" sx={{ width: "100%", mb: 3 }}>
            {error}
          </Alert>
        )}

        <Box component="form" onSubmit={handleSubmit} sx={{ width: "100%" }}>
          <TextField
            fullWidth
            required
            label="Title"
            name="title"
            value={formData.title}
            onChange={handleChange}
            error={!!error}
            sx={{ mb: 3 }}
          />

          <TextField
            fullWidth
            multiline
            rows={10}
            label="Content"
            name="note"
            value={formData.note}
            onChange={handleChange}
            required
            sx={{ mb: 3 }}
          />

          <Box display="flex" justifyContent="flex-end">
            <Button
              variant="outlined"
              sx={{ mr: 2 }}
              onClick={() => navigate("/dashboard")}
            >
              Cancel
            </Button>
            <Button type="submit" variant="contained" disabled={loading}>
              {loading ? (
                <CircularProgress size={24} />
              ) : isEditing ? (
                "Update Note"
              ) : (
                "Create Note"
              )}
            </Button>
          </Box>
        </Box>
      </Box>
    </Container>
  );
};

export default NoteFormPage;
