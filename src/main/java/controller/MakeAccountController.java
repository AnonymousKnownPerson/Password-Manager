package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;
import view.MakeAccountView;
import view.MenuView;

import java.io.IOException;

public class MakeAccountController {
    public static void MakeAccountController(MainModel model, int numberOfAccounts) throws IOException {
        MainModel.itIsInMenu(false);
        MainModel.itIsInAccountMaker(true);
        while(MainModel.isInAccountMaker()) {
            Terminal terminal = model.getTerminal();
            Screen screen = model.getScreen();
            KeyStroke keyStroke = terminal.pollInput();
            screen.refresh();
            String account = MainModel.getNameOfTheAccount();
            String passwd = MainModel.getPasswordOfTheAccount();
            if (keyStroke != null &&MainModel.getStateOfAccountMaker()==1) {
                switch (keyStroke.getKeyType()) {
                    case Escape:
                        terminal.close();
                        break;
                    case Enter:
                        terminal.clearScreen();
                        MainModel.setXPosition(28);
                        PasswordGrabber.CreateAccount(MainModel.getNameOfTheAccount(),MainModel.getPasswordOfTheAccount());
                        MainModel.clearNameOfTheAccount();
                        MainModel.clearPasswordOfTheAccount();
                        terminal.clearScreen();
                        MenuView.LaunchViewMenu(model,1);
                        MenuController.LaunchMenu(model);
                        break;
                    default:
                        char temp = keyStroke.getCharacter();
                        passwd += temp;
                        int Random = MainModel.getXPosition();
                        terminal.setCursorPosition(Random,13);
                        MainModel.setXPosition(Random+1);
                        terminal.putCharacter('X');
                        MainModel.setPasswordOfTheAccount(passwd);
                        break;
                }

            }
            if (keyStroke != null && MainModel.getStateOfAccountMaker()==0) {
                switch (keyStroke.getKeyType()) {
                    case Escape:
                        terminal.close();
                        break;
                    case Enter:
                        MainModel.setStateOfAccountMaker(1);
                        terminal.clearScreen();
                        MainModel.setXPosition(28);
                        MakeAccountView.LaunchViewMenu(model,1);
                        break;
                    default:
                        char temp = keyStroke.getCharacter();
                        account += temp;
                        int Random = MainModel.getXPosition();
                        terminal.setCursorPosition(Random,6);
                        MainModel.setXPosition(Random+1);
                        terminal.putCharacter(temp);
                        MainModel.setNameOfTheAccount(account);
                        break;
                }

            }

        }

    }
}
