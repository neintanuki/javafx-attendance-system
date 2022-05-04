package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.DBConnection;

public class StudentDB extends DBConnection {
  public ResultSet getStudent() {
    Connection conn = super.getConnection();
    ResultSet rs = null;

    try {
      String getStudentStmt = "SELECT * FROM student";

      PreparedStatement pStmt = conn.prepareStatement(getStudentStmt);
  
      rs = pStmt.executeQuery();
      
      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      System.out.println("Tiggeredddd");
    }

    return rs;
  }

  public ResultSet findCourse(String id) {
    Connection conn = super.getConnection();
    ResultSet rs = null;

    try {
      String getCourseStmt = "SELECT courseTitle FROM course WHERE id::text = ?";

      PreparedStatement pStmt = conn.prepareStatement(getCourseStmt);
      pStmt.setString(1, id);
  
      rs = pStmt.executeQuery();
      
      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }

    return rs;
  }  
}
