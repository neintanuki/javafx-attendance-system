package views.teacher;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import controllers.DBConnection;
import controllers.GlobalController;
import controllers.WindowManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TeacherHandler implements Initializable {
  @FXML
  private AnchorPane mainView;

  @FXML
  private Label studentsCount;

  // @FXML
  // private Label adminsCount;

  // @FXML
  // private Label coursesCount;

  private WindowManager wm = new WindowManager();
  private DBConnection db = new DBConnection();

  public void getStudent() {
    FXMLLoader loader = wm.inheritStageReturnsLoader(mainView, "/views/teacher/studentList.fxml");
    GlobalController.setStudentListLoader(loader);
  }

  public void getAttendance() {
    FXMLLoader loader = wm.inheritStageReturnsLoader(mainView, "/views/teacher/attendance.fxml");
    // GlobalController.setStudentListLoader(loader);
  }

  public void setCount() {
    try {
      Connection conn = db.getConnection();

      Statement adminStmt = conn.createStatement();
      // Statement teacherStmt = conn.createStatement();
      // Statement courseStmt = conn.createStatement();

      ResultSet student = adminStmt.executeQuery("SELECT COUNT(*) FROM student;");
      // ResultSet teacher = teacherStmt.executeQuery("SELECT COUNT(*) FROM teacher;");
      // ResultSet course = courseStmt.executeQuery("SELECT COUNT(*) FROM course;");

      student.next();
      // teacher.next();
      // course.next();

      studentsCount.setText(Integer.toString(student.getInt(1)));
      // teachersCount.setText(Integer.toString(teacher.getInt(1)));
      // coursesCount.setText(Integer.toString(course.getInt(1)));

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    setCount();    
  }
}
