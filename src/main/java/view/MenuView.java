package view;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import controller.MenuController;
import controller.PreMenuController;
import model.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static view.MakeAccountView.MaceAccountSwing;

public class MenuView {
    public static void LaunchViewMenu(MainModel model,int indexOfTheMenu) throws IOException {
        Screen screen = model.getScreen();
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
    public static void LaunchViewMenuSwing(JFrame frame) {
        JPanel menu = new JPanel();
        JPanel rightSide = new JPanel();
        JPanel delete = new JPanel();
        JPanel add = new JPanel();
        JPanel show = new JPanel();
        frame.getContentPane().removeAll();
        frame.setSize(720, 420);
        JButton buttonShow=new JButton("Pokaż Hasła");
        JButton buttonAdd=new JButton("Dodaj Hasło");
        JButton buttonDelete=new JButton("Usuń Hasło");
        JButton buttonExit=new JButton("Wyjdź");
        menu.setPreferredSize(new Dimension(150, 420));
        buttonShow.addActionListener(e -> {
            try {
                MenuController.AccountControllerSwing(frame, 0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        buttonAdd.addActionListener(e -> MaceAccountSwing(frame,add));
        buttonDelete.addActionListener(e -> System.exit(0));
        buttonExit.addActionListener(e -> System.exit(0));
        menu.setBackground(Color.gray);
        menu.setAlignmentX(Component.LEFT_ALIGNMENT);
        menu.setAlignmentY(Component.CENTER_ALIGNMENT);
        menu.add(buttonShow);
        menu.add(buttonAdd);
        menu.add(buttonDelete);
        menu.add(buttonExit);
        frame.add(menu, BorderLayout.WEST);
        frame.repaint();
        frame.revalidate();



    }
}

