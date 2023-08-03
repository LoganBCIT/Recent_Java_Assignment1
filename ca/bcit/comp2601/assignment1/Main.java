package ca.bcit.comp2601.assignment1;

public class Main {
    public static void main(String[] args) {

        School school = new School();

        Student tiger     = new Student(new Date(30, 12, 1975), new Name("tiGer", "woODs"), "A12345678");
        Teacher einstein  = new Teacher(new Date(14, 3, 1879), new Name("alBert", "einstEin"), "physics");
        Person elon      = new Person(new Date(28,6,1971), new Name("eLon", "mUsk"));
        Teacher bruce     = new Teacher(new Date(27,11,1940), new Name("bRuce", "lEe"), "jeet kun do");
        Teacher oprah     = new Teacher(new Date(29, 1,1954), new Name("oprAh", "winFRey"), "life");
        Student ramanujan = new Student(new Date(22, 12, 1887), new Name("srinivasa", "ramanujan"), "A88844411");
        Person wayne     = new Person(new Date(26, 1, 1961), new Name("wAyne", "grEtzky"));

        einstein.die(new Date(18, 4, 1955));
        bruce.die(new Date(20, 7, 1973));
        ramanujan.die(new Date(26, 4, 1920));

        school.register(tiger);
        school.register(einstein);
        school.register(elon);
        school.register(bruce);
        school.register(oprah);
        school.register(ramanujan);
        school.register(wayne);

        school.printAgesAndYears();
        school.saveDetails();
    }
}
