package views.admin;

import views.dialogs.DialogHandler;
import controllers.AdminController;
import javafx.event.ActionEvent;
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
    ((Stage)(((Button)evt.getSource()).getScene().getWindow())).close();
  }
}
