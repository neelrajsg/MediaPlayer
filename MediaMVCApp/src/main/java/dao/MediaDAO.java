package dao;
import model.Media;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaDAO {

    private final String URL = "jdbc:mysql://localhost:3307/mediaDB";
    private final String USER = "root";
    private final String PASSWORD = "root";

    public boolean uploadMedia(String filename, String filetype, String filedesc, String filepath) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO media (filename, filetype, filedesc, filepath) VALUES (?, ?, ?, ?)")) {
                
                pstmt.setString(1, filename);
                pstmt.setString(2, filetype);
                pstmt.setString(3, filedesc);
                pstmt.setString(4, filepath);
                return pstmt.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    public List<Media> getAllMedia() {
        List<Media> mediaList = new ArrayList<>();
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM media")) {

                while (rs.next()) {
                    Media media = new Media();

                    // Populate the Media object with data from the ResultSet
                    media.setFilename(rs.getString("filename"));
                    media.setFiletype(rs.getString("filetype"));
                    media.setFiledesc(rs.getString("filedesc"));
                    media.setFilepath(rs.getString("filepath"));

                    // Add the populated Media object to the mediaList
                    mediaList.add(media);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Error occurred.");
            e.printStackTrace();
        }
        return mediaList;
    }
}

