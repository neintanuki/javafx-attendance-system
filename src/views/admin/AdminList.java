package views.admin;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import models.AdminDB;

import controllers.WindowManager;

public class AdminList implements Initializable {
  @FXML
  TableView<Admin> adminTable;

  @FXML
  private TableColumn<Admin, String> id; 
  
  @FXML
  private TableColumn<Admin, String> adminUsername;

  @FXML
  private TableColumn<Admin, String> firstName;

  @FXML
  private TableColumn<Admin, String> lastName;

  @FXML
  private TableColumn<Admin, HBox> actions;

  @FXML
  private Button mainAction;

  ObservableList<Admin> obList = FXCollections.observableArrayList();
  AdminDB db = new AdminDB();
  WindowManager wm = new WindowManager();

  public void openAddAdmin() {
    wm.openNewWindow("Add Administrator", "/views/admin/addAdmin.fxml");
  }

  @FXML
  public void clearOblist() {
    obList.clear();

    System.out.println("Cleared");
  }

  @FXML
  public void setTable() {
    ResultSet rs = db.getAdmin();

    try {
      while(rs.next()) {
        obList.add(new Admin(
          rs.getString("id"),
          rs.getString("username"),
          rs.getString("firstName"),
          rs.getString("lastName")
        ));

      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block+
      e.printStackTrace();
    }

    adminTable.setItems(obList);    
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub

    ResultSet rs = db.getAdmin();

    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    adminUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    actions.setCellValueFactory(new PropertyValueFactory<>("btnBar"));

    try {
      while(rs.next()) {
        obList.add(new Admin(
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
