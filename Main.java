import java.util.ArrayList;

public class Main {
    public static void main(String [] args){
        String test = "MISSISSIPPI RIVER";
        ArrayList<Boolean> code = new ArrayList<>();
        Encode encode = new Encode(test);
        encode.findFrequencies();
        encode.buildTree();
        encode.generateHuffmanCodes(encode.getHuffmanTree(), code);
        encode.compressText();
    }
}
