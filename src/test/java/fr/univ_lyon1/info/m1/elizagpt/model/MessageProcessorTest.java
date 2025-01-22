package fr.univ_lyon1.info.m1.elizagpt.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests for MessageProcessor.
 */
public class MessageProcessorTest {

    /**
     * Tests for Adding a message.
     */
    @Test
    public void testAddMessage() {
        MessageProcessor messageProcessor = new MessageProcessor();
        Message message = new Message("Hello, testing!", false);

        messageProcessor.addMessage(message);

        List<Message> messages = messageProcessor.getMessages();
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));
    }

    /**
     * Tests for Deleting a message.
     */
    @Test
    public void testDeleteMessage() {
        MessageProcessor messageProcessor = new MessageProcessor();
        Message message = new Message("Hello, testing!", false);
        messageProcessor.addMessage(message);

        messageProcessor.deleteMessage(message.getId());

        assertTrue(messageProcessor.getMessages().isEmpty());
    }

    /**
     * Tests for getting random message.
     */
    @Test
    public void testGetRandomMessage() {
        MessageProcessor messageProcessor = new MessageProcessor();
        String response = messageProcessor.getRandomMessage("Hello");

        assertNull(response);
    }
}
