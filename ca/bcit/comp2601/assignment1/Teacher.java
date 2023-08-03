package ca.bcit.comp2601.assignment1;

/**
 * Models a Teacher
 *
 * @author Logan Dutton-Anderson
 * @version 1.0
 */
public class Teacher extends Person {
    private final String specialty;

    /**
     * Constructs a teacher
     *
     * @param born      the date the person was born
     * @param name      the persons name
     * @param specialty the teacher's specialty
     */
    public Teacher(final Date born,
                   final Name name,
                   final String specialty) {
        super(born, name);
        validateSpecialty(specialty);
        this.specialty = specialty;
    }

    /**
     * Validates that the teacher's specialty is not blank.
     *
     * @param specialty the teacher's specialty
     */
    private static void validateSpecialty(final String specialty) {
        if (specialty.isBlank()) {
            throw new IllegalPersonException("bad specialty");
        }
    }

    /**
     * Overrides the public String toString() method, which returns a String in one of these two exact formats:
     * a) Alive people example: "Tiger Woods (specialty: mathematics) was born 1975-12-30 and is still alive"
     * b) Dead people example: "Albert Einstein (specialty: mathematics) was born 1879-03-14 and died 1955-04-18"
     * Use the parent's getName() return value, then its getPrettyName() method, and the born/died getYyyyMmDd() method
     */
    @Override
    public String toString() {
        Name name;
        String fullName;
        String birthDay;
        String deathDay;

        birthDay = getDateOfBirth().getYyyyMmDd();

        if (!isAlive()) {
            deathDay = getDateOfDeath().getYyyyMmDd();
        } else {
            deathDay = null;
        }

        name = getName();
        fullName = name.getPrettyName();

        if (isAlive()) {
            return fullName + " (specialty: " + specialty + ") was born " + birthDay + " and is still alive";
        } else {
            return fullName + " (specialty: " + specialty + ") was born " + birthDay + " and died " + deathDay;
        }
    }


    /**
     * returns the teacher's specialty
     *
     * @return the teacher's specialty
     */
    public String getSpecialty() {
        return specialty;
    }
}
