package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Record {
  private String id;
  private String student;
  private Date date;
  private String status;
  private String firstName;
  private String lastName;
  
  public Record(String id, String student, Date date, String status) {
    this.id = id;
    this.student = student;
    this.date = date;
    this.status = status;

    StudentDB db = new StudentDB();
    ResultSet rs = db.getStudentByID(student);

    try {
      if (rs.next()) {
        this.setFirstName(rs.getString("firstName"));
        this.setLastName(rs.getString("lastName"));

      }
    } catch (SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

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
  public String getStatus() {
    return status;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getStudent() {
    return student;
  }
  public void setStudent(String student) {
    this.student = student;
  }
  public void setStatus(String status) {
    this.status = status;
  }
}
