package views.admin;

import views.dialogs.DialogHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import controllers.CourseController;

public class DeleteCourseHandler extends DialogHandler {
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public void delete(ActionEvent evt) {
    CourseController courseController = new CourseController();

    courseController.deleteCourse(this.id);
    ((Stage)(((Button)evt.getSource()).getScene().getWindow())).close();
  }
}
