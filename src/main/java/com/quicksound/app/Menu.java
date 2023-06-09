package com.quicksound.app;

import com.quicksound.songs.Playlist;
import com.quicksound.user.User;
import java.util.Scanner;

public enum Menu {

    INSTANCE;
    AppController appController = AppController.INSTANCE;

    public void displayMenu() throws InterruptedException {
        appController.loadSongsToLibrary();
        displayMainMenu();
    }


    private void displayMainMenu() throws InterruptedException {

        System.out.println("[0] Iniciar Sesion\n[1] Crear Cuenta\n[2] Iniciar como invitado\n[3] Terminar ejecución");
        int option = takeInputInt(0, 3);

        switch (option) {
            case 0 -> displayLoginMenu();
            case 1 -> displayRegisterMenu();
            case 2 -> displayGuestUserMenu();
            case 3 -> exit();
            default -> displayMainMenu();
        }
    }

    private void displayMusicMenu() throws InterruptedException {

        System.out.println("[0] Reproducir una canción. [1] Reproducir una playlist. [2] Acceder al menu de playlists. [3] Volver al menu de usuario.");
        int option = takeInputInt(0, 3);

        switch (option) {
            case 0 -> displaySongMenu();
            case 1 -> displayPlayPlaylistMenu();
            case 2 -> displayUserPlaylistsMenu();
            case 3 -> displayUserMenu();
            default -> displayMainMenu();
        }
    }

    private void displayUserPlaylistsMenu() throws InterruptedException {
        System.out.println("[0] Crear una playlist. [1] Editar una playlist. [2] Eliminar una playlist. [3] Volver al menu de usuario.");
        int option = takeInputInt(0, 3);

        switch (option) {
            case 0 -> displayCreatePlaylistMenu();
            case 1 -> displayEditPlaylistMenu();
            case 2 -> displayRemovePlaylistMenu();
            case 3 -> displayUserMenu();
            default -> displayMusicMenu();
        }
    }

    private void displayRemovePlaylistMenu() throws InterruptedException {

        User currentUser = appController.getCurrentUser();

        if (currentUser.getPlaylistsSize() == 0) {
            System.out.println("Usted no tiene ninguna playlist para eliminar.");
            displayUserPlaylistsMenu();
        } else {
            System.out.println("Cual playlist desea eliminar?");
            currentUser.displayUserPlaylists();
            int option = takeInputInt(0, currentUser.getUserPlaylists().size());

            currentUser.removePlaylist(currentUser.getPlaylist(option));

            System.out.println("Playlist eliminada exitosamente.");
            displayUserMenu();
        }
    }

    private void displayEditPlaylistMenu() throws InterruptedException {

        User currentUser = appController.getCurrentUser();

        if (currentUser.getPlaylistsSize() == 0) {
            System.out.println("Usted no tiene ninguna playlist para modificar.");
            displayUserPlaylistsMenu();
        } else {
            System.out.println("Cual playlist desea modificar?");
            currentUser.displayUserPlaylists();
            int target = takeInputInt(0, currentUser.getUserPlaylists().size());

            System.out.println("[0] Agregar cancion. [1] Eliminar una cancion. [2] Cambiar nombre de la playlist. [3] Borrar playlist. [4] Volver al menu de musica.");
            int option = takeInputInt(0, 4);

            switch (option) {
                case 0 -> displayAddSongMenu(target);
                case 1 -> displayRemoveSongMenu(target);
                case 2 -> displayEditPlaylistNameMenu(target);
                case 3 -> displayDeletePlaylistMenu(target);
                case 4 -> displayMusicMenu();
                default -> displayUserMenu();
            }
        }
    }

