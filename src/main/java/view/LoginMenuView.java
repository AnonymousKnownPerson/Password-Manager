package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import controller.LoginMenuController;
import controller.PasswordGrabber;
import controller.PreMenuController;
import model.MainModel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;

public class LoginMenuView{
    public static void LaunchViewLoginMenu(MainModel model) throws IOException{
        Screen screen = model.getScreen();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.drawLine(30, 0, 30, 6, '|');
        textGraphics.drawLine(50, 0, 50, 6, '|');
        textGraphics.putString(32, 3, "Password  Manager", SGR.BOLD);
        textGraphics.drawLine(30, 1, 50, 1, '-');
        textGraphics.putString(60, 1, "Press ESC to exit");
        textGraphics.putString(60, 3, "Press ArrowLeft ");
        textGraphics.putString(60, 4, "to restart");
        textGraphics.putString(60, 5, "app with new view");
        textGraphics.drawLine(30, 5, 50, 5, '-');
        textGraphics.putString(35, 6, "Wpisz Hasło", SGR.BLINK);
        textGraphics.drawLine(30, 7, 50, 7, '-');
        screen.refresh();

    }
    public static void LaunchViewLoginMenuSwing(JFrame frame){
        JPanel p1 = new JPanel();
        JLabel label  = new JLabel("Podaj Hasło !!!");
        JPasswordField password = new JPasswordField(16);
        JButton button = new JButton("Submit");
        button.addActionListener(e -> {
            try {
                LoginMenuController.LaunchLoginMenuSwing(frame, String.valueOf(password.getPassword()));
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        LoginMenuController.LaunchLoginMenuSwing(frame, String.valueOf(password.getPassword()));
                    } catch (IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        };
        JButton buttonView = new JButton("Zmień Widok");
        buttonView.addActionListener(e -> {
            try {
                PasswordGrabber.ToogleView(0);
                System.exit(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        button.addKeyListener(listener);
        password.addKeyListener(listener);
        p1.add(label);
        p1.add(password);
        p1.add(button);
        p1.add(buttonView);
        frame.add(p1);
        frame.validate();
        frame.repaint();
    }


}
