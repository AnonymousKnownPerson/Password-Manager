package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;
import java.awt.*;
import java.io.IOException;

public class PreMenuView{
    public static void LaunchViewPreMenu(MainModel model) throws IOException{
        Terminal terminal = model.getTerminal();
        Screen screen = model.getScreen();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.drawLine(30, 0, 30, 5, '|');
        textGraphics.drawLine(50, 0, 50, 5, '|');
        textGraphics.putString(32, 3, "Password  Manager", SGR.BOLD);
        textGraphics.drawLine(30, 1, 50, 1, '-');
        textGraphics.putString(60, 1, "Press ESC to exit");
        textGraphics.drawLine(30, 5, 50, 5, '-');
        textGraphics.putString(35, 6, "Ustaw Hasło!", SGR.BLINK);
        textGraphics.drawLine(30, 7, 50, 7, '-');

        screen.refresh();

    }


}
