package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A strategy for searching messages based on different criteria.
 */
public interface SearchStrategy {

    /**
     * Searches for messages based on the provided criteria.
     *
     * @param messages   The list of messages to search.
     * @param searchText The search criteria.
     * @return A list of messages matching the search criteria.
     */
    List<Message> search(List<Message> messages, String searchText);

    /**
     * Substring search strategy.
     */
    class SubstringSearchStrategy implements SearchStrategy {
        @Override
        public List<Message> search(final List<Message> messages, final String searchText) {
            return messages.stream()
                    .filter(message -> message.getText().contains(searchText))
                    .collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return "Substring";
        }
    }

    /**
     * Regex search strategy.
     */
    class RegexSearchStrategy implements SearchStrategy {
        @Override
        public List<Message> search(final List<Message> messages, final String searchText) {
            Pattern pattern = Pattern.compile(".*" + searchText + ".*", Pattern.CASE_INSENSITIVE);
            return messages.stream()
                    .filter(message -> pattern.matcher(message.getText()).matches())
                    .collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return "Regex";
        }
    }

    /**
     * Word-complete search strategy.
     */
    class WordCompleteSearchStrategy implements SearchStrategy {
        @Override
        public List<Message> search(final List<Message> messages, final String searchText) {
            String regex = "\\b" + searchText + "\\b";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            return messages.stream()
                    .filter(message -> pattern.matcher(message.getText()).find())
                    .collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return "Mot Complet";
        }
    }

    /**
     * A helper class for creating a ComboBox with available search strategies.
     */
    class CompleteSearchStrategy {
        /**
         * Creates a ComboBox with available search strategies.
         *
         * @return A ComboBox with search strategies.
         */
        public ComboBox<SearchStrategy> createComboBox() {
            return new ComboBox<>(FXCollections.observableArrayList(
                    new SubstringSearchStrategy(),
                    new RegexSearchStrategy(),
                    new WordCompleteSearchStrategy()
            ));
        }
    }
}
