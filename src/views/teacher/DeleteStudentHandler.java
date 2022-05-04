package views.teacher;

import controllers.GlobalController;
import controllers.StudentController;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;

public class DeleteStudentHandler {
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public void delete(ActionEvent evt) {
    StudentController studentController = new StudentController();

    studentController.deleteStudent(this.id);

    FXMLLoader loader = GlobalController.getLoader();
    FXMLLoader studentLoader = GlobalController.getStudentListLoader();

    TeacherHandler teacherHandler = loader.getController();
    StudentList studentListController = studentLoader.getController();

    teacherHandler.setCount();
    studentListController.clearOblist();
    studentListController.setTable();

    ((Stage)(((Button)evt.getSource()).getScene().getWindow())).close();
  }
}
