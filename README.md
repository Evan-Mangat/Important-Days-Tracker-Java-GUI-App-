# Important-Days-Tracker--Java-GUI-App
 # 📅 Important Days Tracker

A Java GUI application to track birthdays, anniversaries, and special occasions. Built with Swing and backed by a Spring Boot REST API, this app helps users manage important dates through a polished desktop interface.

---

## ✨ Features

- 🗓️ Add, view, and remove events of 3 types: **Birthday**, **Anniversary**, and **Occasion**
- 🔍 Filter events by type or by whether they are **upcoming**, **passed**, or **happening this year**
- 🌐 Fully connected to a **Spring Boot** web service that handles storage and JSON serialization
- 💾 Automatically saves to and loads from a JSON file on the server
- 👤 Uses MVC architecture for a clean, maintainable codebase
- 🖱️ User-friendly interface with responsive buttons, date pickers, and intuitive dialogs

---

## 🛠️ Technologies Used

### 💻 Client (Java Desktop App)
- Java 21
- Java Swing
- MVC Architecture
- JSON via GSON
- LocalDate + DatePicker

### 🌐 Server (Web API)
- Spring Boot (Java 21)
- REST API (GET/POST endpoints)
- JSON serialization (GSON)
- Data persistence via file storage (`list.json`)

---

## 🚀 Getting Started

### Prerequisites
- Java 21 (Oracle JDK recommended)
- VS Code with Java Extension Pack

### Run Instructions

#### 1. Start the Server
```bash
cd webappserver
./mvnw spring-boot:run

