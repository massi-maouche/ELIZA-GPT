package fr.univ_lyon1.info.m1.elizagpt.model;

/**
 * The Message class represents a message in the application.
 */
public class Message {

    private final String text;
    private final int id;
    private final boolean isEliza;
    private static int idSuivant = 1;

    /**
     * Constructs a Message with the given text and Eliza status.
     *
     * @param text    The text of the message.
     * @param isEliza True if the message is from Eliza, false otherwise.
     */
    public Message(final String text, final boolean isEliza) {
        this.text = text;
        this.id = idSuivant;
        idSuivant++;
        this.isEliza = isEliza;
    }

    /**
     * Gets the text of the message.
     *
     * @return The text of the message.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the ID of the message.
     *
     * @return The ID of the message.
     */
    public int getId() {
        return id;
    }

    /**
     * Checks if the message is from Eliza.
     *
     * @return True if the message is from Eliza, false otherwise.
     */
    public boolean isEliza() {
        return isEliza;
    }
}
