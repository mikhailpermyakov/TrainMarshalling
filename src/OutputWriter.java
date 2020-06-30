import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputWriter {
  public static void write(String outputFileName, List<String> outputData) throws IOException {
    File outputFile = new File(outputFileName);
    FileWriter outputFileWriter = new FileWriter(outputFile);
    //if there are line separators at the end of the data set
    while (outputData.size() > 0 && System.lineSeparator().equals(outputData.get(outputData.size() - 1)))
      //remove them
      outputData.remove(outputData.size() - 1);
    
    //and replace a concluding value with the trimmed one, i.e. 'yes\r\n' -> 'yes'
    if (outputData.size() > 0) {
      outputData.add(outputData.remove(outputData.size() - 1).trim());
    }
    
    for (String string : outputData) {
      outputFileWriter.write(string);
    }
    outputFileWriter.close();
  }
}
