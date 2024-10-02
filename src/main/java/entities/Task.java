package entities;

import enums.Priority;
import enums.TaskStatus;

import java.time.LocalDate;

public class Task {

    private int id;
    private String title;
    private String description;
    private Priority priority;
    private TaskStatus status;
    private LocalDate creationDate;
    private LocalDate deadline;


    public Task(String title, String description, Priority priority, TaskStatus status, LocalDate creationDate,LocalDate deadline) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.deadline = deadline;
    }
    public Task() {}

    // getters
    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public String getPriority() {return priority.name();}
    public String getStatus() {return status.name();}
    public LocalDate getCreationDate() {return creationDate;}
    public LocalDate getDeadline() {return deadline;}

    // setters
    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setPriority(Priority priority) {this.priority = priority;}
    public void setStatus(TaskStatus status) {this.status = status;}
    public void setCreationDate(LocalDate creationDate) {this.creationDate = creationDate;}
    public void setDeadline(LocalDate deadline) {this.deadline = deadline;}


    // crud
    public void getAll(){}
    public void create(){}
    public void update(){}
    public void delete(){}
    public void unassignTask(){}
    public void updateStatus(){}

}
