package com.quicksound;

import com.quicksound.songs.SongLibrary;
import com.quicksound.user.UserManager;

import java.util.Scanner;

public enum Menu {
    INSTANCE;

    public void displayMenu(){
        displayMusicMenu();
    }


    private void displayMainMenu(){

        System.out.println("[0] Iniciar Sesion\n[1] Crear Cuenta\n[2] Iniciar como invitado");
        int option = takeInputInt(0, 2);

        switch(option){
            case 0:
                displayLoginMenu();
                break;
            case 1:
                displayRegisterMenu();
                break;
            default:
                displayGuestUserMenu();
                break;
        }

    }

    private void displayMusicMenu(){

        System.out.println("[0] Reproducir una canción. [1] Acceder al menu de playlists.");
        int option = takeInputInt(0, 1);

        switch(option){
            case 0:
                displaySongMenu();
                break;
            case 1:
                displayPlaylistMenu();
                break;
            default:
                displayMusicMenu();
                break;
        }
    }

    private void displayLoginMenu(){}

    private void displayRegisterMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Por favor introduzca el nombre de usuario.");
        String name = input.nextLine();

        System.out.println("Por favor introduzca la contraseña.");
        String password = input.nextLine();

        UserManager.INSTANCE.registerUser(name, password);
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
