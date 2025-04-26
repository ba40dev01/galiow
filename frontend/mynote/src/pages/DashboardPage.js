import React from "react";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import noteService from "../api/noteService";
import {
  Button,
  Container,
  Typography,
  Box,
  List,
  ListItem,
  ListItemText,
  IconButton,
} from "@mui/material";
import { Add, Edit, Delete } from "@mui/icons-material";

const DashboardPage = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const [notes, setNotes] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchNotes = async () => {
      try {
        const notes = await noteService.getNotes();
        setNotes(notes);
      } catch (err) {
        console.error("Failed to fetch notes:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchNotes();
  }, []);

  const handleDelete = async (noteId) => {
    try {
      await noteService.deleteNote(noteId);
      setNotes(notes.filter((note) => note.id !== noteId));
    } catch (err) {
      console.error("Failed to delete note:", err);
    }
  };

  if (loading) {
    return <div>Loading notes...</div>;
  }

  return (
    <Container maxWidth="md">
      <Box
        sx={{
          mt: 4,
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
        }}
      >
        <Typography variant="h4">Welcome, {user?.name}</Typography>
        <Button variant="contained" color="error" onClick={logout}>
          Logout
        </Button>
      </Box>

      <Box sx={{ mt: 4 }}>
        <Box sx={{ display: "flex", justifyContent: "flex-end", mb: 2 }}>
          <Button
            variant="contained"
            startIcon={<Add />}
            onClick={() => navigate("/notes/new")}
          >
            New Note
          </Button>
        </Box>

        {notes.length === 0 ? (
          <Typography variant="body1">
            No notes found. Create your first note!
          </Typography>
        ) : (
          <List>
            {notes.map((note) => (
              <ListItem
                key={note.id}
                secondaryAction={
                  <>
                    <IconButton
                      edge="end"
                      onClick={() => navigate(`/note/${note.id}/edit`)}
                    >
                      <Edit />
                    </IconButton>
                    <IconButton
                      edge="end"
                      onClick={() => handleDelete(note.id)}
                    >
                      <Delete />
                    </IconButton>
                  </>
                }
              >
                <ListItemText
                  primary={note.title}
                  secondary={note.description}
                />
              </ListItem>
            ))}
          </List>
        )}
      </Box>
    </Container>
  );
};

export default DashboardPage;
