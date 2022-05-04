package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

public class StudentAttendance {
  private String id;
  private String firstName;
  private String lastName;
  private String course;
  private HBox btnBar;
  private String courseId;

  public StudentAttendance(String id, String firstName, String lastName, String course) {
    this.setId(id);
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setCourse(course);

    ToggleButton present = new ToggleButton("Present");
    ToggleButton absent = new ToggleButton("Absent");

    this.btnBar = new HBox(present, absent);
    this.btnBar.setAlignment(Pos.CENTER);
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setCourse(String course) {
    System.out.println(course);
    StudentDB db = new StudentDB();
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
