package views.admin;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import models.Teacher;
import models.TeacherDB;

public class TeacherList implements Initializable {
  @FXML
  TableView<Teacher> adminTable;

  @FXML
  private TableColumn<Teacher, String> id; 
  
  @FXML
  private TableColumn<Teacher, String> adminUsername;

  @FXML
  private TableColumn<Teacher, String> firstName;

  @FXML
  private TableColumn<Teacher, String> lastName;

  @FXML
  private TableColumn<Teacher, ButtonBar> actions;

  @FXML
  private Button mainAction;

  TeacherDB db = new TeacherDB();
  ObservableList<Teacher> obList = FXCollections.observableArrayList();
  private WindowManager wm = new WindowManager();

  public void openAddTeacher() {
    wm.openNewWindow("Add Teacher", "/views/admin/addTeacher.fxml");
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub

    ResultSet rs = db.getTeacher();

    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    adminUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    actions.setCellValueFactory(new PropertyValueFactory<>("btnBar"));

    try {
      while(rs.next()) {
        obList.add(new Teacher(
          rs.getString("id"),
          rs.getString("username"),
          rs.getString("firstName"),
          rs.getString("lastName")
        ));

      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    adminTable.setItems(obList);
  }
}