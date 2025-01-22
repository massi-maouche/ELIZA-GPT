package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.controller.Command;
import fr.univ_lyon1.info.m1.elizagpt.controller.Command.AddMessageCommand;
import fr.univ_lyon1.info.m1.elizagpt.controller.Command.DeleteMessageCommand;
import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;
import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.view.SearchStrategy.CompleteSearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.view.SearchStrategy.SubstringSearchStrategy;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class of the View (GUI) of the application.
 */
public class View implements Observer {
    private final VBox dialog;
    private TextField text = null;
    private TextField searchText = null;
    private Label searchTextLabel = null;
    private final Controller controller;
    private SearchStrategy searchStrategy;
    private final ComboBox<SearchStrategy> strategyComboBox;
    private final List<List<Message>> searchHistory = new ArrayList<>();

    /**
     * Create the main view of the application.
     */
    public View(final Stage stage, final int width, final int height, final Controller controller) {
        this.controller = controller;
        this.controller.addObserver(this);
        stage.setTitle("Eliza GPT");
        CompleteSearchStrategy completeSearchStrategy = new CompleteSearchStrategy();

        strategyComboBox = completeSearchStrategy.createComboBox();
        strategyComboBox.setValue(new SubstringSearchStrategy());
        strategyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            setSearchStrategy(newValue);
            searchText(searchText);
        });

        final VBox root = new VBox(10);

        final Pane search = createSearchWidget();
        root.getChildren().add(search);

        ScrollPane dialogScroll = new ScrollPane();
        dialog = new VBox(10);
        dialogScroll.setContent(dialog);
        dialogScroll.vvalueProperty().bind(dialog.heightProperty());
        root.getChildren().add(dialogScroll);
        dialogScroll.setFitToWidth(true);

        final Pane input = createInputWidget();
        root.getChildren().add(input);

        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        text.requestFocus();
        stage.show();
    }

    public void setSearchStrategy(final SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    static final String BASE_STYLE = "-fx-padding: 8px; "
            + "-fx-margin: 5px; "
            + "-fx-background-radius: 5px;";
    static final String USER_STYLE = "-fx-background-color: #A0E0A0; " + BASE_STYLE;
    static final String ELIZA_STYLE = "-fx-background-color: #A0A0E0; " + BASE_STYLE;

    @Override
    public void update(final List<Message> messages) {
        dialog.getChildren().clear();

        for (Message message : messages) {
            HBox hBox = new HBox();
            final Label label = new Label(message.getText());
            final Button deleteButton = new Button("X");

            GridPane messagePane = new GridPane();
            messagePane.add(label, 0, 0);
            messagePane.add(deleteButton, 1, 0);

            if (message.isEliza()) {
                messagePane.setStyle(ELIZA_STYLE);
                hBox.setAlignment(Pos.BASELINE_LEFT);
            } else {
                messagePane.setStyle(USER_STYLE);
                hBox.setAlignment(Pos.BASELINE_RIGHT);
            }
            DeleteMessageCommand deleteM = new DeleteMessageCommand(controller, message.getId());
            deleteButton.setOnAction(e -> controller.executeCommand(deleteM));

            hBox.getChildren().add(messagePane);
            dialog.getChildren().add(hBox);
        }
    }

    /**
     * Répond à l'utilisateur en ajoutant un message au dialogue.
     *
     * @param text Le texte de la réponse.
     */
    public void replyToUser(final String text) {
        Command command = new AddMessageCommand(controller, text, true);
        controller.executeCommand(command);
    }

    /**
     * Envoie un message, génère une réponse et la répond à l'utilisateur.
     *
     * @param text Le texte du message.
     */
    private void sendMessage(final String text) {
        Command command = new AddMessageCommand(controller, text, false);
        controller.executeCommand(command);

        String response = controller.generateResponse(text);
        replyToUser(response);
    }

    /**
     * Extract the name of the user from the dialog.
     */
    private Pane createSearchWidget() {
        final HBox firstLine = new HBox();
        final HBox secondLine = new HBox();
        final HBox searchBox = new HBox();
        firstLine.setAlignment(Pos.BASELINE_LEFT);
        secondLine.setAlignment(Pos.BASELINE_LEFT);

        searchText = new TextField();
        searchText.setOnAction(e -> searchText(searchText));
        firstLine.getChildren().add(searchText);

        final Button send = new Button("Search");
        send.setOnAction(e -> searchText(searchText));

        searchTextLabel = new Label();
        final Button undo = new Button("Undo search");
        undo.setOnAction(e -> undoSearch());

        secondLine.getChildren().addAll(send, searchTextLabel, undo);

        searchBox.getChildren().addAll(firstLine, strategyComboBox);

        final VBox input = new VBox();
        input.getChildren().addAll(searchBox, secondLine);

        return input;
    }

    /**
     * Effectue une recherche dans le dialogue en fonction du texte spécifié.
     * Supprime les éléments du dialogue qui ne contiennent pas le texte de recherche.
     *
     * @param text Le texte de recherche.
     */
    private void searchText(final TextField text) {
        String currentSearchText = text.getText();
        if (currentSearchText == null || currentSearchText.isEmpty()) {
            searchTextLabel.setText("No active search");
            update(controller.getMessages());
        } else {
            searchTextLabel.setText("Searching for: " + currentSearchText);
            List<Message> searchResult;
            searchResult = searchStrategy.search(controller.getMessages(), currentSearchText);
            searchHistory.add(new ArrayList<>(controller.getMessages()));
            update(searchResult);
        }

        text.setText("");
    }

    private void undoSearch() {
        if (!searchHistory.isEmpty()) {

            List<Message> previousSearchResult;
            previousSearchResult = searchHistory.remove(searchHistory.size() - 1);


            update(previousSearchResult);

            searchTextLabel.setText("Undo search");
        }
    }

    private Pane createInputWidget() {
        final Pane input = new HBox();
        text = new TextField();
        text.setOnAction(e -> {
            sendMessage(text.getText());
            text.setText("");
        });
        final Button send = new Button("Send");
        send.setOnAction(e -> {
            sendMessage(text.getText());
            text.setText("");
        });
        input.getChildren().addAll(text, send);
        return input;
    }
}
