package views.admin;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import controllers.WindowManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import models.Admin;
import models.Course;
import models.CourseDB;
import models.Teacher;
import models.TeacherDB;

public class CourseList implements Initializable {
  @FXML
  TableView<Course> courseTable;

  @FXML
  private TableColumn<Course, String> id; 

  @FXML
  private TableColumn<Course, String> courseTitle; 

  @FXML
  private TableColumn<Course, String> assignedTeacher; 
  
  @FXML
  private TableColumn<Course, Date> yearStart;

  @FXML
  private TableColumn<Course, Date> yearEnd;

  @FXML
  private TableColumn<Course, HBox> actions;

  @FXML
  private Button mainAction;

  CourseDB db = new CourseDB();
  ObservableList<Course> obList = FXCollections.observableArrayList();
  private WindowManager wm = new WindowManager();

  @FXML
  public void clearOblist() {
    obList.clear();

    System.out.println("Cleared");
  }

  @FXML
  public void setTable() {
    ResultSet rs = db.getCourse();

    try {
      while(rs.next()) {
        obList.add(new Course(
          rs.getString("id"),
          rs.getString("courseTitle"),
          rs.getString("assignedTeacher"),
          rs.getDate("yearStart"),
          rs.getDate("yearEnd")
        ));

      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    courseTable.setItems(obList);    
  }

  public void openAddCourse() {
    wm.openNewWindow("Add Course", "/views/admin/addCourse.fxml");
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    courseTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    assignedTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
    yearStart.setCellValueFactory(new PropertyValueFactory<>("yearStart"));
    yearEnd.setCellValueFactory(new PropertyValueFactory<>("yearEnd"));
    actions.setCellValueFactory(new PropertyValueFactory<>("btnBar"));

    setTable();
  }
}