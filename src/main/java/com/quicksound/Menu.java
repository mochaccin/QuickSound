package com.quicksound;

import com.quicksound.songs.SongLibrary;

import java.lang.reflect.Type;
import java.util.Scanner;

public enum Menu {
    INSTANCE;

    public void displayMenu(){
        displayMusicMenu();
    }

    private void displayMainMenu(){

    }

    private void displayMusicMenu(){

        Scanner input = new Scanner(System.in);
        System.out.println("[0] Desea reproducir una canción? [1] o una lista de canciones?");
        int option = input.nextInt();

        switch(option){
            case 0:
                displaySongMenu();
                break;
            case 1:
                displayPlaylistMenu();
                break;
            default:
                displayMenu();
                break;
        }


    }

    private void displayUserMenu(){

    }

    private void displayPlaylistMenu(){
    }

    private void displaySongMenu(){

        Scanner input = new Scanner(System.in);
        System.out.println("Que cancion desea reproducir?");

        int id = input.nextInt();

        SongLibrary.INSTANCE.displaySongs();
        AppController.INSTANCE.playSong(SongLibrary.INSTANCE.searchSongById(id));
        input.next();

    }

    private void exit(){
    }

    private boolean validarRango(int num, int[] range){
        if(range[0] >= num && num >= range[1]){
            return false;
        } return true;
    }
}
