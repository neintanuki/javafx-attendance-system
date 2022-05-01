package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

  public void updateAdmin(String firstName, String lastName, String username, String password, String id) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "UPDATE admin SET username = ?, firstName = ?, lastName = ?, password = crypt(?, gen_salt('md5')) WHERE id::text = ?"
      );

      pStmt.setString(1, username);
      pStmt.setString(2, firstName);
      pStmt.setString(3, lastName);
      pStmt.setString(4, password);
      pStmt.setString(5, id);

      pStmt.executeQuery();

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
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
