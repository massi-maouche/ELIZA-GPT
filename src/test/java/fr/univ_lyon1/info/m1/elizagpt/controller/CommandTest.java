package fr.univ_lyon1.info.m1.elizagpt.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for the {@link Command} class.
 */
public class CommandTest {

    /**
     * Tests the execution of the {@link Command.AddMessageCommand} class.
     */
    @Test
    public void testAddMessageCommandExecution() {
        Controller controller = Controller.getInstance();
        Command.AddMessageCommand addMessageCommand;
        addMessageCommand = new Command.AddMessageCommand(controller, "Hello", true);
        addMessageCommand.execute();
        assertEquals(1, controller.getMessages().size());
    }

    /**
     * Tests the execution of the {@link Command.DeleteMessageCommand} class.
     */
    @Test
    public void testDeleteMessageCommandExecution() {
        Controller controller = Controller.getInstance();
        controller.addMessage("Bonjour", false);
        int messageId = controller.getMessages().get(0).getId();

        Command.DeleteMessageCommand deleteMessageCommand;
        deleteMessageCommand = new Command.DeleteMessageCommand(controller, messageId);
        deleteMessageCommand.execute();
        assertEquals(0, controller.getMessages().size());
    }
}
