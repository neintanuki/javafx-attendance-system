package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import controllers.AttendanceController;
import controllers.DBConnection;
import controllers.LoginController;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

public class StudentAttendance extends RecordDB {
  private String id;
  private String firstName;
  private String lastName;
  private String course;
  private HBox btnBar;
  private String courseId;
  private AttendanceController aController = new AttendanceController();

  public StudentAttendance(String id, String firstName, String lastName, String course) {
    this.setId(id);
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setCourse(course);

    ToggleButton present = new ToggleButton("Present");
    ToggleButton absent = new ToggleButton("Absent");

    this.btnBar = new HBox(present, absent);
    this.btnBar.setAlignment(Pos.CENTER);

    present.getStyleClass().add("defaultAction");
    absent.getStyleClass().add("defaultAction");

    present.setOnAction(event -> {
      if (present.isSelected()) {
        // mark as present
        present.getStyleClass().add("present");
        present.getStyleClass().removeAll("defaultAction");
        absent.getStyleClass().add("defaultAction");
        absent.getStyleClass().removeAll("absent");

        absent.setSelected(false);
        aController.markPresent(this.id, Date.valueOf(LocalDate.now()), this.getCourseId());
      }
    });

    absent.setOnAction(event -> {
      if (absent.isSelected()) {
        // mark as absent
        absent.getStyleClass().add("absent");
        absent.getStyleClass().removeAll("defaultAction");
        present.getStyleClass().add("defaultAction");
        present.getStyleClass().removeAll("present");

        present.setSelected(false);
        aController.markAbsent(this.id, Date.valueOf(LocalDate.now()), this.getCourseId());
      }
    });

    switch (getStatus(this.firstName, this.lastName, this.course)) {
      case 1:
        present.getStyleClass().add("present");
        break;

      case 2:
        absent.getStyleClass().add("absent");
        break;

      default:
        break;
    }

    try {
      ResultSet rs = super.getRecord(course, LoginController.getTempUserId(), Date.valueOf(LocalDate.now()));

      if(rs.next()) {
        if (rs.getString("status").equals("PRESENT")) {
          present.getStyleClass().add("present");
        } else if(rs.getString("status").equals("ABSENT")) {
          absent.getStyleClass().add("absent");
        }
      }
    } catch (SQLException e) {
      //TODO: handle exception
      e.printStackTrace();
    }

  }

  private int getStatus(String firstName, String lastName, String course) {
    try {
      DBConnection db = new DBConnection();
      Connection conn = db.getConnection();
      PreparedStatement pStmt = conn.prepareStatement(
        "SELECT * FROM attendance WHERE firstName = ? AND lastName = ? AND course = ?"
      );

      pStmt.setString(1, firstName);
      pStmt.setString(2, lastName);
      pStmt.setString(3, course);

      ResultSet rs = pStmt.executeQuery();

      rs.next();
      
      switch (rs.getString("status")) {
        case "PRESENT":
          return 1;

        case "ABSENT":
          return 2;

        default:
          break;
      }

      conn.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return 0;

  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
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

    this.setCourseId(course);

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
