package fr.univ_lyon1.info.m1.elizagpt.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of French verbs and their conjugations.
 */
public final class VerbManager {
    private static final List<Verb> VERBS = loadVerbs();

    private VerbManager() { }

    /**
     * Loads the list of French verbs from a CSV file.
     *
     * @return The list of French verbs.
     */
    private static List<Verb> loadVerbs() {
        List<Verb> verbs = new ArrayList<>();
        String file = "french-verb-conjugation.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            if (reader.readLine() != null) {

                while ((line = reader.readLine()) != null) {
                    String[] verbsTmp = line.split(",");

                    String firstSingular = verbsTmp[5];
                    String secondPlural = verbsTmp[9];
                    if (!firstSingular.isEmpty()) {
                        verbs.add(new Verb(firstSingular, secondPlural));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("INTERPRETATION ERROR!");
        }

        return verbs;
    }

    /**
     * Gets the list of French verbs.
     *
     * @return The list of French verbs.
     */
    public static List<Verb> getVerbs() {
        return VERBS;
    }
}
