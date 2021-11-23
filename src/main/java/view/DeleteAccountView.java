package view;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;

import java.io.IOException;

public class DeleteAccountView {
    public static void DeleteAccountListView (MainModel model,int deletePosition) throws IOException {
        Terminal terminal = model.getTerminal();
        Screen screen = model.getScreen();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.drawLine(21, 1, 80, 1, '-');
        textGraphics.drawLine(22, 2+(3*deletePosition), 22, 3*(deletePosition+1), '<');
        textGraphics.drawLine(78, 2+(3*deletePosition), 78, 3*(deletePosition+1), '>');
        if(deletePosition-1>=0){
            textGraphics.drawLine(78, 2+(3*(deletePosition-1)), 78, 3*(deletePosition), ' ');
            textGraphics.drawLine(22, 2+(3*(deletePosition-1)), 22, 3*(deletePosition), ' ');
        }
        if(deletePosition+1<7){
            textGraphics.drawLine(22, 2+3*(deletePosition+1), 22, 3*(deletePosition+2), ' ');
            textGraphics.drawLine(78, 2+3*(deletePosition+1), 78, 3*(deletePosition+2), ' ');
        }

        terminal.flush();
        screen.refresh();

    }
}
