package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceController extends DBConnection {
  public void markPresent(String id, Date date, String course) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement checker = conn.prepareStatement(
        "SELECT * FROM attendance WHERE student::text = ? AND teacher::text = ?"
      );
      PreparedStatement add = conn.prepareStatement(
        "INSERT INTO attendance(student, status, date, teacher, course) VALUES (?::uuid, 'PRESENT', ?, ?::uuid, ?::uuid)"
      );
      PreparedStatement update = conn.prepareStatement(
        "UPDATE attendance SET status = 'PRESENT' WHERE student::text = ? AND date = ? AND teacher::text = ? AND course::text = ?"
      );

      checker.setString(1, id);
      checker.setString(2, LoginController.getTempUserId());

      add.setString(1, id);
      add.setDate(2, date);
      add.setString(3, LoginController.getTempUserId());
      add.setString(4, course);

      update.setString(1, id);
      update.setDate(2, date);
      update.setString(3, LoginController.getTempUserId());
      update.setString(4, course);

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

  public void markAbsent(String id, Date date, String course) {
    try {
      Connection conn = super.getConnection();
      PreparedStatement checker = conn.prepareStatement(
        "SELECT * FROM attendance WHERE student::text = ? AND teacher::text = ?"
      );
      PreparedStatement add = conn.prepareStatement(
        "INSERT INTO attendance(student, status, date, teacher, course) VALUES (?::uuid, 'ABSENT', ?, ?::uuid, ?::uuid)"
      );
      PreparedStatement update = conn.prepareStatement(
        "UPDATE attendance SET status = 'ABSENT' WHERE student::text = ? AND date = ? AND teacher::text = ? AND course::text = ?"
      );

      checker.setString(1, id);
      checker.setString(2, LoginController.getTempUserId());

      add.setString(1, id);
      add.setDate(2, date);
      add.setString(3, LoginController.getTempUserId());
      add.setString(4, course);

      update.setString(1, id);
      update.setDate(2, date);
      update.setString(3, LoginController.getTempUserId());
      update.setString(4, course);

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
