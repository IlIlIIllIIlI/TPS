import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Slot {
    protected Date startTime;
    protected double duration;
    protected String room;

    Slot(String startTime, double duration, String room) throws ParseException {

        if (startTime == null || duration < 1 || room == null ) {
            throw new IllegalArgumentException("Duration and room can't be null or < 1 ");
        }
        String pattern = "HH'h'mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        this.startTime= simpleDateFormat.parse(startTime);
        this.duration = duration;
        this.room = room;
    }

    Slot(Date startTime, double duration, String room){
        if (startTime == null || duration < 1 || room == null ) {
            throw new IllegalArgumentException("Duration and room can't be null or < 1 ");
        }
        this.startTime= startTime;
        this.duration = duration;
        this.room = room;
    }

    public Date getEndTime(){
        return new Date(this.startTime.getTime() + ((long)duration*(long)60000));
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

    public Date getStartTime() {
        return this.startTime;
    }

    public double getDuration() {
        return this.duration;
    }

    public String getRoom() {
        return this.room;
    }
}
