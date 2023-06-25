package com.quicksound.app;

import com.quicksound.guis.GuiManager;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        run();
    }

    public static void run() throws InterruptedException {
        AppController.INSTANCE.loadSongsToLibrary();
        GuiManager.INSTANCE.setUpGUIS();
        GuiManager.INSTANCE.getWelcomeWindow().setVisible(true);
    }
}
