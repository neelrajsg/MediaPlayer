package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@SuppressWarnings("serial")
@WebServlet("/media/stream")
public class MediaStreamServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getParameter("filePath");
        File mediaFile = new File(filePath);

        if (mediaFile.exists()) {
            String mimeType = getServletContext().getMimeType(mediaFile.getName());
            response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
            response.setContentLength((int) mediaFile.length());

            try (FileInputStream inStream = new FileInputStream(mediaFile); 
                 OutputStream outStream = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Media file not found.");
        }
    }
}
