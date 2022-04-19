package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.DBConnection;

public class AdminDB extends DBConnection {
  Connection conn = super.getConnection();

  public ResultSet getAdmin() {
    ResultSet rs = null;

    try {
      String getAdminStmt = "SELECT id, username, firstName, lastName FROM admin";

      PreparedStatement pStmt = conn.prepareStatement(getAdminStmt);
  
      rs = pStmt.executeQuery();
      
      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      System.out.println("Tiggeredddd");
    }

    return rs;
  }

}
