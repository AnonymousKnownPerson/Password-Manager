package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import controller.PasswordGrabber;
import model.MainModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountListView {
    public static void LaunchAccountListView (MainModel model) throws IOException {
        Terminal terminal = model.getTerminal();
        Screen screen = model.getScreen();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.drawLine(21, 1, 80, 1, '-');

        terminal.flush();
        screen.refresh();

    }
}
