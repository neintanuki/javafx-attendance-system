package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentController extends DBConnection {
  public void addStudent(String firstName, String lastName, String course, String teacher) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "INSERT INTO student (firstName, lastName, course, teacher) VALUES (?, ?, ?::uuid, ?::uuid)"
      );

      pStmt.setString(1, firstName);
      pStmt.setString(2, lastName);
      pStmt.setString(3, course);
      pStmt.setString(4, teacher);

      pStmt.executeQuery();

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  public void updateStudent(String firstName, String lastName, String course, String id) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "UPDATE student SET firstName = ?, lastName = ?, course = ?::uuid WHERE id::text = ?"
      );

      pStmt.setString(1, firstName);
      pStmt.setString(2, lastName);
      pStmt.setString(3, course);
      pStmt.setString(4, id);

      pStmt.executeQuery();

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  public void deleteStudent(String id) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "DELETE FROM student WHERE id::text = ?"
      );
      PreparedStatement pStmtRecord = conn.prepareStatement(
        "DELETE FROM attendance WHERE student::text = ?"
      );

      pStmt.setString(1, id);
      pStmtRecord.setString(1, id);

      pStmt.addBatch();
      pStmtRecord.addBatch();

      pStmt.executeBatch();
      pStmtRecord.executeBatch();

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }
}
