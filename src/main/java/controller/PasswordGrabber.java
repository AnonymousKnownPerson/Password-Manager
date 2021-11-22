package controller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordGrabber {
    public static String PasswordGetter() throws java.io.IOException{
        Path fileName = Path.of("password.txt");
        String content = Files.readString(fileName, StandardCharsets.US_ASCII);
        Base64.Decoder decoder = Base64.getMimeDecoder();
        return new String(decoder.decode(content));
    }
    public static String PasswordSetter(String passwordToHash){
        Base64.Encoder encoder = Base64.getMimeEncoder();
        String message = passwordToHash;
        String eStr = encoder.encodeToString(message.getBytes());
        return eStr;
    }
    public static void PutToFile(String password, String nameOfTheFile){
        File file = new File(nameOfTheFile+".txt");
        String data = password;
        try(FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            //convert string to byte array
            byte[] bytes = data.getBytes();
            //write byte array to file
            bos.write(bytes);
            bos.close();
            fos.close();
            System.out.print("Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void CreateFile(String nameOfTheFile){
        try {
            File myObj = new File(nameOfTheFile+".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static boolean PasswordChecker(String password1,String password2){
        if(password1==password2) return true;
        else return false;
    }

}
