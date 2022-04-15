package models;

import controllers.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Actions extends Validator {
  DBConnection db = new DBConnection();
  Connection conn = db.getConnection();

  private String tableName = null;

  // sql queries
  private String allSql = "SELECT * FROM ?";
  private String findSql = "SELECT * FROM ? WHERE id = ? LIMIT 1";
  private String findBySql = "SELECT * FROM ? WHERE ? = ?";

  protected ArrayList<Map<String, Object>> all() {
    
    try {
      PreparedStatement stmt = conn.prepareStatement(allSql);

      stmt.setString(1, tableName);

      ResultSet rs = stmt.executeQuery();

      return formatResultSet(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  protected Map<String, Object> find(String id) {
    try {
      PreparedStatement stmt = conn.prepareStatement(findSql);
      stmt.setString(1, tableName);
      stmt.setString(2, id);
      ResultSet rs = stmt.executeQuery();
      ResultSetMetaData md = rs.getMetaData();
      int columns = md.getColumnCount();
      Map<String, Object> obj = new HashMap<>(columns);

      while(rs.next()) {
        for (int i = 1; i <= columns; i++) {
          obj.put(md.getColumnName(i), rs.getObject(i));
        }
      }

      return obj;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return new HashMap<String, Object>();
  }

  protected ArrayList<Map<String, Object>> findBy(String key, String expectedVal) {
    try {
      PreparedStatement stmt = conn.prepareStatement(findBySql);

      stmt.setString(1, tableName);
      stmt.setString(2, key);
      stmt.setString(3, expectedVal);

      ResultSet rs = stmt.executeQuery();

      return formatResultSet(rs);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  protected void insert(String[] properties, String[] values) {

  }

  protected void update() {

  }

  protected void remove() {

  }

  protected ArrayList<Map<String, Object>> formatResultSet(ResultSet rs) {
    ArrayList<Map<String, Object>> list = new ArrayList<>(50);

    try {
      ResultSetMetaData md = rs.getMetaData();
      int columns = md.getColumnCount();

      while(rs.next()) {
        Map<String, Object> row = new HashMap<>(columns);

        for (int i = 1; i <= columns; i++) {

          row.put(md.getColumnName(i), rs.getObject(i));
        }

        list.add(row);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return list;
  }
}
