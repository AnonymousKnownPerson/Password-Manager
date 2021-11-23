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
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginMenuController {
    public static void LaunchLoginMenu(MainModel model) throws IOException, IllegalArgumentException, InterruptedException {
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
