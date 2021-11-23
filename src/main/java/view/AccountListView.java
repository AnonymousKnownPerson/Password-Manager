package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;

import java.io.IOException;

public class AccountListView {
    public static void LaunchAccountListView (MainModel model) throws IOException {
        Terminal terminal = model.getTerminal();
        Screen screen = model.getScreen();
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.drawLine(0, 1, 80, 1, '-');
        textGraphics.drawLine(20, 0, 20, 24, '|');
        textGraphics.putString(60, 0, "Press ESC to Quit", SGR.BOLD);
        terminal.flush();
        screen.refresh();
    }
}
