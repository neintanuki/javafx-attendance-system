package views.teacher;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controllers.DBConnection;
import controllers.GlobalController;
import controllers.LoginController;
import controllers.StudentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import models.Course;
import models.CourseDB;
import models.Validator;

public class addStudentHandler implements Initializable {
  @FXML
  private TextField firstName;

  @FXML
  private TextField lastName;

  @FXML
  private ChoiceBox<Course> course;

  @FXML
  private Button btn;

  private Validator validator = new Validator();
  private StudentController studentController = new StudentController();

  private DBConnection dbConnection = new DBConnection();

  private boolean checkIfExists(String firstName, String lastName) {
    try {
      Connection conn = dbConnection.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "SELECT * FROM student WHERE firstName = ? AND lastName = ?"
      );

      pStmt.setString(1, firstName);
      pStmt.setString(2, lastName);

      ResultSet rs = pStmt.executeQuery();

      conn.close();

      return rs.next();

    } catch (Exception e) {
      //TODO: handle exception
      e.printStackTrace();
    }

    return true;
  }

  public void addStudent() {
    String firstName = this.firstName.getText().substring(0, 1).toUpperCase() + this.firstName.getText().substring(1).toLowerCase();
    String lastName = this.lastName.getText().substring(0, 1).toUpperCase() + this.lastName.getText().substring(1).toLowerCase();
    boolean hasError = false;

    // removes error class
    this.firstName.getStyleClass().remove("error");
    this.lastName.getStyleClass().remove("error");
    course.getStyleClass().remove("error");

    // validate firstName
    if(!validator.isValidName(firstName)) {
      Tooltip hint = new Tooltip();
      hint.setText("Contains illegal characters");
      this.firstName.setTooltip(hint);

      hasError = true;
      this.firstName.getStyleClass().add("error"); 
    }

    // validate lastName
    if(!validator.isValidName(lastName)) {
      Tooltip hint = new Tooltip();
      hint.setText("Contains illegal characters");
      this.lastName.setTooltip(hint);

      hasError = true;
      this.lastName.getStyleClass().add("error"); 
    }

    // validate course
    if(course.getSelectionModel().isEmpty()) {
      Tooltip hint = new Tooltip();
      hint.setText("Please choose a valid course");
      course.setTooltip(hint);

      hasError = true;
      course.getStyleClass().add("error"); 
    }

    // check if student already exists
    if (checkIfExists(firstName, lastName)) {
      Tooltip hint = new Tooltip();
      hint.setText("Student already exists");
      this.firstName.setTooltip(hint);
      this.lastName.setTooltip(hint);

      hasError = true;
      this.firstName.getStyleClass().add("error");
      this.lastName.getStyleClass().add("error");
    }

    if (!hasError) {
      // add student via controller
      studentController.addStudent(firstName, lastName, course.getValue().getId(), LoginController.getTempUserId());

      FXMLLoader loader = GlobalController.getLoader();
      FXMLLoader studentListLoader = GlobalController.getStudentListLoader();
  
      TeacherHandler teacherHandler = loader.getController();
      StudentList studentListController = studentListLoader.getController();
  
      teacherHandler.setCount();
      studentListController.clearOblist();
      studentListController.setTable();

      // close window
      Stage stage = (Stage) btn.getScene().getWindow();
      stage.close();

    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    CourseDB db = new CourseDB();
    ResultSet rs = db.getCourseWithID(LoginController.getTempUserId());
    ObservableList<Course> arr = FXCollections.observableArrayList();

    try {
      while(rs.next()) {
        if (rs.getString("assignedTeacher").equals(LoginController.getTempUserId())) {
          arr.add(new Course(
            rs.getString("id"),
            rs.getString("courseTitle"),
            rs.getString("assignedTeacher"),
            rs.getDate("yearStart"),
            rs.getDate("yearEnd")
          ));
        }
      }
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }

    course.setItems(arr);

    if (arr.size() > 0) {
      course.setValue(arr.get(0));
    }

    course.setConverter(new StringConverter<Course>() {
      @Override
      public String toString(Course obj) {
        if (obj != null) {
          return obj.getTitle();
        }

        return null;
      }

      @Override
      public Course fromString(String arg) {
        return (Course) course.getItems().stream().filter(ap -> { 
          return ap.getTitle().equals(arg);
        }).findFirst().orElse(null);
      }
    });
  }
}
