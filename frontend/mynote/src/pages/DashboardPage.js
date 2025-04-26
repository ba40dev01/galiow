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
  Chip,
  Divider,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Paper,
  CircularProgress,
} from "@mui/material";
import { Add, Edit, Delete } from "@mui/icons-material";
import { format } from "date-fns";

const DashboardPage = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const [notes, setNotes] = useState([]);
  const [filteredNotes, setFilteredNotes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedCategory, setSelectedCategory] = useState("all");

  useEffect(() => {
    const fetchNotes = async () => {
      try {
        const notes = await noteService.getNotes();
        setNotes(notes);
        setFilteredNotes(notes);
      } catch (err) {
        console.error("Failed to fetch notes:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchNotes();
  }, []);

  useEffect(() => {
    if (selectedCategory === "all") {
      setFilteredNotes(notes);
    } else {
      setFilteredNotes(
        notes.filter((note) => note.category === selectedCategory)
      );
    }
  }, [selectedCategory, notes]);

  const handleDelete = async (noteId) => {
    try {
      await noteService.deleteNote(noteId);
      setNotes(notes.filter((note) => note.id !== noteId));
    } catch (err) {
      console.error("Failed to delete note:", err);
    }
  };

  const formatDate = (dateString) => {
    if (!dateString) return "";
    const date = new Date(dateString);
    return format(date, "MMM dd, yyyy HH:mm");
  };

  if (loading) {
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
          justifyContent: "space-between",
          alignItems: "center",
        }}
      >
        <Typography variant="h4">Welcome, {user?.username}</Typography>
        <Button variant="contained" color="error" onClick={logout}>
          Logout
        </Button>
      </Box>

      <Box sx={{ mt: 4 }}>
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            mb: 2,
            gap: 2,
          }}
        >
          <FormControl sx={{ minWidth: 200 }}>
            <InputLabel>Filter by Category</InputLabel>
            <Select
              value={selectedCategory}
              label="Filter by Category"
              onChange={(e) => setSelectedCategory(e.target.value)}
            >
              <MenuItem value="all">All Categories</MenuItem>
              <MenuItem value="PERSONAL">Personal</MenuItem>
              <MenuItem value="WORK">Work</MenuItem>
              <MenuItem value="STUDY">Study</MenuItem>
              <MenuItem value="IDEAS">Ideas</MenuItem>
            </Select>
          </FormControl>

          <Button
            variant="contained"
            startIcon={<Add />}
            onClick={() => navigate("/note/new")}
          >
            New Note
          </Button>
        </Box>

        {filteredNotes.length === 0 ? (
          <Paper elevation={3} sx={{ p: 3, textAlign: "center" }}>
            <Typography variant="body1">
              No notes found
              {selectedCategory !== "all" ? ` in ${selectedCategory}` : ""}.
              Create your first note!
            </Typography>
          </Paper>
        ) : (
          <List sx={{ width: "100%", bgcolor: "background.paper" }}>
            {filteredNotes.map((note) => (
              <React.Fragment key={note.id}>
                <ListItem
                  secondaryAction={
                    <>
                      <IconButton
                        edge="end"
                        onClick={() => navigate(`/note/${note.id}/edit`)}
                        sx={{ mr: 1 }}
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
                    primary={
                      <Box
                        sx={{ display: "flex", alignItems: "center", mb: 1 }}
                      >
                        <Typography variant="h6" component="div">
                          {note.title}
                        </Typography>
                        <Chip
                          label={note.category}
                          size="small"
                          sx={{ ml: 2 }}
                          color="primary"
                        />
                      </Box>
                    }
                    secondary={
                      <>
                        <Typography
                          component="div"
                          variant="body2"
                          color="text.primary"
                          sx={{ mb: 1 }}
                        >
                          {note.note}
                        </Typography>
                        <Box sx={{ display: "flex", gap: 2 }}>
                          <Typography variant="caption">
                            Created: {formatDate(note.createAt)}
                          </Typography>
                          <Typography variant="caption">
                            Updated: {formatDate(note.updatedAt)}
                          </Typography>
                        </Box>
                      </>
                    }
                  />
                </ListItem>
                <Divider component="li" />
              </React.Fragment>
            ))}
          </List>
        )}
      </Box>
    </Container>
  );
};

export default DashboardPage;
