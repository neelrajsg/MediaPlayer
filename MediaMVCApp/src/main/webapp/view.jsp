<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.MediaDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Media" %>

<%
    MediaDAO mediaDAO = new MediaDAO();  
    List<Media> mediaList = mediaDAO.getAllMedia();
%>
<html>
<head>
    <title>View Media</title>
</head>
<body>
    <h1>View Media</h1>
    <form method="post" action="playMedia.jsp">
    <%
        if (mediaList != null && !mediaList.isEmpty()) {
            for (Media media : mediaList) {
    %>
                <input type="checkbox" name="mediaFiles" value="<%= media.getFilepath() %>">
                <input type="hidden" name="mediaTypes" value="<%= media.getFiletype() %>">
                <%= media.getFilename() %> - <%= media.getFiletype() %> <br>
    <%
            }
        } else {
    %>
        <p>No media files available.</p>
    <%
        }
    %>
    <input type="submit" value="Play Selected Media">
</form>
</body>
</html>
