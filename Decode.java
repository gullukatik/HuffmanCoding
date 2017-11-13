import java.util.*;

public class Decode {
    private byte[] compressedText;
    private String decompressedText;
    private int bitSize;
    private Node huffmanTree;
    private LinkedHashMap<Character, Integer> frequencies;

    Decode(byte[] compressedText, int bitSize, LinkedHashMap<Character, Integer> frequencies) {
        this.compressedText = compressedText.clone();
        this.bitSize = bitSize;
        this.frequencies = frequencies;
        decompressedText = "";
    }

    public String getDecompressedText() {
        return decompressedText;
    }

    public void buildTree() {
        Node n;
        ArrayList<Node> q = new ArrayList<>();

        for (char l : frequencies.keySet()) {
            n = new Node(l, frequencies.get(l));
            q.add(n);
        }

        Collections.sort(q, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                if (n1.getFrequency() < n2.getFrequency())
                    return -1;
                else if (n1.getFrequency() > n2.getFrequency())
                    return 1;
                else {
                    if (n1.getLetter() < n2.getLetter())
                        return -1;
                    else if (n1.getLetter() > n2.getLetter())
                        return 1;
                    else
                        return 0;
                }
            }
        });

        while (q.size() > 1) {
            Node left = q.get(0);
            q.remove(0);
            Node right = q.get(0);
            q.remove(0);

            n = new Node(left.getLetter(), left.getFrequency() + right.getFrequency(), left, right);
            q.add(n);

            Collections.sort(q, new Comparator<Node>() {
                @Override
                public int compare(Node n1, Node n2) {
                    if (n1.getFrequency() < n2.getFrequency())
                        return -1;
                    else if (n1.getFrequency() > n2.getFrequency())
                        return 1;
                    else {
                        if (n1.getLetter() < n2.getLetter())
                            return -1;
                        else if (n1.getLetter() > n2.getLetter())
                            return 1;
                        else
                            return 0;
                    }
                }
            });
        }

        huffmanTree = q.get(0);
    }

    public void decompressText(){
        boolean[] bits = new boolean[compressedText.length * 8];
        for (int i = 0; i < compressedText.length * 8; i++) {
            if ((compressedText[i / 8] & (1 << (7 - (i % 8)))) > 0)
                bits[i] = true;
        }
        Node n = huffmanTree;
        for(int i = 0; i < bitSize; i++){
            if(!bits[i]){
                if(n.getLeft().getLeft() == null && n.getLeft().getRight() == null){
                    decompressedText += n.getLeft().getLetter();
                    n = huffmanTree;
                }
                else
                    n = n.getLeft();
            }
            else {
                if(n.getRight().getLeft() == null && n.getRight().getRight() == null){
                    decompressedText += n.getRight().getLetter();
                    n = huffmanTree;
                }
                else
                    n = n.getRight();
            }
        }
    }
}
