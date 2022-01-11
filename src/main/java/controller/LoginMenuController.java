package controller;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;
import view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.googlecode.lanterna.input.KeyType.ArrowLeft;

public class LoginMenuController {
    public static void LaunchLoginMenu(MainModel model) throws IOException, IllegalArgumentException, InterruptedException {
        while(MainModel.isInLoginMenu()){ //<- sprawdza czy jest w menu
            Terminal terminal = model.getTerminal();
            Screen screen = model.getScreen();
            KeyStroke keyStroke = terminal.pollInput();
            String password=model.getpwd();
            screen.refresh();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case Escape:
                        terminal.close();
                        System.exit(0);
                        break;
                    case ArrowLeft:
                        PasswordGrabber.ToogleView(1);
                        terminal.close();
                        break;
                    case Enter:
                        Path relative = Paths.get("password.txt");
                        String thingToDecode = Files.readString(relative);
                        String temp1 = PasswordGrabber.decrypt(thingToDecode);
                        if(password.equals(temp1)){
                            terminal.clearScreen();
                            terminal.flush();
                            MenuView.LaunchViewMenu(model, 1);
                            MenuController.LaunchMenu(model);
                            break;}
                        else{
                            terminal.close();
                        }
                        break;
                    default:
                        if(keyStroke.getCharacter()==null) System.exit(0);
                        char temp=keyStroke.getCharacter();
                        password+=temp;
                        terminal.putCharacter(temp);
                        model.setpwd(password);
                        break;
                }
            }
        }
    }
    public static void LaunchLoginMenuSwing(JFrame frame, String pass) throws IOException, InterruptedException {
        Path relative = Paths.get("password.txt");
        String thingToDecode = Files.readString(relative);
        String temp1 = PasswordGrabber.decrypt(thingToDecode);
        if(pass.equals(temp1)){
            MenuView.LaunchViewMenuSwing(frame);
        }else System.exit(0);



    }

}
