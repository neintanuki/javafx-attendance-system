package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import controllers.DBConnection;

public class Login extends DBConnection {
  private Connection conn = super.getConnection();
  private PreparedStatement pStmt;


  protected HashMap<String, Object> findUser(String username, String password, String role) throws SQLException {
    // queries
    String findUserStmt = "SELECT firstName, lastName, username FROM %s WHERE username = ? AND password = crypt(?, password) LIMIT 1;";

    findUserStmt = String.format(findUserStmt, role.toLowerCase());
    
    pStmt = conn.prepareStatement(findUserStmt);

    pStmt.setString(1, username);
    pStmt.setString(2, password);

    System.out.println(pStmt);

    ResultSet rs = pStmt.executeQuery();
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();

    HashMap<String, Object> data = new HashMap<>();
    while(rs.next()) {
      for (int i = 1; i <= columns; i++) {
        data.put(md.getColumnName(i), rs.getObject(i));
      }
    }

    return data;
  }

}
