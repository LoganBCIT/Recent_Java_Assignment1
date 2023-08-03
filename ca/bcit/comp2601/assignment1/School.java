package ca.bcit.comp2601.assignment1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Models a School
 *
 * @author Logan Dutton-Anderson
 * @version 1.0
 */
public class School {
    private final List<Person> roster;

    {
        roster = new ArrayList<>();
    }

    /**
     * Adds a person to the people arrayList if they are not null
     *
     * @param p the person being added
     */
    public void register(Person p) {
        if (p == null) {
            throw new IllegalPersonException("cannot register a non-person");
        }
        roster.add(p);
    }

    /**
     * Prints out all the people in the roster using their respective toString() methods.
     */
    public void printRoster() {
        roster.forEach(System.out::println);
    }

    /**
     * declares a local variable w of type Writeable, which takes three
     * parameters: fullName, yearBorn, and maxYear; for example: "Tiger Woods", 1975, 2022, or "Albert Einstein", 1879,
     * 1955. It uses a lambda expression to loop through the integers from yearBorn to maxYear and prints the person's
     * name and age for each year of life. For example (use all the years; some were omitted here for brevity):
     * Tiger Woods: 1975 (age 0)
     * Tiger Woods: 1976 (age 1)
     * Tiger Woods: 1977 (age 2)
     * Tiger Woods: 1978 (age 3)
     * Tiger Woods: 1979 (age 4)
     * Tiger Woods: 1980 (age 5)
     * ... etc ...
     * Tiger Woods: 2019 (age 44)
     * Tiger Woods: 2020 (age 45)
     * Tiger Woods: 2021 (age 46)
     * Tiger Woods: 2022 (age 47) // if the Person is alive, loop until the current year
     * <p>
     * Finally, loop through your List of Person references and use a lambda expression to determine the argument values
     * to pass to w's printData() method - fullName, yearBorn, and maxYear – as follows:
     * 1. fullName: the Person's getName().getPrettyName()
     * 2. yearBorn: the Person's getDateOfBirth().getYear()
     * 3. maxYear: the current year (if alive), or the Person's getDateOfDeath().getYear() (if not alive).
     * Albert Einstein: 1879 (age 0)
     * Albert Einstein: 1880 (age 1)
     * Albert Einstein: 1881 (age 2)
     * Albert Einstein: 1882 (age 3)
     * ... etc ...
     * Albert Einstein: 1953 (age 74)
     * Albert Einstein: 1954 (age 75)
     * Albert Einstein: 1955 (age 76) // if the Person is not alive, loop until their last year of life
     */
    public void printAgesAndYears() {

        Writeable w;
        w = (fullName, yearBorn, maxYear) -> {
            int age;
            for (int i = yearBorn; i <= maxYear; i++) {
                age = i - yearBorn;
                System.out.print(fullName + ": " + i + " (age " + age + ")" + "\r\n");
            }
        };

        for (Person person : roster) {
            int currentYear;
            String fullName;
            int yearBorn;
            int maxYear;

            currentYear = Date.CURRENT_YEAR;
            fullName = person.getName().getPrettyName();
            yearBorn = person.getDateOfBirth().getYear();

            if (person.isAlive()) {
                maxYear = currentYear;
            } else {
                maxYear = person.getDateOfDeath().getYear();
            }

            w.printData(fullName, yearBorn, maxYear);
        }
    }

    public void saveDetails() {
        String fullName;
        String initials;
        String birthDayOfTheWeek;
        String birthDate;
        String deathDayOfTheWeek;
        String deathDate;

        try (FileWriter filewriter = new FileWriter("people.txt")) {
            for (Person person : roster) {

                fullName = person.getName().getPrettyName();
                initials = person.getName().getInitials();
                birthDayOfTheWeek = person.getDateOfBirth().getDayOfTheWeek();
                birthDate = person.getDateOfBirth().getYyyyMmDd();

                if (!person.isAlive()) {
                    deathDayOfTheWeek = person.getDateOfDeath().getDayOfTheWeek();
                    deathDate = person.getDateOfDeath().getYyyyMmDd();
                } else {
                    deathDate = null;
                    deathDayOfTheWeek = null;
                }


                if (person.isAlive()) {
                    filewriter.write(fullName + " (" + initials + ") was born on " + birthDayOfTheWeek + " "
                            + birthDate + "." + "\r\n");
                } else {
                    filewriter.write(fullName + " (" + initials + ") was born on " + birthDayOfTheWeek + " "
                            + birthDate + " and died on " + deathDayOfTheWeek + " " + deathDate + "." + "\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
