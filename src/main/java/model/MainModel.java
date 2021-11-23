package model;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class MainModel {
    private DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    private Screen screen;
    private Terminal terminal;
    private TextGraphics textGraphics;
    private static String SECRET_KEY = "Komunikacja Człowiek-Komputer";
    private static final String SALT = "Bygdoszcz?!";
    private static boolean premenu = false;
    private static boolean loginmenu = false;
    private static boolean menu = false;
    private static boolean accountList = false;
    private static boolean accountMaker = false;
    private static int stateOfAccountMaker=0;
    private static String nameOfTheAccount="";
    private static String passwordOfTheAccount="";
    private static int xPosition=28;
    private static int deletePosition=0;
    private static int indexMenu = 1;
    private static int numberOfAccounts = 0;
    private static int pageOfAccounts = 1;

    private String pwd="";

    private final String[] PreMenuOptions = {
            "START",
            "EXIT"
    };
    public MainModel(Terminal terminal, Screen screen, TextGraphics textGraphic) throws IOException, NoSuchAlgorithmException {
        this.terminal = terminal;
        this.screen = screen;
        this.textGraphics = textGraphic;
    }
    public static class Account {
        public String login;
        public String password;
        public Account(String name, String pswd){
            login=name;
            password=pswd;
        }
    }
    public static void setPageOfAccounts(int numberOfTheIndex){
        pageOfAccounts=numberOfTheIndex;
    }
    public static int getPageOfAccounts(){
        return pageOfAccounts;
    }
    public static void setDeletePosition(int numberOfTheIndex){
        deletePosition=numberOfTheIndex;
    }
    public static int getDeletePosition(){
        return deletePosition;
    }
    public static void setXPosition(int numberOfTheIndex){
        xPosition=numberOfTheIndex;
    }
    public static int getXPosition(){
        return xPosition;
    }
    public static String getNameOfTheAccount(){
        return nameOfTheAccount;
    }
    public static void setNameOfTheAccount(String temp){
        nameOfTheAccount=temp;
    }
    public static void clearNameOfTheAccount(){
        nameOfTheAccount="";
    }
    public static String getPasswordOfTheAccount(){
        return passwordOfTheAccount;
    }
    public static void setPasswordOfTheAccount(String temp){
        passwordOfTheAccount=temp;
    }
    public static void clearPasswordOfTheAccount(){
        passwordOfTheAccount="";
    }
    public static String getSECRET_KEY(){
        return SECRET_KEY;
    }
    public static String getSALT(){
        return SALT;
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
    public static boolean isInAccountMaker(){
        return accountMaker;
    }
    public static void itIsInAccountMaker(boolean temp){
        accountMaker=temp;
    }
    public static boolean isInMenu(){
        return menu;
    }
    public static void itIsInMenu(boolean temp){
        menu=temp;
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
    public static void setStateOfAccountMaker(int numberOfTheIndex){
        stateOfAccountMaker=numberOfTheIndex;
    }
    public static int getStateOfAccountMaker(){
        return stateOfAccountMaker;
    }
    public static void setNumberOfAccounts(int numberOfTheAccounts){
        numberOfAccounts=numberOfTheAccounts;
    }
    public static int getNumberOfAccounts(){
        return numberOfAccounts;
    }


}
