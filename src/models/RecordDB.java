package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.DBConnection;

public class RecordDB extends DBConnection {
  public ResultSet getRecord() {
    Connection conn = super.getConnection();
    ResultSet rs = null;

    try {
      String getStudentStmt = "SELECT * FROM attendance";

      PreparedStatement pStmt = conn.prepareStatement(getStudentStmt);
  
      rs = pStmt.executeQuery();
      
      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      System.out.println("Tiggeredddd");
    }

    return rs;
  }
}
