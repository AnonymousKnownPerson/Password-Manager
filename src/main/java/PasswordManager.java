import controller.PasswordGrabber;

import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {
    public static void main(String[] args) throws IOException, InterruptedException, FontFormatException, NoSuchAlgorithmException {
        if(PasswordGrabber.CheckView()==0) AppStarted.AppStarted();
        else AppStarted.AppStartedSwing();
    }
}