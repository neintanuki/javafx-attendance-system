package views.teacher;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import controllers.DBConnection;
import controllers.GlobalController;
import controllers.LoginController;
import controllers.TeacherController;
import controllers.WindowManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import models.Validator;

public class TeacherHandler implements Initializable {
  @FXML
  private AnchorPane mainView;

  @FXML
  private Label studentsCount;

  @FXML
  private Label coursesCount;

  @FXML
  private TextField editUsername;

  @FXML
  private PasswordField editOldPassword;

  @FXML
  private PasswordField editNewPassword;

  private WindowManager wm = new WindowManager();
  private DBConnection db = new DBConnection();

  public void closeWindow() {
    mainView.getScene().getWindow().hide();
  }

  public void getStudent() {
    FXMLLoader loader = wm.inheritStageReturnsLoader(mainView, "/views/teacher/studentList.fxml");
    GlobalController.setStudentListLoader(loader);
  }

  public void getAttendance() {
    FXMLLoader loader = wm.inheritStageReturnsLoader(mainView, "/views/teacher/attendance.fxml");
    // GlobalController.setStudentListLoader(loader);
  }

  public void getRecord() {
    FXMLLoader loader = wm.inheritStageReturnsLoader(mainView, "/views/teacher/records.fxml");
    // GlobalController.setStudentListLoader(loader);
  }

  public void showSignOut() {
    wm.openNewWindow("Sign Out", "/views/teacher/signout.fxml");
  }

  public void setCount() {
    try {
      Connection conn = db.getConnection();

      Statement adminStmt = conn.createStatement();
      Statement courseStmt = conn.createStatement();

      String adminStmtString = String.format("SELECT COUNT(*) FROM student WHERE teacher::text = '%s'", LoginController.getTempUserId());
      String courseStmtString = String.format("SELECT COUNT(*) FROM course WHERE assignedTeacher::text = '%s'", LoginController.getTempUserId());

      ResultSet student = adminStmt.executeQuery(adminStmtString);
      ResultSet course = courseStmt.executeQuery(courseStmtString);

      student.next();
      course.next();

      studentsCount.setText(Integer.toString(student.getInt(1)));
      coursesCount.setText(Integer.toString(course.getInt(1)));

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  public void updateProfile() {
    TeacherController teacherController = new TeacherController();
    Validator validator = new Validator();
    boolean hasError = false;

    // removes error class
    editUsername.getStyleClass().remove("error");
    editOldPassword.getStyleClass().remove("error");
    editNewPassword.getStyleClass().remove("error");

    // validate username
    if(!validator.isValidUsername(editUsername.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Contains illegal characters");
      editUsername.setTooltip(hint);

      hasError = true;
      editUsername.getStyleClass().add("error"); 
    }    

    // validate old password
    if(!validator.isValidPassword(editOldPassword.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Password must at least 7 characters long");
      editOldPassword.setTooltip(hint);

      hasError = true;
      editOldPassword.getStyleClass().add("error"); 
    } 

    // validate old password
    if(!validator.isOldPassword(LoginController.getTempUserId(), editOldPassword.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Invalid Password");
      editNewPassword.setTooltip(hint);

      hasError = true;
      editOldPassword.getStyleClass().add("error"); 
    }

    // validate new password
    if(!validator.isValidPassword(editNewPassword.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Password must at least 7 characters long");
      editNewPassword.setTooltip(hint);

      hasError = true;
      editNewPassword.getStyleClass().add("error"); 
    } 

    if(!hasError) {
      teacherController.updateProfile(editUsername.getText(), editNewPassword.getText(), LoginController.getTempUserId());

      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setHeaderText("Profile Updated");
      alert.show();

      editOldPassword.clear();
      editNewPassword.clear();
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    setCount();    

    editUsername.setText(LoginController.getTempUsername());

    // display welcome message
    wm.inheritStage(mainView, "/views/admin/welcome.fxml");
  }
}
