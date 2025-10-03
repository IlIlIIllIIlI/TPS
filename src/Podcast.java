import java.util.Objects;
public class Podcast extends Media{
    protected String host;
    protected int episodeNumber;
    protected String mail;


    Podcast(String title, int year, double duration,String host, int episodeNumber){
        super(title,year,duration);
        this.host=host;
        this.episodeNumber=episodeNumber;
    }
    @Override
    public void play(){
        System.out.println("Playing podcast : "+this.title);
    }

    @Override
    public void pause(){
        System.out.println("Podcast paused");
    }

    @Override
    public String getMediaType(){
        return "Podcast";
    }

    @Override
    public double getFileSize(){
        return this.duration*7;
    }
    @Override
    public void displayInfo(){
        System.out.println("Title : "+this.title);
        System.out.println("Year : "+this.year);
        System.out.println("Duration : "+this.duration+"min");
        System.out.println("This Media is a "+this.getMediaType());
        System.out.println("Size : "+this.getFileSize()+"MB");

    }

    public void subscribe(String mail){
        this.mail=mail;
        System.out.println("You are subscribed !");
    }

}
