package repository.Impl;

import entities.Member;
import entities.Task;
import enums.Priority;
import enums.Role;
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
        String sql = "SELECT t.*, m.id AS member_id, m.first_name, m.last_name, m.email, m.role " +
                "FROM tasks t " +
                "LEFT JOIN member_tasks mt ON t.id = mt.task_id " +
                "LEFT JOIN members m ON mt.member_id = m.id " +
                "WHERE t.id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            task = new Task();
            List<Member> assignedMembers = new ArrayList<>();

            if (rs.next()) {
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(Priority.valueOf(rs.getString("priority").toUpperCase()));
                task.setStatus(TaskStatus.valueOf(rs.getString("status").toUpperCase()));
                task.setCreationDate(rs.getDate("creation_date").toLocalDate());
                task.setDeadline(rs.getDate("deadline").toLocalDate());
                do {
                    int memberId = rs.getInt("member_id");
                    if (memberId != 0) { // check if member_id is not null
                        Member member = new Member();
                        member.setId((long) memberId);
                        member.setFirstName(rs.getString("first_name"));
                        member.setSecondName(rs.getString("last_name"));
                        member.setEmail(rs.getString("email"));
                        member.setUserRole(Role.valueOf(rs.getString("role").toUpperCase()));
                        assignedMembers.add(member);
                    }
                } while (rs.next());
            }
            task.setAssignedMembers(assignedMembers);
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

    @Override
    public void assignToMembers(int taskId, int[] membersIds) throws SQLException {
        String deleteSql = "DELETE FROM member_tasks WHERE task_id = ?";
        String insertSql = "INSERT INTO member_tasks (task_id, member_id) VALUES (?, ?)";

        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            deleteStmt.setInt(1, taskId);
            deleteStmt.executeUpdate();
            for (int memberId : membersIds) {
                insertStmt.setInt(1, taskId);
                insertStmt.setInt(2, memberId);
                insertStmt.executeUpdate();
            }
        }
    }

    public int getTaskCount() throws SQLException {
        String query = "SELECT COUNT(*) FROM tasks";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    public List<Task> getTasksByPage(int offset, int noOfRecords) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks LIMIT ? OFFSET ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, noOfRecords);
            preparedStatement.setInt(2, offset);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setPriority(Priority.valueOf(resultSet.getString("priority").toUpperCase()));
                task.setStatus(TaskStatus.valueOf(resultSet.getString("status").toUpperCase()));
                task.setCreationDate(resultSet.getDate("creation_date").toLocalDate());
                task.setDeadline(resultSet.getDate("deadline").toLocalDate());
                tasks.add(task);
            }
        }
        return tasks;
    }


    private void loadDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
