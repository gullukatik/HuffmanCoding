import java.util.ArrayList;
import java.util.Base64;


public class Main {
    public static void main(String[] args) {
        String test = "ozanata";
        ArrayList<Boolean> code = new ArrayList<>();
        Encode encode = new Encode(test);
        encode.findFrequencies();
        encode.buildTree();
        encode.generateHuffmanCodes(encode.getHuffmanTree(), code);
        encode.compressText();
        byte[] bytes = encode.getCompressedText();
        bytes.toString();
        String s = new String(bytes);
        System.out.println(bytes);
        Decode decode = new Decode(encode.getCompressedText(), encode.getBitSize(), encode.getFrequencies());
        decode.buildTree();
        decode.decompressText();
        System.out.println(decode.getDecompressedText());
        int x = 0;
    }
}
