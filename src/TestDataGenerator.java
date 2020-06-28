import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {
  public static void generate(String name, int length) throws IOException {
    File testDataFile = new File(name);
    FileWriter writer = new FileWriter(testDataFile);
    List<String> testData = new ArrayList<>();
    testData.add(length + System.lineSeparator());
  
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= length; i++) {
      sb.append(i).append(" ");
    }
    testData.add(sb.toString().trim() + System.lineSeparator());
    //reverse
    String[] data = sb.toString().trim().split("\\s+");
    sb = new StringBuilder();
    for (int i = length - 1; i >= 0; i--){
      sb.append(data[i]).append(" ");
    }
    
    testData.add(sb.toString().trim() + System.lineSeparator());
    testData.add("0" + System.lineSeparator());
    testData.add("0");
  
    for (String string : testData) {
      writer.write(string);
    }
    
    writer.close();
  }
}
