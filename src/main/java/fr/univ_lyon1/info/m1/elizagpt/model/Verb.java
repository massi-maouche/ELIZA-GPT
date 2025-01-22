package fr.univ_lyon1.info.m1.elizagpt.model;

/**
 * Information about conjugation of a verb.
 */
public class Verb {
    private final String firstSingular;
    private final String secondPlural;

    public String getFirstSingular() {
        return firstSingular;
    }

    public String getSecondPlural() {
        return secondPlural;
    }

    Verb(final String firstSingular, final String secondPlural) {
        this.firstSingular = firstSingular;
        this.secondPlural = secondPlural;
    }

    @Override
    public String toString() {
        return "(" + this.firstSingular + "," + this.secondPlural + ")";
    }

}
