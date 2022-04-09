package views.login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class EventHandler implements Initializable{

  @FXML
  private ChoiceBox<String> role;

  private String[] roles = {"Admin", "Teacher"};

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub

    role.getItems().addAll(roles);
    role.setValue(roles[roles.length - 1]);
  }

}
