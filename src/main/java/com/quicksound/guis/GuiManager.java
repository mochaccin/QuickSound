package com.quicksound.guis;

public enum GuiManager {
    INSTANCE;

    RegisterWindow registerWindow;
    WelcomeWindow welcomeWindow;
    MainWindow mainWindow;
    AccountWindow accountWindow;
    UsernameWindow usernameWindow;
    PasswordWindow passwordWindow;
    SongsWindow songsWindow;
    PlaylistsWindow playlistsWindow;

    public void setUpGUIS(){
        welcomeWindow = new WelcomeWindow(800, 600);
        registerWindow = new RegisterWindow(800, 600);
        mainWindow = new MainWindow(800, 600);
        accountWindow = new AccountWindow(800, 600);
        usernameWindow = new UsernameWindow(800, 600);
        passwordWindow = new PasswordWindow(800, 600);
        songsWindow = new SongsWindow(800, 600);
        playlistsWindow = new PlaylistsWindow(800, 600);
    }

    public RegisterWindow getRegisterWindow() {
        return registerWindow;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public WelcomeWindow getWelcomeWindow() {
        return welcomeWindow;
    }

    public AccountWindow getAccountWindow() {
        return accountWindow;
    }

    public UsernameWindow getUsernameWindow() {
        return usernameWindow;
    }

    public PasswordWindow getPasswordWindow() {
        return passwordWindow;
    }

    public SongsWindow getSongsWindow() {
        return songsWindow;
    }

    public PlaylistsWindow getPlaylistsWindow() {
        return playlistsWindow;
    }
}
