package views.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import models.Validator;

import java.io.IOException;

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

  @FXML
  private Button btn;

  AdminController adminController = new AdminController();

  public void addAdmin(ActionEvent evt) {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminList.fxml"));
    try {
      fxmlLoader.load();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    AdminList adminList = fxmlLoader.getController();

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

    // is username unique
    if(!super.isUniqUsername(username.getText())) {
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

      // clear table
      // adminList.clearOblist();

      // repopulate table
      // adminList.setTable();

      // close window
      Stage stage = (Stage) btn.getScene().getWindow();
      stage.close();

    }
  }
}
