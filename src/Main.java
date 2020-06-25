import java.io.IOException;
import java.util.List;

public class Main {
  public static void main(String[] args) throws Exception {
    String inputPath = args[0];
    String outputPath = args[1];

    List<String> inputData = InputReader.read(inputPath);
    ValidationResult validationResult = InputValidator.validate(inputData);

    if (validationResult.isValid()) {
      List<String> processedData = DataProcessor.process(inputData);
      OutputWriter.write(outputPath, processedData);
    } else {
      throw new Exception(validationResult.getErrorMessage());
    }
  }
}
