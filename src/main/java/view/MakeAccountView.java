package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import controller.LoginMenuController;
import controller.MakeAccountController;
import controller.PasswordGrabber;
import controller.PreMenuController;
import model.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MakeAccountView {
    public static void LaunchViewMenu(MainModel model, int stateOfTheRandom) throws IOException {
        Terminal terminal = model.getTerminal();
        Screen screen = model.getScreen();
        TextGraphics textGraphics = screen.newTextGraphics();
        if(stateOfTheRandom==0) {
            textGraphics.putString(30, 3, "Podaj Swój Login", SGR.BOLD);
            textGraphics.drawLine(29, 2, 47, 2, '-');
            textGraphics.drawLine(28, 2, 28, 4, '|');
            textGraphics.drawLine(29, 4, 47, 4, '-');
            textGraphics.drawLine(48, 2, 48, 4, '|');
        }else{
            textGraphics.putString(30, 10, "Podaj Swoje Hasło", SGR.BOLD);
            textGraphics.drawLine(29, 9, 47, 9, '-');
            textGraphics.drawLine(28, 9, 28, 11, '|');
            textGraphics.drawLine(29, 11, 47, 11, '-');
            textGraphics.drawLine(48, 9, 48, 11, '|');
        }
        textGraphics.putString(42, 1, "Password Manager - Press ESC to exit", SGR.BOLD);
        screen.refresh();
        terminal.flush();
    }
    public static void MaceAccountSwing(JFrame frame, JPanel panel){
        MenuView.LaunchViewMenuSwing(frame);
        JPanel panel1  = new JPanel();
        JPanel panel2  = new JPanel();
        JPanel panel3  = new JPanel();
        JLabel help  = new JLabel("");
        JLabel label1  = new JLabel("Ustaw Imie !!!");
        JTextField name = new JTextField(16);
        JLabel label  = new JLabel("Ustaw Hasło !!!");
        JPasswordField newPassword = new JPasswordField(16);
        JButton button = new JButton("Submit");
        button.addActionListener(e ->{
        if(name.getText().length()==0){
            help.setText("Login Is Missing");
        }else if(String.valueOf(newPassword.getPassword()).length()==0){
            help.setText("Password Is Missing");
        }else{
            try {
                MakeAccountController.MakeAccountControllerSwing(name.getText(),String.valueOf(newPassword.getPassword()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            MenuView.LaunchViewMenuSwing(frame);

        }});
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(name.getText().length()==0){
                        help.setText("Login Is Missing");
                    }else if(String.valueOf(newPassword.getPassword()).length()==0) {
                        help.setText("Password Is Missing");
                    }else{ 
                        try {
                            MakeAccountController.MakeAccountControllerSwing(name.getText(),String.valueOf(newPassword.getPassword()));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        MenuView.LaunchViewMenuSwing(frame);
                    }
                }
            }
        };
        button.addKeyListener(listener);
        newPassword.addKeyListener(listener);
        panel1.add(label1);
        panel1.add(name);
        panel2.add(label);
        panel2.add(newPassword);
        panel3.add(help);
        panel3.add(button);
        panel.add(panel1);
        panel.add(panel2);
        panel3.setPreferredSize(new Dimension(500,500));
        panel.add(panel3);
        panel.setLayout(new FlowLayout());
        frame.add(panel);
        panel.setVisible(true);
        panel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        frame.validate();
        frame.repaint();
    }

}
