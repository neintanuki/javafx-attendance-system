package models;

public class Validator {
  public String sanitize(String s, boolean isUsername) {
    String sanitizedString = s.strip();

    if (isUsername) {
      return sanitizedString.toLowerCase();
    }

    return sanitizedString;
  }
}
