import java.util.*;

record Artist(String name, List<Album> albumList) {



    /** EXERCISE 1
     * @return Songs with more than 10,000 playbacks sorted by playbackCount (descending order).
     *
     * Expected Result:
     * --- Greatest Songs ---
     * Song[name=Enter Sandman, playbackCount=165362]
     * Song[name=Master Of Puppets (Song), playbackCount=97272]
     * Song[name=Battery, playbackCount=18567]
     * Song[name=Lux AEterna, playbackCount=13425]
     *
     */

    public List<Artist> greatestSongs() {
        return  List.of(new Artist(this.name(), this.albumList()));
    }

}

public class Main {
    public static void main(String[] args) {
        List<Song> masterOfPuppets = List.of(
                new Song("Orion", 9_123),
                new Song("Master Of Puppets (Song)", 97_272),
                new Song("Battery", 18_567));
        List<Song> blackAlbum = List.of(
                new Song("Enter Sandman", 165_362),
                new Song("Through the Never", 6_771),
                new Song("My Friend of Misery", 1_236),
                new Song("Unknown Error", null));
        List<Song> seasons = List.of(
                new Song("Shadows Follow", 1_309),
                new Song("Screaming Suicide", 2_472),
                new Song("Lux AEterna", 13_425));
        Artist artist = new Artist("Metallica", List.of(
                new Album("Master Of Puppets", masterOfPuppets),
                new Album("Black Album", blackAlbum),
                new Album("72 Seasons", seasons)));

        System.out.println("-------------- Greatest Songs --------------");
        artist.greatestSongs().forEach(System.out::println);
    }
}

record Album(String name, List<Song> songs) { }

record Song(String name, Integer playbackCount) { }