package ca.bcit.comp2601.assignment1;

/**
 * Models a Name
 *
 * @author Logan Dutton-Anderson
 * @version 1.0
 */
public class Name {
    private final String first;
    private final String last;

    /**
     * Constructs a Full Name
     *
     * @param first the first name
     * @param last  the last name
     */
    public Name(final String first,
                final String last) {
        validateFirstName(first);
        validateLastName(last);
        this.first = first;
        this.last = last;
    }

    /**
     * Validates the firstName to ensure that it is not null or blank
     *
     * @param firstName the firstName being validated
     */
    private static void validateFirstName(final String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("invalid first name");
        }
    }

    /**
     * Validates the name to ensure that it is not null or blank
     *
     * @param lastName the name being validated
     */
    private static void validateLastName(final String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("invalid last name");
        }
    }

    /**
     * Returns the full name in title case
     *
     * @return the formatted full name
     */
    public String getPrettyName() {
        String formattedFirst;
        String formattedLast;

        formattedFirst = toTitleCase(first);
        formattedLast = toTitleCase(last);

        return formattedFirst + " " + formattedLast;
    }

    /**
     * Returns the initials of the full name.
     *
     * @return The initials of the full name.
     */
    public String getInitials() {
        StringBuilder initials = new StringBuilder();

        if (first != null && !first.isBlank()) {
            initials.append(Character.toUpperCase(first.charAt(0)));
            initials.append('.');
        }

        if (last != null && !last.isEmpty()) {
            initials.append(Character.toUpperCase(last.charAt(0)));
            initials.append('.');
        }

        return initials.toString();
    }

    /**
     * Puts a string into title case
     *
     * @param input the string being edited
     * @return the title cased string
     */
    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        char[] chars;
        boolean capitalizeNext;

        chars = input.toCharArray();
        capitalizeNext = true;

        for (int i = 0; i < chars.length; i++) {
            char currentChar;
            currentChar = chars[i];

            if (Character.isWhitespace(currentChar)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                chars[i] = Character.toTitleCase(currentChar);
                capitalizeNext = false;
            } else {
                chars[i] = Character.toLowerCase(currentChar);
            }
        }

        return new String(chars);
    }

    /**
     * Returns the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getFirst() {
        return first;
    }

    /**
     * Returns the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLast() {
        return last;
    }
}
