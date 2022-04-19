package controllers;

import java.sql.SQLException;

import models.Login;

public class LoginController extends Login {

  private String[] roles = {"Teacher", "Admin"};
  private String role = "Teacher";
  private String tempUsername = "";
  private WindowManager wm = new WindowManager();


  public boolean login(String username, String password) {

    // store username for later use
    tempUsername = username;

    try {
      if(!findUser(username, password, role).isEmpty()) {
        // proceed to main dashboard

        switch (role.toLowerCase()) {
          case "admin":
            wm.openNewWindow("Admin Dashboard", "../views/admin/admin.fxml");
            break;
        }

        return true;
      } else {
        wm.openNewWindow("Access Denied", "../views/dialogs/forbidden.fxml");
        System.out.println("Not Found");
      }

    } catch (SQLException e) {
      //TODO: handle exception
      wm.openNewWindow("Something went wrong", "../views/dialogs/error.fxml");
      e.printStackTrace();      
    }

    return false;

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
