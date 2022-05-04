package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceController extends DBConnection {
  public void markPresent(String id, Date date) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement checker = conn.prepareStatement(
        "SELECT * FROM attendance WHERE student::text = ?"
      );
      PreparedStatement add = conn.prepareStatement(
        "INSERT INTO attendance(student, status, date) VALUES (?::uuid, 'PRESENT', ?)"
      );
      PreparedStatement update = conn.prepareStatement(
        "UPDATE attendance SET status = 'PRESENT' WHERE student::text = ?"
      );

      checker.setString(1, id);

      add.setString(1, id);
      add.setDate(2, date);

      update.setString(1, id);

      ResultSet rsChecker = checker.executeQuery();

      if (rsChecker.next()) {
        update.executeQuery();
      } else {
        add.executeQuery();
      }

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  public void markAbsent(String id, Date date) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement checker = conn.prepareStatement(
        "SELECT * FROM attendance WHERE student::text = ?"
      );
      PreparedStatement add = conn.prepareStatement(
        "INSERT INTO attendance(student, status, date) VALUES (?::uuid, ?, ?)"
      );
      PreparedStatement update = conn.prepareStatement(
        "UPDATE attendance SET status = ? WHERE student::text = ?"
      );

      checker.setString(1, id);

      add.setString(1, id);
      add.setString(2, "ABSENT");
      add.setDate(3, date);

      update.setString(1, "ABSENT");
      update.setString(2, id);

      ResultSet rsChecker = checker.executeQuery();

      if (rsChecker.next()) {
        System.out.println("Updated");
        update.executeQuery();
      } else {
        add.executeQuery();
      }

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  public void reset(String id) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement pStmt= conn.prepareStatement(
        "DELETE FROM attendance WHERE id::text = ?"
      );

      pStmt.setString(1, id);

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }
}
