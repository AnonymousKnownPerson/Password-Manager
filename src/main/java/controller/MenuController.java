package controller;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.MainModel;
import view.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class  MenuController {
    public static void LaunchMenu(MainModel model) throws IOException, IllegalArgumentException, InterruptedException {
        MainModel.itIsInMenu(true);
        MainModel.itIsInAccountList(false);
        MainModel.itIsInAccountMaker(false);
        model.setIndexMenu(1);
        while (MainModel.isInMenu()) {
            Terminal terminal = model.getTerminal();
            Screen screen = model.getScreen();
            KeyStroke keyStroke = terminal.pollInput();
            TextGraphics textGraphics = model.getTextGraphics();
            screen.refresh();
            if (keyStroke != null) {
                switch (keyStroke.getKeyType()) {
                    case ArrowUp:
                        if (model.getIndexMenu() != 1) {
                            int temp = model.getIndexMenu();
                            model.setIndexMenu(temp - 1);
                            MenuView.LaunchViewMenu(model, model.getIndexMenu());
                        }
                        break;

                    case ArrowDown:
                        if (model.getIndexMenu() != 4) {
                            int temp = model.getIndexMenu();
                            model.setIndexMenu(temp + 1);
                            MenuView.LaunchViewMenu(model, model.getIndexMenu());
                        }
                        break;
                    case Enter:
                        if (model.getIndexMenu() == 4) {
                            terminal.close();
                            System.exit(0);
                        }
                        else if (model.getIndexMenu() == 3) {
                            File f = new File("passwords.txt");
                            if (!f.exists()) {
                                textGraphics.putString(23, 2, "Nie ma pliku \"passwords\", tworzę nowy plik.");
                                PasswordGrabber.CreateFile("passwords");
                                break;
                            }
                            boolean deleTemp = true;
                            AccountListView.LaunchAccountListView(model);
                            PasswordGrabber.ReadAccounts(model, 1);
                            while (deleTemp) {
                                KeyStroke keyStroke1 = terminal.pollInput();
                                DeleteAccountView.DeleteAccountListView(model, MainModel.getDeletePosition());
                                textGraphics.putString(22, 0, "Strona " + MainModel.getPageOfAccounts(), SGR.BOLD);
                                terminal.flush();
                                if (keyStroke1 != null) {
                                    switch (keyStroke1.getKeyType()) {
                                        case ArrowUp:
                                            if (MainModel.getDeletePosition() != 0) {
                                                int tp = MainModel.getDeletePosition();
                                                MainModel.setDeletePosition(tp - 1);

                                            }
                                            break;
                                        case ArrowDown:
                                            if (MainModel.getDeletePosition() != 6) {
                                                int tp = MainModel.getDeletePosition();
                                                MainModel.setDeletePosition(tp + 1);
                                            }
                                            break;
                                        case ArrowRight:
                                            int temp2 = MainModel.getPageOfAccounts();
                                            AccountListView.LaunchAccountListView(model);
                                            MainModel.setPageOfAccounts(temp2 + 1);
                                            PasswordGrabber.ReadAccounts(model, temp2 + 1);

                                            break;
                                        case ArrowLeft:
                                            if (MainModel.getPageOfAccounts() != 1) {
                                                int temp3 = MainModel.getPageOfAccounts();
                                                AccountListView.LaunchAccountListView(model);
                                                MainModel.setPageOfAccounts(temp3 - 1);
                                                PasswordGrabber.ReadAccounts(model, temp3 - 1);

                                            }

                                            break;
                                        case Escape:
                                            deleTemp = false;
                                            screen.refresh();
                                            terminal.flush();
                                            screen.clear();
                                            MainModel.setPageOfAccounts(1);
                                            MenuView.LaunchViewMenu(model, model.getIndexMenu());
                                            break;
                                        case Enter:
                                            PasswordGrabber.DeleteAccount(MainModel.getDeletePosition());
                                            deleTemp = false;
                                            terminal.flush();
                                            screen.refresh();
                                            MenuView.LaunchViewMenu(model, model.getIndexMenu());
                                            break;


                                    }
                                }
                            }
                        } else if (model.getIndexMenu() == 2) {

                            MainModel.setStateOfAccountMaker(0);
                            File f = new File("passwords.txt");
                            if (!f.exists()) {
                                textGraphics.putString(23, 2, "Nie ma pliku \"passwords\", tworzę nowy plik.");
                                terminal.flush();
                                screen.refresh();
                                Thread.sleep(2000);
                                MainModel.setNumberOfAccounts(0);
                                PasswordGrabber.CreateFile("passwords");

                            }
                            screen.refresh();
                            terminal.clearScreen();
                            MakeAccountView.LaunchViewMenu(model, MainModel.getStateOfAccountMaker());
                            MakeAccountController.MakeAccountController(model);
                        } else if (model.getIndexMenu() == 1) {
                            File f = new File("passwords.txt");
                            if (!f.exists()) {
                                textGraphics.putString(23, 2, "Nie ma pliku \"passwords\", tworzę nowy plik.");
                                PasswordGrabber.CreateFile("passwords");
                            }
                            AccountListView.LaunchAccountListView(model);
                            PasswordGrabber.ReadAccounts(model, 1);
                            boolean accountTemp = true;
                            while (accountTemp) {
                                KeyStroke keyStroke2 = terminal.pollInput();
                                textGraphics.putString(22, 0, "Strona " + MainModel.getPageOfAccounts(), SGR.BOLD);
                                terminal.flush();
                                screen.refresh();
                                if (keyStroke2 != null) {
                                    switch (keyStroke2.getKeyType()) {
                                        case ArrowRight:
                                            int temp2 = MainModel.getPageOfAccounts();
                                            AccountListView.LaunchAccountListView(model);
                                            MainModel.setPageOfAccounts(temp2 + 1);
                                            PasswordGrabber.ReadAccounts(model, temp2 + 1);
                                            break;
                                        case ArrowLeft:
                                            if (MainModel.getPageOfAccounts() != 1) {
                                                int temp3 = MainModel.getPageOfAccounts();
                                                AccountListView.LaunchAccountListView(model);
                                                MainModel.setPageOfAccounts(temp3 - 1);
                                                PasswordGrabber.ReadAccounts(model, temp3 - 1);
                                            }
                                            break;
                                        case Escape:
                                            accountTemp = false;
                                            MainModel.setPageOfAccounts(1);
                                            screen.refresh();
                                            terminal.flush();
                                            screen.clear();
                                            MenuView.LaunchViewMenu(model, model.getIndexMenu());
                                            break;
                                    }

                                }

                            }
                            break;
                        }

                }
            }
        }
    }
    public static void AccountControllerSwing(JFrame frame,int numberOfThePage) throws IOException {
        File f = new File("passwords.txt");
        if (!f.exists()) PasswordGrabber.CreateFile("passwords");
        PasswordGrabber.ReadAccountsSwing(1, frame);
        /*
        boolean accountTemp = true;
        while (accountTemp) {
            if (keyStroke2 != null) {
                switch (keyStroke2.getKeyType()) {
                    case ArrowRight:
                        int temp2 = MainModel.getPageOfAccounts();
                        AccountListView.LaunchAccountListView(model);
                        MainModel.setPageOfAccounts(temp2 + 1);
                        PasswordGrabber.ReadAccounts(model, temp2 + 1);
                        break;
                    case ArrowLeft:
                        if (MainModel.getPageOfAccounts() != 1) {
                            int temp3 = MainModel.getPageOfAccounts();
                            AccountListView.LaunchAccountListView(model);
                            MainModel.setPageOfAccounts(temp3 - 1);
                            PasswordGrabber.ReadAccounts(model, temp3 - 1);
                        }
                        break;
                }

            }

        }
        */

    }



}







