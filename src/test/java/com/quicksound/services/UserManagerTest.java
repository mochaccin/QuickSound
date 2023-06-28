package com.quicksound.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    UserManager userManager;
    UserAuthentication userAuthentication;

    @BeforeEach
    void setUp() {
        userManager = UserManager.INSTANCE;
        userAuthentication = UserAuthentication.INSTANCE;
    }

    @AfterEach
    void tearDown() {
        userAuthentication = null;
        userManager = null;
    }

    @Test
    void registerUser() {
        String[] userData = {"Pancho", "completo"};
        userManager.registerUser(userData[0], userData[1]);
        assertEquals("Pancho", userManager.getUsers().get(0).getName());
        assertEquals("completo", userManager.getUsers().get(0).getPassword());
    }

    @Test
    void checkDuplicateUser() {
        String[] userData = {"Pancho", "completo"};
        userManager.registerUser(userData[0], userData[1]);
        assertTrue(userManager.checkDuplicateUser("Pancho"));
        assertFalse(userManager.checkDuplicateUser("Camilo"));
    }

    @Test
    void deleteUser() {
        String[] userData = {"Pancho", "completo"};
        userManager.registerUser(userData[0], userData[1]);
        userAuthentication.login(userData[0], userData[1]);

        userManager.deleteUser();
        assertFalse(userManager.isUserRegistered(userData));
    }

    @Test
    void isUserRegistered() {
        String[] unregisteredUserData = {"Pancho", "completo"};
        String[] registeredUserData = {"sancho", "pansa"};
        userManager.registerUser(registeredUserData[0], registeredUserData[1]);

        assertTrue(userManager.isUserRegistered(registeredUserData));
        assertFalse(userManager.isUserRegistered(unregisteredUserData));
    }

    @Test
    void changeUserUsername() {
        String[] userData = {"Pancho", "completo"};
        userManager.registerUser(userData[0], userData[1]);
        userAuthentication.login(userData[0], userData[1]);

        userManager.changeUserUsername("pepito");

        assertEquals("pepito", userAuthentication.getCurrentUser().getName());
        assertNotEquals("Hyotic", userAuthentication.getCurrentUser().getName());
    }

    @Test
    void changeUserPassword() {

        String[] userData = {"Pancho", "completo"};
        userManager.registerUser(userData[0], userData[1]);
        userAuthentication.login(userData[0], userData[1]);

        userManager.changeUserPassword("12345pizza");

        assertEquals("12345pizza", userAuthentication.getCurrentUser().getPassword());
        assertNotEquals("completo1234", userAuthentication.getCurrentUser().getPassword());

    }
}