import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
  public static List<String> process(List<String> inputData) {
    List<String> result = new ArrayList<>();
    String previousLine = "";
    //a flag indicating that the current string contains the total amount of the coaches
    boolean isTrainLength = true;

    for (String currentLine : inputData) {
      if ("0".equals(currentLine.trim())) {
        if (previousLine.equals(currentLine)) {
          break;
        }
        result.add(System.lineSeparator());
        isTrainLength = true;
        continue;
      }

      if (!isTrainLength)
        result.add(estimate(DataParser.parse(currentLine)) + System.lineSeparator());
      previousLine = currentLine;
      isTrainLength = false;
    }

    while (System.lineSeparator().equals(result.get(result.size() - 1)))
      result.remove(result.size() - 1);
    result.add(result.remove(result.size() - 1).trim());
    return result;
  }

  private static String estimate(int[] expectedOrder) {
    int totalAmount = expectedOrder.length;
    String result;
    IStack stack = new Stack(totalAmount);
    int inboundCounter = 0;
    int outboundCounter = 0;
    int nextExpected = expectedOrder[0];

    while (inboundCounter < totalAmount || (!stack.isEmpty() && stack.peek() == nextExpected)) {
      if (((!stack.isEmpty()) && stack.peek() == nextExpected)) {
        stack.delete();
        if (outboundCounter < expectedOrder.length - 1)
          nextExpected = expectedOrder[++outboundCounter];
      } else {
        stack.add(inboundCounter++ + 1);
      }
    }

    if (outboundCounter == totalAmount - 1)
      result = "Yes";
    else
      result = "No";

    return result;
  }
}
