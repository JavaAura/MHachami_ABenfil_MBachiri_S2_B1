# TeamSync Application

## Overview

TeamSync is a web application built using Java, deployed on Tomcat, and connected to a MySQL database. This application is designed for managing team tasks and resources effectively.

## Project Structure



## Prerequisites

- **Docker**: [Install Docker](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Install Docker Compose](https://docs.docker.com/compose/install/)
- **Java**: Ensure you have Java Development Kit (JDK) installed for building the application.

## Configuration

Before running the application, ensure the following configuration parameters in the .env

- **Database Configuration**:
  - `DB_HOST`: Set to `mysql` (service name).
  - `DB_PORT`: Default is `3306`.
  - `DB_NAME`: Set to your desired database name (default: `teamsync`).
  - `DB_USER`: Set your MySQL username (default: `root`).
  - `DB_PASSWORD`: Set your MySQL password (default: `root`).

## Building and Running the Application

To build and run the application using Docker and Docker Compose, follow these steps:

1. **Clone the Repository**:
   ```bash
   git https://github.com/JavaAura/MHachami_ABenfil_MBachiri_S2_B1.git
   cd TeamSync
   
2. **Build the Application**:
   ```bash
   mvn clean package
3. **Build the Application**:
   ```bash
   mvn clean package
4. **Run Docker Compose**:
	```bash
 	 docker-compose up --build
Acces to the applicatio via http://localhost:8086/teamsync
   
 
## Gitflow
Our workflow is based on using standards, obviously branches and tags of versions.
We do have Branches as following:
<ul>
  <li>main : For current developments.</li>
  <li>feature/xxxx : For the new features related to app. the name of branch must starts with prefix "feature/" and tracks origin/main, and "xxx" is the significant title composed of the number of ticket and short brief title.</li>
  <li>fix/xxxx : Same as features, but this for bugs related monitored on develop, uat and staging branch, it must tracks origion/develop also and "xxx" as explained previously.</li>
  <li>hotfix/xxxx : Same as previous, but the difference here is it must tracks main, because it is related to urgent and hot bugs monitored on the production.</li>
</ul>

Working progress must be committed and pushed every day.
If work is done on feature/xxxx or fix/xxxx to develop, you have to initialize Merge Request using Githu UI. From your branch to develop.
The title of the Merge Request must be as this : [FEATURE|FIX][FRONTEND|BACKEND] : Some comments.

If your Merge Request is still in progress you can add Draft: prefix to begining of title of MR.
