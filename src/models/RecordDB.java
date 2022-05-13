package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.DBConnection;

public class RecordDB extends DBConnection {
  public ResultSet getRecord(String course, String teacher, Date date) {
    Connection conn = super.getConnection();
    ResultSet rs = null;

    try {
      String getStudentStmt = "SELECT * FROM attendance WHERE teacher::text = ? AND course::text = ? AND date = ?";

      PreparedStatement pStmt = conn.prepareStatement(getStudentStmt);

      pStmt.setString(1, teacher);
      pStmt.setString(2, course);
      pStmt.setDate(3, date);

      rs = pStmt.executeQuery();
      
      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      System.out.println("Tiggeredddd");
    }

    return rs;
  }
}
