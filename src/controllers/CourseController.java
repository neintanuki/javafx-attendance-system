package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;

public class CourseController extends DBConnection {

  public void addCourse(String courseTitle, String assignedTeacher, LocalDate yearStart, LocalDate yearEnd) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "INSERT INTO course (courseTitle, assignedTeacher, yearStart, yearEnd) VALUES (?, ?::uuid, ?, ?)"
      );

      pStmt.setString(1, courseTitle);
      pStmt.setString(2, assignedTeacher);
      pStmt.setDate(3, new java.sql.Date(Date.from(yearStart.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
      pStmt.setDate(4, new java.sql.Date(Date.from(yearEnd.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));

      pStmt.executeQuery();

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  public void updateCourse(String id, String title, String teacher, LocalDate yearStart, LocalDate yearEnd) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "UPDATE course SET courseTitle = ?, assignedTeacher = ?::uuid, yearStart = ?, yearEnd = ? WHERE id::text = ?"
      );

      pStmt.setString(1, title);
      pStmt.setString(2, teacher);
      pStmt.setDate(3, Date.valueOf(yearStart));
      pStmt.setDate(4, Date.valueOf(yearEnd));
      pStmt.setString(5, id);

      pStmt.executeQuery();

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  public void deleteAdmin(String id) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "DELETE FROM admin WHERE id::text = ?"
      );

      pStmt.setString(1, id);

      pStmt.executeQuery();

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }
  
}
