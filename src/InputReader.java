import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {
  /**
   * Reads a file line by line and returns an ArrayList of the read strings
   * */
  public static List<String> read(String path) throws FileNotFoundException {
    List<String> result = new ArrayList<>();
    File inputDataFile = new File(path);
    Scanner scanner = new Scanner(inputDataFile);

    while (scanner.hasNextLine())
      result.add(scanner.nextLine());

    return result;
  }
}
