package view;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import controller.MakeAccountController;
import controller.PasswordGrabber;
import model.MainModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    public static void DeleteAccountByIdSwing(JFrame frame, JPanel panel){
        JFrame frame1 = new JFrame();
        MainModel.setDeleteFrameActive(true);
        frame1.setTitle("Podaj ID");
        JLabel label1  = new JLabel("Podaj Id  !!!");
        JTextField name = new JTextField(16);
        JButton button = new JButton("Usuń");
        JLabel helper  = new JLabel("");
        button.addActionListener(e ->{
            if(name.getText().length()==0){
                helper.setText("Nothing is written");
            }else{
                try {
                    int number = Integer.parseInt(name.getText());
                try {
                    PasswordGrabber.DeleteAccount(number);
                    MainModel.setDeleteFrameActive(false);
                    frame1.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }}catch(NumberFormatException exception){
                   helper.setText("Invalid Id(Can't Convert to Int)");
                }
            }});
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (name.getText().length() == 0) {
                        helper.setText("Nothing is written");
                    } else {
                        try {
                            int number = Integer.parseInt(name.getText());
                            try {
                                PasswordGrabber.DeleteAccount(number);
                                MainModel.setDeleteFrameActive(false);
                                frame1.dispose();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        } catch (NumberFormatException exception) {
                            helper.setText("Invalid Id(Can't Convert to Int)");
                        }
                    }
                }
            }
        };
        name.addKeyListener(listener);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setResizable(false);
        frame1.setSize(360,100);
        JPanel p1 = new JPanel();
        p1.add(label1);
        p1.add(name);
        p1.add(button);
        p1.add(helper);
        frame1.add(p1);
        ImageIcon image = new ImageIcon("icon.jpg");
        frame1.setIconImage(image.getImage());
        frame1.revalidate();
        frame1.repaint();
        frame1.requestFocusInWindow();
        frame1.setVisible(true);
    }
}
