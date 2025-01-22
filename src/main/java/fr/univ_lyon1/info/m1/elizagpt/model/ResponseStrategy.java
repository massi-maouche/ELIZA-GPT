package fr.univ_lyon1.info.m1.elizagpt.model;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Strategy to process a message (and probably reply to it).
 */
public interface ResponseStrategy {
    List<Verb> VERBS = VerbManager.getVerbs();

    /**
     * Returns a response based on the normalized text and name provided.
     *
     * @param normalizedText The normalized text.
     * @param name The name.
     * @return The generated response.
     */
    String getResponse(String normalizedText, String name);

    /**
     * Implementation of the "Hello" response.
     */
    class HelloResponse implements ResponseStrategy {
        @Override
        public String getResponse(final String normalizedText, final String name) {
            return "Bonjour " + name + ".";
        }
    }

    /**
     * Implementation of the "HEISENBERG" response.
     */
    class HResponse implements ResponseStrategy {
        @Override
        public String getResponse(final String normalizedText, final String name) {
            return "HEISENBERG";
        }
    }

    /**
     * Implementation of the response concerning the name.
     */
    class NameResponse implements ResponseStrategy {
        @Override
        public String getResponse(final String normalizedText, final String name) {
            if (name != null) {
                return "Votre nom est " + name + ".";
            } else {
                return "Je ne connais pas votre nom.";
            }
        }
    }

    /**
     * Implementation of the response concerning the teacher.
     */
    class TeacherResponse implements ResponseStrategy {
        @Override
        public String getResponse(final String normalizedText, final String name) {
            return "Le plus " + normalizedText + " est bien sûr votre enseignant de MIF01 !";
        }
    }

    /**
     * Implementation of the response to general questions.
     */
    class QuestionResponse implements ResponseStrategy {
        @Override
        public String getResponse(final String normalizedText, final String name) {
            return "Ici c moi qui pose les questions";
        }
    }

    /**
     * Implementation of the clever default response.
     */
    class CleverDefaultResponse implements ResponseStrategy {
        private final Random random = new Random();

        @Override
        public String getResponse(final String normalizedText, final String name) {
            if (random.nextBoolean()) {
                return cleverResponse();
            } else {
                return defaultResponse(name);
            }
        }

        private String cleverResponse() {
            return pickRandom(new String[]{
                    "Il fait beau aujourd'hui, vous ne trouvez pas ?",
                    "Je ne comprends pas.",
                    "Hmmm, hmm ..."
            });
        }

        private String defaultResponse(final String name) {
            if (name != null) {
                return "Qu'est-ce qui vous fait dire cela, " + name + " ?";
            } else {
                return "Qu'est-ce qui vous fait dire cela ?";
            }
        }

        private String pickRandom(final String[] array) {
            return array[random.nextInt(array.length)];
        }
    }

    /**
     * Implementation of the custom question response.
     */
    class CustomQuestionResponse implements ResponseStrategy {
        private final Random random = new Random();

        @Override
        public String getResponse(final String normalizedText, final String name) {
            final String startQuestion = pickRandom(new String[]{
                    "Pourquoi dites-vous que ",
                    "Pourquoi pensez-vous que ",
                    "Êtes-vous sûr que ",
            });
            return startQuestion + firstToSecondPerson(normalizedText) + " ?";
        }

        /**
         * Implementation of firstToSecondPerson.
         */
        public String firstToSecondPerson(final String text) {
            String processedText = text
                    .replace("mon ", "votre ")
                    .replace("ma ", "votre ")
                    .replace("mes ", "vos ")
                    .replace("moi", "vous");

            for (Verb v : VERBS) {
                processedText = processedText.replaceAll(
                        "[Jj]e " + v.getFirstSingular(),
                        "vous " + v.getSecondPlural());
            }

            return processedText;
        }

        private String pickRandom(final String[] array) {
            return array[random.nextInt(array.length)];
        }
    }

    /**
     * Implementation of the "Au revoir" response.
     */
    class GoodbyeResponse implements ResponseStrategy {
        private final Random random = new Random();

        @Override
        public String getResponse(final String normalizedText, final String name) {
            String response;
            if (name != null) {
                response = pickRandom(new String[]{
                        "Oh non, c'est trop triste de se quitter, " + name + " !",
                        "Au revoir, " + name + "!"
                });
            } else {
                response = pickRandom(new String[]{
                        "Oh non, c'est trop triste de se quitter !",
                        "Au revoir !"
                });
            }
            return response;
        }

        private String pickRandom(final String[] array) {
            return array[random.nextInt(array.length)];
        }
    }

    /**
     * Implementation of the default response for the text.
     */
    class TextResponse implements ResponseStrategy {
        @Override
        public String getResponse(final String normalizedText, final String name) {
            Pattern pattern;
            Matcher matcher;

            pattern = Pattern.compile(".*Je m'appelle (.*)\\.", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(normalizedText);
            if (matcher.matches()) {
                return new HelloResponse().getResponse(normalizedText, name);
            }

            pattern = Pattern.compile("Quel est mon nom \\?", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(normalizedText);
            if (matcher.matches()) {
                return new NameResponse().getResponse(normalizedText, name);
            }

            pattern = Pattern.compile("Qui est le plus (.*) \\?", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(normalizedText);
            if (matcher.matches()) {
                return new TeacherResponse().getResponse(matcher.group(1), name);
            }

            pattern = Pattern.compile("(.*) \\?", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(normalizedText);
            if (matcher.matches()) {
                return new QuestionResponse().getResponse(normalizedText, name);
            }

            pattern = Pattern.compile("SAY MY NAME\\.", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(normalizedText);
            if (matcher.matches()) {
                return new HResponse().getResponse(normalizedText, name);
            }

            pattern = Pattern.compile("(Je .*)\\.", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(normalizedText);
            if (matcher.matches()) {
                return new CustomQuestionResponse().getResponse(matcher.group(1), name);
            }

            pattern = Pattern.compile("Au revoir\\.", Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(normalizedText);
            if (matcher.matches()) {
                return new GoodbyeResponse().getResponse(normalizedText, name);
            }

            return new CleverDefaultResponse().getResponse(normalizedText, name);
        }
    }
}