    private void displayEditPlaylistNameMenu(int playlistId) throws InterruptedException {
        System.out.println("Por favor ingrese el nombre deseado.");
        String newName = takeInputString();
        String oldName = appController.getCurrentUser().getPlaylist(playlistId).getName();


        if (newName.equals(oldName)) {
            System.out.println("No se ha podido cambiar el nombre de la playlist.");
        } else {
            appController.getCurrentUser().getPlaylist(playlistId).changePlaylistName(newName);
            System.out.println("Nombre cambiado exitosamente. Viejo nombre: " + oldName + " | Nuevo nombre: " + newName);
        }
        displayUserPlaylistsMenu();
    }

    private void displayDeletePlaylistMenu(int playlistId) throws InterruptedException {
        appController.getCurrentUser().getUserPlaylists().remove(playlistId);
        System.out.println("Playlist eliminada exitosamente.");
        displayMusicMenu();
    }

    private void displayRemoveSongMenu(int playlistId) throws InterruptedException {

        Playlist playlist = appController.getCurrentUser().getPlaylist(playlistId);

        if (playlist.getSize() == 0) {
            System.out.println("La playlist no tiene ninguna cancion para poder eliminar.");
        } else {

            System.out.println("Que cancion deseas eliminar?");
            playlist.displaySongs();
            int target = takeInputInt(0, playlist.getSize());

            playlist.removeSong(playlist.getSongById(target));
            System.out.println("La cancion se ha eliminado exitosamente de la playlist.");

        }
        displayUserPlaylistsMenu();
    }

    private void displayAddSongMenu(int playlistId) throws InterruptedException {

        if (appController.getLibrarySize() == 0) {
            System.out.println("No hay canciones para agregar a la playlist.");
        } else {

            System.out.println("Que cancion deseas agregar?");
            appController.displaySongs();
            int songId = takeInputInt(0, appController.getLibrarySize());

            appController.addSongToUserPlaylist(playlistId, songId);
            System.out.println("La cancion se ha agregado exitosamente a la playlist.");
        }
        displayUserPlaylistsMenu();
    }

    private void displayCreatePlaylistMenu() throws InterruptedException {

        User currentUser = appController.getCurrentUser();

        System.out.println("Por favor ingrese el nombre de la playlist a crear.");
        String name = takeInputString();

        if (appController.getLibrarySize() == 0) {

            System.out.println("Lamentablemente no hay canciones en la biblioteca para agregar. Pero puedes agregarlas mas adelante.");
            appController.createPlaylist(currentUser, name);
            System.out.println("Playlist creada exitosamente.");

        } else {

            System.out.println("Por favor ingrese la cantidad de canciones iniciales a agregar.");
            int cant = takeInputInt(0, appController.getLibrarySize());

            appController.createPlaylist(currentUser, name);

            for (int i = 0; i < cant; i++) {
                System.out.println("Que cancion deseas agregar?");
                appController.displaySongs();
                int option = takeInputInt(0, appController.getLibrarySize());
                appController.addSongToUserPlaylist(currentUser.getPlaylistsSize()-1, option);
            }
            System.out.println("Playlist creada exitosamente.");
        }
        displayUserMenu();

    }

    private void displayLoginMenu() throws InterruptedException {
        String[] loginData = takeRegisterInputs();

        if(appController.login(loginData[0], loginData[1])) {
            displayUserMenu();
        } else {
            displayMainMenu();
        }
    }

    private String[] takeRegisterInputs(){
        System.out.println("Por favor introduzca el nombre de usuario.");
        String name = takeInputString();
        System.out.println("Por favor introduzca la contraseña.");
        String password = takeInputString();
        return new String[] {name, password};
    }

    private void displayRegisterMenu() throws InterruptedException {

        String[] userData = takeRegisterInputs();
        appController.registerUser(userData[0], userData[1]);

        if (!appController.isUserRegistered(userData)) {
            displayRegisterMenu();
        }
        displayMainMenu();
    }

