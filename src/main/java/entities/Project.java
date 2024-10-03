package entities;

import java.time.LocalDate;

import enums.ProjectStatus;

public class Project {
	private int id;
	private String name;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private ProjectStatus status = ProjectStatus.InPreparation;

	public Project() {
	}

	public Project(String name, String discription, LocalDate startDate, LocalDate endDate, ProjectStatus status) {
		setName(name);
		setDescription(discription);
		setStartDate(startDate);
		setEndDate(endDate);
		setStatus(status);
	}

	public Project(int id) {
		setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}
}
