package views.teacher;

import controllers.GlobalController;
import controllers.WindowManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class TeacherHandler {
  @FXML
  private AnchorPane mainView;

  private WindowManager wm = new WindowManager();

  public void getStudent() {
    FXMLLoader loader = wm.inheritStageReturnsLoader(mainView, "/views/teacher/studentList.fxml");
    GlobalController.setStudentListLoader(loader);
  }
}
