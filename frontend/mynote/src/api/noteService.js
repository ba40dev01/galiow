import api from "../utils/axiosConfig";

const API_URL = "/note";

const getNotes = async () => {
  const response = await api.get(API_URL);
  return response.data;
};

const getNoteById = async (noteId) => {
  const response = await api.get(`${API_URL}/${noteId}`);
  return response.data[0];
};

const createNote = async (noteData) => {
  const response = await api.post(API_URL, noteData);
  return response.data;
};

const updateNote = async (noteId, noteData) => {
  const response = await api.patch(`${API_URL}/${noteId}`, noteData);
  return response.data;
};

const deleteNote = async (noteId) => {
  const response = await api.delete(`${API_URL}/${noteId}`);
  return response.data;
};

const noteService = {
  getNotes,
  createNote,
  updateNote,
  deleteNote,
  getNoteById,
};

export default noteService;
