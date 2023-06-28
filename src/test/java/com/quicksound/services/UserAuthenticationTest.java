package com.quicksound.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAuthenticationTest {

    UserAuthentication userAuthentication;
    UserManager userManager;

    @BeforeEach
    void setUp() {
        userAuthentication = UserAuthentication.INSTANCE;
        userManager = UserManager.INSTANCE;
    }

    @AfterEach
    void tearDown() {
        userAuthentication = null;
        userManager = null;
    }

    @Test
    void authenticate() {

        assertFalse(userAuthentication.authenticate("alo", "zxc123"));

        userManager.registerUser("alo", "zxc123");

        assertTrue(userAuthentication.authenticate("alo", "zxc123"));
        assertFalse(userAuthentication.authenticate("hola", "pepito"));
    }

    @Test
    void login() {

        assertFalse(userAuthentication.isUserLoggedIn("completoLover", "zxc123"));
        userManager.registerUser("completoLover", "zxc123");

        assertTrue(userAuthentication.login("completoLover", "zxc123"));
        assertFalse(userAuthentication.login("diego", "123"));
    }

    @Test
    void logout() {
        assertEquals(null, userAuthentication.getCurrentUser());
        userManager.registerUser("completoLover", "zxc123");
        userAuthentication.login("completoLover", "zxc123");

        assertTrue(userAuthentication.isUserLoggedIn("completoLover", "zxc123"));
        userAuthentication.logout();
        assertFalse(userAuthentication.isUserLoggedIn("completoLover", "zxc123"));
    }

    @Test
    void addPlaylist() {
        userManager.registerUser("completoLover", "zxc123");

        userAuthentication.login("completoLover", "zxc123");

        userAuthentication.addPlaylist("playlist1");
        assertEquals("playlist1", userAuthentication.getCurrentUser().getPlaylist(0).getName());
        assertFalse(userAuthentication.getCurrentUser().getPlaylistsSize() == 0);

    }

    @Test
    void isUserLoggedIn() {
        userManager.registerUser("completoLover", "zxc123");

        userAuthentication.login("completoLover", "zxc123");

        assertTrue(userAuthentication.isUserLoggedIn("completoLover", "zxc123"));
        assertFalse(userAuthentication.isUserLoggedIn("hola", "zxc"));
    }


    @Test
    void isInputStringValid() {
        // no se aceptan simbolos.
        assertTrue(userAuthentication.isInputStringValid("hola123"));
        assertFalse(userAuthentication.isInputStringValid("}{)(]["));
    }
}