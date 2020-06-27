public class Stack {
  private int depth;
  private int[] stack;
  private int top;

  public Stack(int depth) {
    this.depth = depth;
    stack = new int[depth];
    top = -1;
  }

  public void add(int element) {
    stack[++top] = element;
  }

  public int delete() {
    if (top > -1)
      return stack[top--];
    else
      return -1;
  }

  public int peek() {
    if (top > -1)
      return stack[top];
    else
      return -1;
  }

  public boolean isEmpty() {
    return (top == -1);
  }

  public boolean isFull() {
    return (top == depth - 1);
  }
}
