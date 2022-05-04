package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentController extends DBConnection {
  public void addStudent(String firstName, String lastName, String course) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "INSERT INTO student (firstName, lastName, course) VALUES (?, ?, ?::uuid)"
      );

      pStmt.setString(1, firstName);
      pStmt.setString(2, lastName);
      pStmt.setString(3, course);

      pStmt.executeQuery();

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }
}
