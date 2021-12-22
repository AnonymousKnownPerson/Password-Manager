package controller;

import model.MainModel;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.io.IOException;

public class PreMenuController {
    public static void LaunchPreMenu(MainModel model) throws IOException{
        while(MainModel.isInPreMenu()){
            Terminal terminal = model.getTerminal();
            Screen screen = model.getScreen();
            KeyStroke keyStroke = terminal.pollInput();
            String password=model.getpwd();
            screen.refresh();

            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowLeft:
                        PasswordGrabber.ToogleView(1);
                        terminal.close();
                        break;
                    case Escape:
                        terminal.close();
                        break;
                    case Enter:
                        String pswd = PasswordGrabber.encrypt(password) ;
                        PasswordGrabber.CreateFile("password");
                        PasswordGrabber.PutToFile(pswd,"password");
                        terminal.close();
                        break;
                    default:
                        char temp=keyStroke.getCharacter();
                        password+=temp;
                        terminal.putCharacter(temp);
                        model.setpwd(password);
                        break;
                }

            }

            }
    }
    public static void LaunchPreMenuSwing(JFrame frame, String pass){
        String pswd = PasswordGrabber.encrypt(pass) ;
        PasswordGrabber.CreateFile("password");
        PasswordGrabber.PutToFile(pswd,"password");
        System.exit(0);
    }

}
