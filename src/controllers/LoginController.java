package controllers;

import java.sql.SQLException;
import java.util.HashMap;

import controllers.GlobalController;
import javafx.fxml.FXMLLoader;
import models.Login;

public class LoginController extends Login {

  private String[] roles = {"Teacher", "Admin"};
  private String role = "Teacher";
  private static String tempUsername = "";
  private static String tempUserId;
  private WindowManager wm = new WindowManager();

  public boolean login(String username, String password) {

    // store username for later use
    tempUsername = username;

    try {
      HashMap<String, Object> rs = findUser(username, password, role);

      if(!rs.isEmpty()) {
        // proceed to main dashboard
        tempUserId = rs.get("id").toString();

        switch (role.toLowerCase()) {
          case "admin":
            FXMLLoader loader = wm.openNewWindowReturnsLoader("Admin Dashboard", "../views/admin/admin.fxml");

            GlobalController.setLoader(loader);
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

  public static String getTempUsername() {
    return LoginController.tempUsername;
  }

  public static String getTempUserId() {
    return LoginController.tempUserId;
  }

}
