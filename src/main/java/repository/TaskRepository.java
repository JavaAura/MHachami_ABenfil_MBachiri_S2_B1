package repository;

import entities.Task;
import java.sql.SQLException;
import java.util.List;

public interface TaskRepository {
    void addTask(Task task) throws SQLException;
    List<Task> getAllTasks() throws SQLException;
    Task getTaskById(int id) throws SQLException;
    void updateTask(Task task) throws SQLException;
    void deleteTaskById(int id) throws SQLException;
    void assignToMembers(int id, int[] membersIds) throws SQLException;
}
