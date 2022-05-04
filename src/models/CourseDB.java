package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.DBConnection;

public class CourseDB extends DBConnection {
  public ResultSet getCourse() {
    Connection conn = super.getConnection();
    ResultSet rs = null;

    try {
      String getCourseStmt = "SELECT * FROM course";

      PreparedStatement pStmt = conn.prepareStatement(getCourseStmt);
  
      rs = pStmt.executeQuery();
      
      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      System.out.println("Tiggeredddd");
    }

    return rs;
  }

  public ResultSet getCourseWithID(String id) {
    Connection conn = super.getConnection();
    ResultSet rs = null;

    try {
      String getCourseStmt = "SELECT * FROM course WHERE assignedTeacher::text = ?";

      PreparedStatement pStmt = conn.prepareStatement(getCourseStmt);
  
      pStmt.setString(1, id);
      rs = pStmt.executeQuery();
      
      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      System.out.println("Tiggeredddd");
    }

    return rs;
  }
}
