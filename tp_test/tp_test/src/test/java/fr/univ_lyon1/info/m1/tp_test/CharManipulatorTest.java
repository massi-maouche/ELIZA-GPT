package fr.univ_lyon1.info.m1.tp_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharManipulatorTest {

    private CharManipulator manipulator;

    @BeforeEach
    public void setUp() {
        manipulator = new CharManipulator();
    }

    @Test
    public void orderNormalString() {
        assertThat("A", is(manipulator.invertOrder("A")));
        assertThat("DCBA", is(manipulator.invertOrder("ABCD")));
        assertThat("321DCBA", is(manipulator.invertOrder("ABCD123")));
    }

    @Test
    public void orderEmptyString()
    {
        assertThat("", is(manipulator.invertOrder("")));
    }

    @Test
    public void invertNormalString() {
        assertThat("A", is(manipulator.invertCase("a")));
        assertThat("DcbA", is(manipulator.invertCase("dCBa")));
        assertThat("321dcAb", is(manipulator.invertCase("321DCaB")));
    }

    @Test
    public void invertEmptyString()
    {
        assertThat("", is(manipulator.invertCase("")));
    }

    @Test
    public void removePattern()
    {
        assertThat("cc", is(manipulator.removePattern("coucou", "ou")));
        assertThat("ab", is(manipulator.removePattern("aabb", "ab")));
        assertThat("Miiippi", is(manipulator.removePattern("Mississippi", "ss")));
    }
}
