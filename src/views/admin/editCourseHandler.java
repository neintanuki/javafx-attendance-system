package views.admin;

import models.Teacher;
import models.TeacherDB;
import models.Validator;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import controllers.CourseController;
import controllers.TeacherController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.Node;

public class editCourseHandler implements Initializable {
    
  @FXML
  private TextField courseTitle;

  @FXML
  private ChoiceBox<Teacher> assignedTeacher;

  @FXML
  private DatePicker yearStart;

  @FXML
  private DatePicker yearEnd;

  @FXML
  private Button btn;

  CourseController courseController = new CourseController();
  Validator validator = new Validator();
  TeacherDB tDb = new TeacherDB();

  // set assigned teacher
  ResultSet rs = tDb.getTeacher();
  ObservableList<Teacher> tList = FXCollections.observableArrayList();
  
  private String id;
  private String teacherId;

  public void updateCourse(ActionEvent evt) {
    boolean hasError = false;

    // remove classes
    courseTitle.getStyleClass().remove("error");
    assignedTeacher.getStyleClass().remove("error");
    yearStart.getStyleClass().remove("error");
    yearEnd.getStyleClass().remove("error");

    // validate course title
    if(!validator.isValidName(courseTitle.getText())) {
      Tooltip hint = new Tooltip();
      hint.setText("Contains illegal characters");
      courseTitle.setTooltip(hint);

      hasError = true;
      courseTitle.getStyleClass().add("error"); 
    }

    if (!hasError) {
      courseController.updateCourse(
        id,
        courseTitle.getText(),
        assignedTeacher.getValue().getId(),
        yearStart.getValue(),
        yearEnd.getValue()
      );

      Stage stage = (Stage) btn.getScene().getWindow();
      stage.close();
    }
  }

  public void setUpdateInfo(String courseTitle, String teacherId, Date yearStart, Date yearEnd, String id) {
    this.id = id; 
    this.courseTitle.setText(courseTitle);
    this.teacherId = teacherId;
    this.yearStart.setValue(yearStart.toLocalDate());
    this.yearEnd.setValue(yearEnd.toLocalDate());

    for (int i = 0; i < tList.size(); i++) {
      if(tList.get(i).getId().equals(this.teacherId)) {
        assignedTeacher.setValue(tList.get(i));
        break;
      }
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

    try {
      while(rs.next()) {
        tList.add(new Teacher(
          rs.getString("id"),
          rs.getString("username"),
          rs.getString("firstName"),
          rs.getString("lastName")
        ));
      }
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }

    assignedTeacher.setItems(tList);
    // assignedTeacher.setValue(tList.get(0));

    assignedTeacher.setConverter(new StringConverter<Teacher>() {
      @Override
      public String toString(Teacher obj) {
        if (obj != null) {
          return String.format("%s %s", obj.getFirstName(), obj.getLastName());
        }

        return null;
      }

      @Override
      public Teacher fromString(String arg) {
        return (Teacher) assignedTeacher.getItems().stream().filter(ap -> { 
          String name = String.format("%s %s", ap.getFirstName(), ap.getLastName());
          return name.equals(arg);
        }).findFirst().orElse(null);
      }
    });

    // set year start limit

    yearStart.setDayCellFactory(d -> 
      new DateCell() {
        @Override
        public void updateItem(LocalDate item, boolean empty) {
          super.updateItem(item, empty);
          setDisable(item.isAfter(LocalDate.now()));
        }
      }
    );

    // set year end limit

    yearEnd.setDayCellFactory(d -> 
    new DateCell() {
      @Override
        public void updateItem(LocalDate item, boolean empty) {
          super.updateItem(item, empty);
          setDisable(item.isBefore(LocalDate.now()));
        }
      }
    );

    // set year start value
    yearStart.setValue(LocalDate.now());
    yearEnd.setValue(LocalDate.now());
    
  }
}
