package views.login;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;

public class Login extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    try {
      // Define root node and show scene
      Group root = new Group();
      Scene scene = new Scene(root, 400, 400);

      stage.setScene(scene);
      stage.show();
      
    } catch(Exception err) {
      System.out.println(err);
    }
  }
}