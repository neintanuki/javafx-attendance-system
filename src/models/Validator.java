package models;

public class Validator {
  public String sanitize(String s, boolean isUsername) {
    String sanitizedString = s.strip();

    if (isUsername) {
      return sanitizedString.toLowerCase();
    }

    return sanitizedString;
  }

  public boolean isValidUsername(String str) {
    return str.matches("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$") && !str.isBlank();
  }

  public boolean isValidName(String str) {
    return str.matches("^[a-zA-Z\\s]+");
  }

  public boolean isValidPassword(String str) {
    return str.length() > 6 && !str.isBlank();
  }
}
