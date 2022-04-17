package views.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import controllers.WindowManager;

public class AdminHandler {
  
  @FXML
  private AnchorPane mainView;

  private WindowManager wm = new WindowManager();

  public void getAdmin() {
    System.out.println("Working...");

    wm.inheritStage(mainView, "/views/admin/adminList.fxml");
  }

}
