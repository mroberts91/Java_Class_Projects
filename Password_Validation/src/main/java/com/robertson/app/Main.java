package com.robertson.app;

import com.robertson.app.javaFX.MainFX;
import com.robertson.app.swing.MainSwing;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args){
        // Allow the user to pick which UI to use
        // If enhanced version, call that main and then exit the program when they close the window.

        switch (getUIChoice()){
            case 0:
                MainFX.main(new String[]{});
                System.exit(0);
            case 1:
                MainSwing.main(new String[]{});
                System.exit(1);
        }
    }

    // Display the dialog box for the UI selection
    private static int getUIChoice() {
        return JOptionPane.showConfirmDialog(null,
                "Would you like to use our enhanced Validator?\nYes - Enhanced Version\nNo - Legacy Version",
                "Validator Choice", JOptionPane.YES_NO_OPTION);
    }

}
