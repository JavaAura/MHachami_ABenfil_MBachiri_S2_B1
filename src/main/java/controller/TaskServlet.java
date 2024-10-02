package controller;

import entities.Task;
import repository.Impl.TaskRepositoryImpl;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
    private TaskRepositoryImpl taskRepository;

    public void init() {
        taskRepository = new TaskRepositoryImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("list".equals(action)) {
                List<Task> tasks = taskRepository.getAllTasks();
                request.setAttribute("tasks", tasks);
                RequestDispatcher dispatcher = request.getRequestDispatcher("task-list.jsp");
                dispatcher.forward(request, response);
            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Task task = taskRepository.getTaskById(id);
                request.setAttribute("task", task);
                RequestDispatcher dispatcher = request.getRequestDispatcher("task-form.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("create".equals(action)) {
                Task task = new Task();
                task.setTitle(request.getParameter("title"));
                task.setDescription(request.getParameter("description"));
                taskRepository.addTask(task);
                response.sendRedirect("tasks?action=list");
            } else if ("update".equals(action)) {
                Task task = new Task();
                task.setId(Integer.parseInt(request.getParameter("id")));
                task.setTitle(request.getParameter("title"));
                task.setDescription(request.getParameter("description"));
                taskRepository.updateTask(task);
                response.sendRedirect("tasks?action=list");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                taskRepository.deleteTaskById(id);
                response.sendRedirect("tasks?action=list");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public void destroy() {
    }
}