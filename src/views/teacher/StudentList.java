package views.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.WindowManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import models.Student;

public class StudentList implements Initializable {
  @FXML
  TableView<Student> studentTable;

  @FXML
  private TableColumn<Student, String> id; 

  @FXML
  private TableColumn<Student, String> firstName;

  @FXML
  private TableColumn<Student, String> lastName;

  @FXML
  private TableColumn<Student, String> course;

  @FXML
  private TableColumn<Student, HBox> actions;

  @FXML
  private Button mainAction;

  private WindowManager wm = new WindowManager();

  public void openAddStudent() {
    wm.openNewWindow("Add Student", "/views/teacher/addStudent.fxml");
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    course.setCellValueFactory(new PropertyValueFactory<>("course"));
    actions.setCellValueFactory(new PropertyValueFactory<>("btnBar"));        
  }
}
