package models;

import javafx.scene.layout.HBox;

public class Student {
  private String id;
  private String firstName;
  private String lastName;
  private String course;
  private HBox btnBar;

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
  public void setCourse(String course) {
    this.course = course;
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
}
