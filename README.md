# TeamSync Application

## Overview

TeamSync is a web application built using Java, deployed on Tomcat, and connected to a MySQL database. This application is designed for managing team tasks and resources effectively.

## Figma Design

Check out the design for TeamSync on [Figma](https://www.figma.com/design/YwXTUwAGHlm5Y6uJRuBAwn/Teamsync?node-id=0-1&t=DEkDK80d7DLxqeOx-1).

## Project Structure

(Include details about the structure here)

## Prerequisites

- **Docker**: [Install Docker](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Install Docker Compose](https://docs.docker.com/compose/install/)
- **Java**: Ensure you have Java Development Kit (JDK) installed for building the application.

## Configuration

Before running the application, ensure the following configuration parameters in the `.env` file:

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
   git clone https://github.com/JavaAura/MHachami_ABenfil_MBachiri_S2_B1.git
   cd TeamSync
   ```

2. **Build the Application**:
   ```bash
   mvn clean package
   ```

3. **Run Docker Compose**:
   ```bash
   docker-compose up --build
   ```

Access the application via `http://localhost:8086/teamsync`.

## Gitflow

Our workflow is based on using standards, with branches and tags for versions. We have the following branches:

- `main`: For current developments.
- `feature/xxxx`: For new features related to the app. The branch name must start with the prefix "feature/" and track `origin/main`, with "xxx" representing a significant title, composed of the ticket number and a short brief.
- `fix/xxxx`: Similar to features, but for bugs monitored on develop, UAT, and staging branches. It must track `origin/develop`.
- `hotfix/xxxx`: Similar to the previous branches, but this must track `main` as it addresses urgent bugs found in production.

Working progress must be committed and pushed daily. 

For completed work on `feature/xxxx` or `fix/xxxx`, you must initiate a Merge Request via the GitHub UI, from your branch to `develop`. The title format should be:

- `[FEATURE|FIX][FRONTEND|BACKEND]: Some comments`

If the Merge Request is still in progress, you can add "Draft:" at the beginning of the title.
