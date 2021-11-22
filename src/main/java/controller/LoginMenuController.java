package controller;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;
import view.LoginMenuView;
import view.MenuView;

import java.io.IOException;

public class LoginMenuController {
    public static void LaunchLoginMenu(MainModel model) throws IOException, IllegalArgumentException{
        while(MainModel.isInLoginMenu()){ //<- sprawdza czy jest w menu
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
                        terminal.close();
                        break;
                    case Enter:
                        String temp1 = PasswordGrabber.PasswordGetter();
                        if(password.equals(temp1)){
                            terminal.clearScreen();
                            MenuView.LaunchViewMenu(model, 1);
                            MenuController.LaunchMenu(model);
                            break;}
                        else{
                            terminal.close();
                        }
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
}
