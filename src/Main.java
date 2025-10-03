//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer(10);
        Movie avatar = new Movie("Avatar", 2009, 162, "Sci-Fi","James Cameron", "James Cameron");
        Movie inception = new Movie("Inception", 2010,148,"Thriller","Christopher Nolan", "Christopher Nolan");
        Song bohemian = new Song("Bohemian Rhapsody", 1975,6,"A Night At The Opera", "Queen");
        Song thriller = new Song("Thriller", 1982,5.5,"Thriller","Michael Jackson");

        player.addMedia(avatar);
        player.addMedia(inception);
        player.addMedia(bohemian);
        player.addMedia(thriller);
        
        player.displayPlaylist();
        player.getTotalDuration();
        player.getTotalSize();
        player.getMediaByType("song");
        player.playAll();
        player.getRecentMedia(1980);
        player.getLargeMedia(1000);
    }
}