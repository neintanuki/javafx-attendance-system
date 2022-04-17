package views.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AdminHandler {
  
  @FXML
  private AnchorPane mainView;

  public void getAdmin() {
    System.out.println("Working...");

    try {
      // Define root node and show scene
      Parent root = FXMLLoader.load(getClass().getResource("adminList.fxml"));

      mainView.getChildren().removeAll();
      mainView.getChildren().setAll(root);
      AnchorPane.setRightAnchor(root, 0.0);
      AnchorPane.setLeftAnchor(root, 0.0);
      AnchorPane.setTopAnchor(root, 0.0);
      AnchorPane.setBottomAnchor(root, 0.0);



    } catch(Exception err) {
      System.out.println("Something went wrong");
      err.printStackTrace();
    }
  }

}
