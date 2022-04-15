package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DBConnection {
  private static Dotenv dotenv = Dotenv.load();
  private static String url = dotenv.get("DB_URL");
  private static String user = dotenv.get("USERNAME");
  private static String password = dotenv.get("PASSWORD");
  private static Connection conn = null;

  public Connection getConnection() {
    try {
      conn = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      System.out.println(e);
    }

    return conn;
  }
} 
