import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Slot {
    Date startTime;
    double duration;
    String room;

    Slot(String startTime, double duration, String room){
        try {
            if (startTime == null || duration < 1 || room == null ) {
                throw new IllegalArgumentException();
            }
            String pattern = "HH'h'mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            this.startTime= simpleDateFormat.parse(startTime);
            this.duration = duration;
            this.room = room;
        } catch (ParseException e) {
            System.out.println("Incorrect Format");
        } catch (IllegalArgumentException e){
            System.out.println("Duration and room can't be null or < 1 ");
        }
    }

    public Date getEndTime(){
        return new Date(this.startTime.getTime() + ((int)duration*(long)60000));
    }

    public void display(){
        System.out.println("Start time : "+this.startTime);
        System.out.println("Duration : "+this.duration+" min");
        System.out.println("End time : "+this.getEndTime());
        System.out.println("Room : "+this.room);
    }

    public boolean hasTimeConflict(Slot other){
        if (this.room.equalsIgnoreCase(other.room)){
            if (other.startTime.after(this.startTime)&&other.getEndTime().after(this.startTime)||this.startTime.after(other.startTime)&&this.getEndTime().after(other.startTime)){
                System.out.println("There is a time conflict");
                return true;
            }
        }
        return false;
    }
}
