public class Song extends Media{
    protected String album;
    protected String artist;

    Song(String title, int year, double duration,String album, String artist){
        super(title,year,duration);
        this.album=album;
        this.artist=artist;
    }
    @Override
    public void play(){
        System.out.println("Playing song : "+this.title);
    }

    @Override
    public void pause(){
        System.out.println("Song paused");
    }

    @Override
    public String getMediaType(){
        System.out.println("Song");
        return "Song";
    }

    @Override
    public double getFileSize(){
        return this.duration*4;
    }

    @Override
    public void displayInfo(){
        System.out.println("Title : "+this.title);
        System.out.println("Year : "+this.year);
        System.out.println("Duration : "+this.duration+"min");
        System.out.println("This Media is a "+this.getMediaType());
        System.out.println("Artist : "+this.artist);
        System.out.println("Album : "+this.album);
        System.out.println("Size : "+this.getFileSize()+"MB");
        this.showLyrics();
    }
    public void showLyrics() {
        System.out.println("Displaying lyrics for "+this.title);
    }
}
