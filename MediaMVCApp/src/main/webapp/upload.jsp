<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.MediaDAO" %>
<%
    String message = "";
    // No need to handle POST here, let the servlet handle it.
%>
<html>
<head>
    <title>Upload Media</title>
</head>
<body>
    <h1>Upload Media</h1>
    <form action="media" method="post" enctype="multipart/form-data"> <!-- Specify the servlet URL -->
        <label for="filename">File Name:</label><br>
        <input type="text" id="filename" name="filename" required><br><br>
        <label for="filetype">File Type:</label><br>
        <input type="text" id="filetype" name="filetype" required><br><br>
        <label for="filedesc">File Description:</label><br>
        <input type="text" id="filedesc" name="filedesc" required><br><br>
        <label for="filepath">Select File:</label><br>
        <input type="file" id="filepath" name="filepath" required><br><br>
        <input type="submit" value="Upload">
    </form>
    <p><%= message %></p>
</body>
</html>
