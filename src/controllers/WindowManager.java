package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WindowManager {
  public void openNewWindow(String title, String path) {
    // create dialog when login failed
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      Stage stage = new Stage();
  
      stage.setTitle(title);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void inheritStage(AnchorPane ap, String path) {
    try {
      // Define root node and show scene
      Parent root = FXMLLoader.load(getClass().getResource(path));

      ap.getChildren().removeAll();
      ap.getChildren().setAll(root);
      AnchorPane.setRightAnchor(root, 0.0);
      AnchorPane.setLeftAnchor(root, 0.0);
      AnchorPane.setTopAnchor(root, 0.0);
      AnchorPane.setBottomAnchor(root, 0.0);
  
    } catch (Exception e) {
      //TODO: handle exception
      e.printStackTrace();
    }
  }

}
