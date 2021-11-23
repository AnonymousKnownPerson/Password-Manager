package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;
import view.MakeAccountView;
import view.MenuView;

import java.io.IOException;

public class MakeAccountController {
    public static void MakeAccountController(MainModel model, int numberOfAccounts) throws IOException, InterruptedException {
        MainModel.itIsInMenu(false);
        MainModel.itIsInAccountMaker(true);
        MainModel.setStateOfAccountMaker(0);
        while(MainModel.isInAccountMaker()) {
            Terminal terminal = model.getTerminal();
            Screen screen = model.getScreen();
            KeyStroke keyStroke = terminal.pollInput();
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
                        MainModel.setStateOfAccountMaker(0);
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
                        screen.clear();
                        terminal.setCursorPosition(Random,13);
                        MainModel.setXPosition(Random+1);
                        terminal.putCharacter('*');
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
                        int temp1 = MainModel.getStateOfAccountMaker();
                        terminal.clearScreen();
                        screen.clear();
                        MainModel.setXPosition(28);
                        MakeAccountView.LaunchViewMenu(model,temp1);
                        break;
                    default:
                        char temp = keyStroke.getCharacter();
                        account += temp;
                        int Random = MainModel.getXPosition();
                        terminal.setCursorPosition(Random,6);
                        terminal.putCharacter(temp);
                        MainModel.setXPosition(Random+1);
                        MainModel.setNameOfTheAccount(account);
                        break;
                }

            }

        }

    }
}
