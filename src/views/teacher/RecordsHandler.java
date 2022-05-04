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
  private ChoiceBox<Course> course;

  @FXML
  private DatePicker datePicker;

  private ObservableList<Course> cList = FXCollections.observableArrayList();
  private ObservableList<Record> rList = FXCollections.observableArrayList();
  private FilteredList<Record> fList;

  public void changeDate() {
    fList = new FilteredList<>(rList, i -> i.getDate().equals(Date.valueOf(datePicker.getValue())));
    recordsTable.setItems(fList);
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    RecordDB db = new RecordDB();
    CourseDB courseDB = new CourseDB();
    ResultSet rs = db.getRecord();
    ResultSet rsCourse = courseDB.getCourse();

    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

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

    course.setItems(cList);
    
    if (cList.size() > 0) {
      course.setValue(cList.get(0));
    }

    datePicker.setValue(LocalDate.now());
    changeDate();

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
