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
        frame.getContentPane().removeAll();

        JPanel menu = new JPanel();
        JPanel add = new JPanel();
        JPanel delete = new JPanel();
        frame.setSize(720, 420);
        JButton buttonShow=new JButton("Pokaż Hasła");
        JButton buttonAdd=new JButton("Dodaj Hasło");
        JButton buttonDelete=new JButton("Usuń Hasło");
        JButton buttonExit=new JButton("Wyjdź");
        menu.setPreferredSize(new Dimension(150, 420));
        buttonShow.addActionListener(e -> {
            try {
                MainModel.setMenuSwing(1);
                MenuController.AccountControllerSwing(frame, MainModel.getPageOfAccounts());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        buttonDelete.addActionListener(e -> {
            if(!MainModel.getDeleteFrameActive())DeleteAccountView.DeleteAccountByIdSwing(frame, delete);
        });
        String message = null;
        if (MainModel.getDecrypted()) {
            message = "zaszyfruj";
        } else {
            message = "odszyfruj";
        }
        JButton decButton = new JButton(message);
        decButton.addActionListener(e -> {
            MainModel.setDecrypted(!MainModel.getDecrypted());
            if(MainModel.getMenuSwing()==1||MainModel.getMenuSwing()==3){
                try {
                    MenuView.LaunchViewMenuSwing(frame);
                    MenuController.AccountControllerSwing(frame, MainModel.getPageOfAccounts());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                MenuView.LaunchViewMenuSwing(frame);
            }
        });
        buttonAdd.addActionListener(e -> MaceAccountSwing(frame,add));
        buttonExit.addActionListener(e -> System.exit(0));
        menu.setBackground(Color.gray);
        menu.setAlignmentX(Component.LEFT_ALIGNMENT);
        menu.setAlignmentY(Component.CENTER_ALIGNMENT);
        menu.add(buttonShow);
        menu.add(buttonAdd);
        menu.add(buttonDelete);
        menu.add(decButton);
        menu.add(buttonExit);
        menu.setLayout(new GridLayout(5,1,0,17));
        menu.setBorder(BorderFactory.createEmptyBorder(10, 10, 170, 10));
        frame.add(menu, BorderLayout.WEST);
        frame.repaint();
        frame.revalidate();



    }
}

