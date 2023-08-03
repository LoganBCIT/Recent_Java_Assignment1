package ca.bcit.comp2601.assignment1;

/**
 * Models a Person
 *
 * @author Logan Dutton-Anderson
 * @version 1.0
 */
public class Person implements Comparable<Person> {
    private Date died;
    private final Date born;
    private final Name name;

    /**
     * Constructs a person
     *
     * @param born the date the person was born
     * @param name the persons name
     */
    public Person(final Date born,
                  final Name name) {
        validatePerson(born, name);
        this.born = born;
        this.name = name;
    }

    /**
     * Validates if a person is valid
     *
     * @param born the persons date of birth
     * @param name the persons name
     */
    private static void validatePerson(final Date born, final Name name) {
        if (born == null) {
            throw new IllegalPersonException("invalid date of birth");
        }
        if (name == null) {
            throw new IllegalPersonException("invalid name");
        }
    }

    /**
     * Sets the date of death to the date specified
     *
     * @param dateOfDeath the date of death
     */
    public void die(final Date dateOfDeath) {
        died = dateOfDeath;
    }

    /**
     * Checks if the person is alive.
     *
     * @return true if the person is alive, false otherwise
     */
    public boolean isAlive() {
        return died == null;
    }

    /**
     * Compares this person with another person based on their birthdays.
     * Younger people are considered "larger".
     *
     * @param p the person to compare
     * @return a negative integer if this person is younger, zero if they are the same age,
     * or a positive integer if this person is older
     */
    @Override
    public int compareTo(Person p) {
        return born.compareTo(p.born);
    }

    /**
     * a String in one of these two exact formats:
     * a) Alive people example: "Tiger Woods was born 1975-12-30 and is still alive"
     * b) Dead people example: "Albert Einstein was born 1879-03-14 and died 1955-04-18"
     * Use the name variable's getPrettyName() method, and the born/died getYyyyMmDd() method.
     *
     * @return returns the string
     */
    @Override
    public String toString() {
        String fullName;
        String birthDay;
        String deathDay;

        birthDay = born.getYyyyMmDd();
        if (!isAlive()) {
            deathDay = died.getYyyyMmDd();
        } else {
            deathDay = null;
        }

        fullName = name.getPrettyName();


        if (isAlive()) {
            return fullName + " was born " + birthDay + " and is still alive";
        } else {
            return fullName + " was born " + birthDay + " and died " + deathDay;
        }
    }

    /**
     * Returns the date of death for the person.
     *
     * @return the date of death, or null if the person is still alive
     */
    public Date getDateOfDeath() {
        return died;
    }

    /**
     * Returns the birth date of the person.
     *
     * @return the birth date of the person
     */
    public Date getDateOfBirth() {
        return born;
    }

    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    public Name getName() {
        return name;
    }

}
