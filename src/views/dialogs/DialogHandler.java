package views.dialogs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class DialogHandler {
  private static Stage stage;

  public void enablePrevWindow() {
    stage.getScene().getRoot().setDisable(false);
  }

  public static void setStage(Stage newStage) {
    stage = newStage;
  }

  public void closeThisWindow(ActionEvent evt) {
    ((Stage)(((Button)evt.getSource()).getScene().getWindow())).close();
    enablePrevWindow();
  }

}
