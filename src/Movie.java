import java.util.Objects;

public class Movie extends Media{
    protected String genre;
    protected String director;
    protected String producer;

    Movie(String title, int year, double duration,String genre, String director, String producer){
        super(title,year,duration);
        this.genre=genre;
        this.director=director;
        this.producer=producer;
    }
    @Override
    public void play(){
        System.out.println("Playing movie : "+this.title);
    }

    @Override
    public void pause(){
        System.out.println("Movie paused");
    }

    @Override
    public String getMediaType(){
        return "Movie";
    }

    @Override
    public double getFileSize(){
        return this.duration*10;
    }
    @Override
    public void displayInfo(){
        System.out.println("Title : "+this.title);
        System.out.println("Year : "+this.year);
        System.out.println("Duration : "+this.duration+"min");
        System.out.println("This Media is a "+this.getMediaType());
        System.out.println("Genre : "+this.genre);
        this.showCredit();
        System.out.println("Size : "+this.getFileSize()+"MB");

    }

    public void showCredit() {
        System.out.println("Director : "+this.director);
        System.out.println("Producer : "+this.producer);

    }
}
