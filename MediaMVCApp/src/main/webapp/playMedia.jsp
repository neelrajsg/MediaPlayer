<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.MediaDAO" %>
<%@ page import="controller.MediaController" %>

<%
    String[] mediaFiles = request.getParameterValues("mediaFiles");
    String[] mediaTypes = request.getParameterValues("mediaTypes");

    // Debugging output
    // response.getWriter().println("Selected media files: " + java.util.Arrays.toString(mediaFiles));
    // response.getWriter().println("Selected media types: " + java.util.Arrays.toString(mediaTypes));
%>
<html>
<head>
    <title>Play Media</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .media-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 20px;
            margin: 20px;
        }
        .media-item {
            text-align: center;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .media-title {
            font-weight: bold;
            margin-bottom: 10px;
        }
        video, audio {
            width: 100%;
            height: auto;
            max-height: 200px;
        }
    </style>
</head>
<body>
    <h1>Playing Selected Media</h1>
    <div class="media-grid">
        <%
            if (mediaFiles != null && mediaTypes != null && mediaFiles.length > 0 && mediaTypes.length > 0) {
                for (int i = 0; i < mediaFiles.length; i++) {
                    String filePath = mediaFiles[i];
                    String fileType = mediaTypes[i];

                    // Debugging file path
                    System.out.println("Media Path: " + filePath);
        %>
                    <div class="media-item">
                        <div class="media-title">Media Title <%= (i + 1) %></div>
                        <%
                            if (fileType.equalsIgnoreCase("video")) {
                        %>
                            <video controls>
                                <source src="media/stream?filePath=<%= filePath %>" type="video/mp4">
                                <source src="media/stream?filePath=<%= filePath %>" type="video/ogg">
                                Your browser does not support the video tag.
                            </video>
                        <% 
                            } else if (fileType.equalsIgnoreCase("audio")) { 
                        %>
                            <audio controls>
                                <source src="media/stream?filePath=<%= filePath %>" type="audio/mpeg">
                                Your browser does not support the audio tag.
                            </audio>
                        <% 
                            } else { 
                        %>
                            <p>Unsupported media type: <%= fileType %></p>
                        <% 
                            } 
                        %>
                    </div>
        <%
                }
            } else {
        %>
            <p>No media selected or media types missing!</p>
        <%
            }
        %>
    </div>
</body>
</html>
