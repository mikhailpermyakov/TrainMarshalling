import java.util.List;

public class Main {
  public static void main(String[] args) throws Exception {
    String inputFile = args[0];
    String outputFile = inputFile + "_result";

    List<String> inputData = InputReader.read(inputFile);
    ValidationResult validationResult = InputValidator.validate(inputData);

    if (validationResult.isValid()) {
      List<String> processedData = DataProcessor.process(inputData);
      OutputWriter.write(outputFile, processedData);
    } else {
      throw new Exception(validationResult.getErrorMessage());
    }
  }
}
