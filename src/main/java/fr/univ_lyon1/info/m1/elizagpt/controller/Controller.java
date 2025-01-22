package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.ResponseStrategy.TextResponse;
import fr.univ_lyon1.info.m1.elizagpt.view.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The Controller class handles the interaction between the view and the model.
 */
public final class Controller {

    private final MessageProcessor model;
    private final List<Observer> observers = new ArrayList<>();
    private static Controller instance;


    /**
     * Adds an observer to the list of observers.
     *
     * @param ob The observer to be added.
     */
    public void addObserver(final Observer ob) {
        this.observers.add(ob);
    }

    /**
     * Notifies all observers with the given list of messages.
     *
     * @param messages The list of messages to notify observers with.
     */
    public void notifyObservers(final List<Message> messages) {
        for (Observer observer : observers) {
            observer.update(messages);
        }
    }

    /**
     * Constructs a Controller with a new MessageProcessor.
     */
    private Controller() {
        this.model = new MessageProcessor();
        model.setResponseStrategy(new TextResponse());
    }

    /**
     * Constructs an instance of Controller.
     */
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Adds a new message to the model and notifies observers.
     *
     * @param text     The text of the message.
     * @param isEliza  True if the message is from Eliza, false otherwise.
     */
    public void addMessage(final String text, final boolean isEliza) {
        Message message = new Message(text, isEliza);
        this.model.addMessage(message);
        this.notifyObservers(model.getMessages());
    }

    /**
     * Deletes a message from the model by its ID and notifies observers.
     *
     * @param id The ID of the message to delete.
     */
    public void deleteMessage(final int id) {
        this.model.deleteMessage(id);
        this.notifyObservers(model.getMessages());
    }

    public List<Message> getMessages() {
        return this.model.getMessages();
    }

    /**
     * Generates a response for the given text using the model.
     *
     * @param text The input text.
     * @return The generated response.
     */
    public String generateResponse(final String text) {
        return this.model.getRandomMessage(text);
    }

    /**
     * Executes command.
     */
    public void executeCommand(final Command command) {
        command.execute();
    }

}
