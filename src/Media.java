import java.time.LocalDate;
import java.util.Objects;

abstract class Media {
    protected String title;
    protected int year;
    protected double duration;

    Media(String title, int year, double duration){
        this.title=title;
        this.year=year;
        this.duration=duration;
    }

    public void displayInfo(){
        System.out.println("Title : "+this.title);
        System.out.println("Year : "+this.year);
        System.out.println("Duration : "+this.duration+"min");
    }

    public int getAge(){
       int date = LocalDate.now().getYear();
       int age = date-this.year;
       System.out.println("Age : "+age);
       return age;
    }

    public abstract void play();
    public abstract void pause();
    public abstract String getMediaType();
    public abstract double getFileSize();
}

