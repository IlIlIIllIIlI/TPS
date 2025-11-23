package fr.bts.iris.slam.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Session {
    protected int id;
    protected String name;
    protected ArrayList<Slide> slides;

    public Session(String name) {
        if (name == null||name.isBlank()) {
            throw new IllegalArgumentException("Session name cannot be null or empty");
        }

        if (name.trim().length()<3) {
            throw new IllegalArgumentException("Session name must be at least 3 characters long");
        }

        if (name.trim().length()>50) {
            throw new IllegalArgumentException("Session name cannot exceed 50 characters");
        }

        String specialChar = "!@#$%^&*()+=[]{}|;:'\",<>?/\\";

        for (int i = 0; i < name.length(); i++) {
            if (specialChar.indexOf(name.charAt(i)) != -1) {
                throw new IllegalArgumentException("Session name contains invalid characters");
            }
        }


        this.name = name;
        this.slides = new ArrayList<>();
        this.id=0;
    }

    public Session(int id, String name) {
        if (name == null||name.isBlank()) {
            throw new IllegalArgumentException("Session name cannot be null or empty");
        }

        if (name.trim().length()<3) {
            throw new IllegalArgumentException("Session name must be at least 3 characters long");
        }

        if (name.trim().length()>50) {
            throw new IllegalArgumentException("Session name cannot exceed 50 characters");
        }

        String specialChar = "!@#$%^&*()+=[]{}|;:'\",<>?/\\";

        for (int i = 0; i < name.length(); i++) {
            if (specialChar.indexOf(name.charAt(i)) != -1) {
                throw new IllegalArgumentException("Session name contains invalid characters");
            }
        }


        this.id = id;
        this.name = name;
        this.slides = new ArrayList<>();

    }

    public void addSlide(Slide slide){
        if (slide == null) {
            throw new IllegalArgumentException("Slide cannot be null");
        }
        this.slides.add(slide);
    }

    public void removeSlide(Slide slide){
        if (slide == null) {
            throw new IllegalArgumentException("Slide cannot be null");
        }
        this.slides.remove(slide);
    }

    public void removeSlideAt(int index){
        if (index<0 || index>=this.slides.size()){
            throw new IndexOutOfBoundsException("Invalid slide index: "+index);
        }

        this.slides.remove(index);
    }

    public Slide getSlide(int index){
        if (index<0 || index>=this.slides.size()){
            throw new IndexOutOfBoundsException("Invalid slide index: "+index);
        }

        return this.slides.get(index);
    }

    public List<Slide> getSlides(){
        return new ArrayList<>(slides);
    }

    public void setName(String name) {
        if (name == null||name.isBlank()) {
            throw new IllegalArgumentException("Session name cannot be null or empty");
        }

        if (name.trim().length()<3) {
            throw new IllegalArgumentException("Session name must be at least 3 characters long");
        }

        if (name.trim().length()>50) {
            throw new IllegalArgumentException("Session name cannot exceed 50 characters");
        }
        String specialChar = "!@#$%^&*()+=[]{}|;:'\",<>?/\\";

        for (int i = 0; i < name.length(); i++) {
            if (specialChar.indexOf(name.charAt(i)) != -1) {
                throw new IllegalArgumentException("Session name contains invalid characters");
            }
        }

        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getSlideCount(){
        return this.slides.size();
    }

    public String getName() {
        return this.name;
    }

    public boolean isEmpty(){
        return this.slides.isEmpty();
    }

    public int clear(){
        this.slides.clear();
        return 0;
    }

    public boolean contains(Slide slide){
        return this.slides.contains(slide);
    }

    public int indexOf(Slide slides){
        return this.slides.indexOf(slides);
    }
}
