package com.quicksound;

import com.quicksound.songs.Player;
import com.quicksound.songs.Playlist;
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

        System.out.println("[0] Iniciar Sesion\n[1] Crear Cuenta\n[2] Iniciar como invitado\n[3] Terminar ejecución");
        int option = takeInputInt(0, 2);

        switch (option) {
            case 0 -> displayLoginMenu();
            case 1 -> displayRegisterMenu();
            case 2 -> displayGuestUserMenu();
            case 3 -> exit();
            default -> displayMainMenu();
        }
    }

    private void displayMusicMenu(){

        System.out.println("[0] Reproducir una canción. [1] Reproducir una playlist. [2] Acceder al menu de playlists.");
        int option = takeInputInt(0, 2);

        switch (option) {
            case 0 -> displaySongMenu();
            case 1 -> displayPlayPlaylistMenu();
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

        if (currentUser.getPlaylistsSize() == 0) {
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
        User currentUser = UserAuthentication.INSTANCE.getCurrentUser();

        System.out.println("Cual playlist desea modificar?");
        currentUser.displayUserPlaylists();
        int option = takeInputInt(0, currentUser.getPlaylistsSize());

        Playlist playlist = currentUser.getPlaylist(option);

        System.out.println("Que cancion deseas eliminar?");
        playlist.displaySongs();
        int target = takeInputInt(0, playlist.getSize());

        playlist.removeSong(playlist.getSongById(target));
        System.out.println("La cancion se ha eliminado exitosamente de la playlist.");
    }

    private void displayAddSongMenu() {

        User currentUser = UserAuthentication.INSTANCE.getCurrentUser();

        System.out.println("Cual playlist desea modificar?");
        currentUser.displayUserPlaylists();
        int option = takeInputInt(0, currentUser.getPlaylistsSize());

        Playlist playlist = currentUser.getPlaylist(option);

        System.out.println("Que cancion deseas agregar?");
        playlist.displaySongs();
        int target = takeInputInt(0, playlist.getSize());

        playlist.addSong(playlist.getSongById(target));
        System.out.println("La cancion se ha agregado exitosamente a la playlist.");
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
        String[] loginData = takeTwoStringInputs();

        if(UserAuthentication.INSTANCE.login(loginData[0], loginData[1])) {
            displayUserMenu();
        } else {
            displayMainMenu();
        }
    }

    private String[] takeTwoStringInputs(){
        System.out.println("Por favor introduzca el nombre de usuario.");
        String name = takeInputString();
        System.out.println("Por favor introduzca la contraseña.");
        String password = takeInputString();
        return new String[] {name, password};
    }

    private void displayRegisterMenu(){

        UserManager userManager = UserManager.INSTANCE;

        String[] userData = takeTwoStringInputs();

        userManager.registerUser(userData[0], userData[1]);

        if (!Objects.equals(userManager.getLastUser().getName(), userData[0])) {
            displayRegisterMenu();
        }
        displayMainMenu();
    }

    private void displayUserMenu() {
        System.out.println("[0] Menu de reproduccion. [1] Menu de playlist. [2] Configuracion de la cuenta. [3] Cerrar sesion.");
        int option = takeInputInt(0, 4);

        switch (option) {
            case 0 -> displayPlayerMenu();
            case 1 -> displayUserPlaylistsMenu();
            case 2 -> displayEditPlaylistMenu();
            case 3 -> logout();
            default -> displayUserMenu();
        }
    }

    private void displayPlayerMenu() {
        System.out.println("[0] Reproducir una cancion. [1] Reproducir una de mis playlists.");
        int option = takeInputInt(0, 2);

        switch (option) {
            case 0 -> displaySongMenu();
            case 1 -> displayPlayPlaylistMenu();
            default -> displayUserMenu();
        }
    }

    private void displayPlayPlaylistMenu() {
        User currentUser = UserAuthentication.INSTANCE.getCurrentUser();
        System.out.println("Que playlist deseas reproducir?");
        currentUser.displayUserPlaylists();
        int option = takeInputInt(0, currentUser.getPlaylistsSize());
        AppController.INSTANCE.playPlaylist(currentUser.getPlaylist(option));
    }

    private void displayGuestUserMenu(){

        System.out.println("[0] Reproducir una cancion. [1] Volver al menu principal.");
        int option = takeInputInt(0, 2);

        switch (option) {
            case 0 -> displaySongMenu();
            case 1 -> displayMainMenu();
            default -> displayGuestUserMenu();
        }
    }

    private void displaySongMenu(){
        System.out.println("Que cancion desea reproducir?");
        SongLibrary.INSTANCE.displaySongs();
        int option = takeInputInt(0, SongLibrary.INSTANCE.getSongLibrarySize());
        AppController.INSTANCE.playSong(SongLibrary.INSTANCE.searchSongById(option));
    }

    private void logout() {
        UserAuthentication.INSTANCE.logout();
        displayMainMenu();
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
