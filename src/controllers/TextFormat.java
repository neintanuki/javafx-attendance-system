package controllers;

import models.Validator;
import javafx.scene.control.TextFormatter;

public class TextFormat extends Validator {
  
  public TextFormatter<?> getUsernameFormatter() {
    TextFormatter<?> formatter = new TextFormatter<>(change -> {
      String sanitizedText = super.sanitize(change.getText(), true);

      change.setText(sanitizedText);

      return change;
    });

    return formatter;
  }
}
