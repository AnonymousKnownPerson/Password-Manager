package controller;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.*;
import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordGrabber {
    public static String decrypt(String strToDecrypt) {
        String SK=MainModel.getSECRET_KEY();
        String ST=MainModel.getSALT();
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SK.toCharArray(), ST.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    public static String encrypt(String strToEncrypt) {
        String SK=MainModel.getSECRET_KEY();
        String ST=MainModel.getSALT();
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SK.toCharArray(), ST.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
    public List<String> AccountSender(Path pathToTheFile) throws IOException {
        BufferedReader abc = new BufferedReader(new FileReader(String.valueOf(pathToTheFile)));
        List<String> data = new ArrayList<String>();
        String s;
        while((s=abc.readLine())!=null)data.add(s);
        abc.close();
        return data;
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
    public static void CreateAccount(String nameOfTheAccount, String nameOfThePassword) throws IOException {
        String enc = encrypt(nameOfThePassword);
        FileWriter fw = new FileWriter("passwords.txt",true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(nameOfTheAccount);
        bw.newLine();
        bw.write(enc);
        bw.newLine();
        bw.close();
    }
    public static void ReadAccounts(MainModel model) throws FileNotFoundException {
        Terminal terminal = model.getTerminal();
        Screen screen = model.getScreen();
        TextGraphics textGraphics = screen.newTextGraphics();
        Scanner input = new Scanner(new File("Passwords.txt"));
        MainModel.Account[] accounts = new MainModel.Account[0];
        while(input.hasNext()) {
            int temp=MainModel.getNumberOfAccounts();
            MainModel.setNumberOfAccounts(temp+1);
            String login = input.next();
            String password = input.next();
            MainModel.Account newAccount = new MainModel.Account(login,password);
            accounts = addAccount(accounts, newAccount);
        }
        int i=0;
        for(MainModel.Account account : accounts){
            textGraphics.putString(23, 2+3*(i),account.login);
            textGraphics.putString(23, 3*(i+1), account.password);
            textGraphics.drawLine(23, 1+3*(i+1), 80, 1+3*(i+1), '-');
            i++;
        }
        terminal.flush();
        screen.refresh();

    }
    private static MainModel.Account[] addAccount(MainModel.Account[] accounts, MainModel.Account accountsToAdd) {
        MainModel.Account[] newAccounts = new MainModel.Account[accounts.length + 1];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        newAccounts[newAccounts.length - 1] = accountsToAdd;
        return newAccounts;
    }
}
