public class DataParser {
  /**
   * Parses a single line to return an array of integers
   * */
  public static int[] parse(String inputLIne) {
    String[] numbers = inputLIne.split("\\s+");
    int[] result = new int[numbers.length];
    if ("0".equals(inputLIne.trim())){
      return new int[]{-1};
    }

    for (int i = 0; i < numbers.length; i++) {
      result[i] = Integer.parseInt(numbers[i].trim());
    }

    return result;
  }
}
