<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task - Edit</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<form method="post" action="tasks?action=update&id=${task.id}">
    <div class="form-group">
        <label for="inputTitle">Title</label>
        <input name="title" type="text" class="form-control" id="inputTitle" value="${task.title}" placeholder="Enter task title" required>
    </div>
    <div class="form-group">
        <label for="inputDescription">Description</label>
        <textarea name="description" class="form-control" id="inputDescription" rows="3" placeholder="Enter task description" required>${task.description}</textarea>
    </div>
    <div class="form-group">
        <label for="inputPriority">Priority</label>
        <select name="priority" id="inputPriority" class="form-control" required>
            <option value="" disabled>Select priority...</option>
            <option value="LOW" ${task.priority == 'LOW' ? 'selected' : ''}>LOW</option>
            <option value="MEDIUM" ${task.priority == 'MEDIUM' ? 'selected' : ''}>MEDIUM</option>
            <option value="HIGH" ${task.priority == 'HIGH' ? 'selected' : ''}>HIGH</option>
        </select>
    </div>
    <div class="form-group">
        <label for="inputStatus">Status</label>
        <select name="status" id="inputStatus" class="form-control" required>
            <option value="" disabled>Select status...</option>
            <option value="TO_DO" ${task.status == 'TO_DO' ? 'selected' : ''}>TO DO</option>
            <option value="DOING" ${task.status == 'DOING' ? 'selected' : ''}>DOING</option>
            <option value="DONE" ${task.status == 'DONE' ? 'selected' : ''}>DONE</option>
        </select>
    </div>
    <div class="form-group">
        <label for="inputDeadline">Deadline</label>
        <input type="date" name="deadline" class="form-control" id="inputDeadline" value="${task.deadline}" required>
    </div>
    <button type="submit" class="btn btn-primary">Save Changes</button>
</form>
</body>
</html>
