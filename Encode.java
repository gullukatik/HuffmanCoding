import java.util.*;


public class Encode {
    private String text;
    private Node huffmanTree;
    private ArrayList<Boolean> compressedText;
    private HashMap<Character, Integer> frequencies;
    private Hashtable<Character, ArrayList<Boolean>> codeTable;

    Encode(String t) {
        text = t;
        huffmanTree = null;
        compressedText = new ArrayList<>();
        frequencies = new HashMap<>();
        codeTable = new Hashtable<>();
    }

    public Node getHuffmanTree() {
        return huffmanTree;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Boolean> getCompressedText() {
        return compressedText;
    }

    public void findFrequencies() {
        for (int i = 0; i < text.length(); i++) {
            if (frequencies.containsKey(text.charAt(i))) {
                frequencies.put(text.charAt(i), frequencies.get(text.charAt(i))+1);
            } else {
                frequencies.put(text.charAt(i), 1);
            }
        }
    }

    public void buildTree() {
        Node n;
        PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                if (node.getFrequency() < t1.getFrequency())
                    return -1;
                else if (node.getFrequency() == t1.getFrequency())
                    return 0;
                else
                    return 1;
            }
        });
        for (char l: frequencies.keySet()){
            n = new Node(l, frequencies.get(l));
            q.offer(n);
        }

        while(q.size() > 1) {
            Node right = q.poll();
            Node left = q.poll();

            n = new Node('\0', left.getFrequency()+right.getFrequency(), left, right);
            q.offer(n);
        }

        huffmanTree = q.poll();
    }

    public void generateHuffmanCodes(Node n, ArrayList<Boolean> code) {
        if(n == null)
            return;
        if(n.getLetter() != '\0'){
            ArrayList<Boolean> c = new ArrayList<>(code);
            codeTable.put(n.getLetter(), c);
        }
        else {
            code.add(false);
            generateHuffmanCodes(n.getLeft(), code);
            code.remove(code.size()-1);

            code.add(true);
            generateHuffmanCodes(n.getRight(), code);
            code.remove(code.size()-1);
        }
    }

    public void compressText() {
        for(int i = 0; i < text.length(); i++){
            compressedText.addAll(codeTable.get(text.charAt(i)));
        }
    }
}
