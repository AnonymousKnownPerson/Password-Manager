package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.MainModel;

import java.io.IOException;

public class LoginMenuView {
    public static void LaunchViewLoginMenu(MainModel model) throws IOException{
        Screen screen = model.getScreen();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.drawLine(30, 0, 30, 6, '|');
        textGraphics.drawLine(50, 0, 50, 6, '|');
        textGraphics.putString(32, 3, "Password  Manager", SGR.BOLD);
        textGraphics.drawLine(30, 1, 50, 1, '-');
        textGraphics.putString(60, 1, "Press ESC to exit");
        textGraphics.drawLine(30, 5, 50, 5, '-');
        textGraphics.putString(35, 6, "Wpisz Hasło", SGR.BLINK);
        textGraphics.drawLine(30, 7, 50, 7, '-');
        screen.refresh();

    }


}
