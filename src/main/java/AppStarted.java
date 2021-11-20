import java.awt.*;
import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;
import controller.PreMenuController;

public class AppStarted {
    public static void AppStarted() throws IOException, InterruptedException, FontFormatException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal =  defaultTerminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        TextGraphics textGraphics = screen.newTextGraphics();
        MainModel database = new MainModel(terminal, screen, textGraphics);
        PreMenuController.LaunchPreMenu(database);
    }
}
