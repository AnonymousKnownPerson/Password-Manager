import java.awt.*;
import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import controller.LoginMenuController;
import model.MainModel;
import view.LoginMenuView;
import view.PreMenuView;
import controller.PreMenuController;
import java.io.File;

public class AppStarted {
    public static void AppStarted() throws IOException, InterruptedException, FontFormatException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        Terminal terminal =  defaultTerminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);
        TextGraphics textGraphics = screen.newTextGraphics();
        MainModel database = new MainModel(terminal, screen, textGraphics);
        File f = new File("password.txt");
        if(!f.exists()) {
            MainModel.itIsInPreMenu(true);
            PreMenuView.LaunchViewPreMenu(database);
            PreMenuController.LaunchPreMenu(database);
        }else{
            MainModel.itIsInLoginMenu(true);
            LoginMenuView.LaunchViewLoginMenu(database);
            LoginMenuController.LaunchLoginMenu(database);
        }


    }
}
