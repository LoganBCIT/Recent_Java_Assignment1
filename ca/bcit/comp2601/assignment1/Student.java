package ca.bcit.comp2601.assignment1;

/**
 * Models a Student
 *
 * @author Logan Dutton-Anderson
 * @version 1.0
 */
public class Student extends Person {
    private final String studentNumber;

    public static final int STUDENT_NUM_LENGTH;

    static {
        STUDENT_NUM_LENGTH = 9;
    }

    /**
     * Constructs a student
     *
     * @param born          the date the person was born
     * @param name          the persons name
     * @param studentNumber the students student number
     */
    public Student(final Date born,
                   final Name name,
                   final String studentNumber) {
        super(born, name);
        validateStudentNumber(studentNumber);
        this.studentNumber = studentNumber;
    }

    /**
     * Validates that the student number is not null or blank, and is exactly 9 characters long
     *
     * @param studentNumber the student number
     */
    private static void validateStudentNumber(final String studentNumber) {
        if (studentNumber == null || studentNumber.isBlank()) {
            throw new IllegalPersonException("bad student number");
        }
        if (studentNumber.length() != STUDENT_NUM_LENGTH) {
            throw new IllegalPersonException("Student Number must be " + STUDENT_NUM_LENGTH + "characters long.");
        }
    }

    /**
     * Returns the student number of the student.
     *
     * @return The student number of the student.
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * Overrides the public String toString() method, which returns a String in one of these two exact formats:
     * a) Alive people example: "Tiger Woods (student number: A12345678) was born 1975-12-30 and is still alive"
     * b) Dead people example: "Albert Einstein (student number: A87654321) was born 1879-03-14 and died 1955-04-18"
     * Use the parent's getName() return value, then its getPrettyName() method, and the born/died getYyyyMmDd()
     * method.
     *
     * @return the string in the exact format
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
            return fullName + " (student number: " + studentNumber + ") was born " + birthDay + " and is still alive";
        } else {
            return fullName + " (student number: " + studentNumber + ") was born " + birthDay + " and died " + deathDay;
        }
    }

}
