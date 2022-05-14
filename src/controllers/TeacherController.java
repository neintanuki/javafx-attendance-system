package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherController extends DBConnection {
  public void addTeacher(String firstName, String lastName, String username, String password) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "INSERT INTO teacher (username, password, firstName, lastName) VALUES (?, crypt(?, gen_salt('md5')), ?, ?)"
      );

      pStmt.setString(1, username);
      pStmt.setString(2, password);
      pStmt.setString(3, firstName);
      pStmt.setString(4, lastName);

      pStmt.executeQuery();

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  public void updateTeacher(String firstName, String lastName, String username, String password, String id) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "UPDATE teacher SET username = ?, firstName = ?, lastName = ?, password = crypt(?, gen_salt('md5')) WHERE id::text = ?"
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
      e.printStackTrace();
    }
  }

  public void deleteTeacher(String id) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "DELETE FROM teacher WHERE id::text = ?"
      );
      PreparedStatement pStmtDelete = conn.prepareStatement(
        "DELETE FROM student WHERE teacher::text = ?"
      );

      pStmt.setString(1, id);
      pStmtDelete.setString(1, id);
      
      pStmt.addBatch();
      pStmtDelete.addBatch();

      pStmtDelete.executeBatch();
      pStmt.executeBatch();

      // pStmt.executeQuery();
      // pStmtDelete.executeQuery();

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  public void updateProfile(String username, String password, String id) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "UPDATE teacher SET username = ?, password = crypt(?, gen_salt('md5')) WHERE id::text = ?"
      );

      pStmt.setString(1, username);
      pStmt.setString(2, password);
      pStmt.setString(3, id);

      pStmt.executeQuery();

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }
}
