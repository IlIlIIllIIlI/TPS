package fr.bts.iris.slam;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD;

    public static Difficulty fromString(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Unknown difficulty");
        }

        try {
            return Difficulty.valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown difficulty: " + text);
        }
    }
}
