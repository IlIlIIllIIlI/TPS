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

        if (title == null || slot == null || title.isEmpty()) {
            throw new IllegalArgumentException("Movie can't be null or empty");
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
        try {
            if (title == null|| title.isEmpty()) {
                throw new IllegalArgumentException();
            }
            for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
                if (movie.getKey().equalsIgnoreCase(title)){
                    this.movies.remove(title);
                    return;
                }
            }

            throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println("Movie does not exist");
        }
    }

    public Slot getMovieSlot(String title) {
        try {
            if (title == null|| title.isEmpty()) {
                throw new IllegalArgumentException();
            }
            for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
                if (movie.getKey().equalsIgnoreCase(title)){
                    return movie.getValue();
                }
            }
            throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println("Movie does not exist");
        }
        return null;
    }

    public void updateMovieSlot(String title, Slot newSlot) {
        try {
            if (title == null|| title.isEmpty()|| newSlot == null) {
                throw new IllegalArgumentException();
            }
            for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
                if (Objects.equals(movie.getKey(), title)){
                    movie.setValue(newSlot);
                    return;
                }
            }

            throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println("Movie does not exist");
        }
    }

    public void display(){
        try {
            if (movies.isEmpty()){
                throw new IndexOutOfBoundsException();
            }
            for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
                System.out.println("Movie : "+movie.getKey());
                movie.getValue().display();
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("There are no movies currently planned");
        }
    }

    public void showStats() {
        System.out.println("There are "+movies.size()+"movies ");
        double totalDuration = 0;
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            totalDuration += movie.getValue().duration;
        }
        System.out.println("Total duration : "+totalDuration);

        HashMap<String, Integer>moviesRoom = new HashMap<>();
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            boolean exists = false;
            for (Map.Entry<String, Integer> room : moviesRoom.entrySet()){
                if (Objects.equals(room.getKey(), movie.getValue().room)){
                    exists = true;
                    room.setValue(room.getValue() + 1);
                }
            }

            if (!exists) {
                moviesRoom.put(movie.getValue().room,1);
            }
        }

        for (Map.Entry<String, Integer> room : moviesRoom.entrySet()){
            System.out.println(room.getKey()+"Has "+room.getValue()+" Movies");
        }
    }

    private boolean importMovie(String[] movieData){
        try {
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
        } catch (IllegalArgumentException e) {
            System.out.println("Couldn't import movie : "+e.getMessage());
            return false;
        }

        return true;
    }

    public void importMovieSchedule(String[][] movies){
        HashMap<String, Slot> backup = new HashMap<>(this.movies);
        int counter =1;
        for (String[] movie : movies){
            if (importMovie(movie)){
                System.out.println("Movie "+counter+" imported successfully");
                counter++;
            }else{
                this.movies.clear();
                this.movies.putAll(backup);
                System.out.println("Process cancelled because movie "+counter+" couldn't be imported correctly");
                return;
            }
        }
    }

    public boolean hasMovieConflicts() {
        Slot[] values = movies.values().toArray(new Slot[movies.size()]);
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
            if (movie.getValue().room.equalsIgnoreCase(room)){
                moviesRoom.add(movie.getValue());
            }
        }
        return moviesRoom;
    }

    public boolean isRoomAvailable(String room, String startTime, double duration) {
        Slot movietime = new Slot(startTime,duration,room);
        for (Map.Entry<String, Slot> movie : this.movies.entrySet()){
            if (movietime.hasTimeConflict(movie.getValue())){
                return true;
            }
        }

        return false;
    }
}
