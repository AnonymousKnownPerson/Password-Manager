package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import controller.LoginMenuController;
import controller.PreMenuController;
import model.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class PreMenuView{
    public static void LaunchViewPreMenu(MainModel model) throws IOException{
        Screen screen = model.getScreen();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.drawLine(30, 0, 30, 5, '|');
        textGraphics.drawLine(50, 0, 50, 5, '|');
        textGraphics.putString(32, 3, "Password  Manager", SGR.BOLD);
        textGraphics.drawLine(30, 1, 50, 1, '-');
        textGraphics.putString(60, 1, "Press ESC to exit");
        textGraphics.drawLine(30, 5, 50, 5, '-');
        textGraphics.putString(35, 6, "Ustaw Hasło!", SGR.BLINK);
        textGraphics.drawLine(30, 7, 50, 7, '-');
        textGraphics.putString(60, 3, "Press \"`\" to restart");
        textGraphics.putString(60, 4, "app with new view");

        screen.refresh();

    }
    public static void LaunchViewPreMenuSwing(JFrame frame) {
        JPanel p1 = new JPanel();
        JLabel label  = new JLabel("Ustaw Hasło !!!");
        JPasswordField newPassword = new JPasswordField(16);
        JButton button = new JButton("Submit");
        button.addActionListener(e -> PreMenuController.LaunchPreMenuSwing(frame,String.valueOf(newPassword.getPassword())));
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    PreMenuController.LaunchPreMenuSwing(frame,String.valueOf(newPassword.getPassword()));
                }
            }
        };
        button.addKeyListener(listener);
        newPassword.addKeyListener(listener);
        p1.add(label);
        p1.add(newPassword);
        p1.add(button);
        frame.add(p1);
        frame.validate();
        frame.repaint();
    }
}