    private void displayUserMenu() throws InterruptedException {
        System.out.println("[0] Menu de reproduccion. [1] Menu de playlist. [2] Configuracion de la cuenta. [3] Cerrar sesion.");
        int option = takeInputInt(0, 3);

        switch (option) {
            case 0 -> displayMusicMenu();
            case 1 -> displayUserPlaylistsMenu();
            case 2 -> displayUserConfigurationMenu();
            case 3 -> logout();
            default -> displayUserMenu();
        }
    }

    private void displayUserConfigurationMenu() throws InterruptedException {

        System.out.println("[0] Cambiar nombre. [1] Cambiar contraseña. [2] Eliminar cuenta. [3] Cerrar sesion. [4] Volver al menu de usuario.");
        int option = takeInputInt(0, 4);

        switch (option) {
            case 0 -> displayChangeUserNameMenu();
            case 1 -> displayChangeUserPasswordMenu();
            case 2 -> displayDeleteUserAccountMenu();
            case 3 -> logout();
            case 4 -> displayUserMenu();
            default -> displayUserConfigurationMenu();
        }
    }

    private void displayChangeUserPasswordMenu() throws InterruptedException {

        System.out.println("Por favor introduzca la nueva contraseña.");
        String newPassword = takeInputString();

        if (appController.changeUserPassword(newPassword)) {
            System.out.println("Contraseña cambiada exitosamente.");
        } else {
            System.out.println("La contraseña no se ha podido modificar.");
        }

        displayUserMenu();
    }

    private void displayDeleteUserAccountMenu() throws InterruptedException {

        System.out.println("Estas seguro de que quieres eliminar tu cuenta?\n [0] Si. [1] No.");
        int option = takeInputInt(0, 1);

        if (option == 0) {
            appController.deleteUser();
            System.out.println("Nos vemos!");
            displayMainMenu();
        } else {
            System.out.println("Sabia decision.");
            displayUserMenu();
        }

    }

    private void displayChangeUserNameMenu() throws InterruptedException {

        System.out.println("Por favor introduzca el nuevo nombre de usuario.");
        String newUsername = takeInputString();

        if (appController.changeUserUsername(newUsername)) {
            System.out.println("Nombre de usuario cambiado exitosamente.");
        } else {
            System.out.println("El nombre de usuario no se ha podido modificar.");
        }

        displayUserMenu();
    }


    private void displayPlayPlaylistMenu() throws InterruptedException {
        User currentUser = appController.getCurrentUser();

        if (currentUser.getPlaylistsSize() == 0) {
            System.out.println("Usted no tiene ninguna playlist.");
            displayMusicMenu();
        } else {

            System.out.println("Que playlist deseas reproducir?");
            currentUser.displayUserPlaylists();
            int option = takeInputInt(0, currentUser.getPlaylistsSize());

            if (currentUser.getPlaylist(option).getSize() != 0) {
                appController.playPlaylist(currentUser.getPlaylist(option));
            } else {
                System.out.println("La playlist seleccionada esta vacia.");
            }
            displayMusicMenu();
        }

    }

    private void displayGuestUserMenu() throws InterruptedException {

        System.out.println("[0] Reproducir una cancion. [1] Volver al menu principal.");
        int option = takeInputInt(0, 1);

        switch (option) {
            case 0 -> displaySongMenu();
            case 1 -> displayMainMenu();
            default -> displayGuestUserMenu();
        }
    }

    private void displaySongMenu() throws InterruptedException {

        if (appController.getLibrarySize() == 0) {
            System.out.println("No hay ninguna cancion disponible para reproducir.");
        } else {
            System.out.println("Que cancion desea reproducir?");
            appController.displaySongs();
            int option = takeInputInt(0, appController.getLibrarySize());
            appController.playSong(appController.searchSongById(option));
        }

        if (appController.getCurrentUser() != null) {
            displayMusicMenu();
        } else {
           displayGuestUserMenu();
        }
    }

    private void logout() throws InterruptedException {
        appController.logout();
        displayMainMenu();
    }
    private void exit(){
        appController = null;
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
