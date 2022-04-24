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
}
