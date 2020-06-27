import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
  public static List<String> process(List<String> inputData) {
    List<String> result = new ArrayList<>();
    String previousLine = "";
    //a flag indicating that the current string contains the total amount of the coaches
    boolean isTrainLength = true;

    for (String currentLine : inputData) {
      if ("0".equals(currentLine)) {
        if (previousLine.equals(currentLine)) {
          break;
        }
        result.add(System.lineSeparator());
        isTrainLength = true;
        previousLine = currentLine;
        continue;
      }

      if (!isTrainLength)
        result.add(estimate(DataParser.parse(currentLine)) + System.lineSeparator());
      previousLine = currentLine;
      isTrainLength = false;
    }
    return result;
  }

  //train marshalling algorithm implementation
  private static String estimate(int[] expectedOrder) {
    int totalAmount = expectedOrder.length;
    String result;
    Stack stack = new Stack(totalAmount);
    //all counters start with '0' so either subtraction or increment is used below to keep consistency when comparing
    int inboundCounter = 0;
    int outboundCounter = 0;
    int nextExpected = expectedOrder[0];

    //check if it is possible to make a move
    while (inboundCounter < totalAmount || (!stack.isEmpty() && stack.peek() == nextExpected)) {
      //sorting yard is not empty and the next expected coach is on the top
      if (((!stack.isEmpty()) && stack.peek() == nextExpected)) {
        stack.delete();
        //get the next expected number if the expected sequence formation is not yet complete
        if (outboundCounter < expectedOrder.length - 1)
          nextExpected = expectedOrder[++outboundCounter];
      } else {
        stack.add(++inboundCounter);
      }
    }

    if (outboundCounter == totalAmount - 1)
      //all the coaches from the inbound train have been successfully rearranged
      result = "Yes";
    else
      result = "No";

    return result;
  }
}
