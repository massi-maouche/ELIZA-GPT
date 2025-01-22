package fr.univ_lyon1.info.m1.elizagpt.controller;

/**
 * Represents a command in the ElizaGPT application.
 */
public interface Command {
    /**
     * Executes the command.
     */
    void execute();

    /**
     * Concrete Command for adding a message.
     */
    class AddMessageCommand implements Command {
        private final Controller controller;
        private final String text;
        private final boolean isEliza;

        /**
         * Constructs an AddMessageCommand.
         *
         * @param controller The controller instance.
         * @param text        The text of the message.
         * @param isEliza     Indicates whether the message is from Eliza.
         */
public AddMessageCommand(final Controller controller, final String text, final boolean isEliza) {
            this.controller = controller;
            this.text = text;
            this.isEliza = isEliza;
        }

        @Override
        public void execute() {
            controller.addMessage(text, isEliza);
        }
    }

    /**
     * Concrete Command for deleting a message.
     */
    class DeleteMessageCommand implements Command {
        private final Controller controller;
        private final int messageId;

        /**
         * Constructs a DeleteMessageCommand.
         *
         * @param controller The controller instance.
         * @param messageId  The ID of the message to be deleted.
         */
        public DeleteMessageCommand(final Controller controller, final int messageId) {
            this.controller = controller;
            this.messageId = messageId;
        }

        @Override
        public void execute() {
            controller.deleteMessage(messageId);
        }
    }
}
