package views.admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import models.Admin;
import models.AdminDB;

public class AdminList implements Initializable {
  @FXML
  private TableView<Admin> adminTable;

  @FXML
  private TableColumn<Admin, String> id; 
  
  @FXML
  private TableColumn<Admin, String> adminUsername;

  @FXML
  private TableColumn<Admin, String> firstName;

  @FXML
  private TableColumn<Admin, String> lastName;

  ObservableList<Admin> obList = FXCollections.observableArrayList();
  AdminDB db = new AdminDB();

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub

    ResultSet rs = db.getAdmin();

    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    adminUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
    firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

    try {
      while(rs.next()) {
        obList.add(new Admin(
          rs.getString("id"),
          rs.getString("username"),
          rs.getString("firstName"),
          rs.getString("lastName")
        ));

        System.out.println("sdjk");
        System.out.println(rs.getString("id"));
        System.out.println(rs.getString("username"));
        System.out.println(rs.getString("firstname"));
        System.out.println(rs.getString("lastname"));

      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    adminTable.setItems(obList);
  }
}
