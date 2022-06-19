package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

      conn.close();
    } catch (SQLException e) {
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
      e.printStackTrace();
    }
  }

  public void updateProfile(String username, String password, String id) {
    System.out.println("fireeedd");
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "UPDATE admin SET username = ?, password = crypt(?, gen_salt('md5')) WHERE id::text = ?"
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
