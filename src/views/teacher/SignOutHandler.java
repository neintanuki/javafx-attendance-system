package views.teacher;

import java.io.IOException;

import controllers.GlobalController;
import controllers.WindowManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class SignOutHandler extends WindowManager {
  public void signOut(ActionEvent evt) {
    FXMLLoader loader = GlobalController.getLoader();
    TeacherHandler teacherHandler = loader.getController();

    teacherHandler.closeWindow();
    ((Stage)(((Button)evt.getSource()).getScene().getWindow())).close();

    super.openNewWindow("User Login", "/views/login/login.fxml");
  }
}
