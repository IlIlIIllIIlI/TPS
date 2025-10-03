import com.sun.jdi.InconsistentDebugInfoException;

import java.util.Arrays;

public class MediaPlayer {
    private Media[] playlist;
    private int mediaCount;
    private int capacity;

    public MediaPlayer(int capacity){
        this.playlist = new Media[capacity];
        this.mediaCount=0;
        this.capacity=capacity;
    }

    public void addMedia(Media media){
        if (this.mediaCount >= this.capacity) {
            System.out.println("Playlist Full");
            return;
        }
        this.playlist[mediaCount]=media;
        this.mediaCount++;
    }

    public void playAll(){
        if (mediaCount == 0) {
            System.out.println("Playlist Empty :(");
            return;
        }
        for (int i = 0; i < mediaCount; i++) {
            this.playlist[i].play();
        }
    }

    public void displayPlaylist(){
        if (mediaCount == 0) {
            System.out.println("Playlist Empty :(");
            return;
        }
        for (int i = 0; i < mediaCount; i++) {
            Media media=this.playlist[i];
            media.displayInfo();
            System.out.println("");
        }
    }

    public void getTotalSize(){
        if (mediaCount == 0) {
            System.out.println("Playlist Empty :(");
            return;
        }
        double totalSize=0;
        for (int i = 0; i < mediaCount; i++) {
            Media media=this.playlist[i];
            totalSize +=media.getFileSize();
        }

        System.out.println("Total Size : "+totalSize+"MB");
    }

    public Media[] getMediaByType(String type){
        if (mediaCount == 0) {
            System.out.println("Playlist Empty :(");
            return null ;
        }
        int countType =0;
        for (int i = 0; i < mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.getMediaType().equalsIgnoreCase(type)) {
                countType++;
            }
        }
        Media[] medias = new Media[countType];
        String[] mediasTitle= new String[countType];
        int index=0;
        for (int i = 0; i < mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.getMediaType().equalsIgnoreCase(type)) {
                medias[index]=media;
                mediasTitle[index]=media.title;
                index++;
            }
        }

        System.out.println("Tout les "+type.toLowerCase()+"s "+ Arrays.toString(mediasTitle));
        return medias;
    }

    public Media[] getRecentMedia(int year){
        if (mediaCount == 0) {
            System.out.println("Playlist Empty :(");
            return null ;
        }
        int countAge =0;
        for (int i = 0; i < mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.year > year) {
                countAge++;
            }
        }
        Media[] medias = new Media[countAge];
        int index=0;
        for (int i = 0; i < mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.year > year) {
                medias[index]=media;
                index++;
            }
        }

        return medias;
    }

    public Media[] getLargeMedia(double sizeLimit){
        if (mediaCount == 0) {
            System.out.println("Playlist Empty :(");
            return null ;
        }
        int countAge =0;
        for (int i = 0; i < mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.getFileSize() > sizeLimit) {
                countAge++;
            }
        }
        Media[] medias = new Media[countAge];
        int index=0;
        for (int i = 0; i < mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.getFileSize() > sizeLimit) {
                medias[index]=media;
                index++;
            }
        }

        return medias;
    }

    public double getTotalDuration(){
        if (mediaCount == 0) {
            System.out.println("Playlist Empty :(");
            return 0;
        }
        double totalDuration=0;
        for (int i = 0; i < mediaCount; i++) {
            Media media=this.playlist[i];
            totalDuration +=media.duration;
        }

        System.out.println("Total Size : "+totalDuration+"min");
        return totalDuration;
    }


}
