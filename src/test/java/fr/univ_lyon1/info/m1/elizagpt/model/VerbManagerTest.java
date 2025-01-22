package fr.univ_lyon1.info.m1.elizagpt.model;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test cases for the {@link VerbManager} class.
 */
public class VerbManagerTest {

    /**
     * Test cases for the getVerbs method.
     */
    @Test
    public void testGetVerbs() {
        List<Verb> verbs = VerbManager.getVerbs();

        assertNotNull(verbs);
    }
}
