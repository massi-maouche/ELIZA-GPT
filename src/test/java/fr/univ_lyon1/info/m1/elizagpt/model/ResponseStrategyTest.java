package fr.univ_lyon1.info.m1.elizagpt.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test cases for the {@link ResponseStrategy} class.
 */
public class ResponseStrategyTest {

    /**
     * Example of testing hello response class.
     */
    @Test
    public void testHelloResponse() {
        ResponseStrategy.HelloResponse helloResponse = new ResponseStrategy.HelloResponse();
        String response = helloResponse.getResponse("Hello", "John");

        assertEquals("Bonjour John.", response);
    }

    /**
     * Example of testing Hresponse class.
     */
    @Test
    public void testHResponse() {
        ResponseStrategy.HResponse hResponse = new ResponseStrategy.HResponse();
        String response = hResponse.getResponse("Some text", "John");

        assertEquals("HEISENBERG", response);
    }

    /**
     * Example of testing NAME response class.
     */
    @Test
    public void testNameResponse() {
        ResponseStrategy.NameResponse nameResponse = new ResponseStrategy.NameResponse();
        String responseWithName = nameResponse.getResponse("Some text", "John");
        String responseWithoutName = nameResponse.getResponse("Some text", null);

        assertEquals("Votre nom est John.", responseWithName);
        assertEquals("Je ne connais pas votre nom.", responseWithoutName);
    }

    /**
     * Example of testing teacher response class.
     */
    @Test
    public void testTeacherResponse() {
        ResponseStrategy.TeacherResponse teacherResponse = new ResponseStrategy.TeacherResponse();
        String response = teacherResponse.getResponse("Some text", "John");

        assertEquals("Le plus Some text est bien sûr votre enseignant de MIF01 !", response);
    }

    /**
     * Example of testing question response class.
     */
    @Test
    public void testQuestionResponse() {
        ResponseStrategy.QuestionResponse questionResponse;
        questionResponse = new ResponseStrategy.QuestionResponse();
        String response = questionResponse.getResponse("Some text", "John");

        assertEquals("Ici c moi qui pose les questions", response);
    }

    /**
     * Example of testing clever response class.
     */
    @Test
    public void testCleverDefaultResponse() {
        ResponseStrategy.CleverDefaultResponse cleverDefaultResponse;
        cleverDefaultResponse = new ResponseStrategy.CleverDefaultResponse();
        String response = cleverDefaultResponse.getResponse("Some text", "John");

        assertNotNull(response);
    }

    /**
     * Example of testing custom response class.
     */
    @Test
    public void testCustomQuestionResponse() {
        ResponseStrategy.CustomQuestionResponse customQuestionResponse;
        customQuestionResponse = new ResponseStrategy.CustomQuestionResponse();
        String response = customQuestionResponse.getResponse("Some text", "John");

        assertNotNull(response);
    }

    /**
     * Example of testing goodbye response class.
     */
    @Test
    public void testGoodbyeResponse() {
        ResponseStrategy.GoodbyeResponse goodbyeResponse = new ResponseStrategy.GoodbyeResponse();
        String responseWithName = goodbyeResponse.getResponse("Some text", "John");
        String responseWithoutName = goodbyeResponse.getResponse("Some text", null);

        assertNotNull(responseWithName);
        assertNotNull(responseWithoutName);
    }

    /**
     * Example of testing general response class.
     */
    @Test
    public void testTextResponse() {
        ResponseStrategy.TextResponse textResponse = new ResponseStrategy.TextResponse();
        String response = textResponse.getResponse("Je m'appelle John.", "John");

        assertEquals("Bonjour John.", response);
    }
}

