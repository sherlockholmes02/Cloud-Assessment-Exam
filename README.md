# ☁️ Cloud Employee Assessment Exam

An Android application built as a **technical assessment exam for a Cloud Employee role**.
The project demonstrates modern Android development, clean architecture practices, API integration, and comprehensive unit testing.

---

## 📱 Project Overview

This application fetches and displays users from the StackExchange API and allows interactive exploration of user information.

It is designed to evaluate a candidate’s ability to:

* Work with real-world REST APIs
* Build modern Android UI
* Apply Clean Architecture
* Implement reactive state management
* Write maintainable and testable code

---

## ✨ Features

### 🔹 User List Screen

* Fetches users from the StackExchange API
* Displays users in a scrollable list
* Shows key user information

### 🔹 User Detail Screen

* Navigates to a dedicated screen
* Displays more detailed information about the selected user

### 🔹 Search & Filter

* Real-time filtering of fetched users
* Filters by user display name
* Case-insensitive matching

---

## 🧠 Architecture

This project follows **Clean Architecture** with three distinct layers:

### 1️⃣ Presentation Layer

**UI → ViewModel**

* Built using Jetpack Compose
* ViewModel exposes UI state using StateFlow
* Handles user interactions and screen state

### 2️⃣ Domain Layer

**UseCase (Business Logic)**

* Contains core business rules
* Responsible for data transformation
* Extracts and maps API response
* Removes unnecessary wrapper objects (e.g., `items`)
* Retains only the `User` list for app consumption

### 3️⃣ Data Layer

**Repository → API**

* Repository handles data operations
* Fetches data from remote API
* Provides data back to domain layer
* Supports clean separation of concerns

### 🔁 Data Flow

```
UI → ViewModel → UseCase → Repository → API
API → Repository → UseCase → ViewModel → UI
```

---

## 🛠 Tech Stack

### Modern Android Technologies

* Kotlin
* Jetpack Compose (Declarative UI)
* Hilt + Dagger2 (Dependency Injection)
* Kotlin Coroutines (Asynchronous programming)
* StateFlow (Reactive state management)
* Coil (Image loading)
* Retrofit (REST API networking)

### Architecture & Patterns

* Clean Architecture
* MVVM
* Repository Pattern
* Use Case Pattern

---

## 🧪 Testing

Comprehensive unit tests were implemented to ensure reliability and correctness.

### ✅ ViewModel Tests

* Successful user fetch scenario
* Failure/error handling scenario
* Search filtering behavior

### ✅ Utility Tests

* Date utility test
* Converts `creationDate` from Long → formatted String

---

## 🚀 Getting Started

### Prerequisites

* Android Studio (latest)
* Android SDK
* JDK 17+

### Installation

```bash
git clone https://github.com/sherlockholmes02/Cloud-Assessment-Exam.git
```

Open the project in Android Studio and run on an emulator or device.

---

## ⏱ Development Time

This project was completed in a total of **24 hours** of focused development time.

---

## 🎬 App Demo
* 📹 Video Demo: (https://github.com/user-attachments/assets/26541957-2f77-41d7-afe5-90d5ff63ba39)
---

## 🎯 Purpose of This Project

This project was developed as part of a Cloud Employee technical assessment to demonstrate:

* Strong Android fundamentals
* Modern development practices
* Scalable architecture design
* Clean, testable, and maintainable code

---

## 🙌 Author

Developed as a technical assessment submission.
