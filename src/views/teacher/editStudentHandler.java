package views.teacher;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controllers.GlobalController;
import controllers.StudentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.Course;
import models.CourseDB;
import models.Validator;
import javafx.fxml.FXMLLoader;

public class editStudentHandler implements Initializable {
  @FXML
  private TextField firstName;

  @FXML
  private TextField lastName;

  @FXML
  private ChoiceBox<Course> course;

  @FXML
  private Button btn;

  private String id;
  private String courseId;

  Validator validator = new Validator();
  StudentController studentController = new StudentController();

  // set courses
  CourseDB cDb = new CourseDB();
  ResultSet rs = cDb.getCourse();
  ObservableList<Course> cList = FXCollections.observableArrayList();

  public void updateStudent() {
    boolean hasError = false;

    // removes error class
    firstName.getStyleClass().remove("error");
    lastName.getStyleClass().remove("error");
    course.getStyleClass().remove("error");

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

    // validate course
    if(course.getSelectionModel().isEmpty()) {
      Tooltip hint = new Tooltip();
      hint.setText("Please choose a valid course");
      course.setTooltip(hint);

      hasError = true;
      course.getStyleClass().add("error"); 
    }

    if (!hasError) {
      // add student via controller
      studentController.updateStudent(firstName.getText(), lastName.getText(), course.getValue().getId(), this.id);

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

  public void setUpdateInfo(String id, String firstName, String lastName, String courseId) {
    this.id = id;
    this.firstName.setText(firstName);
    this.lastName.setText(lastName);
    this.courseId = courseId;

    for (int i = 0; i < cList.size(); i++) {
      if(cList.get(i).getId().equals(this.courseId)) {
        course.setValue(cList.get(i));
        break;
      }
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    try {
      while(rs.next()) {
        cList.add(new Course(
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
    
    if (cList.size() > 0) {
      course.setItems(cList);
      course.setValue(cList.get(0));
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
