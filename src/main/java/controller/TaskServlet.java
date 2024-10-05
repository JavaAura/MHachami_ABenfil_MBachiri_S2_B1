package controller;

import entities.Member;
import entities.Task;
import enums.Priority;
import enums.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.Impl.TaskRepositoryImpl;
import repository.Impl.TeamRepositoryImpl;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class TaskServlet extends HttpServlet {
    private TaskRepositoryImpl taskRepository;
    private static final Logger logger = LoggerFactory.getLogger(TaskServlet.class);

    public void init() {
        try {
            taskRepository = new TaskRepositoryImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null || action.isEmpty()) {
                response.sendRedirect("tasks?action=list");
            } else if ("add".equalsIgnoreCase(action)) {
                request.getRequestDispatcher("views/tasks/add-form.jsp").forward(request, response);
            } else if ("list".equalsIgnoreCase(action)) {
                List<Task> tasks = taskRepository.getAllTasks();
                request.setAttribute("tasks", tasks);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/tasks/task-list.jsp");
                dispatcher.forward(request, response);
            } else if ("edit".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Task task = taskRepository.getTaskById(id);
                if (task.getId() == 0) {response.sendRedirect(request.getContextPath() + "/error-404.jsp");return;}
                request.setAttribute("task", task);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/tasks/edit-form.jsp");
                dispatcher.forward(request, response);
            } else if ("assign".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Task task = taskRepository.getTaskById(id);
                if (task.getId() == 0) {response.sendRedirect(request.getContextPath() + "/error-404.jsp");return;}
                TeamRepositoryImpl teamRepository = new TeamRepositoryImpl();
                List<Member> members = teamRepository.getMembers(); // assuming 'getMembers' returns all members in teams
                request.setAttribute("members", members);
                request.setAttribute("task", task);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/tasks/assign-member.jsp");
                dispatcher.forward(request, response);
            } else if ("details".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Task task = taskRepository.getTaskById(id);
                logger.info(String.valueOf(task.getId()));
                if (task.getId() == 0) {response.sendRedirect(request.getContextPath() + "/error-404.jsp");return;}
                request.setAttribute("task", task);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/tasks/details.jsp");
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
            } else if ("assign".equalsIgnoreCase(action)) {
                int taskId = Integer.parseInt(request.getParameter("task_id"));
                String[] memberIdsStr = request.getParameterValues("members_id[]");
                int[] membersIds =  Stream.of(memberIdsStr).mapToInt(Integer::parseInt).toArray();
                taskRepository.assignToMembers(taskId, membersIds);
                response.sendRedirect("tasks?action=list");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public void destroy() {
    }
}