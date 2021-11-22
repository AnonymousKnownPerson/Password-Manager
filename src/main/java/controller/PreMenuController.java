package controller;

import com.googlecode.lanterna.TerminalPosition;
import model.MainModel;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PreMenuController {
    public static void LaunchPreMenu(MainModel model) throws IOException{
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
                        terminal.close();
                        break;
                    case Enter:
                        String pswd = PasswordGrabber.PasswordSetter(password);
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
}
