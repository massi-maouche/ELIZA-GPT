package fr.univ_lyon1.info.m1.elizagpt.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test cases for the {@link Message} class.
 */
class MessageTest {

    private Message message;
    private String text;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        text = "Hello, testing!";

        message = new Message(text, false);
    }

    /**
     * Tests Objects.
     */
    @Test
    public void testMessageConstructor() {

        assertEquals(text, message.getText());
        assertFalse(message.isEliza());
        assertTrue(message.getId() > 0);
    }

    /**
     * Example of testing a method same for other methods.
     */
    @Test
    void getText() {
        assertEquals(text, message.getText());
    }
}
