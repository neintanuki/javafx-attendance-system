package views.teacher;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

  public void addStudent() {
    boolean hasError = false;

    // removes error class
    firstName.getStyleClass().remove("error");
    lastName.getStyleClass().remove("error");

    // validate firstName
    if(!validator.isValidName(firstName.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Contains illegal characters");
      firstName.setTooltip(hint);

      hasError = true;
      firstName.getStyleClass().add("error"); 
    }

    // validate lastName
    if(!validator.isValidName(lastName.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Contains illegal characters");
      lastName.setTooltip(hint);

      hasError = true;
      lastName.getStyleClass().add("error"); 
    }

    if (!hasError) {
      // add student via controller
      studentController.addStudent(firstName.getText(), lastName.getText(), course.getValue().getId());

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
        arr.add(new Course(
          rs.getString("id"),
          rs.getString("courseTitle"),
          rs.getString("assignedTeacher"),
          rs.getDate("yearStart"),
          rs.getDate("yearEnd")
        ));
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
