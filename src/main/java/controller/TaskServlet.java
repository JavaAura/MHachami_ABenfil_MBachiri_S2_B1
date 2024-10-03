package controller;

import entities.Task;
import enums.Priority;
import enums.TaskStatus;
import repository.Impl.TaskRepositoryImpl;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class TaskServlet extends HttpServlet {
    private TaskRepositoryImpl taskRepository;

    public void init() {
        taskRepository = new TaskRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equalsIgnoreCase(action)) {
                request.getRequestDispatcher("views/tasks/add-form.jsp").forward(request, response);
            } else if ("list".equalsIgnoreCase(action)) {
                List<Task> tasks = taskRepository.getAllTasks();
                request.setAttribute("tasks", tasks);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/tasks/task-list.jsp");
                dispatcher.forward(request, response);
            } else if ("edit".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Task task = taskRepository.getTaskById(id);
                if (task == null) {response.sendRedirect(request.getContextPath() + "/error-404.jsp");return;}
                request.setAttribute("task", task);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/tasks/edit-form.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("create".equals(action)) {
                Task task = new Task();
                task.setTitle(request.getParameter("title"));
                task.setDescription(request.getParameter("description"));
                task.setStatus(TaskStatus.valueOf(request.getParameter("status")));
                task.setPriority(Priority.valueOf(request.getParameter("priority")));
                task.setCreationDate(LocalDate.parse(request.getParameter("creation_date")));
                task.setDeadline(LocalDate.parse(request.getParameter("deadline")));
                taskRepository.addTask(task);
                response.sendRedirect("tasks?action=list");
            } else if ("update".equals(action)) {
                Task task = new Task();
                task.setId(Integer.parseInt(request.getParameter("id")));
                task.setTitle(request.getParameter("title"));
                task.setDescription(request.getParameter("description"));
                task.setStatus(TaskStatus.valueOf(request.getParameter("status")));
                task.setPriority(Priority.valueOf(request.getParameter("priority")));
                task.setDeadline(LocalDate.parse(request.getParameter("deadline")));
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