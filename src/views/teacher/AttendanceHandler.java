package views.teacher;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import controllers.GlobalController;
import controllers.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import models.Course;
import models.CourseDB;
import models.StudentAttendance;
import models.StudentDB;

public class AttendanceHandler implements Initializable {

  @FXML
  private TableView<StudentAttendance> studentTable;

  @FXML
  private TableColumn<StudentAttendance, String> id; 

  @FXML
  private TableColumn<StudentAttendance, String> firstName;

  @FXML
  private TableColumn<StudentAttendance, String> lastName;

  @FXML
  private TableColumn<StudentAttendance, HBox> actions;

  @FXML
  private Label dateToday;

  @FXML
  private ChoiceBox<Course> course;

  private LocalDate localDate = LocalDate.now();
  private CourseDB courseDB = new CourseDB();
  private StudentDB studentDB = new StudentDB();
  private ObservableList<Course> cList = FXCollections.observableArrayList();
  private ObservableList<StudentAttendance> sList = FXCollections.observableArrayList();
  private FilteredList<StudentAttendance> fList;

  public void changeCourse() {
    fList = new FilteredList<>(sList, i -> i.getCourseId().equals(course.getValue().getId()));
    studentTable.setItems(fList);
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    actions.setCellValueFactory(new PropertyValueFactory<>("btnBar"));

    ResultSet rs = courseDB.getCourse();
    ResultSet rsStudent = studentDB.getStudent();

    try {
      while(rs.next()) {
        if (rs.getString("assignedTeacher").equals(LoginController.getTempUserId())) {
          cList.add(new Course(
            rs.getString("id"),
            rs.getString("courseTitle"),
            rs.getString("assignedTeacher"), 
            rs.getDate("yearStart"),
            rs.getDate("yearEnd")
          ));
        }
      }

      while(rsStudent.next()) {
        sList.add(new StudentAttendance(
          rsStudent.getString("id"),
          rsStudent.getString("firstName"),
          rsStudent.getString("lastName"),
          rsStudent.getString("course")
        ));
      }
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }

    course.setItems(cList);
    
    if (cList.size() > 0) {
      course.setValue(cList.get(0));
      changeCourse();
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

    dateToday.setText(String.format("%s, %d, %d", localDate.getMonth(), localDate.getDayOfMonth(), localDate.getYear()));

  }
  
}
