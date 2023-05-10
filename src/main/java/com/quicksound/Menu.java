package com.quicksound;

import com.quicksound.songs.SongLibrary;
import com.quicksound.user.User;
import com.quicksound.user.UserAuthentication;
import com.quicksound.user.UserManager;

import java.util.Objects;
import java.util.Scanner;

public enum Menu {
    INSTANCE;

    public void displayMenu(){
        displayMainMenu();
    }


    private void displayMainMenu(){

        System.out.println("[0] Iniciar Sesion\n[1] Crear Cuenta\n[2] Iniciar como invitado");
        int option = takeInputInt(0, 2);

        switch (option) {
            case 0 -> displayLoginMenu();
            case 1 -> displayRegisterMenu();
            default -> displayGuestUserMenu();
        }

    }

    private void displayMusicMenu(){

        System.out.println("[0] Reproducir una canción. [1] Reproducir una playlist. [2] Acceder al menu de playlists.");
        int option = takeInputInt(0, 2);

        switch (option) {
            case 0 -> displaySongMenu();
            case 1 -> displayPlaylistMenu();
            case 2 -> displayUserPlaylistsMenu();
            default -> displayMusicMenu();
        }
    }

    private void displayUserPlaylistsMenu() {
        System.out.println("[0] Crear una playlist. [1] Editar una playlist. [2] Eliminar una playlist.");
        int option = takeInputInt(0, 2);

        switch (option) {
            case 0 -> displayCreatePlaylistMenu();
            case 1 -> displayEditPlaylistMenu();
            case 2 -> displayRemovePlaylistMenu();
            default -> displayMusicMenu();
        }
    }

    private void displayRemovePlaylistMenu() {

        User currentUser = UserAuthentication.INSTANCE.getCurrentUser();

        if (currentUser.getPlaylistSize() == 0) {
            System.out.println("Usted no tiene ninguna playlist.");
            displayUserPlaylistsMenu();
        } else {
            System.out.println("Cual playlist desea eliminar?");
            currentUser.displayUserPlaylists();
            int option = takeInputInt(0, currentUser.getUserPlaylists().size());

            currentUser.removePlaylist(currentUser.getPlaylist(option));
            displayUserMenu();
        }
    }

    private void displayEditPlaylistMenu() {

        User currentUser = UserAuthentication.INSTANCE.getCurrentUser();

        System.out.println("[0] Agregar cancion. [1] Eliminar una cancion. [2] Cambiar nombre de la playlist. [3] Borrar platlist.");
        int option = takeInputInt(0, 4);

        switch (option) {
            case 0 -> displayAddSongMenu();
            case 1 -> displayRemoveSongMenu();
            case 2 -> displayEditPlaylistMenu();
            case 3 -> currentUser.getUserPlaylists().remove(0);
            default -> displayMusicMenu();
        }
    }

    private void displayRemoveSongMenu() {
    }

    private void displayAddSongMenu() {
    }

    private void displayCreatePlaylistMenu() {

        User currentUser = UserAuthentication.INSTANCE.getCurrentUser();

        System.out.println("Por favor ingrese el nombre de la playlist a crear.");
        String name = takeInputString();

        System.out.println("Por favor ingrese la cantidad de canciones iniciales a agregar.");
        int cant = takeInputInt(0, SongLibrary.INSTANCE.getSongLibrarySize()+1);

        AppController.INSTANCE.createPlaylist(currentUser, name);

        for (int i = 0; i < cant; i++) {
            System.out.println("Que cancion deseas agregar?");
            SongLibrary.INSTANCE.displaySongs();
            int option = takeInputInt(0, SongLibrary.INSTANCE.getSongLibrarySize()+1);
            currentUser.getLastUserPlaylist().addSongById(option);
        }

        displayUserMenu();
    }

    private void displayLoginMenu(){
        String[] loginData = takeLoginInputs();

        if(UserAuthentication.INSTANCE.login(loginData[0], loginData[1])) {
            displayUserMenu();
        } else {
            displayMainMenu();
        }

    }

    private String[] takeLoginInputs(){
        System.out.println("Por favor introduzca el nombre de usuario.");
        String name = takeInputString();
        System.out.println("Por favor introduzca la contraseña.");
        String password = takeInputString();
        return new String[] {name, password};
    }

    private void displayRegisterMenu(){

        UserManager userManager = UserManager.INSTANCE;

        String[] userData = takeRegisterInputs();

        userManager.registerUser(userData[0], userData[1]);

        if (!Objects.equals(userManager.getLastUser().getName(), userData[0])) {
            displayRegisterMenu();
        }

        displayMainMenu();
    }

    private String[] takeRegisterInputs(){

        System.out.println("Por favor introduzca el nombre de usuario.");
        String name = takeInputString();
        System.out.println("Por favor introduzca la contraseña.");
        String password = takeInputString();
        return new String[] {name, password};

    }

    private void displayUserMenu() {
        displayUserPlaylistsMenu();
    }
    private void displayGuestUserMenu(){
        displayMusicMenu();
    }

    private void displayPlaylistMenu(){
    }

    private void displaySongMenu(){
        System.out.println("Que cancion desea reproducir?");
        SongLibrary.INSTANCE.displaySongs();
    }

    private void exit(){
        System.exit(0);
    }

    private String takeInputString(){

        Scanner input = new Scanner(System.in);
        String str = "";

        while(!str.matches("[a-zA-Z]+")){
            try{
                str = input.nextLine();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return str;
    }

    private int takeInputInt(int min, int max){

        Scanner input = new Scanner(System.in);
        String str = "";
        int value = 0;

        while(!str.matches("\\d")){
            try{
                str = input.nextLine();
                value = Integer.parseInt(str);
                if (value < min || value > max){str = "";}
            } catch(Exception e) {
                System.out.println("Please enter a valid number");
            }
        }

        return value;

    }
}
