package views.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Validator;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


import controllers.AdminController;
import controllers.DBConnection;
import controllers.GlobalController;
import controllers.LoginController;
import controllers.WindowManager;

public class AdminHandler implements Initializable {
  
  @FXML
  private AnchorPane mainView;

  @FXML
  private Label teachersCount;

  @FXML
  private Label adminsCount;

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

  public void getAdmin() {
    FXMLLoader loader = wm.inheritStageReturnsLoader(mainView, "/views/admin/adminList.fxml");
    GlobalController.setAdminListLoader(loader);
  }

  public void getTeacher() {
    FXMLLoader loader = wm.inheritStageReturnsLoader(mainView, "/views/admin/teacherList.fxml");
    GlobalController.setTeacherListLoader(loader);
  }

  public void getCourse() {
    FXMLLoader loader = wm.inheritStageReturnsLoader(mainView, "/views/admin/courseList.fxml");
    GlobalController.setCourseListLoader(loader);
  }

  public void showSignOut() {
    wm.openNewWindow("Sign Out", "/views/admin/signout.fxml");
  }

  public void setCount() {
    try {
      Connection conn = db.getConnection();

      PreparedStatement adminStmt = conn.prepareStatement("SELECT COUNT(*) FROM admin WHERE id::text != ?");
      Statement teacherStmt = conn.createStatement();
      Statement courseStmt = conn.createStatement();

      adminStmt.setString(1, LoginController.getTempUserId());

      ResultSet admin = adminStmt.executeQuery();
      ResultSet teacher = teacherStmt.executeQuery("SELECT COUNT(*) FROM teacher;");
      ResultSet course = courseStmt.executeQuery("SELECT COUNT(*) FROM course;");

      admin.next();
      teacher.next();
      course.next();

      adminsCount.setText(Integer.toString(admin.getInt(1)));
      teachersCount.setText(Integer.toString(teacher.getInt(1)));
      coursesCount.setText(Integer.toString(course.getInt(1)));

      System.out.println(admin.getInt(1));

      conn.close();
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  public void updateProfile() {
    AdminController adminController = new AdminController();
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
      adminController.updateProfile(editUsername.getText(), editNewPassword.getText(), LoginController.getTempUserId());

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
