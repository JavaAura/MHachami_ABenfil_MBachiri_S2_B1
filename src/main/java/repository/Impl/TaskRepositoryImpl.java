package repository.Impl;

import entities.Task;
import enums.Priority;
import enums.TaskStatus;
import repository.TaskRepository;
import utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class TaskRepositoryImpl implements TaskRepository {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public void addTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, priority, status, craetion_date, deadline) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getPriority());
            stmt.setString(4, task.getStatus());
            stmt.setDate(5, Date.valueOf(task.getCreationDate()));
            stmt.setDate(6, Date.valueOf(task.getDeadline()));
            stmt.executeUpdate();
        }
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(Priority.valueOf(rs.getString("priority")));
                task.setStatus(TaskStatus.valueOf(rs.getString("status")));
                task.setCreationDate(LocalDate.parse(rs.getString("creation_date")));
                task.setDeadline(LocalDate.parse(rs.getString("deadline")));
                tasks.add(task);
            }
        }
        return tasks;
    }

    public Task getTaskById(int id) throws SQLException {
        Task task = null;
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(enums.Priority.valueOf(rs.getString("priority")));
                task.setStatus(enums.TaskStatus.valueOf(rs.getString("status")));
                task.setCreationDate(LocalDate.parse(rs.getString("creation_date")));
                task.setDeadline(LocalDate.parse(rs.getString("deadline")));
            }
        }
        return task;
    }

    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET title = ?, description = ?, priority = ?, status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getPriority());
            stmt.setString(4, task.getStatus());
            stmt.setInt(5, task.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteTaskById(int id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
