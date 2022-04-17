package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

  public void inheritStage(Stage stage) {
    
  }

}
