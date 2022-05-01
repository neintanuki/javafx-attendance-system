package views.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import controllers.DBConnection;
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

  private WindowManager wm = new WindowManager();
  private DBConnection db = new DBConnection();

  public void getAdmin() {
    wm.inheritStage(mainView, "/views/admin/adminList.fxml");
  }

  public void getTeacher() {
    wm.inheritStage(mainView, "/views/admin/teacherList.fxml");
  }

  public void getCourse() {
    wm.inheritStage(mainView, "/views/admin/courseList.fxml");
  }

  public void setCount() {
    try {
      Connection conn = db.getConnection();

      Statement adminStmt = conn.createStatement();
      Statement teacherStmt = conn.createStatement();
      Statement courseStmt = conn.createStatement();

      ResultSet admin = adminStmt.executeQuery("SELECT COUNT(*) FROM admin;");
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

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    setCount();
  }

}
