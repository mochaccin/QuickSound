package com.quicksound.app;

import com.quicksound.guis.GuiManager;
import com.quicksound.services.SongLibrary;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        SongLibrary.INSTANCE.loadLibrary();
        GuiManager.INSTANCE.setUpGUIS();
        GuiManager.INSTANCE.getWelcomeWindow().setVisible(true);
    }
}
