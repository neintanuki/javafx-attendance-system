package views.admin;

import views.dialogs.DialogHandler;
import controllers.AdminController;
import controllers.TeacherController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteTeacherHandler extends DialogHandler {
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public void delete(ActionEvent evt) {
    TeacherController teacherController = new TeacherController();

    teacherController.deleteTeacher(this.id);
    ((Stage)(((Button)evt.getSource()).getScene().getWindow())).close();
  }
}
