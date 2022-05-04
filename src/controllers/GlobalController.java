package controllers;

import javafx.fxml.FXMLLoader;

public class GlobalController {
  private static FXMLLoader loader;
  private static FXMLLoader adminListLoader;
  private static FXMLLoader teacherListLoader;
  private static FXMLLoader courseListLoader;
  private static FXMLLoader studentListLoader;

  public static FXMLLoader getLoader() {
    return loader;
  }

  public static FXMLLoader getStudentListLoader() {
    return studentListLoader;
  }

  public static void setStudentListLoader(FXMLLoader studentListLoader) {
    GlobalController.studentListLoader = studentListLoader;
  }

  public static FXMLLoader getTeacherListLoader() {
    return teacherListLoader;
  }

  public static void setTeacherListLoader(FXMLLoader teacherListLoader) {
    GlobalController.teacherListLoader = teacherListLoader;
  }

  public static FXMLLoader getCourseListLoader() {
    return courseListLoader;
  }

  public static void setCourseListLoader(FXMLLoader courseListLoader) {
    GlobalController.courseListLoader = courseListLoader;
  }

  public static FXMLLoader getAdminListLoader() {
    return adminListLoader;
  }

  public static void setAdminListLoader(FXMLLoader adminListLoader) {
    GlobalController.adminListLoader = adminListLoader;
  }

  public static void setLoader(FXMLLoader loader) {
    GlobalController.loader = loader;
  }
}

