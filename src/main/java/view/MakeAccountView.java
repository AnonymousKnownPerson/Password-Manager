package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;

import java.io.IOException;

public class MakeAccountView {
    public static void LaunchViewMenu(MainModel model, int indexOfTheMenu) throws IOException {
        Terminal terminal = model.getTerminal();
        Screen screen = model.getScreen();
        KeyStroke keyStroke = null;
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(30, 10, "Podaj Swoje Hasło", SGR.BOLD);
        textGraphics.drawLine(29, 9, 47, 9, '-');
        textGraphics.drawLine(28, 9, 28, 11, '|');
        textGraphics.drawLine(29, 11, 47, 11, '-');
        textGraphics.drawLine(48, 9, 48, 11, '|');

        terminal.flush();
        screen.refresh();

    }
}
