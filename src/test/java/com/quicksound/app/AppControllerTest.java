package com.quicksound.app;

import com.quicksound.songs.Playlist;
import com.quicksound.songs.SongLibrary;
import com.quicksound.user.User;
import com.quicksound.user.UserAuthentication;
import com.quicksound.user.UserManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppControllerTest {

    AppController appController;
    SongLibrary songLibrary;
    UserAuthentication userAuthentication;
    UserManager userManager;
    User user;
    String[] userData;

    @BeforeEach
    void setUp() {
        appController = AppController.INSTANCE;
        songLibrary = SongLibrary.INSTANCE;
        user = new User("Hyotic", "completo1234");
        userData = new String[]{user.getName(), user.getPassword()};
        appController.loadSongsToLibrary();
    }

    @AfterEach
    void tearDown() {
        appController = null;
        userAuthentication = null;
        userManager = null;
        user = null;
        userData = null;
    }

    @Test
    @DisplayName("Show if a playlist is created.")
    void createPlaylist() {
        int valorInicial = user.getPlaylistsSize();
        appController.createPlaylist(user, "playlist1");
        assertEquals(valorInicial+1, user.getPlaylistsSize());
        assertEquals("playlist1", user.getPlaylist(0).getName());
    }

    @Test
    @DisplayName("Verify that the song we get by id is correct.")
    void searchSongById() {
        assertEquals(songLibrary.searchSongById(0), appController.searchSongById(0));
    }

    @Test
    @DisplayName("Verify that the user we get is the actual logged in user.")
    void getCurrentUser() {
        appController.registerUser("gnocchifan123", "chile18");
        appController.login("gnocchifan123", "chile18");
        assertEquals("gnocchifan123", appController.getCurrentUser().getName());
    }

    @Test
    @DisplayName("Display if a user is registered in the db or not.")
    void isUserRegistered() {

        String[] unregisteredUserData = userData;
        String[] registeredUserData = {"sancho", "pansa"};
        appController.registerUser(registeredUserData[0], registeredUserData[1]);

        assertTrue(appController.isUserRegistered(registeredUserData));
        assertFalse(appController.isUserRegistered(unregisteredUserData));

    }

    @Test
    @DisplayName("See if a user is registered successfully.")
    void registerUser() {

        String[] userData = {"Pancho", "completo"};
        appController.registerUser(userData[0], userData[1]);

        assertTrue(appController.isUserRegistered(userData));
    }

    @Test
    @DisplayName("See if a song is added successfully to a X user playlist.")
    void addSongToUserPlaylist() {

        appController.registerUser(user.getName(), user.getPassword());
        appController.login(user.getName(), user.getPassword());
        appController.getCurrentUser().addPlaylist(new Playlist("playlist1"));

        appController.addSongToUserPlaylist(0, 0);

        String playlistTitle = appController.getCurrentUser().getPlaylist(0).getSongById(0).getTitle();

        assertEquals(songLibrary.searchSongById(0).getTitle(), playlistTitle);

    }

    @Test
    @DisplayName("See if a user can login only if the account exists.")
    void isUserLoggedIn() {

        appController.registerUser("completoLover", "zxc123");

        assertTrue(appController.login("completoLover", "zxc123"));
        assertFalse(appController.login("user1", "1234"));

    }

    @Test
    @DisplayName("Verify that the current user account is deleted.")
    void deleteUser() {

        appController.registerUser(userData[0], userData[1]);
        appController.login(userData[0], userData[1]);

        appController.deleteUser();
        assertFalse(appController.isUserRegistered(userData));
    }

    @Test
    @DisplayName("Verify that the user name has been changed.")
    void changeUserUsername() {

        appController.registerUser(userData[0], userData[1]);
        appController.login(userData[0], userData[1]);

        appController.changeUserUsername("pepito");

        assertEquals("pepito", appController.getCurrentUser().getName());
        assertNotEquals("Hyotic", appController.getCurrentUser().getName());

    }

    @Test
    @DisplayName("Verify that the user password has been changed.")
    void changeUserPassword() {

        appController.registerUser(userData[0], userData[1]);
        appController.login(userData[0], userData[1]);

        appController.changeUserPassword("12345pizza");

        assertEquals("12345pizza", appController.getCurrentUser().getPassword());
        assertNotEquals("completo1234", appController.getCurrentUser().getPassword());
    }

    @Test
    @DisplayName("Verify that the user logs out successfully.")
    void logout() {

        appController.registerUser(userData[0], userData[1]);
        appController.login(userData[0], userData[1]);

        assertTrue(appController.isUserLoggedIn(userData[0], userData[1]));

        appController.logout();

        assertFalse(appController.isUserLoggedIn(userData[0], userData[1]));

    }

    @Test
    @DisplayName("Verify that the songs have been loaded successfully to the song library.")
    void loadSongsToLibrary() {
        appController.clearSongLibrary();
        assertEquals(0, appController.getLibrarySize());
        appController.loadSongsToLibrary();
        assertEquals(3, appController.getLibrarySize());
    }

    @Test
    @DisplayName("Verify that the user is actually logged in.")
    void IsUserLoggedIn() {

        appController.registerUser(userData[0], userData[1]);
        appController.login(userData[0], userData[1]);

        assertTrue(appController.isUserLoggedIn(userData[0], userData[1]));
        assertFalse(appController.isUserLoggedIn("lasagna", "gnocchi123"));

    }

    @Test
    @DisplayName("Verify that the song library has been cleared up.")
    void clearSongLibrary() {
        assertEquals(3, appController.getLibrarySize());
        appController.clearSongLibrary();
        assertEquals(0, appController.getLibrarySize());
    }
}