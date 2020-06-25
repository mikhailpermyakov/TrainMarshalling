import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputWriter {
  public static void write(String path, List<String> outputData) throws IOException {
    File outputFile = new File(path);
    FileWriter outputFileWriter = new FileWriter(outputFile);
    for (String string : outputData) {
      outputFileWriter.write(string);
    }
    outputFileWriter.close();
  }
}
