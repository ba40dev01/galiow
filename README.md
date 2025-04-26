# Full-Stack Authentication App (note App)

A React + Spring Boot application with JWT authentication and CRUD operations.

## Features

- User registration and login
- JWT authentication
- Protected routes
- CRUD for notes
- Specification
- Responsive UI

## Technologies

- **Frontend**: React 18, Material-UI, Axios
- **Backend**: Spring Boot 3, Spring Security, JWT, MySql

## Setup

1. Clone the repository
2. Run `docker-compose up --build`
3. Backend runs on `http://localhost:8080`
4. Access frontend at `http://localhost:3000`
5. Register a new User
6. Login
7. Select,Create,Update,Delete note 
 

## API Endpoints

- POST `/auth/register` - User registration
- POST `/auth/login` - User login
- GET `/note` - Get user notes 
- POST `/note` - Create new note
- PATCH `/note/{id}` - update note
- DELETE `/note/{id}` - Delete note

## Demo
url = https://galiow-1.onrender.com/
