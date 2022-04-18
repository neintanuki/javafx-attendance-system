package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController extends DBConnection {

  public void addAdmin(String firstName, String lastName, String username, String password) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "INSERT INTO admin (username, password, firstName, lastName) VALUES (?, crypt(?, gen_salt('md5')), ?, ?)"
      );

      pStmt.setString(1, username);
      pStmt.setString(2, password);
      pStmt.setString(3, firstName);
      pStmt.setString(4, lastName);

      pStmt.executeQuery();
    } catch (SQLException e) {
      //TODO: handle exception
    }
  }
  
}
