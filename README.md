# 🧪 SwagLabs Automation Testing Project

This project automates end-to-end UI testing for the [SwagLabs demo website](https://www.saucedemo.com/) using **Selenium WebDriver**, **TestNG**, and **Maven**. It includes structured logging, custom TestNG listeners, and generates interactive test reports using **Allure**. The project is also ready for **Jenkins** CI/CD integration.

---

## 📋 Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Execution](#execution)
- [Reporting](#reporting)
- [Logging](#logging)
- [Jenkins Integration](#jenkins-integration)
- [Screenshots](#screenshots)
- [Author](#author)

---

## ✨ Features

- ✅ Automated login and inventory validation tests
- 🌐 Cross-browser testing support
- ⚙️ Maven-based build management
- 🧪 TestNG framework with parallel execution
- 📝 Custom listeners for enhanced test event handling
- 📊 Allure reporting with step-by-step results
- 📁 Log4j/SLF4J logging for real-time tracking
- 🔁 Jenkins-ready for continuous integration

---

## 🛠️ Tech Stack

| Tool         | Description                        |
|--------------|------------------------------------|
| Java         | Programming language               |
| Selenium     | UI automation                      |
| TestNG       | Testing framework                  |
| Maven        | Build tool                         |
| Allure       | Test reporting                     |
| Log4j/SLF4J  | Logging                            |
| Jenkins      | CI/CD Integration (planned)        |

---

## 📁 Project Structure

<pre>
src
├── main
│   └── java
│       └── PID_PageName       # Base classes (WebDriver manager, config)
├── test
│   └── java
│       └── TCID_PageName      # Test cases
├── listeners                          # Custom TestNG listeners
├── utils                              # Utilities (screenshots, waits, etc.)
├── logs                               # Log output directory
├── reports                            # Allure report files
├── pom.xml                            # Maven build file
└── testng.xml                         # TestNG suite configuration
</pre>

---

## ⚙️ Installation

1. **Clone the Repository**
```bash
git clone https://github.com/OmarAly07/Selenium_SwagLabs.git
cd Selenium_SwagLabs
