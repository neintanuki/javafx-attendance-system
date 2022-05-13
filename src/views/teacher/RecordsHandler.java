package views.teacher;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import models.Record;

import controllers.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import models.Course;
import models.CourseDB;
import models.RecordDB;

public class RecordsHandler implements Initializable {

  @FXML
  private TableView<Record> recordsTable;

  @FXML
  private TableColumn<Record, String> id; 

  @FXML
  private TableColumn<Record, String> firstName;

  @FXML
  private TableColumn<Record, String> lastName;
 
  @FXML
  private TableColumn<Record, String> status;

  @FXML
  private ChoiceBox<Course> course;

  @FXML
  private DatePicker datePicker;

  private ObservableList<Course> cList = FXCollections.observableArrayList();
  private ObservableList<Record> rList = FXCollections.observableArrayList();

  public void onChange() {
    RecordDB db = new RecordDB();
    ResultSet rs = db.getRecord(course.getValue().getId(), LoginController.getTempUserId(), Date.valueOf(datePicker.getValue()));

    rList.clear();

    try {
      while(rs.next()) {
        rList.add(new Record(
          rs.getString("id"),
          rs.getString("student"),
          rs.getDate("date"),
          rs.getString("status")
        ));
      }
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    CourseDB courseDB = new CourseDB();
    ResultSet rsCourse = courseDB.getCourse();
    RecordDB db = new RecordDB();

    datePicker.setValue(LocalDate.now());

    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    status.setCellValueFactory(new PropertyValueFactory<>("status"));

    try {
      while(rsCourse.next()) {
        if (rsCourse.getString("assignedTeacher").equals(LoginController.getTempUserId())) {
          cList.add(new Course(
            rsCourse.getString("id"),
            rsCourse.getString("courseTitle"),
            rsCourse.getString("assignedTeacher"), 
            rsCourse.getDate("yearStart"),
            rsCourse.getDate("yearEnd")
          ));
        }
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

    try {

      ResultSet rs = db.getRecord("course.getValue().getId()", LoginController.getTempUserId(), Date.valueOf(datePicker.getValue()));

      while(rs.next()) {
        rList.add(new Record(
          rs.getString("id"),
          rs.getString("student"),
          rs.getDate("date"),
          rs.getString("status")
        ));
      }

    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }

    recordsTable.setItems(rList);
    
  }
  
}
