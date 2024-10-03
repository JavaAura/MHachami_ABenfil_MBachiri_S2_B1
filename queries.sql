
/*
 * Create Database
 */
CREATE DATABASE team_sync

/*
 * Create Projects table
 */

CREATE TABLE projects (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name VARCHAR(255) UNIQUE,
	description VARCHAR(255),
	start_date DATE,
	end_date DATE,
	status ENUM('InPreparation',
				'InProgress',
				'Paused',
				'Completed',
				'Canceled') DEFAULT 'InPreparation'
);

/*
 * Create Tasks table
 */

CREATE TABLE tasks(
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	title VARCHAR(255) UNIQUE,
	description VARCHAR(255),
	priority ENUM('Low',
				'Medium',
				'High')	,
	status ENUM('ToDo',
				'Doing',
				'Done'),
	creation_date DATE,
	due_date DATE
);

/*
 * Create Teams table
 */

CREATE TABLE teams(
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name VARCHAR(255) UNIQUE
);

/*
 * Create Members table
 */

CREATE TABLE members(
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	email VARCHAR(255) UNIQUE,
	role ENUM('ProjectManager',
			'Developer',
			'Designer')
);

/*
 * Create team_projects table
 */

CREATE TABLE team_projects(
	team_id INT,
	project_id INT,
	FOREIGN KEY (team_id) REFERENCES teams(id),
	FOREIGN KEY (project_id) REFERENCES projects(id)
);

/*
 * Create team_members table
 */

CREATE TABLE team_members(
	team_id INT,
	member_id INT,
	FOREIGN KEY (team_id) REFERENCES teams(id),
	FOREIGN KEY (member_id) REFERENCES members(id)
);

/*
 * Create team_members table
 */

CREATE TABLE member_tasks(
	task_id INT,
	member_id INT,
	FOREIGN KEY (task_id) REFERENCES tasks(id),
	FOREIGN KEY (member_id) REFERENCES members(id)
);

/*
 * Create tasks table
 */

create table if not exists tasks(
    id int primary key auto_increment,
    title varchar(150) not null unique,
    description text not null,
    priority enum('LOW', 'MEDIUM', 'HIGH') default 'LOW',
    status enum('TO_DO', 'DOING', 'DONE') default 'TO_DO',
    creation_date date default now(),
    deadline date default now()
);

