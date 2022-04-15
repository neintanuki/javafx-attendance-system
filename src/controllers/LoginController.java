package controllers;

import java.sql.SQLException;

import models.Login;

public class LoginController extends Login {

  private String[] roles = {"Teacher", "Admin"};
  private String role = "Teacher";
  private String tempUsername = "";

  public void login(String username, String password) {
    System.out.println("Logging in...");

    // store username for later use
    tempUsername = username;

    try {
      System.out.println(findUser(username, password, role));

      if(!findUser(username, password, role).isEmpty()) {
        System.out.println("Found");
      } else {
        System.out.println("Not Found");
      }

    } catch (SQLException e) {
      //TODO: handle exception
      System.out.println(e);
    }

  }

  public String[] getRoles() {
    return roles;
  }

  public String getCurrentRole() {
    return role;
  }

  public void setCurrentRole(String newRole) {
    role = newRole;
  }
}
