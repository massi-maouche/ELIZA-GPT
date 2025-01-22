package fr.univ_lyon1.info.m1.elizagpt.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for the {@link Verb} class.
 */
public class VerbTest {

    /**
     * Test cases for ToString.
     */
    @Test
    public void testToString() {
        Verb verb = new Verb("parle", "parlez");
        String toStringResult = verb.toString();

        assertEquals("(parle,parlez)", toStringResult);
    }
}
