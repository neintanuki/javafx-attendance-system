package views.admin;

import views.dialogs.DialogHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import controllers.CourseController;
import controllers.GlobalController;

public class DeleteCourseHandler extends DialogHandler {
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public void delete(ActionEvent evt) {
    CourseController courseController = new CourseController();

    courseController.deleteCourse(this.id);

    FXMLLoader loader = GlobalController.getLoader();
    FXMLLoader courseLoader = GlobalController.getCourseListLoader();

    AdminHandler adminHandler = loader.getController();
    CourseList courseListController = courseLoader.getController();

    adminHandler.setCount();
    courseListController.clearOblist();
    courseListController.setTable();

    ((Stage)(((Button)evt.getSource()).getScene().getWindow())).close();
  }
}
