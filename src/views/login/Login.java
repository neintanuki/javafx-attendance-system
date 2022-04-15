package views.login;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Login extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    try {
      // Define root node and show scene
      Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
      Scene scene = new Scene(root);

      stage.setTitle("User Login");
      stage.setScene(scene);
      stage.show();
      
    } catch(Exception err) {
      System.out.println("Something went wrong");
      err.printStackTrace();
    }
  }
}