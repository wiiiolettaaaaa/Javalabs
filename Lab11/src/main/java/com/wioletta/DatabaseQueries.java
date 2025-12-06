package com.wioletta;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueries {
    public static List<String> getAllEmployees() throws SQLException, IOException {
        List<String> employees = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {
            while (rs.next()) {
                employees.add(rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        }
        return employees;
    }

    public static List<String> getAllTasks() throws SQLException, IOException {
        List<String> tasks = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tasks")) {
            while (rs.next()) {
                tasks.add(rs.getString("description"));
            }
        }
        return tasks;
    }

    public static List<String> getEmployeesByDepartment(int departmentId) throws SQLException, IOException {
        List<String> employees = new ArrayList<>();
        String query = "SELECT first_name, last_name FROM employees WHERE department_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, departmentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                employees.add(rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        }
        return employees;
    }

    public static void addTask(int employeeId, String description) throws SQLException, IOException {
        String query = "INSERT INTO tasks (description, employee_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, description);
            pstmt.setInt(2, employeeId);
            pstmt.executeUpdate();
        }
    }

    public static List<String> getTasksByEmployee(int employeeId) throws SQLException, IOException {
        List<String> tasks = new ArrayList<>();
        String query = "SELECT description FROM tasks WHERE employee_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tasks.add(rs.getString("description"));
            }
        }
        return tasks;
    }

    public static void deleteEmployee(int employeeId) throws SQLException, IOException {
        String query = "DELETE FROM employees WHERE employee_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
        }
    }
}
