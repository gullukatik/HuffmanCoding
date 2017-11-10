public class Node {
    private char letter;
    private int frequency;
    private Node left;
    private Node right;

    public Node(char l, int freq) {
        letter = l;
        frequency = freq;
        left = null;
        right = null;
    }

    public Node(char l, int freq, Node le, Node ri) {
        letter = l;
        frequency = freq;
        left = le;
        right = ri;
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
