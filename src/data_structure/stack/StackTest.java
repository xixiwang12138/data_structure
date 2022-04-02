package data_structure.stack;

public class StackTest {
    public static void main(String[] args) {
        LinkStack linkStack = new LinkStack();
        linkStack.push(1);
        linkStack.push(2);
        linkStack.push(3);
        linkStack.push(4);

        linkStack.display();
        linkStack.pop();
        linkStack.display();
    }
}
