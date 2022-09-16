import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.awt.*;

public class Steganography{

    public static HashMap<String,String> letterToBinary = new HashMap<String,String>();
    public static Set<String> set;

    public static void main(String[] args){
        
        InitializeMap();

        if(args.length == 2){
            Encoder(args[0],args[1]);
        }else{
            if(args.length == 1){
                String ans = Decoder(args[0]);
                if(ans.equals("") || ans.equals(" ")){
                    System.out.println("Sorry, looks like your file was corrupted");
                }else{
                    System.out.println(ans);
                }
            }else{
                System.out.println("\nInvalid Arguments: \n\nTo encode an image, run \n'$ java Steganography \"<your message>\" <image file location>' \n\nTo decode an image, run \n'$ java Steganography <image file location>'\n ");
            }
            
        }
    }

    //initialize hashmap with all accepted values
    public static void InitializeMap(){
        letterToBinary.put("A", "01000001");
        letterToBinary.put("B", "01000010");
        letterToBinary.put("C", "01000011");
        letterToBinary.put("D", "01000100");
        letterToBinary.put("E", "01000101");
        letterToBinary.put("F", "01000110");
        letterToBinary.put("G", "01000111");
        letterToBinary.put("H", "01001000");
        letterToBinary.put("I", "01001001");
        letterToBinary.put("J", "01001010");
        letterToBinary.put("K", "01001011");
        letterToBinary.put("L", "01001100");
        letterToBinary.put("M", "01001101");
        letterToBinary.put("N", "01001110");
        letterToBinary.put("O", "01001111");
        letterToBinary.put("P", "01010000");
        letterToBinary.put("Q", "01010001");
        letterToBinary.put("R", "01010010");
        letterToBinary.put("S", "01010011");
        letterToBinary.put("T", "01010100");
        letterToBinary.put("U", "01010101");
        letterToBinary.put("V", "01010110");
        letterToBinary.put("W", "01010111");
        letterToBinary.put("X", "01011000");
        letterToBinary.put("Y", "01011001");
        letterToBinary.put("Z", "01011010");
        letterToBinary.put("a", "01100001");
        letterToBinary.put("b", "01100010");
        letterToBinary.put("c", "01100011");
        letterToBinary.put("d", "01100100");
        letterToBinary.put("e", "01100101");
        letterToBinary.put("f", "01100110");
        letterToBinary.put("g", "01100111");
        letterToBinary.put("h", "01101000");
        letterToBinary.put("i", "01101001");
        letterToBinary.put("j", "01101010");
        letterToBinary.put("k", "01101011");
        letterToBinary.put("l", "01101100");
        letterToBinary.put("m", "01101101");
        letterToBinary.put("n", "01101110");
        letterToBinary.put("o", "01101111");
        letterToBinary.put("p", "01110000");
        letterToBinary.put("q", "01110001");
        letterToBinary.put("r", "01110010");
        letterToBinary.put("s", "01110011");
        letterToBinary.put("t", "01110100");
        letterToBinary.put("u", "01110101");
        letterToBinary.put("v", "01110110");
        letterToBinary.put("w", "01110111");
        letterToBinary.put("x", "01111000");
        letterToBinary.put("y", "01111001");
        letterToBinary.put("z", "01111010");
        letterToBinary.put(" ", "00100000");
        letterToBinary.put("","00000000");

        //binary to letter map create and reverse letter to binary
        set = letterToBinary.keySet();

    }

    // Encode image 
    public static void Encoder(String msg, String src){
        Picture image = new Picture(src);
        String binary = stringToBinary(msg) + "0000000000000";
        int counter = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        for(int col = 0; col < image.width(); col++){
            for(int row = 0; row < image.height(); row++){
                if(counter < binary.length()){
                    Color color = image.get(col,row);
                    red = color.getRed();
                    green = color.getGreen();
                    blue = color.getBlue();
                    if(counter < binary.length()){
                        if(red % 2 != Integer.parseInt(String.valueOf(binary.charAt(counter))) % 2){
                            red = Math.abs(red - 1);
                        }
                    }
                    counter++;

                    
                    if(counter < binary.length()){
                        if(green % 2 != Integer.parseInt(String.valueOf(binary.charAt(counter))) % 2){
                            green = Math.abs(green - 1);
                        }
                    }

                    counter++;

                    if(counter < binary.length()){
                        if(blue % 2 != Integer.parseInt(String.valueOf(binary.charAt(counter))) % 2){
                            blue = Math.abs(blue - 1);
                        }
                    }
                    counter++;

                    image.set(col,row, new Color(red,green,blue));
                }
            }
        }
        image.save(src);
        System.out.println("Image encryption successful!");
    }

    public static String Decoder(String src){
        Picture image = new Picture(src);
        String ans = "";
        int counter = 0;
        for(int col = 0; col < image.width(); col++){
            for(int row = 0; row < image.height(); row++){
                Color color = image.get(col,row);
                ans += (color.getRed() % 2) + "";
                if(ans.length() > 8){
                    if(ans.substring(ans.length() - 8, ans.length()).equals("00000000")){
                        return binaryToString(ans);
                    }
                }
                ans += (color.getGreen() % 2) + "";
                if(ans.length() > 8){
                    if(ans.substring(ans.length() - 8, ans.length()).equals("00000000")){
                        return binaryToString(ans);
                    }
                }
                ans += (color.getBlue() % 2) + "";
                if(ans.length() > 8){
                    if(ans.substring(ans.length() - 8, ans.length()).equals("00000000")){
                        return binaryToString(ans);
                    }
                }   
            }
        }
        return binaryToString(ans);

    }

    public static String GetKey(String lookingfor){
        for (Map.Entry<String, String> entry : letterToBinary.entrySet()) {
            if(entry.getValue().equals(lookingfor)){
                return entry.getKey();
            }
        }
        return "";
    }

    public static String stringToBinary(String msg){
        String ans = "";
        for(int i = 0; i < msg.length(); i++){
            ans += letterToBinary.get(msg.charAt(i) + "");
        }

        return ans;
    }

    public static String binaryToString(String msg){
        String ans = "";
        String curr = "";
        for(int i = 0; i < msg.length(); i++){
            if(curr.length() == 8){
                ans += GetKey(curr);
                curr = msg.charAt(i) + "";
            }else{
                curr += msg.charAt(i);
            }
        }
        ans += GetKey(curr);
        return ans;
    }

}