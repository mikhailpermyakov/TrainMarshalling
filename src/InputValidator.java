import java.util.List;

public class InputValidator {
  public static ValidationResult validate(List<String> inputData) {
    ValidationResult result = new ValidationResult();
    int size = inputData.size();
    String previousLine = "";
    boolean isTrainLength;
    int currentLength;
    
    //ZERO CHECK. Train length <= 1000
    for (String currentLine : inputData) {
      if (currentLine.split("\\s+").length > 1000) {
        result.setValid(false);
        result.setErrorMessage("Train Marshalling Error: maximum train length exceeded");
        return result;
      }
    }
    
    //FIRST CHECK. All values are non-negative integers, no empty strings
    for (String string : inputData) {
      for (String stringNum : string.trim().split("\\s+")) {
        try {
          int number = Integer.parseInt(stringNum.trim());
          if (number < 0) {
            result.setValid(false);
            result.setErrorMessage("Train Marshalling Error: an unexpected negative value occurred");
            return result;
          }
        } catch (NumberFormatException e) {
          result.setValid(false);
          String errorMessage;
          if (stringNum.isEmpty())
            errorMessage = "Train Marshalling Error: unexpected empty string";
          else
            errorMessage = "Train Marshalling Error: unable to parse an integer number.\n" + e.getMessage();
          result.setErrorMessage(errorMessage);
          return result;
        }
      }
    }
    
    //SECOND CHECK. The input data set ends with double "0"s
    if (!(inputData.get(size - 1).equals("0") && inputData.get(size - 2).equals("0"))) {
      result.setValid(false);
      result.setErrorMessage("Train Marshalling Error: Invalid input file structure: concluding '0' expected");
      return result;
    }
    
    //THIRD CHECK. No consecutive "0"s amidst the data set
    for (int i = 0; i < inputData.size(); i++) {
      String currentLine = inputData.get(i);
      
      if ("0".equals(currentLine) && currentLine.equals(previousLine) && i < inputData.size() - 1) {
        result.setValid(false);
        result.setErrorMessage("Train Marshalling Error: End of file marker occurred while the end of the data set hasn't been reached");
        return result;
      }
      previousLine = currentLine;
    }
    
    //FOURTH CHECK. Degenerate case of 1
    isTrainLength = true;
    for (int i = 0; i < inputData.size() - 1; i++) {
      String currentLine = inputData.get(i);
      if (isTrainLength && "1".equals(currentLine)) {
        String nextLine = inputData.get(i + 1);
        if (!"1".equals(nextLine)) {
          result.setValid(false);
          result.setErrorMessage("Train Marshalling Error: Invalid number: expected 1 but found " + nextLine);
          return result;
        }
      }
      
      if ("0".equals(currentLine)) {
        isTrainLength = true;
        continue;
      }
      
      isTrainLength = false;
    }
    
    //FIFTH CHECK. The length of the expected sequence equals to the number in the block header
    isTrainLength = true;
    currentLength = 0;
    previousLine = "";
    for (String currentLine : inputData) {
      if (isTrainLength) {
        try {
          currentLength = Integer.parseInt(currentLine.trim());
        } catch (NumberFormatException e) {
          result.setValid(false);
          result.setErrorMessage("Train Marshalling Error: a train length doesn't look like a valid integer number");
          return result;
        }
      }
      
      //"0" indicates the end of the block
      if ("0".equals(currentLine)) {
        //if the previous line is also "0" it means the end of the data set
        if (previousLine.equals(currentLine)) {
          result.setValid(true);
          break;
        }
        
        //the train length has been determined on the previous line, so a meaningful string is now expected, not a "0"
        if (!(currentLength == 1) && previousLine.equals(String.valueOf(currentLength))) {
          result.setValid(false);
          result.setErrorMessage("Train Marshalling Error: Invalid sequence length: the train is supposed to have at least one coach");
          return result;
        }
        
        //the next line contains the train length since the current one is "0"
        isTrainLength = true;
        previousLine = currentLine;
        continue;
      }
      
      if (!isTrainLength) {
        if (currentLine.trim().split("\\s+").length != currentLength) {
          result.setValid(false);
          result.setErrorMessage("Train Marshalling Error: The length of the expected sequence is inconsistent with the passed amount");
          return result;
        }
      }
      
      previousLine = currentLine;
      isTrainLength = false;
    }
    
    //SIXTH CHECK. Every number in the sequence does not exceed its length
    isTrainLength = true;
    for (String currentLine : inputData) {
      if (isTrainLength) {
        isTrainLength = false;
        continue;
      }
      
      if ("0".equals(currentLine)) {
        isTrainLength = true;
        continue;
      }
      
      //The expected sequence as a string array
      String[] numbersStr = currentLine.trim().split("\\s+");
      int number;
      for (String numberStr : numbersStr) {
        number = Integer.parseInt(numberStr);
        if (number > numbersStr.length) {
          result.setValid(false);
          result.setErrorMessage("Train Marshalling Error: a coach number occurred that exceeds its maximum possible number");
          return result;
        }
        
        if (number == 0) {
          result.setValid(false);
          result.setErrorMessage("Train Marshalling Error: a coach number equals zero");
          return result;
        }
      }
    }
    
    //SEVENTH CHECK. A sequence does not contain repeated values
    isTrainLength = true;
    for (String currentLine : inputData) {
      if (isTrainLength) {
        isTrainLength = false;
        continue;
      }
      
      if ("0".equals(currentLine)) {
        isTrainLength = true;
        continue;
      }
      
      //brute-force search
      String[] numbersStr = currentLine.split("\\s+");
      for (int i = 0; i < numbersStr.length; i++)
        for (int j = i + 1; j < numbersStr.length; j++) {
          if (numbersStr[i].equals(numbersStr[j])) {
            result.setValid(false);
            result.setErrorMessage("Train Marshalling Error: duplicate value occurred");
            return result;
          }
        }
    }
    
    return result;
  }
}
