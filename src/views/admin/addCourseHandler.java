package views.admin;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.CourseController;
import controllers.GlobalController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.Teacher;
import models.TeacherDB;
import models.Validator;

public class addCourseHandler implements Initializable {

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
  
  private TeacherDB tDb = new TeacherDB();
  private Validator validator = new Validator();
  private CourseController courseController = new CourseController();

  public void addCourse(ActionEvent evt) {
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
      courseController.addCourse(
        courseTitle.getText(),
        assignedTeacher.getValue().getId(),
        yearStart.getValue(),
        yearEnd.getValue()
      );

      FXMLLoader loader = GlobalController.getLoader();
      FXMLLoader courseLoader = GlobalController.getCourseListLoader();

      AdminHandler adminHandler = loader.getController();
      CourseList courseListController = courseLoader.getController();

      adminHandler.setCount();
      courseListController.clearOblist();
      courseListController.setTable();

      Stage stage = (Stage) btn.getScene().getWindow();
      stage.close();
    }

  }


  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // set assigned teacher

    ResultSet rs = tDb.getTeacher();
    ObservableList<Teacher> tList = FXCollections.observableArrayList();

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
    assignedTeacher.setValue(tList.get(0));

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