package com.sowmya.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * StudentServlet - Handles student mark input and performance analysis.
 * Author: sowmya849
 */
@WebServlet("/analyze")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String studentName = request.getParameter("studentName");
        String[] subjectNames = request.getParameterValues("subjectName");
        String[] marksStr = request.getParameterValues("marks");
        String[] maxMarksStr = request.getParameterValues("maxMarks");

        if (subjectNames == null || marksStr == null || maxMarksStr == null) {
            response.sendRedirect("index.html");
            return;
        }

        int numSubjects = subjectNames.length;
        double[] marks = new double[numSubjects];
        double[] maxMarks = new double[numSubjects];
        double totalObtained = 0;
        double totalMax = 0;

        for (int i = 0; i < numSubjects; i++) {
            marks[i] = Double.parseDouble(marksStr[i]);
            maxMarks[i] = Double.parseDouble(maxMarksStr[i]);
            totalObtained += marks[i];
            totalMax += maxMarks[i];
        }

        double percentage = (totalObtained / totalMax) * 100;
        double cgpa = percentage / 9.5;
        String grade = calculateGrade(percentage);
        String performance = calculatePerformance(percentage);
        String advice = generateAdvice(percentage);
        String gradeColor = getGradeColor(grade);

        PrintWriter out = response.getWriter();
        out.println(generateResultPage(studentName, subjectNames, marks, maxMarks,
                numSubjects, totalObtained, totalMax, percentage, cgpa, grade,
                performance, advice, gradeColor));
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) return "O";
        else if (percentage >= 80) return "A+";
        else if (percentage >= 70) return "A";
        else if (percentage >= 60) return "B+";
        else if (percentage >= 50) return "B";
        else if (percentage >= 40) return "C";
        else return "F";
    }

    private String calculatePerformance(double percentage) {
        if (percentage >= 90) return "Outstanding";
        else if (percentage >= 80) return "Excellent";
        else if (percentage >= 70) return "Very Good";
        else if (percentage >= 60) return "Good";
        else if (percentage >= 50) return "Average";
        else if (percentage >= 40) return "Below Average";
        else return "Fail";
    }

    private String generateAdvice(double percentage) {
        if (percentage >= 90) return "Exceptional work! You are on track for top placements. Consider competitive research internships.";
        else if (percentage >= 80) return "Great performance! Focus on building strong projects and a GitHub portfolio to stand out.";
        else if (percentage >= 70) return "Good job! Strengthen your core subject knowledge and work on at least 2 solid projects.";
        else if (percentage >= 60) return "Decent performance. Identify weak subjects and dedicate extra time. Add certifications to boost your profile.";
        else if (percentage >= 50) return "You need to improve. Focus on understanding fundamentals. Practice regularly and seek mentorship.";
        else return "Don't give up! Revisit basics, get help from professors, and stay consistent. Improvement is always possible.";
    }

    private String getGradeColor(String grade) {
        switch (grade) {
            case "O": return "#00c853";
            case "A+": return "#1de9b6";
            case "A": return "#00b0ff";
            case "B+": return "#7c4dff";
            case "B": return "#ff9100";
            case "C": return "#ff6d00";
            default: return "#ff1744";
        }
    }

    private String generateResultPage(String studentName, String[] subjectNames,
            double[] marks, double[] maxMarks, int numSubjects,
            double totalObtained, double totalMax, double percentage,
            double cgpa, String grade, String performance,
            String advice, String gradeColor) {

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'>")
          .append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>")
          .append("<title>Result - ").append(studentName).append("</title>")
          .append("<link rel='stylesheet' href='css/style.css'>")
          .append("<link href='https://fonts.googleapis.com/css2?family=Space+Mono:wght@400;700&family=Syne:wght@400;700;800&display=swap' rel='stylesheet'>")
          .append("</head><body class='result-page'>")
          .append("<div class='container'>")
          .append("<a href='index.html' class='back-btn'>← Analyze Another</a>")
          .append("<div class='result-header'>")
          .append("<h1>Performance Report</h1>")
          .append("<p class='student-name'>").append(studentName).append("</p>")
          .append("</div>")
          .append("<div class='grade-hero' style='border-color:").append(gradeColor).append(";'>")
          .append("<div class='grade-circle' style='background:").append(gradeColor).append(";'>")
          .append("<span class='grade-letter'>").append(grade).append("</span>")
          .append("</div>")
          .append("<div class='grade-info'>")
          .append("<h2 style='color:").append(gradeColor).append(";'>").append(performance).append("</h2>")
          .append("<p class='percentage'>").append(String.format("%.2f", percentage)).append("%</p>")
          .append("<p class='cgpa'>CGPA: ").append(String.format("%.2f", cgpa)).append(" / 10</p>")
          .append("</div></div>")
          .append("<div class='subjects-table'>")
          .append("<h3>Subject-wise Breakdown</h3>")
          .append("<table><thead><tr><th>Subject</th><th>Marks</th><th>Max</th><th>%</th><th>Grade</th></tr></thead><tbody>");

        for (int i = 0; i < numSubjects; i++) {
            double subPercent = (marks[i] / maxMarks[i]) * 100;
            String subGrade = calculateGrade(subPercent);
            String subColor = getGradeColor(subGrade);
            sb.append("<tr>")
              .append("<td>").append(subjectNames[i]).append("</td>")
              .append("<td>").append((int) marks[i]).append("</td>")
              .append("<td>").append((int) maxMarks[i]).append("</td>")
              .append("<td>").append(String.format("%.1f", subPercent)).append("%</td>")
              .append("<td><span class='badge' style='background:").append(subColor).append(";'>")
              .append(subGrade).append("</span></td>")
              .append("</tr>");
        }

        sb.append("</tbody><tfoot><tr>")
          .append("<td><strong>Total</strong></td>")
          .append("<td><strong>").append((int) totalObtained).append("</strong></td>")
          .append("<td><strong>").append((int) totalMax).append("</strong></td>")
          .append("<td><strong>").append(String.format("%.2f", percentage)).append("%</strong></td>")
          .append("<td><span class='badge' style='background:").append(gradeColor).append(";'>")
          .append(grade).append("</span></td>")
          .append("</tr></tfoot></table></div>")
          .append("<div class='advice-box'>")
          .append("<h3>💡 AI Advisor Recommendation</h3>")
          .append("<p>").append(advice).append("</p>")
          .append("</div>")
          .append("<div class='grade-scale'>")
          .append("<h3>Grade Scale</h3>")
          .append("<div class='scale-items'>")
          .append("<span class='scale-item' style='background:#00c853'>O: 90-100</span>")
          .append("<span class='scale-item' style='background:#1de9b6'>A+: 80-89</span>")
          .append("<span class='scale-item' style='background:#00b0ff'>A: 70-79</span>")
          .append("<span class='scale-item' style='background:#7c4dff'>B+: 60-69</span>")
          .append("<span class='scale-item' style='background:#ff9100'>B: 50-59</span>")
          .append("<span class='scale-item' style='background:#ff6d00'>C: 40-49</span>")
          .append("<span class='scale-item' style='background:#ff1744'>F: &lt;40</span>")
          .append("</div></div>")
          .append("</div></body></html>");

        return sb.toString();
    }
}
