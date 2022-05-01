package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import controllers.DBConnection;
import controllers.WindowManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import views.admin.editCourseHandler;
import javafx.fxml.FXMLLoader;

public class Course extends DBConnection {
  private String id;
  private String title;
  private String teacher;
  private Date yearStart;
  private Date yearEnd;
  private HBox btnBar;
  private String teacherId;

  private WindowManager wm = new WindowManager();

  public Course(String id, String title, String teacher, Date yearStart, Date yearEnd) {
    this.setId(id);
    this.setTitle(title);
    this.setTeacher(teacher);
    this.setYearStart(yearStart);
    this.setYearEnd(yearEnd);

    Button update = new Button();
    Button delete = new Button();

    this.btnBar = new HBox(update, delete);
    this.btnBar.setAlignment(Pos.CENTER);

    // events
    update.setOnAction(event -> {
      FXMLLoader loader = wm.openNewWindowReturnsLoader("Update Administrator", "../views/admin/editCourse.fxml");

      editCourseHandler controller = loader.getController();
      controller.setUpdateInfo(this.title, this.teacherId, this.yearStart, this.yearEnd, this.id);

    // delete.setOnAction(event -> {
    //   FXMLLoader loader = wm.openNewWindowReturnsLoader("Delete Administrator", "../views/admin/deleteAdmin.fxml");

    //   DeleteAdminHandler controller = loader.getController();
    //   controller.setId(this.id);
    });
  }

  public HBox getBtnBar() {
    return btnBar;
  }

  public void setBtnBar(HBox btnBar) {
    this.btnBar = btnBar;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getYearEnd() {
    return yearEnd;
  }

  public void setYearEnd(Date yearEnd) {
    this.yearEnd = yearEnd;
  }

  public Date getYearStart() {
    return yearStart;
  }

  public void setYearStart(Date yearStart) {
    this.yearStart = yearStart;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    TeacherDB db = new TeacherDB();
    ResultSet rs = db.findTeacher(teacher);

    this.teacherId = teacher;

    try {
      if (rs.next()) {
        this.teacher = String.format("%s %s", rs.getString("firstName"), rs.getString("lastName"));
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
}
