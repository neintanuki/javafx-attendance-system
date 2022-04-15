package views.login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import controllers.TextFormat;
import controllers.LoginController;

public class EventHandler implements Initializable {

  @FXML
  private ChoiceBox<String> role;

  @FXML
  private Button loginBtn;

  @FXML
  private TextField username;

  @FXML
  private PasswordField password;

  private TextFormat tFormat = new TextFormat();
  private LoginController lController = new LoginController();

  // Events
  public void onLoginBtnPressed() {
    lController.setCurrentRole(role.getValue());
    lController.login(username.getText(), password.getText());
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub

    role.getItems().addAll(lController.getRoles());
    role.setValue(lController.getCurrentRole());

    username.setTextFormatter(tFormat.getUsernameFormatter());
  }

}
