public class Stack {
  private int depth;
  private int[] stack;
  private int top;

  public Stack(int depth) {
    this.depth = depth;
    stack = new int[depth];
    top = -1;
  }

  /**
   * Adds a new element to the top of the stack
   * */
  public void add(int element) {
    stack[++top] = element;
  }

  /**
   * Removes the top element from the stack. There's no need to modify the stack array object here, decreasing the stack counter is enough
   * */
  public int delete() {
    if (top > -1)
      return stack[top--];
    else
      return -1;
  }
  
  /***
   * Reads the top element of the stack without removing it. Since the stack normally contains only natural numbers, -1 would indicate that the stack is empty
   */
  public int peek() {
    if (top > -1)
      return stack[top];
    else
      return -1;
  }

  /**
   * Returns true if the stack contains no values, otherwise returns false
   * */
  public boolean isEmpty() {
    return (top == -1);
  }

  public boolean isFull() {
    return (top == depth - 1);
  }
}
