import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
  public static List<String> process(List<String> inputData) {
    List<String> result = new ArrayList<>();
    String previousLine = "";
    //a flag indicating that the current string contains the total amount of the coaches
    boolean isTrainLength = true;

    //iterating through the list of the input data
    for (String currentLine : inputData) {
      //end of block
      if ("0".equals(currentLine)) {
        //the following would mean the end of file
        if (previousLine.equals(currentLine)) {
          break;
        }
        result.add(System.lineSeparator());
        //the current line denotes the end of the block meaning on the next iteration the train length is expected
        isTrainLength = true;
        previousLine = currentLine;
        continue;
      }

      if (!isTrainLength) {
        //parse the current line to get an array of the expected order numbers and then pass it to the respective method to get Yes or No in response
        String currentLineEstimation = estimate(DataParser.parse(currentLine.trim())) + System.lineSeparator();
        result.add(currentLineEstimation);
      }
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
        //remove the top value from the stack, which basically equals to adding a coach to the outbound track
        stack.delete();
        //get the next expected number if the expected sequence formation is not yet complete
        if (outboundCounter < expectedOrder.length - 1)
          nextExpected = expectedOrder[++outboundCounter];
      } else {
        //move the next coach from the inbound track to the station by first increasing the inbound counter and then adding the value to the stack
        stack.add(++inboundCounter);
      }
    }

    //all the coaches from the inbound train have been collected at the outbound track and hence successfully rearranged
    if (outboundCounter == totalAmount - 1)
      result = "Yes";
    else
      result = "No";

    return result;
  }
}
