package fr.univ_lyon1.info.m1.elizagpt.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test cases for the {@link Controller} class.
 */
class ControllerTest {

    private Controller controller;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        controller = Controller.getInstance();

        if (!controller.getMessages().isEmpty()) {
            controller.getMessages().clear();
        }
    }

    /**
     * Tests the {@link Controller#addMessage(String, boolean)} method.
     */
    @Test
    void testAddMessage() {
        String messageText = "Hello, testing!";
        boolean isEliza = false;

        controller.addMessage(messageText, isEliza);

        assertEquals(1, controller.getMessages().size());
        assertEquals(messageText, controller.getMessages().get(0).getText());
        assertEquals(isEliza, controller.getMessages().get(0).isEliza());
    }

    /**
     * Tests the {@link Controller#deleteMessage(int)} method.
     */
    @Test
    void testDeleteMessage() {
        String messageText = "Message to be deleted";
        boolean isEliza = false;
        controller.addMessage(messageText, isEliza);

        int messageId = controller.getMessages().get(0).getId();
        controller.deleteMessage(messageId);

        assertTrue(controller.getMessages().isEmpty());
    }

    /**
     * Tests the {@link Controller#generateResponse(String)} method.
     */
    @Test
    void testGenerateResponse() {
        String inputText = "say my name";
        String response = controller.generateResponse(inputText);

        assertEquals(response, "HEISENBERG");
    }
}
