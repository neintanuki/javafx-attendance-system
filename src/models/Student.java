package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.WindowManager;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import views.teacher.editStudentHandler;

public class Student {
  private String id;
  private String firstName;
  private String lastName;
  private String course;
  private String courseId;
  private HBox btnBar;

  private WindowManager wm = new WindowManager();

  public Student(String id, String firstName, String lastName, String course) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.course = course;

    Button update = new Button();
    Button delete = new Button();

    this.btnBar = new HBox(update, delete);
    this.btnBar.setAlignment(Pos.CENTER);

    // events
    update.setOnAction(event -> {
      FXMLLoader loader = wm.openNewWindowReturnsLoader("Update Student", "../views/teacher/editStudent.fxml");

      editStudentHandler controller = loader.getController();
      controller.setUpdateInfo(this.id, this.firstName, this.lastName, this.course);
    });

    delete.setOnAction(event -> {
      FXMLLoader loader = wm.openNewWindowReturnsLoader("Delete Administrator", "../views/admin/deleteTeacher.fxml");

      // DeleteTeacherHandler controller = loader.getController();
      // controller.setId(this.id);
    });
  }

  public String getId() {
    return id;
  }
  public HBox getBtnBar() {
    return btnBar;
  }
  public void setBtnBar(HBox btnBar) {
    this.btnBar = btnBar;
  }
  public String getCourse() {
    return course;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public void setId(String id) {
    this.id = id;
  }

  public void setCourse(String course) {
    StudentDB db = new StudentDB();
    System.out.println("agdhgadhahad");
    ResultSet rs = db.findCourse(course);

    this.courseId = course;

    try {
      if (rs.next()) {
        this.course = rs.getString("courseTitle");
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
