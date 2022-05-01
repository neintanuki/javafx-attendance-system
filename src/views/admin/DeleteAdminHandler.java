package views.admin;

import views.dialogs.DialogHandler;
import controllers.AdminController;
import controllers.GlobalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteAdminHandler extends DialogHandler {
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public void delete(ActionEvent evt) {
    AdminController adminController = new AdminController();

    adminController.deleteAdmin(this.id);

    FXMLLoader loader = GlobalController.getLoader();
    FXMLLoader adminLoader = GlobalController.getAdminListLoader();

    AdminHandler adminHandler = loader.getController();
    AdminList adminListController = adminLoader.getController();

    adminHandler.setCount();
    adminListController.clearOblist();
    adminListController.setTable();

    ((Stage)(((Button)evt.getSource()).getScene().getWindow())).close();
  }
}
