package fr.univ_lyon1.info.m1.tp_test;

public class CharManipulator implements ICharManipulator {

    @Override
    public String invertOrder(String input) {
        if (input == null) {
            return null;
        }

        char[] chars = input.toCharArray();
        int length = chars.length;

        for (int i = 0; i < length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[length - 1 - i];
            chars[length - 1 - i] = temp;
        }

        return new String(chars);
    }

    @Override
    public String invertCase(String input) {
        if (input == null) {
            return null;
        }

        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];

            if (Character.isUpperCase(currentChar)) {
                chars[i] = Character.toLowerCase(currentChar);
            } else if (Character.isLowerCase(currentChar)) {
                chars[i] = Character.toUpperCase(currentChar);
            }
        }

        return new String(chars);
    }

    @Override
    public String removePattern(String input, String pattern) {
        if (input == null || pattern == null) {
            return null;
        }
        return input.replace(pattern, "");
    }
}
