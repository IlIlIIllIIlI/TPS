package fr.bts.iris.slam.model;

import java.util.ArrayList;

public class Deck {
    protected String name;
    protected String description;
    protected int id;
    protected ArrayList<FlashCard> cards;

    public Deck(String name, String description){
        if (name == null|| name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty or null");
        }
        if (description == null|| description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty or null");
        }

        this.description = description;
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public Deck(int id, String name, String description){
        this.name = name;
        this.description = description;
        this.id = id;
        this.cards = new ArrayList<>();

    }


    public void addCard(FlashCard card){
        if (card == null) {
            throw new IllegalArgumentException("FlashCard cannot be null");
        }
        this.cards.add(card);
    }
    public void removeCard(FlashCard card){
        if (card == null) {
            throw new IllegalArgumentException("FlashCard cannot be null");
        }
        this.cards.remove(card);
    }

    public FlashCard getCard(int index) {
        return cards.get(index);
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<FlashCard> getCards() {
        return cards;
    }
}
