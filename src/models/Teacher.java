package models;

import javafx.fxml.FXMLLoader;
import controllers.TeacherController;
import controllers.WindowManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import views.admin.editTeacherHandler;

public class Teacher {
  private String id;
  private String firstName;
  private String lastName;
  private String username;
  private HBox btnBar;
  private WindowManager wm = new WindowManager();
  private TeacherController teacherController = new TeacherController();

  public Teacher(String id, String username, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    
    Button update = new Button();
    Button delete = new Button();

    this.btnBar = new HBox(update, delete);
    this.btnBar.setAlignment(Pos.CENTER);

    // events
    update.setOnAction(event -> {
      FXMLLoader loader = wm.openNewWindowReturnsLoader("Update Administrator", "../views/admin/editTeacher.fxml");

      editTeacherHandler controller = loader.getController();
      controller.setUpdateInfo(this.firstName, this.lastName, this.username, this.id);
    });

    // delete.setOnAction(event -> {
    //   FXMLLoader loader = wm.openNewWindowReturnsLoader("Delete Administrator", "../views/admin/deleteAdmin.fxml");

    //   DeleteAdminHandler controller = loader.getController();
    //   controller.setId(this.id);
    // });
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public HBox getBtnBar() {
    return btnBar;
  }

  public void setUsername(HBox btnBar) {
    this.btnBar = btnBar;
  }
}