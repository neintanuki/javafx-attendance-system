package controllers;

import javafx.fxml.FXMLLoader;

public class GlobalController {
  private static FXMLLoader loader;

  public static FXMLLoader getLoader() {
    return loader;
  }

  public static void setLoader(FXMLLoader loader) {
    GlobalController.loader = loader;
  }
}

