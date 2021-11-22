package controller;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;

import java.io.IOException;

public class LoginMenuController {
    public static void LaunchLoginMenu(MainModel model) throws IOException{
        while(MainModel.isInPreMenu()){ //<- sprawdza czy jest w menu
            Terminal terminal = model.getTerminal();
            Screen screen = model.getScreen();
            KeyStroke keyStroke = terminal.pollInput();
            String password=model.getpwd();
            TextGraphics textGraphics = model.getTextGraphics();
            TerminalPosition startPosition = terminal.getCursorPosition();
            screen.refresh();

            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case Escape:
                        terminal.bell();
                        terminal.close();
                        break;
                    case Enter:
                        String temp1 = PasswordGrabber.PasswordGetter();
                        if(PasswordGrabber.PasswordChecker(temp1,password))terminal.close();
                        break;
                    case Delete:
                        password = password.substring(0, password.length() -1);
                        model.setpwd(password);
                        break;
                    default:
                        char temp=keyStroke.getCharacter();
                        password+=temp;
                        terminal.putCharacter(temp);
                        terminal.bell();
                        model.setpwd(password);
                        break;
                }

            }

            }
    }
}
