package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Logic to process a message (and probably reply to it).
 */
public class MessageProcessor {
    private final List<Message> messages = new ArrayList<>();
    private ResponseStrategy responseStrategy;

    /**
     * Normalize the text: remove extra spaces, add a final dot if missing.
     *
     * @return Normalized text.
     */
    public String normalize(final String text) {
        return text.replaceAll("\\s+", " ")
                .replaceAll("^\\s+", "")
                .replaceAll("\\s+$", "")
                .replaceAll("[^.!?:]$", "$0.");
    }

    public void setResponseStrategy(final ResponseStrategy responseStrategy) {
        this.responseStrategy = responseStrategy;
    }

    /**
     * Add a message to the list of messages.
     *
     * @param message The message to add.
     */
    public void addMessage(final Message message) {
        this.messages.add(message);
    }

    /**
     * Delete a message from the list of messages by its ID.
     *
     * @param id The ID of the message to delete.
     */
    public void deleteMessage(final int id) {
        this.messages.removeIf(message -> message.getId() == id);
    }

    /**
     * Get the list of messages.
     *
     * @return The list of messages.
     */
    public List<Message> getMessages() {
        return this.messages;
    }

    /**
     * Get a random message as a reply to the input text.
     *
     * @param text The input text.
     * @return A randomly generated reply.
     */
    public String getRandomMessage(final String text) {
        String normalizedText = normalize(text);
        String response = null;

        if (responseStrategy != null) {
            response = responseStrategy.getResponse(normalizedText, getName());
        }

        return response;
    }

    /**
     * Get the name from the list of messages.
     *
     * @return The name if found, otherwise null.
     */
    private String getName() {
        for (Message message : messages) {
            if (!message.isEliza()) {
                String text = message.getText();
                for (String word : text.split("\\s")) {
                    if (word.equalsIgnoreCase("Je") && text.contains("m'appelle")) {
                        int index = text.indexOf("m'appelle");
                        return text.substring(index + 10).replaceAll("\\.", "");
                    }
                }
            }
        }
        return null;
    }
}
