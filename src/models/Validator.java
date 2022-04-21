package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.transform.Result;

import controllers.DBConnection;

public class Validator extends DBConnection {
  public String sanitize(String s, boolean isUsername) {
    String sanitizedString = s.strip();

    if (isUsername) {
      return sanitizedString.toLowerCase();
    }

    return sanitizedString;
  }

  public boolean isValidUsername(String str) {
    return str.matches("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$") && !str.isBlank();
  }

  public boolean isValidName(String str) {
    return str.matches("^[a-zA-Z\\s]+");
  }

  public boolean isValidPassword(String str) {
    return str.length() > 6 && !str.isBlank();
  }

  public boolean isUniqUsername(String str) {
    String findUserStmt = "SELECT username FROM admin WHERE username = ?;";
    boolean isUniq = false;

    try {
      Connection conn = super.getConnection();

      PreparedStatement pStmt = conn.prepareStatement(findUserStmt);

      pStmt.setString(1, str);

      ResultSet rs = pStmt.executeQuery();

      if (!rs.next()) {
        isUniq = true;
      }

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }

    return isUniq;
  }

  public boolean isOldPassword(String id, String password) {
    String findUserStmt = "SELECT * FROM admin WHERE id::text = ? AND password = crypt(?, gen_salt('md5'));";
    boolean isUniq = false;

    try {
      Connection conn = super.getConnection();

      PreparedStatement pStmt = conn.prepareStatement(findUserStmt);

      pStmt.setString(1, id);
      pStmt.setString(2, password);

      ResultSet rs = pStmt.executeQuery();

      if (!rs.next()) {
        isUniq = true;
      }

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }

    return isUniq;
  }
}
