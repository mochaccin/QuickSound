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

    PlaylistsMenuWindow playlistsMenuWindow;

    DeletePlaylistWindow deletePlaylistWindow;

    EditPlaylistMenuWindow editPlaylistMenuWindow;

    CreatePlaylistWindow createPlaylistWindow;

    PlayPlaylistWindow playPlaylistWindow;

    AddSongWindow addSongWindow;

    private boolean isUserLoggedIn = false;
    private boolean hasPlaylists = false;
    private int playlistIndex;

    public void setUpGUIS(){
        welcomeWindow = new WelcomeWindow(800, 600);
        registerWindow = new RegisterWindow(800, 600);
        mainWindow = new MainWindow(800, 600);
        accountWindow = new AccountWindow(800, 600);
        usernameWindow = new UsernameWindow(800, 600);
        passwordWindow = new PasswordWindow(800, 600);
        songsWindow = new SongsWindow(800, 600);
        playlistsMenuWindow = new PlaylistsMenuWindow(800, 600);
        createPlaylistWindow = new CreatePlaylistWindow(800, 600);

        if (isUserLoggedIn) {
            deletePlaylistWindow = new DeletePlaylistWindow(800, 600);
            playlistsWindow = new PlaylistsWindow(800, 600);
            if (hasPlaylists) {
                playPlaylistWindow = new PlayPlaylistWindow(800, 600);
                editPlaylistMenuWindow = new EditPlaylistMenuWindow(800, 600);
                addSongWindow = new AddSongWindow(800, 600);
            }
        }
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

    public PlaylistsMenuWindow getPlaylistsMenuWindow() {
        return playlistsMenuWindow;
    }

    public DeletePlaylistWindow getDeletePlaylistWindow() {
        return deletePlaylistWindow;
    }

    public EditPlaylistMenuWindow getEditPlaylistMenuWindow() {
        return editPlaylistMenuWindow;
    }

    public CreatePlaylistWindow getCreatePlaylistWindow() {
        return createPlaylistWindow;
    }

    public PlayPlaylistWindow getPlayPlaylistWindow() {
        return playPlaylistWindow;
    }

    public AddSongWindow getAddSongWindow() {
        return addSongWindow;
    }

    private void setUserLoggedIn(boolean userLoggedIn) {
        this.isUserLoggedIn = userLoggedIn;
    }

    public void login(){
        setUserLoggedIn(true);
        setUpGUIS();
        getMainWindow().setVisible(true);
    }

    public void enablePlayPlaylists(int index){
        setHasPlaylists(true);
        setUpGUIS();
        setPlaylistIndex(index);
        playPlaylistWindow.setVisible(true);
    }
    public void setHasPlaylists(boolean hasPlaylists) {
        this.hasPlaylists = hasPlaylists;
    }

    public int getPlaylistIndex() {
        return playlistIndex;
    }

    public void setPlaylistIndex(int playlistIndex) {
        this.playlistIndex = playlistIndex;
    }
}
