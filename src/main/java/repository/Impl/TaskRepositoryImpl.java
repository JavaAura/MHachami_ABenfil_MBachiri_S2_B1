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

    private Connection conn;

    public TaskRepositoryImpl() throws SQLException {
        loadDriver();
        this.conn = DatabaseConnection.getConnection(); // Open the connection once in the constructor
    }

    @Override
    public void addTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, priority, status, creation_date, deadline) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getPriority());
            stmt.setString(4, task.getStatus());
            stmt.setDate(5, Date.valueOf(task.getCreationDate()));
            stmt.setDate(6, Date.valueOf(task.getDeadline()));
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(Priority.valueOf(rs.getString("priority").toUpperCase()));
                task.setStatus(TaskStatus.valueOf(rs.getString("status").toUpperCase()));
                task.setCreationDate(rs.getDate("creation_date").toLocalDate());
                task.setDeadline(rs.getDate("deadline").toLocalDate());
                tasks.add(task);
            }
        }
        return tasks;
    }

    @Override
    public Task getTaskById(int id) throws SQLException {
        Task task = null;
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(Priority.valueOf(rs.getString("priority").toUpperCase()));
                task.setStatus(TaskStatus.valueOf(rs.getString("status").toUpperCase()));
                task.setCreationDate(rs.getDate("creation_date").toLocalDate());
                task.setDeadline(rs.getDate("deadline").toLocalDate());
            }
        }
        return task;
    }

    @Override
    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET title = ?, description = ?, priority = ?, status = ?, deadline = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getPriority());
            stmt.setString(4, task.getStatus());
            stmt.setDate(5, Date.valueOf(task.getDeadline()));
            stmt.setInt(6, task.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteTaskById(int id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private void loadDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
