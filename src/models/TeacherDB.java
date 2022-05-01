package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.DBConnection;

public class TeacherDB extends DBConnection {

  public ResultSet getTeacher() {
    Connection conn = super.getConnection();
    ResultSet rs = null;

    try {
      String getAdminStmt = "SELECT id, username, firstName, lastName FROM teacher";

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
