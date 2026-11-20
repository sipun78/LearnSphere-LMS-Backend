## Project Architecture

The application follows a layered architecture:

- **Controller Layer:** Handles incoming REST API requests.
- **Service Layer:** Contains business logic.
- **Service Implementation Layer:** Implements service interfaces.
- **Repository Layer:** Communicates with the database using Spring Data JPA.
- **Entity Layer:** Defines database entities.
- **DTO Layer:** Transfers data between client and server.
- **Exception Layer:** Handles application exceptions globally.

### Main Modules

- Authentication and User Management
- Course Management
- Lesson Management
- Enrollment Management
- Learning Progress Tracking
- Course Reviews
## Prerequisites

Before running the application, make sure you have:

- Java 21
- Docker Desktop
- Git

## Running the Application Using Docker

### 1. Clone the Repository

git clone https://github.com/sipun78/LearnSphere-LMS-Backend.git

### 2. Navigate to the Project

cd LearnSphere-LMS-Backend

### 3. Configure Environment Variables

Create a `.env` file in the project root:

MYSQL_ROOT_PASSWORD=your_root_password
DB_USERNAME=your_database_username
DB_PASSWORD=your_database_password
JWT_SECRET=your_base64_encoded_jwt_secret

Use your own database credentials. Do not upload the `.env` file to GitHub.

### 4. Build and Start Containers

docker compose up -d --build

### 5. Check Running Containers

docker compose ps

### 6. Stop the Application

docker compose down
## REST API Endpoints

Base URL: `http://localhost:9091`

### Authentication
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate user |

### Courses
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/courses` | Create course |
| GET | `/api/courses` | Get all courses |
| GET | `/api/courses/{id}` | Get course by ID |
| PUT | `/api/courses/{id}` | Update course |
| DELETE | `/api/courses/{id}` | Delete course |

### Lessons
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/lessons` | Create lesson |
| GET | `/api/lessons` | Get all lessons |
| GET | `/api/lessons/{id}` | Get lesson by ID |
| PUT | `/api/lessons/{id}` | Update lesson |
| DELETE | `/api/lessons/{id}` | Delete lesson |

### Enrollments
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/enrollments` | Create enrollment |
| GET | `/api/enrollments` | Get enrollments |
| GET | `/api/enrollments/{id}` | Get enrollment by ID |
| PUT | `/api/enrollments/{id}` | Update enrollment |
| DELETE | `/api/enrollments/{id}` | Delete enrollment |

### Lesson Progress
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/lesson-progress` | Create lesson progress |
| GET | `/api/lesson-progress` | Get lesson progress records |
| GET | `/api/lesson-progress/{id}` | Get progress by ID |
| PUT | `/api/lesson-progress/{id}` | Update lesson progress |
| DELETE | `/api/lesson-progress/{id}` | Delete lesson progress |

### Course Progress
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/course-progress/{userId}/{courseId}` | Create course progress |
| GET | `/api/course-progress` | Get course progress records |
| GET | `/api/course-progress/{id}` | Get progress by ID |

### Course Reviews
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/course-reviews` | Create review |
| GET | `/api/course-reviews` | Get all reviews |
| GET | `/api/course-reviews/{id}` | Get review by ID |
| GET | `/api/course-reviews/course/{courseId}` | Get reviews by course |
| PUT | `/api/course-reviews/{id}` | Update review |
| DELETE | `/api/course-reviews/{id}` | Delete review |

### Users
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/users` | Create user |
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |
## JWT Authentication

The application uses JSON Web Tokens (JWT) to authenticate users and secure protected REST API endpoints.

### Authentication Flow

1. Register a new user using the registration endpoint.
2. Log in using the registered credentials.
3. Receive a JWT token after successful authentication.
4. Include the token in the Authorization header when accessing protected endpoints.

### Authorization Header

```http
Authorization: Bearer <your-jwt-token>
```

### Token Expiration

JWT tokens expire after 1 hour.