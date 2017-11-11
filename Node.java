public class Node {
    private char letter;
    private int frequency;
    private Node left;
    private Node right;

    public Node(char letter, int frequency) {
        this.letter = letter;
        this.frequency = frequency;
        left = null;
        right = null;
    }

    public Node(char letter, int frequency, Node left, Node right) {
        this.letter = letter;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public char getLetter() {
        return letter;
    }

    public int getFrequency() {
        return frequency;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
