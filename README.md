# 🎓 Student Performance Analyzer

A **Java Web Application** built using **Servlets** that analyzes student academic performance — calculates GPA, assigns grades, and provides personalized career advice.

> Developed by [sowmya849](https://github.com/sowmya849) | B.Tech Artificial Intelligence & Data Science

---

## 🚀 Features

- 📊 **Multi-subject mark entry** with dynamic add/remove
- 📈 **Percentage & CGPA calculation** (10-point scale)
- 🏅 **Grade assignment** — O, A+, A, B+, B, C, F
- 💡 **AI Advisor Recommendation** based on performance
- 📋 **Subject-wise breakdown** with individual grades
- 🎨 **Responsive dark-themed UI**

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java 17, Jakarta Servlets |
| Frontend | HTML5, CSS3, JavaScript |
| Build Tool | Apache Maven |
| Server | Apache Tomcat 10+ |

---

## 📁 Project Structure

```
StudentPerformanceAnalyzer/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/sowmya/student/
│       │       └── StudentServlet.java      ← Core logic
│       └── webapp/
│           ├── index.html                   ← Input form
│           ├── css/
│           │   └── style.css                ← Styling
│           └── WEB-INF/
│               └── web.xml                  ← Deployment config
├── pom.xml                                  ← Maven build file
└── README.md
```

---

## ⚙️ How to Run

### Prerequisites
- Java 17+
- Apache Maven 3.8+
- Apache Tomcat 10+

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/sowmya849/StudentPerformanceAnalyzer.git

# 2. Navigate to project
cd StudentPerformanceAnalyzer

# 3. Build the project
mvn clean package

# 4. Deploy the generated .war file to Tomcat
cp target/StudentPerformanceAnalyzer-1.0.war $TOMCAT_HOME/webapps/

# 5. Start Tomcat and open browser
# Visit: http://localhost:8080/StudentPerformanceAnalyzer-1.0/
```

---

## 📸 Screenshots

### Input Form
- Enter student name and subject-wise marks
- Add/remove subjects dynamically

### Result Page
- Visual grade badge with color coding
- Subject-wise performance table
- Personalized career advice

---

## 📊 Grade Scale

| Grade | Percentage | Performance |
|-------|-----------|-------------|
| O | 90 – 100% | Outstanding |
| A+ | 80 – 89% | Excellent |
| A | 70 – 79% | Very Good |
| B+ | 60 – 69% | Good |
| B | 50 – 59% | Average |
| C | 40 – 49% | Below Average |
| F | Below 40% | Fail |

---

## 🔮 Future Enhancements

- [ ] Database integration (MySQL) to store student records
- [ ] Login/Register system for students
- [ ] Semester-wise trend analysis with charts
- [ ] Export result as PDF
- [ ] REST API with Spring Boot

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

⭐ If you found this useful, please give it a star!
