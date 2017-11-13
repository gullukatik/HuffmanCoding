import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String test = "ozan ata";
        ArrayList<Boolean> code = new ArrayList<>();
        Encode encode = new Encode(test);
        encode.findFrequencies();
        encode.buildTree();
        encode.generateHuffmanCodes(encode.getHuffmanTree(), code);
        encode.compressText();
        byte[] bytes = encode.getCompressedText();
        String s = new String(bytes);
        for (int i = 0; i < bytes.length; i++)
            System.out.println(bytes[i]);
        Decode decode = new Decode(encode.getCompressedText(), encode.getBitSize(), encode.getFrequencies());
        decode.buildTree();
        decode.decompressText();
        System.out.println(decode.getDecompressedText());
        int x = 0;
    }
}
