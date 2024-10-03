<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task - Edit</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<form method="post" action="tasks?action=create">
    <div class="form-group">
        <label for="inputTitle">Title</label>
        <input name="title" type="text" class="form-control" id="inputTitle" placeholder="Enter task title" required>
    </div>
    <div class="form-group">
        <label for="inputDescription">Description</label>
        <textarea name="description" class="form-control" id="inputDescription" rows="3" placeholder="Enter task description" required></textarea>
    </div>
    <div class="form-group">
        <label for="inputPriority">Priority</label>
        <select id="inputPriority" name="priority" class="form-control" required>
            <option value="" disabled selected>Choose priority...</option>
            <option value="LOW">LOW</option>
            <option value="MEDIUM">MEDIUM</option>
            <option value="HIGH">HIGH</option>
        </select>
    </div>
    <div class="form-group">
        <label for="inputStatus">Status</label>
        <select id="inputStatus" name="status" class="form-control" required>
            <option value="" disabled selected>Choose status...</option>
            <option value="TO_DO">TO DO</option>
            <option value="DOING">DOING</option>
            <option value="DONE">DONE</option>
        </select>
    </div>
    <div class="form-group">
        <label for="inputCreationDate">Deadline</label>
        <input type="date" name="creation_date" class="form-control" id="inputCreationDate" required>
    </div>
    <div class="form-group">
        <label for="inputDeadline">Deadline</label>
        <input type="date" name="deadline" class="form-control" id="inputDeadline" required>
    </div>
    <button type="submit" class="btn btn-primary">Save Changes</button>
</form>
</body>
</html>
