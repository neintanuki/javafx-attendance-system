package views.admin;

import models.Validator;
import controllers.GlobalController;
import controllers.TeacherController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.scene.Node;

public class editTeacherHandler extends Validator {
    
  @FXML
  private TextField firstName;

  @FXML
  private TextField lastName;

  @FXML
  private TextField username;

  @FXML
  private PasswordField oldPassword;

  @FXML
  private PasswordField newPassword;

  TeacherController teacherController = new TeacherController();
  private String id;

  public void updateTeacher(ActionEvent evt) {
    boolean hasError = false;

    // removes error class
    firstName.getStyleClass().remove("error");
    lastName.getStyleClass().remove("error");
    username.getStyleClass().remove("error");
    oldPassword.getStyleClass().remove("error");
    newPassword.getStyleClass().remove("error");

    // validate firstName
    if(!super.isValidName(firstName.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Contains illegal characters");
      firstName.setTooltip(hint);

      hasError = true;
      firstName.getStyleClass().add("error"); 
    }

    // validate lastName
    if(!super.isValidName(lastName.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Contains illegal characters");
      lastName.setTooltip(hint);

      hasError = true;
      lastName.getStyleClass().add("error"); 
    }

    // validate username
    if(!super.isValidUsername(username.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Contains illegal characters");
      username.setTooltip(hint);

      hasError = true;
      username.getStyleClass().add("error"); 
    }

    // validate old password
    if(!super.isValidPassword(oldPassword.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Password must at least 7 characters long");
      oldPassword.setTooltip(hint);

      hasError = true;
      oldPassword.getStyleClass().add("error"); 
    }

    // is old password correct
    if(!super.isOldPassword(id, oldPassword.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Incorrect password");
      oldPassword.setTooltip(hint);

      hasError = true;
      oldPassword.getStyleClass().add("error");     
    }

    // validate new password
    if(!super.isValidPassword(newPassword.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Password must at least 7 characters long");
      newPassword.setTooltip(hint);

      hasError = true;
      newPassword.getStyleClass().add("error"); 
    }

    if (!hasError) {
      // update admin via controller
      teacherController.updateTeacher(firstName.getText(), lastName.getText(), username.getText(), newPassword.getText(), id);

      FXMLLoader teacherLoader = GlobalController.getTeacherListLoader();
  
      TeacherList teacherListController = teacherLoader.getController();
  
      teacherListController.clearOblist();
      teacherListController.setTable();

      // close window
      Stage stage = (Stage)((Node)evt.getSource()).getScene().getWindow();
      stage.close();
    }
  }

  public void setUpdateInfo(String firstName, String lastName, String username, String id) {
    this.firstName.setText(firstName);
    this.lastName.setText(lastName);
    this.username.setText(username);
    this.id = id;
  }
}
