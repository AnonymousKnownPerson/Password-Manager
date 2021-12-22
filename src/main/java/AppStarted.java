import java.awt.*;
import java.io.IOException;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import controller.LoginMenuController;
import model.MainModel;
import view.LoginMenuView;
import view.PreMenuView;
import controller.PreMenuController;

import javax.swing.*;
import java.io.File;
import java.security.NoSuchAlgorithmException;

public class AppStarted {
    public static void AppStarted() throws IOException, InterruptedException, FontFormatException, NoSuchAlgorithmException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal =  defaultTerminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);
        TextGraphics textGraphics = screen.newTextGraphics();
        MainModel database = new MainModel(terminal, screen, textGraphics);
        File f = new File("password.txt");
        File f1 = new File("passwords.txt");
        if(!f.exists()) {
            if(f1.exists())f1.delete();
            MainModel.itIsInPreMenu(true);
            PreMenuView.LaunchViewPreMenu(database);
            PreMenuController.LaunchPreMenu(database);
        }else{
            MainModel.itIsInLoginMenu(true);
            LoginMenuView.LaunchViewLoginMenu(database);
            LoginMenuController.LaunchLoginMenu(database);
        }
    }
    public static void AppStartedSwing() throws IOException, InterruptedException {
        JFrame frame = new JFrame();
        frame.setTitle("Password Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(360,100);
        ImageIcon image = new ImageIcon("icon.jpg");
        frame.setIconImage(image.getImage());
        frame.setVisible(true);
        File f = new File("password.txt");
        File f1 = new File("passwords.txt");
        if(!f.exists()) {
            if(f1.exists())f1.delete();
            MainModel.itIsInPreMenu(true);
            PreMenuView.LaunchViewPreMenuSwing(frame);
        }else{
            MainModel.itIsInLoginMenu(true);
            LoginMenuView.LaunchViewLoginMenuSwing(frame);
        }
    }
}
