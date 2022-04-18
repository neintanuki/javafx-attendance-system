package views.admin;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Validator;

import controllers.AdminController;

public class addAdminHandler extends Validator {
  @FXML
  private TextField username;

  @FXML
  private TextField firstName;

  @FXML
  private TextField lastName;

  @FXML
  private PasswordField password;

  @FXML
  private PasswordField repeatPassword;

  private AdminController adminController = new AdminController();


  public void addAdmin() {
    boolean hasError = false;

    // removes error class
    firstName.getStyleClass().remove("error");
    lastName.getStyleClass().remove("error");
    username.getStyleClass().remove("error");
    password.getStyleClass().remove("error");
    repeatPassword.getStyleClass().remove("error");

    // validate firstName
    if(!super.isValidName(firstName.getText())) {
      hasError = true;
      firstName.getStyleClass().add("error"); 
    }

    // validate lastName
    if(!super.isValidName(lastName.getText())) {
      hasError = true;
      lastName.getStyleClass().add("error"); 
    }

    // validate username
    if(!super.isValidUsername(username.getText())) {
      hasError = true;
      username.getStyleClass().add("error"); 
    }

    // validate password
    if(!super.isValidPassword(password.getText())) {
      hasError = true;
      password.getStyleClass().add("error"); 
    }

    // validate repeat password
    if(!password.getText().equals(repeatPassword.getText())) {
      hasError = true;
      repeatPassword.getStyleClass().add("error");
    }

    if (!hasError) {
      // add admin via controller
      adminController.addAdmin(firstName.getText(), lastName.getText(), username.getText(), password.getText());
    }
  }
}
