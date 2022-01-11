package controller;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;
import view.MenuView;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.spec.KeySpec;
import java.util.*;
import javax.crypto.*;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.border.Border;

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
    public static void ReadAccounts(MainModel model,int numberOfTheSite) throws IOException {
        Terminal terminal = model.getTerminal();
        Screen screen = model.getScreen();
        TextGraphics textGraphics = screen.newTextGraphics();
        Scanner input = new Scanner(new File("Passwords.txt"));
        MainModel.Account[] accounts = new MainModel.Account[0];
        while(input.hasNext()) {
            String login = input.next();
            String pass = input.next();
            String password = decrypt(pass);
            MainModel.Account newAccount = new MainModel.Account(login,password);
            accounts = addAccount(accounts, newAccount);
        }
        int min=numberOfTheSite*7-7;
        int max=numberOfTheSite*7;
        int i=0;
        int j=0;
        for(MainModel.Account account : accounts){
            if(min<=i && max>i){
            textGraphics.putString(23, 2+3*(j),account.login);
            textGraphics.putString(23, 3*(j+1), account.password);
            textGraphics.drawLine(21, 1+3*(j+1), 80, 1+3*(j+1), '-');
            j++;
            }
            i++;
        }
        terminal.flush();
        screen.refresh();
    }
/*    public static void ReadAccountsSwingDecrypt(int numberOfTheSite,JFrame frame) throws IOException {
        Scanner input = new Scanner(new File("Passwords.txt"));
        MainModel.Account[] accounts = new MainModel.Account[0];
        while(input.hasNext()) {
            String login = input.next();
            String pass = input.next();
            String password = decrypt(pass);
            MainModel.Account newAccount = new MainModel.Account(login,password);
            accounts = addAccount(accounts, newAccount);
        }
        int min=numberOfTheSite*7-7;
        int max=numberOfTheSite*7+1;
        int i=0;
        int j=0;
        for(MainModel.Account account : accounts){
            if(min<=i && max>i){
                JPanel panel1 = new JPanel();
                Border blackline = BorderFactory.createLineBorder(Color.black);
                Color color;
                Random random = new Random();
                final float hue = random.nextFloat();
                final float saturation = 0.05f;
                final float luminance = 1.0f;
                color = Color.getHSBColor(hue, saturation, luminance);
                JLabel label1 = new JLabel("Login - " + account.login +"   Hasło - " + account.password );
                panel1.setPreferredSize(new Dimension(420, 53));

                System.out.println(label1.getText());
                panel1.setBackground(color);
                panel1.setBounds(150,(j)*53,570, 53);
                panel1.setBorder(blackline);
                panel1.setLayout(new BorderLayout());
                panel1.add(label1);
                frame.add(panel1);
                panel1.setVisible(true);
                j++;
            }
            i++;
        }
        frame.validate();
        frame.repaint();
    }*/
    public static void ReadAccountsSwing(int numberOfTheSite,JFrame frame) throws IOException {
        MenuView.LaunchViewMenuSwing(frame);
        Scanner input = new Scanner(new File("Passwords.txt"));
        MainModel.Account[] accounts = new MainModel.Account[0];
        while(input.hasNext()) {
            String login = input.next();
            String password = input.next();
            if(MainModel.getDecrypted()){
                password = decrypt(password);
            }
            MainModel.Account newAccount = new MainModel.Account(login,password);
            accounts = addAccount(accounts, newAccount);
        }
        int min=numberOfTheSite*7-7;
        int max=numberOfTheSite*7+1;
        int i=0;
        int j=0;
        Border blackline = BorderFactory.createLineBorder(Color.black);
        JPanel panel2 = new JPanel();
        JButton leftButton = new JButton("Poprzednia Sprawa");
        JButton rightButton = new JButton("Następna Strona");
        leftButton.addActionListener(e ->{
            if(numberOfTheSite!=1){
                MainModel.setPageOfAccounts(numberOfTheSite-1);
                try {
                    ReadAccountsSwing(numberOfTheSite-1, frame);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        rightButton.addActionListener(e ->{
            MainModel.setPageOfAccounts(numberOfTheSite+1);
            try {
                ReadAccountsSwing(numberOfTheSite+1, frame);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        panel2.setBounds(150, 0,570, 54);
        panel2.setBorder(blackline);
        panel2.add(leftButton);
        panel2.add(rightButton);
        frame.add(panel2);
        for(MainModel.Account account : accounts){
            if(min<=i && max>i){
                JPanel panel1 = new JPanel();

                Color color;
                Random random = new Random();
                final float hue = random.nextFloat();
                final float saturation = 0.05f;
                final float luminance = 1.0f;
                color = Color.getHSBColor(hue, saturation, luminance);
                JLabel label1 = new JLabel("Login - " + account.login +"   Hasło - " + account.password );
                panel1.setPreferredSize(new Dimension(420, 54));
                System.out.println(label1.getText());
                if(j!=7)panel1.setBackground(color);
                JButton deleteButton = new JButton("Usuń konto nr : " + i);
                panel1.setBounds(150,40+(j)*40,570, 54);
                deleteButton.setBounds(460, 60+(j)*40, 100, 34);
                panel1.setBorder(blackline);
                panel1.setLayout(new FlowLayout());
                deleteButton.setLayout(new FlowLayout());
                if(MainModel.getMenuSwing()==3){
                }
                panel1.add(label1);
                if(j!=7)panel1.add(deleteButton);
                frame.add(panel1);
                panel1.setVisible(true);
                j++;
            }
            i++;
        }
        if (j!=8){
            JPanel panel1 = new JPanel();
            panel1.setBounds(150,40+(j)*40,570, 54);
            panel1.setBorder(blackline);
            frame.add(panel1);
        }
        frame.validate();
        frame.repaint();
    }

    static void DeleteAccount(int numberOfTheAccount) throws IOException {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get("passwords.txt"))) {
            result = lines.collect(Collectors.toList());
        }
        File f=new File("passwords.txt");  //Creation of File Descriptor for output file
        FileWriter fw=new FileWriter(f); //Creation of File Writer object
        for(int i=0; i<result.size();i++){
            if(numberOfTheAccount*2==i || numberOfTheAccount*2+1==i){
                continue;
            }
            fw.write(result.get(i)+"\n");
        }
        fw.flush();
    }
    private static MainModel.Account[] addAccount(MainModel.Account[] accounts, MainModel.Account accountsToAdd) {
        MainModel.Account[] newAccounts = new MainModel.Account[accounts.length + 1];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        newAccounts[newAccounts.length - 1] = accountsToAdd;
        return newAccounts;
    }
    public static void ToogleView(int toggleViewNumber) throws IOException {
            FileWriter fileWriter = new FileWriter("toggleview.txt");
            String test = String.valueOf(toggleViewNumber);
            fileWriter.write(test);
            fileWriter.flush(); // empty buffer in the file
            fileWriter.close(); // close the file to allow opening by others applications
    }
    public static int CheckView() throws IOException {
        Scanner input = new Scanner(new File("toggleview.txt"));
        return Integer.parseInt(input.next());
    }

}
