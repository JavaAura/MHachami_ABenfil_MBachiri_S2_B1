
/*
 * Create Database
 */
CREATE DATABASE team_sync;

/*
 * Create Projects table
 */

CREATE TABLE projects (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name VARCHAR(255) UNIQUE,
	description VARCHAR(255),
	start_date DATE,
	end_date DATE,
	team_id INT NULL,
	status ENUM('InPreparation',
				'InProgress',
				'Paused',
				'Completed',
				'Canceled') DEFAULT 'InPreparation',
	FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
);

/*
 * Create Tasks table
 */

CREATE TABLE tasks(
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	title VARCHAR(255) UNIQUE,
	description VARCHAR(255),
	priority ENUM('LOW',
				'MEDIUM',
				'HIGH')	,
	status ENUM('TO_DO',
				'DOING',
				'DONE'),
	creation_date DATE,
	deadline DATE
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
	role ENUM('PROJECTMANAGER',
			'DEVELOPER',
			'DESIGNER')
);

/*
 * Create team_members table
 */

CREATE TABLE team_members (
    team_id INT,
    member_id INT,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    PRIMARY KEY (team_id, member_id)
);
/*
 * Create team_members table
 */

CREATE TABLE member_tasks(
	task_id INT,
	member_id INT,
	FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
	FOREIGN KEY (member_id) REFERENCES members(id)
);


