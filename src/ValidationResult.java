public class ValidationResult {
  private boolean isValid = false;
  private String errorMessage;

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public boolean isValid() {
    return isValid;
  }
}
