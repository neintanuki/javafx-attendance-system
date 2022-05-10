package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.DBConnection;

public class StudentDB extends DBConnection {
  public ResultSet getStudent(String id) {
    Connection conn = super.getConnection();
    ResultSet rs = null;

    try {
      String getStudentStmt = "SELECT * FROM student WHERE id::text = ?";

      PreparedStatement pStmt = conn.prepareStatement(getStudentStmt);

      pStmt.setString(1, id);
  
      rs = pStmt.executeQuery();
      
      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      System.out.println("Tiggeredddd");
    }

    return rs;
  }

  public ResultSet getStudentByCourse(String course) {
    Connection conn = super.getConnection();
    ResultSet rs = null;

    try {
      String getStudentStmt = "SELECT * FROM student WHERE course = ?";

      PreparedStatement pStmt = conn.prepareStatement(getStudentStmt);

      pStmt.setString(1, course);
  
      rs = pStmt.executeQuery();
      
      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      System.out.println("Tiggeredddd");
    }

    return rs;
  }

  public ResultSet getStudentByID(String id) {
    Connection conn = super.getConnection();
    ResultSet rs = null;

    try {
      String getStudentStmt = "SELECT * FROM student WHERE id::text = ?";

      PreparedStatement pStmt = conn.prepareStatement(getStudentStmt);

      pStmt.setString(1, id);
  
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
