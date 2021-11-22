package controller;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;
import view.MakeAccountView;
import view.MenuView;

import java.io.IOException;

public class MenuController {
    public static void LaunchMenu(MainModel model) throws IOException, IllegalArgumentException{
        MainModel.itIsInMenu(true);
        MainModel.itIsInAccountList(false);
        while(MainModel.isInMenu()) { //<- sprawdza czy jest w menu
            Terminal terminal = model.getTerminal();
            Screen screen = model.getScreen();
            KeyStroke keyStroke = terminal.pollInput();
            TextGraphics textGraphics = model.getTextGraphics();
            TerminalPosition startPosition = terminal.getCursorPosition();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowUp:
                        if (model.getIndexMenu() == 1) {
                            terminal.bell();
                        } else {
                            int temp = model.getIndexMenu();
                            model.setIndexMenu(temp - 1);
                            MenuView.LaunchViewMenu(model, model.getIndexMenu());
                        }
                        break;

                    case ArrowDown:
                        if (model.getIndexMenu() == 4) {
                            terminal.bell();
                        } else {
                            int temp = model.getIndexMenu();
                            model.setIndexMenu(temp + 1);
                            MenuView.LaunchViewMenu(model, model.getIndexMenu());
                        }
                        break;
                    case Enter:
                        if(model.getIndexMenu()==4)terminal.close();
                        else if(model.getIndexMenu()==3){

                        }
                        else if(model.getIndexMenu()==2){
                            terminal.clearScreen();
                            MakeAccountView.LaunchViewMenu(model,model.getNumberOfAccounts());
                            MakeAccountController.MakeAccountController(model,model.getNumberOfAccounts());


                        }
                        else if(model.getIndexMenu()==1){

                        }
                }

            }
        }

    }


}
