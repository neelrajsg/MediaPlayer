package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import dao.MediaDAO;

@SuppressWarnings("serial")
@WebServlet("/media")
@MultipartConfig
public class MediaController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the file part
        Part filePart = request.getPart("filepath"); // This retrieves the file input
        String fileName = request.getParameter("filename"); // Get the filename from the input
        String fileType = request.getParameter("filetype"); // Get the file type
        String fileDesc = request.getParameter("filedesc"); // Get the file description
        
        // Path where the file will be saved
        String filePath = "D:/uploads/" + filePart.getSubmittedFileName(); // Use submitted file name

        // Save the file to the server
        filePart.write(filePath); // Write the file to the specified path

        // Save file info to the database
        MediaDAO mediaDAO = new MediaDAO();
        boolean result = mediaDAO.uploadMedia(fileName, fileType, fileDesc, filePath); // Pass all parameters

        // Redirect based on result
        if (result) {
            response.sendRedirect("index.jsp"); // Redirect to success page
        } else {
            response.sendRedirect("error.jsp"); // Redirect to error page if upload fails
        }
    }
}
