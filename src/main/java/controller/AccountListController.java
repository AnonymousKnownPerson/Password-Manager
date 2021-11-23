package controller;

import model.MainModel;

import java.io.IOException;

public class AccountListController {

    public static void LaunchAccountList(MainModel model) throws IOException {
        MainModel.itIsInMenu(false);
        MainModel.itIsInAccountList(true);
    }
}
