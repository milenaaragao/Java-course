//////////////////////// main /////////////////////


package dev.lpa;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Album> albums = new ArrayList<>();

        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Love don't mean a thing", 4.22);
        album.addSong("Holy man", 4.3);
        album.addSong("Hold on", 5.6);
        album.addSong("Lady double dealer", 3.21);
        album.addSong("You can't do it right", 6.23);
        album.addSong("High ball shooter", 4.27);
        album.addSong("The gypsy", 4.2);
        album.addSong("Soldier of fortune", 3.13);
        albums.add(album);

        album = new Album("For those about to rock", "AC/DC");
        album.addSong("For those about to rock", 5.44);
        album.addSong("I put the finger on you", 3.25);
        album.addSong("Lets go", 3.45);
        album.addSong("Inject the venom", 3.33);
        album.addSong("Snowballed", 4.51);
        album.addSong("Evil walks", 3.45);
        album.addSong("C.O.D.", 5.25);
        album.addSong("Breaking the rules", 5.32);
        album.addSong("Night of the long knives", 5.12);
        albums.add(album);

        LinkedList<Song> playList = new LinkedList<Song>();
        albums.get(0).addToPlayList("You can't do it right", playList);
        albums.get(0).addToPlayList("Holy man", playList);
        albums.get(0).addToPlayList("Speed king", playList);  // Does not exist
        albums.get(0).addToPlayList(9, playList);
        albums.get(1).addToPlayList(3, playList);
        albums.get(1).addToPlayList(2, playList);
        albums.get(1).addToPlayList(24, playList);


        System.out.println("\n--- Playlist Contents ---");
        for (Song song : playList) {
            System.out.println(song);
        }// There is no track
    }
}


//////////////////////////// album ///////////////////////

package dev.lpa;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {

    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
    }

    //method to add a new song to the album if it is not already there
    public boolean addSong(String title, double duration) {
        if (findSong(title) == null) { //calls findSong to check if a song with this title isn't in the album yet.
            songs.add(new Song(title, duration)); // creates a new song to the album's list song
            return true;
        } // if song is there (does not return null, it skips adding and returns false.
        return false;
    }

    private Song findSong(String title) { // loop through each song in the songs list
        for (Song song : songs) {
            if (song.getTitle().equals(title)) { // if it find a match, it immediately returns that song object.
                return song;
            }
        } // if it finished the loop without finding a match, it returns null.
        return null;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) { // add song based on its trick number
        int index = trackNumber - 1; // Track numbers start from 1 (-1 java language)
        if (index >= 0 && index < songs.size()) { // check if index is valid
            playList.add(songs.get(index)); //if valid gets the song at that index and adds it to the playlist
            return true; // to say it added the song
        }
        return false; //if the index is invalid.
    }

    public boolean addToPlayList(String title, LinkedList<Song> playList) { //add songs by searching for the songs tittle
        Song song = findSong(title); //calls findSong to find the song object
        if (song != null) { // if song is found add to playlist
            playList.add(song);
            return true; //to say song was added
        }
        return false; // if the song isn't found
    }


}


/////////////////////////////////  song //////////////////////////

package dev.lpa;

public class Song {

    private String title;
    private double duration;

    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + ": " + duration;
    }
}


