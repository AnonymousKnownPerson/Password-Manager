package view;


import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;

import java.awt.*;
import java.io.IOException;

public class MenuView {
    public static void LaunchViewMenu(MainModel model,int indexOfTheMenu) throws IOException {
        Terminal terminal = model.getTerminal();
        Screen screen = model.getScreen();
        KeyStroke keyStroke = null;
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.drawLine(20, 0, 20, 24, '|');
        textGraphics.putString(2, 0, "Password Manager", SGR.BOLD);
        textGraphics.drawLine(0, 1, 19, 1, '-');
        textGraphics.drawLine(0, 3, 19, 3, '-');
        textGraphics.drawLine(0, 5, 19, 5, '-');
        textGraphics.drawLine(0, 7, 19, 7, '-');
        textGraphics.drawLine(0, 9, 19, 9, '-');
        if(indexOfTheMenu==1){
            textGraphics.putString(1, 2, "1. Sprawdź hasła", SGR.BLINK);
        }
        else {
            textGraphics.putString(1, 2, "1. Sprawdź hasła");
        }
        if(indexOfTheMenu==2){
            textGraphics.putString(1, 4, "2. Dodaj hasło", SGR.BLINK);
        }
        else {
            textGraphics.putString(1, 4, "2. Dodaj hasło");
        }
        if(indexOfTheMenu==3){
            textGraphics.putString(1, 6, "3. Usuń hasło", SGR.BLINK);
        }
        else {
            textGraphics.putString(1, 6, "3. Usuń hasło");
        }
        if(indexOfTheMenu==4){
            textGraphics.putString(1, 8, "4. Wyjdź", SGR.BLINK);
        }
        else {
            textGraphics.putString(1, 8, "4. Wyjdź");
        }
        screen.refresh();
    }
}

