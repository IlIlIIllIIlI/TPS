import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MovieScheduler {
    HashMap<String, Slot> movies;

    MovieScheduler(){
        this.movies = new HashMap<>();
    }

    public void addMovie(String title, Slot slot) {

        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title can't be null or empty");
        }
        if (slot == null) {
            throw new IllegalArgumentException("slot can't be null");
        }
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            if (movie.getKey().equalsIgnoreCase(title)){
                throw new IllegalArgumentException(title+" already exist");
            }
            if (movie.getValue().hasTimeConflict(slot)) {
                throw new IllegalArgumentException(title+" create a conflict");
            }
        }
        this.movies.put(title,slot);
    }

    public void removeMovie(String title) {

        if (title == null|| title.isEmpty()) {
            throw new IllegalArgumentException("Movie does not exist");
        }
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            if (movie.getKey().equalsIgnoreCase(title)){
                    this.movies.remove(title);
                    return;
            }
        }

        throw new IllegalArgumentException("Movie does not exist");
    }

    public Slot getMovieSlot(String title) {

        if (title == null|| title.isEmpty()) {
            throw new IllegalArgumentException("Movie does not exist");
        }
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            if (movie.getKey().equalsIgnoreCase(title)){
                return movie.getValue();
            }
        }
        throw new IllegalArgumentException("Movie does not exist");

    }

    public void updateMovieSlot(String title, Slot newSlot) {
        if (title == null|| title.isEmpty()|| newSlot == null) {
            throw new IllegalArgumentException("Movie does not exist");
        }
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            if (Objects.equals(movie.getKey(), title)){
                movie.setValue(newSlot);
                return;
            }
        }
        throw new IllegalArgumentException("Movie does not exist");
    }

    public void display(){
        if (movies.isEmpty()){
            System.out.println("There are no movies currently planned");
        }
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            System.out.println("Movie : "+movie.getKey());
            movie.getValue().display();
        }
    }

    public void showStats() {
        System.out.println("There are "+movies.size()+"movies ");
        double totalDuration = 0;
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            totalDuration += movie.getValue().getDuration();
        }
        System.out.println("Total duration : "+totalDuration);

        HashMap<String, Integer>moviesRoom = new HashMap<>();
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            boolean exists = false;
            for (Map.Entry<String, Integer> room : moviesRoom.entrySet()){
                if (Objects.equals(room.getKey(), movie.getValue().getRoom())){
                    exists = true;
                    room.setValue(room.getValue() + 1);
                }
            }

            if (!exists) {
                moviesRoom.put(movie.getValue().getRoom(),1);
            }
        }

        for (Map.Entry<String, Integer> room : moviesRoom.entrySet()){
            System.out.println(room.getKey()+"Has "+room.getValue()+" Movies");
        }
    }

    private boolean importMovie(String[] movieData) throws ParseException {
            if (movieData.length !=4){
                throw new IllegalArgumentException("Movie must have exactly a title, start time, duration and room");
            }

            String title = movieData[0];
            String startTime = movieData[1];
            double duration = Double.parseDouble(movieData[2]);
            String room = movieData[3];
            if (duration <1){
                throw new IllegalArgumentException("Duration can't be less then 1 min");
            }
            Slot slot = new Slot(startTime,duration,room);

            this.addMovie(title,slot);

        return true;
    }

    public void importMovieSchedule(String[][] movies){
        HashMap<String, Slot> backup = new HashMap<>(this.movies);
        try {
            for (String[] movie : movies) {
                this.importMovie(movie);
            }

        } catch (Exception e) {
            this.movies.clear();
            this.movies.putAll(backup);
            throw new RuntimeException(e);
        }
    }

    public boolean hasMovieConflicts() {
        Slot[] values = this.movies.values().toArray(new Slot[movies.size()]);
        for (int i = 0; i < values.length ; i++) {
            for (int j = i+1; j < values.length ; j++) {
                if (values[i].hasTimeConflict(values[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Slot> getMoviesByRoom(String room){
        ArrayList<Slot> moviesRoom = new ArrayList<>();
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            if (movie.getValue().getRoom().equalsIgnoreCase(room)){
                moviesRoom.add(movie.getValue());
            }
        }
        return moviesRoom;
    }

    public boolean isRoomAvailable(String room, String startTime, double duration) throws ParseException {
        Slot movietime = new Slot(startTime,duration,room);
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            if (movietime.hasTimeConflict(movie.getValue())){
                return true;
            }
        }

        return false;
    }
}
