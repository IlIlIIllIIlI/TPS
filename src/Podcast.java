import java.util.ArrayList;
import java.util.Objects;
public class Podcast extends Media{
    protected String host;
    protected int episodeNumber;
    protected ArrayList<String> mail;

    Podcast(String title, int year, double duration,String host, int episodeNumber){
        super(title,year,duration);
        this.host=host;
        this.episodeNumber=episodeNumber;
        this.mail= new ArrayList<>();
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
        super.displayInfo();
        System.out.println("This Media is a "+this.getMediaType());
        System.out.println("Size : "+this.getFileSize()+"MB");

    }

    public void subscribe(String mail){
        this.mail.add(mail);
        System.out.println("You are subscribed !");
    }

}
