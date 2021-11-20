package controller;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyType;
import model.MainModel;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class PreMenuController {
    public static void LaunchPreMenu(MainModel model) throws IOException{
        while(MainModel.isInPreMenu()){ //<- sprawdza czy jest w menu
            Terminal terminal = model.getTerminal();
            Screen screen = model.getScreen();
            KeyStroke keyStroke = terminal.pollInput();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowUp:
                        if(model.getIndexPremenu()==0){
                            terminal.bell();

                        }
                        else{
                            model.getMainMenu().scrollGui(model.getIndexPremenu(), null, model.getPreMenuOptions());
                            int temp = model.getIndexPremenu();
                            model.setIndexPremenu(temp-1);
                        }

                        }
                }



            }


    }
}
