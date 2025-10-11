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
            throw new ArrayIndexOutOfBoundsException("Playlist empty :(");
        }
        this.playlist[mediaCount]=media;
        this.mediaCount++;
    }

    public void playAll(){
        if (this.mediaCount == 0) {
            throw new ArrayIndexOutOfBoundsException("Playlist empty :(");
        }
        for (int i = 0; i < this.mediaCount; i++) {
            this.playlist[i].play();
        }
    }

    public void displayPlaylist(){
        if (this.mediaCount == 0) {
            System.out.println("Playlist Empty :(");
            return;
        }
        for (int i = 0; i < this.mediaCount; i++) {
            Media media=this.playlist[i];
            media.displayInfo();
            System.out.println("");
        }
    }

    public double getTotalSize(){
        if (this.mediaCount == 0) {
            throw new ArrayIndexOutOfBoundsException("Playlist empty :(");
        }
        double totalSize=0;
        for (int i = 0; i < this.mediaCount; i++) {
            Media media=this.playlist[i];
            totalSize +=media.getFileSize();
        }
        return totalSize;
    }

    public Media[] getMediaByType(String type){
        if (this.mediaCount == 0) {
            throw new ArrayIndexOutOfBoundsException("Playlist empty :(");
        }
        int countType =0;
        for (int i = 0; i < this.mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.getMediaType().equalsIgnoreCase(type)) {
                countType++;
            }
        }
        Media[] medias = new Media[countType];
        String[] mediasTitle= new String[countType];
        int index=0;
        for (int i = 0; i < this.mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.getMediaType().equalsIgnoreCase(type)) {
                medias[index]=media;
                mediasTitle[index]=media.title;
                index++;
            }
        }

        return medias;
    }

    public Media[] getRecentMedia(int year){
        if (this.mediaCount == 0) {
            throw new ArrayIndexOutOfBoundsException("Playlist empty :(");
        }
        int countAge =0;
        for (int i = 0; i < this.mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.year > year) {
                countAge++;
            }
        }
        Media[] medias = new Media[countAge];
        int index=0;
        for (int i = 0; i < this.mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.year > year) {
                medias[index]=media;
                index++;
            }
        }

        return medias;
    }

    public Media[] getLargeMedia(double sizeLimit){
        if (this.mediaCount == 0) {
            throw new ArrayIndexOutOfBoundsException("Playlist empty :(");
        }
        int countAge =0;
        for (int i = 0; i < this.mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.getFileSize() > sizeLimit) {
                countAge++;
            }
        }
        Media[] medias = new Media[countAge];
        int index=0;
        for (int i = 0; i < this.mediaCount; i++) {
            Media media=this.playlist[i];
            if (media.getFileSize() > sizeLimit) {
                medias[index]=media;
                index++;
            }
        }

        return medias;
    }

    public double getTotalDuration(){
        if (this.mediaCount == 0) {
            throw new ArrayIndexOutOfBoundsException("Playlist empty :(");
        }
        double totalDuration=0;
        for (int i = 0; i < this.mediaCount; i++) {
            Media media=this.playlist[i];
            totalDuration +=media.duration;
        }
        return totalDuration;
    }


}
