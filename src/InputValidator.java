import java.util.List;

public class InputValidator {
  public static ValidationResult validate(List<String> inputData) {
    ValidationResult result = new ValidationResult();
    int size = inputData.size();
    String previousLine = "";

    //first check. all values are integers, no empty strings
    for (String string : inputData) {
      for (String stringNum : string.split(" ")) {
        try {
          Integer.parseInt(stringNum.trim());
        } catch (NumberFormatException e) {
          result.setValid(false);
          String errorMessage;
          if (stringNum.isEmpty())
            errorMessage = "Error: unexpected empty string";
          else
            errorMessage = e.getMessage();
          result.setErrorMessage(errorMessage);
          return result;
        }
      }
    }

    //second check. the input data set ends with double "0"s
    if (!(inputData.get(size - 1).equals("0") && inputData.get(size - 2).equals("0"))) {
      result.setValid(false);
      result.setErrorMessage("Invalid input file structure: concluding '0' expected");
      return result;
    }

    //third check if there are consecutive "0"s amidst the data set
    for (int i = 0; i < inputData.size(); i++) {
      String currentLine = inputData.get(i);

      if ("0".equals(currentLine) && currentLine.equals(previousLine) && i < inputData.size() - 1) {
        result.setValid(false);
        result.setErrorMessage("End of file marker occurred while the end of the data set hasn't been reached");
        return result;
      }
      previousLine = currentLine;
    }

    //fourth check. the length of the expected sequence equals to the number in the block header
    boolean isTrainLength = true;
    int currentLength = 0;
    previousLine = "";
    for (String currentLine : inputData) {
      if (isTrainLength) {
        currentLength = Integer.parseInt(currentLine.trim());
      }

      //"0" indicates the end of the block
      if ("0".equals(currentLine.trim())) {
        //if the previous line is also "0" it means the end of the data set
        if (previousLine.equals(currentLine)) {
          break;
        }

        if (currentLength == 0) {
          result.setValid(false);
          result.setErrorMessage("Invalid train length: the train is supposed to have at least one coach");
          return result;
        }

        //next line contains the length or "0"
        isTrainLength = true;
        previousLine = currentLine;
        continue;
      }

      if (!isTrainLength)
        if (currentLine.split(" ").length != currentLength) {
          result.setValid(false);
          result.setErrorMessage("The length of the expected sequence is inconsistent with the passed amount");
          return result;
        }

      previousLine = currentLine;
      isTrainLength = false;
    }

    return result;
  }
}
