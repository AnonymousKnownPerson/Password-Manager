package model;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;



public class MainModel {
    private DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    private Screen screen;
    private Terminal terminal;
    private TextGraphics textGraphics;
    private static boolean premenu = false;
    private static boolean loginmenu = false;
    private static boolean menu = false;
    private static boolean accountList = false;
    private static int indexMenu = 1;
    private static int numberOfAccounts = 0;
    private String pwd="";

    private final String[] PreMenuOptions = {
            "START",
            "EXIT"
    };

    public static int[] cursorPosition = {0,0};
    public MainModel(Terminal terminal, Screen screen, TextGraphics textGraphic) throws IOException {
        this.terminal = terminal;
        this.screen = screen;
        this.textGraphics = textGraphic;
    }
    public static boolean isInPreMenu(){
        return premenu;
    }
    public static void itIsInPreMenu(boolean temp){
        premenu=temp;
    }
    public static boolean isInLoginMenu(){
        return loginmenu;
    }
    public static void itIsInLoginMenu(boolean temp){
        loginmenu=temp;
    }
    public static boolean isInMenu(){
        return menu;
    }
    public static void itIsInMenu(boolean temp){
        menu=temp;
    }
    public static boolean isInAccountList(){
        return accountList;
    }
    public static void itIsInAccountList(boolean temp){
        accountList=temp;
    }
    public Screen getScreen(){
        return screen;
    }
    public Terminal getTerminal(){
        return terminal;
    }
    public TextGraphics getTextGraphics(){
        return textGraphics;
    }
    public String[] getPreMenuOptions(){
        return PreMenuOptions;
    }
    public String getpwd(){
        return pwd;
    }
    public void setpwd(String temp){
        pwd=temp;
    }
    public void setIndexMenu(int numberOfTheIndex){
        indexMenu=numberOfTheIndex;
    }
    public int getIndexMenu(){
        return indexMenu;
    }
    public void setNumberOfAccounts(int numberOfTheAccounts){
        numberOfAccounts=numberOfTheAccounts;
    }
    public int getNumberOfAccounts(){
        return numberOfAccounts;
    }

}
