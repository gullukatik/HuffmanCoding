import java.util.*;

import static java.lang.Math.pow;


public class Encode {
    private String text;
    private byte[] compressedText;
    private int bitSize;
    private Node huffmanTree;
    private HashMap<Character, Integer> frequencies;
    private Hashtable<Character, ArrayList<Boolean>> codeTable;

    Encode(String t) {
        text = t;
        huffmanTree = null;
        frequencies = new LinkedHashMap<>();
        codeTable = new Hashtable<>();
    }

    public Node getHuffmanTree() {
        return huffmanTree;
    }

    public String getText() {
        return text;
    }

    public byte[] getCompressedText() {
        return compressedText;
    }

    public int getBitSize() {
        return bitSize;
    }

    public HashMap<Character, Integer> getFrequencies() {
        return frequencies;
    }

    public void findFrequencies() {
        for (int i = 0; i < text.length(); i++) {
            if (frequencies.containsKey(text.charAt(i))) {
                frequencies.put(text.charAt(i), frequencies.get(text.charAt(i)) + 1);
            } else {
                frequencies.put(text.charAt(i), 1);
            }
        }
    }

    public void buildTree() {
        Node n;
        ArrayList<Node> q = new ArrayList<>();

        for (char l : frequencies.keySet()) {
            n = new Node(l, frequencies.get(l));
            q.add(n);
        }

        Collections.sort(q, (n1, n2) -> {
            if (n1.getFrequency() < n2.getFrequency())
                return -1;
            else if (n1.getFrequency() > n2.getFrequency())
                return 1;
            else
                return 0;
        });

        while (q.size() > 1) {
            Node left = q.get(0);
            q.remove(0);
            Node right = q.get(0);
            q.remove(0);

            n = new Node('\0', left.getFrequency() + right.getFrequency(), left, right);
            q.add(n);

            Collections.sort(q, (n1, n2) -> {
                if (n1.getFrequency() < n2.getFrequency())
                    return -1;
                else if (n1.getFrequency() > n2.getFrequency())
                    return 1;
                else
                    return 0;
            });
        }

        huffmanTree = q.get(0);
    }

    public void generateHuffmanCodes(Node n, ArrayList<Boolean> code) {
        if (n == null)
            return;
        if (n.getLetter() != '\0') {
            ArrayList<Boolean> c = new ArrayList<>(code);
            codeTable.put(n.getLetter(), c);
        } else {
            code.add(false);
            generateHuffmanCodes(n.getLeft(), code);
            code.remove(code.size() - 1);

            code.add(true);
            generateHuffmanCodes(n.getRight(), code);
            code.remove(code.size() - 1);
        }
    }

    public void compressText() {
        ArrayList<Boolean> arr = new ArrayList<>();
        arr.add(true);
        for (int i = 0; i < text.length(); i++) {
            arr.addAll(codeTable.get(text.charAt(i)));
        }

        if(arr.size() % 8 != 0)
            compressedText = new byte[arr.size()/8 + 1];
        else
            compressedText = new byte[arr.size() / 8];
        for (int i = 0; i < arr.size() / 8; i++) {
            int m = 0;
            for (int j = 0; j < 8; j++) {
                if (arr.get(i * 8 + j))
                    m = m + (int) pow(2, 7 - j);
            }
            compressedText[i] = (byte) m;
        }
        int m = 0;
        for (int j = 0; j < arr.size() % 8; j++) {
            if (arr.get((arr.size() / 8) * 8 + j))
                m = m + (int) pow(2, 7 - j);
        }
        if(arr.size() % 8 != 0) {
            compressedText[arr.size() / 8] = (byte) m;
        }
        bitSize = arr.size();
    }
}