# 🏥 Smart Healthcare Management System (HMS)

A robust, full-stack Healthcare Management System built with **Java**, **Spring Boot**, **PostgreSQL**, and **Maven**, designed to simplify patient-doctor interactions through effective appointment scheduling, health profiling, and medical history tracking.

🌐 **Frontend (Swagger UI)**: [Access API Docs](https://smart-health-care-management-system.onrender.com/swagger-ui/index.html#/)

---

## 📁 Project Structure

healthcare-management-system/ ├── backend/ (Spring Boot) │ ├── src/ │ ├── pom.xml │ └── application.properties ├── frontend/ (Swagger UI Hosted) └── README.md

---

## 🧰 Tech Stack

- **Backend**: Java 21, Spring Boot  
- **Frontend**: Swagger UI (API Testing Interface)  
- **Database**: PostgreSQL (`HMSDB`)  
- **Build Tool**: Maven  
- **Hosting**: Render  

---

## 🗃️ Database Schema Overview

**Database**: `HMSDB`

### 🔹 User

| Field      | Type      | Description         |
|------------|-----------|---------------------|
| `UserID`   | PK        | Unique user ID      |
| `Name`     | String    | Full name           |
| `Age`      | Integer   | Age of the user     |
| `Gender`   | String    | Gender              |
| `Contact`  | String    | Contact information |

### 🔹 Doctor

| Field        | Type      | Description         |
|--------------|-----------|---------------------|
| `DoctorID`   | PK        | Unique doctor ID    |
| `Name`       | String    | Doctor's name       |
| `Specialty`  | String    | Area of expertise   |
| `Experience` | Integer   | Years of experience |
| `Contact`    | String    | Contact info        |

### 🔹 Patient Health Profile

| Field          | Type    | Description           |
|----------------|---------|-----------------------|
| `ProfileID`    | PK      | Unique profile ID     |
| `UserID`       | FK      | References `User`     |
| `DoctorID`     | FK      | References `Doctor`   |
| `DoctorName`   | String  | Doctor’s name         |
| `Date`         | Date    | Profile date          |
| `Prescription` | Text    | Given prescription    |

### 🔹 Appointment

| Field           | Type      | Description             |
|------------------|-----------|-------------------------|
| `AppointmentID`  | PK        | Unique appointment ID   |
| `PatientID`      | FK        | References `User`       |
| `DoctorID`       | FK        | References `Doctor`     |
| `Schedule`       | Timestamp | Appointment time        |

### 🔹 Doctor History

| Field         | Type    | Description               |
|---------------|---------|---------------------------|
| `HistoryID`   | PK      | Unique history record     |
| `DoctorID`    | FK      | References `Doctor`       |
| `PatientID`   | FK      | References `User`         |
| `PatientName` | String  | Patient name              |
| `Date`        | Date    | History date              |

---

## 🚀 Features

- 🩺 **Doctor Management**: Create, view, and manage doctors and their specialties  
- 👨‍⚕️ **Patient Records**: Maintain patient health profiles and history  
- 📅 **Appointment Scheduling**: Efficiently schedule and manage appointments  
- 📊 **Prescription Management**: Store and retrieve patient prescriptions securely  
- 🔍 **API Documentation**: Complete Swagger UI for API testing and exploration  

---

## 📦 Installation & Setup

### 1. Clone the repository

git clone https://github.com/Learnathon-By-Geeky-Solutions/the-triology

### 2. Set up PostgreSQL

- Create a database named `HMSDB`

- Update `application.properties`:

  ```bash
  spring.datasource.url=jdbc:postgresql://localhost:5432/HMSDB
  spring.datasource.username=your_username
  spring.datasource.password=your_password

### 3. Run the application

    
    mvn clean install
    mvn spring-boot:run


### 4. Access API

- Visit: [https://smart-health-care-management-system.onrender.com/swagger-ui/index.html#/](https://smart-health-care-management-system.onrender.com/swagger-ui/index.html#/)

---

## 👥 Team Members

| Name         | GitHub ID                                  |
|--------------|--------------------------------------------------------------------|
| Mahmudul Islam Nayon(Team Leader)     | [@Mahislam1](https://github.com/Mahislam1) |
| Sheikh Sady              | [@sheikh-sady](https://github.com/sheikh-sady) |
| Shihab Uddin Himel       | [@CodeCraftShihab](https://github.com/CodeCraftShihab) |


---

## 🎓 Mentor

 Mahtab Shahed  [@mahtabshahed](https://github.com/mahtabshahed)
---

---

## 🤝 Contributing

We welcome contributions! To contribute:

1. Fork the repository 🍴  
2. Create a new branch:  
   ```bash
   git checkout -b feature/your-feature
3. Commit your changes:  
   ```bash
   git commit -m "Add your feature" 
4. Push to the branch:  
   ```bash
   git push origin feature/your-feature
5. Open a pull request.



## 🛡️ License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
