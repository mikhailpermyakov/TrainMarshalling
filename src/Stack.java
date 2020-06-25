public class Stack implements IStack {
  private int depth;
  private int[] stack;
  private int top;

  public Stack(int depth) {
    this.depth = depth;
    stack = new int[depth];
    top = -1;
  }

  @Override
  public void add(int element) {
    stack[++top] = element;
  }

  @Override
  public int delete() {
    if (top > -1)
      return stack[top--];
    else
      return -1;
  }

  @Override
  public int peek() {
    if (top > -1)
      return stack[top];
    else
      return -1;
  }

  @Override
  public boolean isEmpty() {
    return (top == -1);
  }

  @Override
  public boolean isFull() {
    return (top == depth - 1);
  }
}
