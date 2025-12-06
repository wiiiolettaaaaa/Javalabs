package com.wioletta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;

            while (!exit) {
                System.out.println("\nChoose an action:");
                System.out.println("1. Get all employees");
                System.out.println("2. Get all tasks");
                System.out.println("3. Get employees by department");
                System.out.println("4. Add task for an employee");
                System.out.println("5. Get tasks by employee");
                System.out.println("6. Delete employee");
                System.out.println("7. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (choice) {
                        case 1:
                            System.out.println("All employees:");
                            List<String> employees = DatabaseQueries.getAllEmployees();
                            employees.forEach(System.out::println);
                            break;

                        case 2:
                            System.out.println("All tasks:");
                            List<String> tasks = DatabaseQueries.getAllTasks();
                            tasks.forEach(System.out::println);
                            break;

                        case 3:
                            System.out.print("Enter department ID: ");
                            int deptId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Employees in department " + deptId + ":");
                            List<String> deptEmployees = DatabaseQueries.getEmployeesByDepartment(deptId);
                            deptEmployees.forEach(System.out::println);
                            break;

                        case 4:
                            System.out.print("Enter employee ID to assign task: ");
                            int empIdForTask = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter task description: ");
                            String taskDesc = scanner.nextLine();
                            DatabaseQueries.addTask(empIdForTask, taskDesc);
                            System.out.println("Task added successfully.");
                            break;

                        case 5:
                            System.out.print("Enter employee ID to get tasks: ");
                            int empIdForTasks = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Tasks for employee " + empIdForTasks + ":");
                            List<String> employeeTasks = DatabaseQueries.getTasksByEmployee(empIdForTasks);
                            employeeTasks.forEach(System.out::println);
                            break;

                        case 6:
                            System.out.print("Enter employee ID to delete: ");
                            int empIdToDelete = scanner.nextInt();
                            scanner.nextLine();
                            DatabaseQueries.deleteEmployee(empIdToDelete);
                            System.out.println("Employee deleted successfully.");
                            break;

                        case 7:
                            exit = true;
                            System.out.println("Exiting program.");
                            break;

                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error with database operation: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Error reading from database: " + e.getMessage());
                }
            }
        }
    }
}