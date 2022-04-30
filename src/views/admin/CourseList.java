package views.admin;

import controllers.WindowManager;

public class CourseList {
  private WindowManager wm = new WindowManager();

  public void openAddCourse() {
    wm.openNewWindow("Add Course", "/views/admin/addCourse.fxml");
  }
}
