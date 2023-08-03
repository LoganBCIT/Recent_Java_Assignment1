import ca.bcit.comp2601.assignment1.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



public class Assignment1Tester
{
    private static int score;

    static
    {
        score = 0;
    }

    public static void testName()
    {
        Name n1;
        try
        {
            n1 = new Name(null, "woods");
            n1 = new Name("", "woods");
            n1 = new Name(" ", "woods");
            // code should not continue running; should be an exception
            System.out.println("Name class is wrong: code 1");
            return;
        }
        catch(final IllegalArgumentException e)
        {
            if(!e.getMessage().equals("invalid first name"))
            {
                System.out.println("Name class is wrong: code 2");
                return;
            }
        }
        catch(final Exception e)
        {
            System.out.println("Name class is wrong: code 3");
            return;
        }

        try
        {
            n1 = new Name("tiger", null);
            n1 = new Name("tiger", "");
            n1 = new Name("tiger", "  ");
            // code should not continue running; should be an exception
            System.out.println("Name class is wrong: code 4");
            return;
        }
        catch(final IllegalArgumentException e)
        {
            if(!e.getMessage().equals("invalid last name"))
            {
                System.out.println("Name class is wrong: code 5");
                return;
            }
        }
        catch(final Exception e)
        {
            System.out.println("Name class is wrong: code 6");
            return;
        }

        n1 = new Name("tiGer", "woODs");
        if(!n1.getInitials().equals("T.W."))
        {
            System.out.println("Name class is wrong: code 7");
            return;
        }
        if(!n1.getPrettyName().equals("Tiger Woods"))
        {
            System.out.println("Name class is wrong: code 8");
            return;
        }

        System.out.println("Name class looks good: +10 marks");
        score += 10;
    }

    public static void testPerson()
    {
        Person p1;
        try
        {
            Date born = new Date(30, 12, 1975);
            Name name = new Name("tiGer", "woODs");
            p1 = new Person(born, null);
            // code should not continue running; should be an exception
            System.out.println("Person class is wrong: code 1");
            return;
        }
        catch(final IllegalPersonException e)
        {
            if(!e.getMessage().equals("invalid name"))
            {
                System.out.println("Person class is wrong: code 2");
                return;
            }
        }
        catch(final Exception e)
        {
            System.out.println("Person class is wrong: code 3");
            return;
        }


        try
        {
            Date born = new Date(30, 12, 1975);
            Date died = null;
            Name name = new Name("tiGer", "woODs");
            p1 = new Person(null, name);
            // code should not continue running; should be an exception
            System.out.println("Person class is wrong: code 4");
            return;
        }
        catch(final IllegalPersonException e)
        {
            if(!e.getMessage().equals("invalid date of birth"))
            {
                System.out.println("Person class is wrong: code 5");
                return;
            }
        }
        catch(final Exception e)
        {
            System.out.println("Person class is wrong: code 6");
            return;
        }

        try
        {
            Date born = new Date(30, 12, 1975);
            Date died = null;
            Name name = new Name("tiGer", "woODs");
            p1 = new Person(born, name);

            if(!p1.isAlive())
            {
                System.out.println("Person class is wrong: code 7");
                return;
            }
            if(!p1.getDateOfBirth().getYyyyMmDd().equals("1975-12-30"))
            {
                System.out.println("Person class is wrong: code 8");
                return;
            }
            p1.die(new Date(16, 10, 2020));
            if(!p1.getDateOfDeath().getYyyyMmDd().equals("2020-10-16"))
            {
                System.out.println("Person class is wrong: code 9");
                return;
            }
            if(p1.isAlive())
            {
                System.out.println("Person class is wrong: code 10");
                return;
            }

        }
        catch(final IllegalPersonException e)
        {
            if(!e.getMessage().equals("invalid date of birth"))
            {
                System.out.println("Person class is wrong: code 11");
                return;
            }
        }
        catch(final Exception e)
        {
            System.out.println("Person class is wrong: code 12");
            return;
        }


        // Comparable:
        Date date1 = new Date(13, 7, 1979);
        Date date2 = new Date(13, 8, 1979);
        Date date3 = new Date(13, 7, 1980);
        Date date4 = new Date(12, 7, 1979);

        Person person1 = new Person(date4, new Name("oldest", "oldest"));
        Person person2 = new Person(date3, new Name("youngest","youngest"));
        Person person3 = new Person(date2, new Name("young", "young"));
        Person person4 = new Person(date1, new Name("older", "older"));
        person4.die(new Date(31, 10, 2000));

        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);

        List<Person> sorted = new ArrayList<>();
        sorted.add(person1);
        sorted.add(person4);
        sorted.add(person3);
        sorted.add(person2);

        Collections.sort(people);

        if(!people.equals(sorted))
        {
            System.out.println("Person class is wrong: code 13");
            return;
        }

        System.out.println("Person class looks good: +15 marks");
        score += 15;

    }

    public static void testStudent()
    {
        Student s;
        try
        {
            Date born = new Date(30, 12, 1975);
            Name name = new Name("tiGer", "woODs");
            s = new Student(born, name, null);
            s = new Student(born, name, "");
            s = new Student(born, name, "   ");
            // code should not continue running; should be an exception
            System.out.println("Student class is wrong: code 1");
            return;
        }
        catch(final IllegalPersonException e)
        {
            if(!e.getMessage().equals("bad student number"))
            {
                System.out.println("Student class is wrong: code 2");
                return;
            }
        }
        catch(final Exception e)
        {
            System.out.println("Student class is wrong: code 3");
            return;
        }

        Date born = new Date(30, 12, 1975);
        Name name = new Name("tiGer", "woODs");
        Student s1 = new Student(born, name, "A12345678");
        if(!s1.getStudentNumber().equals("A12345678"))
        {
            System.out.println("Student class is wrong: code 4");
            return;
        }

        if(!s1.toString().equals("Tiger Woods (student number: A12345678) was born 1975-12-30 and is still alive"))
        {
            System.out.println("Student class is wrong: code 5");
            return;
        }


        born = new Date(14, 3, 1879);
        name = new Name("alBerT", "einSTEIN");
        s1 = new Student(born, name, "A00555777");
        s1.die(new Date(18, 4, 1955));

        if(!s1.toString().equals("Albert Einstein (student number: A00555777) was born 1879-03-14 and died 1955-04-18"))
        {
            System.out.println("Student class is wrong: code 6");
            return;
        }

        System.out.println("Student class looks good: +5 marks");
        score += 5;
    }


    public static void testTeacher()
    {
        Teacher s;
        try
        {
            Date born = new Date(30, 12, 1975);
            Name name = new Name("tiGer", "woODs");
            s = new Teacher(born, name, "    ");
            s = new Teacher(born, name, "");
            // code should not continue running; should be an exception
            System.out.println("Teacher class is wrong: code 1");
            return;
        }
        catch(final IllegalPersonException e)
        {
            if(!e.getMessage().equals("bad specialty"))
            {
                System.out.println("Teacher class is wrong: code 2");
                return;
            }
        }
        catch(final Exception e)
        {
            System.out.println("Teacher class is wrong: code 3");
            return;
        }
        Date born = new Date(30, 12, 1975);
        Name name = new Name("tiGer", "woODs");
        Teacher t1 = new Teacher(born, name, "mathematics");
        if(!t1.getSpecialty().equals("mathematics"))
        {
            System.out.println("Teacher class is wrong: code 4");
            return;
        }

        if(!t1.toString().equals("Tiger Woods (specialty: mathematics) was born 1975-12-30 and is still alive"))
        {
            System.out.println("Teacher class is wrong: code 5");
            return;
        }


        born = new Date(14, 3, 1879);
        name = new Name("alBerT", "einSTEIN");
        t1 = new Teacher(born, name, "physics");
        t1.die(new Date(18, 4, 1955));

        if(!t1.toString().equals("Albert Einstein (specialty: physics) was born 1879-03-14 and died 1955-04-18"))
        {
            System.out.println("Teacher class is wrong: code 6");
            return;
        }

        System.out.println("Teacher class looks good: +5 marks");
        score += 5;
    }


    public static void testDate()
    {
        Date d;
        d = new Date(1, 1, 2021);
        if((!d.getDayOfTheWeek().equals("Friday")) ||
           (!d.getYyyyMmDd().equals("2021-01-01")) ||
           (!d.next().getYyyyMmDd().equals("2021-01-02")) ||
           (!d.previous().getYyyyMmDd().equals("2020-12-31")))
        {
            System.out.println("Date class is wrong: code 1");
            return;
        }

        String s;
        Date   d2;
        try
        {
            Date d3 = new Date(1, 0, 1);
            // code should not continue running; should be an exception
            System.out.println("Date class is wrong: code 2");
            return;
        }
        catch(final IllegalArgumentException e)
        {
            if(!e.getMessage().equals("invalid month"))
            {
                System.out.println("Date class is wrong: code 3");
                return;
            }
        }
        catch(final Exception e)
        {
            System.out.println("Date class is wrong: code 4");
            return;
        }

        try
        {
            Date d4 = new Date(29, 2, 2023);
            // code should not continue running; should be an exception
            System.out.println("Date class is wrong: code 5");
            return;
        }
        catch(final IllegalArgumentException e)
        {
            if(!e.getMessage().equals("invalid day of the month"))
            {
                System.out.println("Date class is wrong: code 6");
                return;
            }
        }
        catch(final Exception e)
        {
            System.out.println("Date class is wrong: code 7");
            return;
        }

        try
        {
            Date d4 = new Date(29, 2, 2024);
        }
        catch(final Exception e)
        {
            System.out.println("Date class is wrong: code 8");
            return;
        }

        try
        {
            Date d4 = new Date(31, 1, 0);
            // code should not continue running; should be an exception
            System.out.println("Date class is wrong: code 9");
            return;
        }
        catch(final IllegalArgumentException e)
        {
            if(!e.getMessage().equals("invalid year"))
            {
                System.out.println("Date class is wrong: code 10");
                return;
            }
        }
        catch(final Exception e)
        {
            System.out.println("Date class is wrong: code 11");
            return;
        }

        s  = "";
        d2 = new Date(27, 12, 1971);

        for(int i = 0; i <= 400; i++)
        {
            s += d2.getYyyyMmDd() + ": " + d2.getDayOfTheWeek();
            d2 = d2.next();
        }

        // Comparable:
        Date date1 = new Date(13, 7, 1979);
        Date date2 = new Date(13, 8, 1979);
        Date date3 = new Date(13, 7, 1980);
        Date date4 = new Date(12, 7, 1979);

        List<Date> dates = new ArrayList<>();
        dates.add(date1);
        dates.add(date2);
        dates.add(date3);
        dates.add(date4);

        List<Date> sorted = new ArrayList<>();
        sorted.add(date4);
        sorted.add(date1);
        sorted.add(date2);
        sorted.add(date3);

        Collections.sort(dates);

        if(!sorted.equals(dates))
        {
            System.out.println("Date class is wrong: code 12");
            return;
        }

        if(dates.equals("[1979-07-12, 1979-07-13, 1979-08-13, 1980-07-13]"))
        {
            System.out.println("Date class is wrong: code 13");
            return;
        }




        if(s.equals("1971-12-27: Monday1971-12-28: Tuesday1971-12-29: Wednesday1971-12-30: Thursday1971-12-31: Friday1972-01-01: Saturday1972-01-02: Sunday1972-01-03: Monday1972-01-04: Tuesday1972-01-05: Wednesday1972-01-06: Thursday1972-01-07: Friday1972-01-08: Saturday1972-01-09: Sunday1972-01-10: Monday1972-01-11: Tuesday1972-01-12: Wednesday1972-01-13: Thursday1972-01-14: Friday1972-01-15: Saturday1972-01-16: Sunday1972-01-17: Monday1972-01-18: Tuesday1972-01-19: Wednesday1972-01-20: Thursday1972-01-21: Friday1972-01-22: Saturday1972-01-23: Sunday1972-01-24: Monday1972-01-25: Tuesday1972-01-26: Wednesday1972-01-27: Thursday1972-01-28: Friday1972-01-29: Saturday1972-01-30: Sunday1972-01-31: Monday1972-02-01: Tuesday1972-02-02: Wednesday1972-02-03: Thursday1972-02-04: Friday1972-02-05: Saturday1972-02-06: Sunday1972-02-07: Monday1972-02-08: Tuesday1972-02-09: Wednesday1972-02-10: Thursday1972-02-11: Friday1972-02-12: Saturday1972-02-13: Sunday1972-02-14: Monday1972-02-15: Tuesday1972-02-16: Wednesday1972-02-17: Thursday1972-02-18: Friday1972-02-19: Saturday1972-02-20: Sunday1972-02-21: Monday1972-02-22: Tuesday1972-02-23: Wednesday1972-02-24: Thursday1972-02-25: Friday1972-02-26: Saturday1972-02-27: Sunday1972-02-28: Monday1972-02-29: Tuesday1972-03-01: Wednesday1972-03-02: Thursday1972-03-03: Friday1972-03-04: Saturday1972-03-05: Sunday1972-03-06: Monday1972-03-07: Tuesday1972-03-08: Wednesday1972-03-09: Thursday1972-03-10: Friday1972-03-11: Saturday1972-03-12: Sunday1972-03-13: Monday1972-03-14: Tuesday1972-03-15: Wednesday1972-03-16: Thursday1972-03-17: Friday1972-03-18: Saturday1972-03-19: Sunday1972-03-20: Monday1972-03-21: Tuesday1972-03-22: Wednesday1972-03-23: Thursday1972-03-24: Friday1972-03-25: Saturday1972-03-26: Sunday1972-03-27: Monday1972-03-28: Tuesday1972-03-29: Wednesday1972-03-30: Thursday1972-03-31: Friday1972-04-01: Saturday1972-04-02: Sunday1972-04-03: Monday1972-04-04: Tuesday1972-04-05: Wednesday1972-04-06: Thursday1972-04-07: Friday1972-04-08: Saturday1972-04-09: Sunday1972-04-10: Monday1972-04-11: Tuesday1972-04-12: Wednesday1972-04-13: Thursday1972-04-14: Friday1972-04-15: Saturday1972-04-16: Sunday1972-04-17: Monday1972-04-18: Tuesday1972-04-19: Wednesday1972-04-20: Thursday1972-04-21: Friday1972-04-22: Saturday1972-04-23: Sunday1972-04-24: Monday1972-04-25: Tuesday1972-04-26: Wednesday1972-04-27: Thursday1972-04-28: Friday1972-04-29: Saturday1972-04-30: Sunday1972-05-01: Monday1972-05-02: Tuesday1972-05-03: Wednesday1972-05-04: Thursday1972-05-05: Friday1972-05-06: Saturday1972-05-07: Sunday1972-05-08: Monday1972-05-09: Tuesday1972-05-10: Wednesday1972-05-11: Thursday1972-05-12: Friday1972-05-13: Saturday1972-05-14: Sunday1972-05-15: Monday1972-05-16: Tuesday1972-05-17: Wednesday1972-05-18: Thursday1972-05-19: Friday1972-05-20: Saturday1972-05-21: Sunday1972-05-22: Monday1972-05-23: Tuesday1972-05-24: Wednesday1972-05-25: Thursday1972-05-26: Friday1972-05-27: Saturday1972-05-28: Sunday1972-05-29: Monday1972-05-30: Tuesday1972-05-31: Wednesday1972-06-01: Thursday1972-06-02: Friday1972-06-03: Saturday1972-06-04: Sunday1972-06-05: Monday1972-06-06: Tuesday1972-06-07: Wednesday1972-06-08: Thursday1972-06-09: Friday1972-06-10: Saturday1972-06-11: Sunday1972-06-12: Monday1972-06-13: Tuesday1972-06-14: Wednesday1972-06-15: Thursday1972-06-16: Friday1972-06-17: Saturday1972-06-18: Sunday1972-06-19: Monday1972-06-20: Tuesday1972-06-21: Wednesday1972-06-22: Thursday1972-06-23: Friday1972-06-24: Saturday1972-06-25: Sunday1972-06-26: Monday1972-06-27: Tuesday1972-06-28: Wednesday1972-06-29: Thursday1972-06-30: Friday1972-07-01: Saturday1972-07-02: Sunday1972-07-03: Monday1972-07-04: Tuesday1972-07-05: Wednesday1972-07-06: Thursday1972-07-07: Friday1972-07-08: Saturday1972-07-09: Sunday1972-07-10: Monday1972-07-11: Tuesday1972-07-12: Wednesday1972-07-13: Thursday1972-07-14: Friday1972-07-15: Saturday1972-07-16: Sunday1972-07-17: Monday1972-07-18: Tuesday1972-07-19: Wednesday1972-07-20: Thursday1972-07-21: Friday1972-07-22: Saturday1972-07-23: Sunday1972-07-24: Monday1972-07-25: Tuesday1972-07-26: Wednesday1972-07-27: Thursday1972-07-28: Friday1972-07-29: Saturday1972-07-30: Sunday1972-07-31: Monday1972-08-01: Tuesday1972-08-02: Wednesday1972-08-03: Thursday1972-08-04: Friday1972-08-05: Saturday1972-08-06: Sunday1972-08-07: Monday1972-08-08: Tuesday1972-08-09: Wednesday1972-08-10: Thursday1972-08-11: Friday1972-08-12: Saturday1972-08-13: Sunday1972-08-14: Monday1972-08-15: Tuesday1972-08-16: Wednesday1972-08-17: Thursday1972-08-18: Friday1972-08-19: Saturday1972-08-20: Sunday1972-08-21: Monday1972-08-22: Tuesday1972-08-23: Wednesday1972-08-24: Thursday1972-08-25: Friday1972-08-26: Saturday1972-08-27: Sunday1972-08-28: Monday1972-08-29: Tuesday1972-08-30: Wednesday1972-08-31: Thursday1972-09-01: Friday1972-09-02: Saturday1972-09-03: Sunday1972-09-04: Monday1972-09-05: Tuesday1972-09-06: Wednesday1972-09-07: Thursday1972-09-08: Friday1972-09-09: Saturday1972-09-10: Sunday1972-09-11: Monday1972-09-12: Tuesday1972-09-13: Wednesday1972-09-14: Thursday1972-09-15: Friday1972-09-16: Saturday1972-09-17: Sunday1972-09-18: Monday1972-09-19: Tuesday1972-09-20: Wednesday1972-09-21: Thursday1972-09-22: Friday1972-09-23: Saturday1972-09-24: Sunday1972-09-25: Monday1972-09-26: Tuesday1972-09-27: Wednesday1972-09-28: Thursday1972-09-29: Friday1972-09-30: Saturday1972-10-01: Sunday1972-10-02: Monday1972-10-03: Tuesday1972-10-04: Wednesday1972-10-05: Thursday1972-10-06: Friday1972-10-07: Saturday1972-10-08: Sunday1972-10-09: Monday1972-10-10: Tuesday1972-10-11: Wednesday1972-10-12: Thursday1972-10-13: Friday1972-10-14: Saturday1972-10-15: Sunday1972-10-16: Monday1972-10-17: Tuesday1972-10-18: Wednesday1972-10-19: Thursday1972-10-20: Friday1972-10-21: Saturday1972-10-22: Sunday1972-10-23: Monday1972-10-24: Tuesday1972-10-25: Wednesday1972-10-26: Thursday1972-10-27: Friday1972-10-28: Saturday1972-10-29: Sunday1972-10-30: Monday1972-10-31: Tuesday1972-11-01: Wednesday1972-11-02: Thursday1972-11-03: Friday1972-11-04: Saturday1972-11-05: Sunday1972-11-06: Monday1972-11-07: Tuesday1972-11-08: Wednesday1972-11-09: Thursday1972-11-10: Friday1972-11-11: Saturday1972-11-12: Sunday1972-11-13: Monday1972-11-14: Tuesday1972-11-15: Wednesday1972-11-16: Thursday1972-11-17: Friday1972-11-18: Saturday1972-11-19: Sunday1972-11-20: Monday1972-11-21: Tuesday1972-11-22: Wednesday1972-11-23: Thursday1972-11-24: Friday1972-11-25: Saturday1972-11-26: Sunday1972-11-27: Monday1972-11-28: Tuesday1972-11-29: Wednesday1972-11-30: Thursday1972-12-01: Friday1972-12-02: Saturday1972-12-03: Sunday1972-12-04: Monday1972-12-05: Tuesday1972-12-06: Wednesday1972-12-07: Thursday1972-12-08: Friday1972-12-09: Saturday1972-12-10: Sunday1972-12-11: Monday1972-12-12: Tuesday1972-12-13: Wednesday1972-12-14: Thursday1972-12-15: Friday1972-12-16: Saturday1972-12-17: Sunday1972-12-18: Monday1972-12-19: Tuesday1972-12-20: Wednesday1972-12-21: Thursday1972-12-22: Friday1972-12-23: Saturday1972-12-24: Sunday1972-12-25: Monday1972-12-26: Tuesday1972-12-27: Wednesday1972-12-28: Thursday1972-12-29: Friday1972-12-30: Saturday1972-12-31: Sunday1973-01-01: Monday1973-01-02: Tuesday1973-01-03: Wednesday1973-01-04: Thursday1973-01-05: Friday1973-01-06: Saturday1973-01-07: Sunday1973-01-08: Monday1973-01-09: Tuesday1973-01-10: Wednesday1973-01-11: Thursday1973-01-12: Friday1973-01-13: Saturday1973-01-14: Sunday1973-01-15: Monday1973-01-16: Tuesday1973-01-17: Wednesday1973-01-18: Thursday1973-01-19: Friday1973-01-20: Saturday1973-01-21: Sunday1973-01-22: Monday1973-01-23: Tuesday1973-01-24: Wednesday1973-01-25: Thursday1973-01-26: Friday1973-01-27: Saturday1973-01-28: Sunday1973-01-29: Monday1973-01-30: Tuesday"))
        {
            System.out.println("Date class looks good: +40 marks");
            score += 40;
        }
        else
        {
            System.out.println("Date class is wrong: code 14");
            return;
        }
    }

    public static void testSchool()
    {
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

        try
        {
            school.register(null);
            // school should throw exception, not continue running with null person registering
            System.out.println("School class is wrong: code 1");
            return;
        }
        catch(final IllegalPersonException e)
        {
            if(!e.getMessage().equals("cannot register a non-person"))
            {
                System.out.println("School class is wrong: code 2");
                return;
            }
        }
        catch(final Exception e)
        {
            System.out.println("School class is wrong: code 3");
            return;
        }

        // the following tests whether printRoster() is producing correct output:
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        school.printRoster();
        String output = (outContent.toString());
        System.setOut(originalOut);

        if(output.equals("Tiger Woods (student number: A12345678) was born 1975-12-30 and is still alive"+
                System.lineSeparator()+
                "Albert Einstein (specialty: physics) was born 1879-03-14 and died 1955-04-18"
                +System.lineSeparator()+
                "Elon Musk was born 1971-06-28 and is still alive"+
                System.lineSeparator()+
                "Bruce Lee (specialty: jeet kun do) was born 1940-11-27 and died 1973-07-20"+
                System.lineSeparator()+
                "Oprah Winfrey (specialty: life) was born 1954-01-29 and is still alive"+
                System.lineSeparator()+"Srinivasa Ramanujan (student number: A88844411) was born 1887-12-22 and died 1920-04-26"+
                System.lineSeparator()+"Wayne Gretzky was born 1961-01-26 and is still alive"+
                System.lineSeparator()))
        {
            System.out.println("School printRoster() seems correct");
        }
        else
        {
            System.out.println("School class is wrong: code 4");
            return;
        }


        // the following tests whether printRoster() is producing correct output:
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        school.printAgesAndYears();
        output = (outContent.toString());
        System.setOut(originalOut);

        if(output.equals("Tiger Woods: 1975 (age 0)"+System.lineSeparator()+"Tiger Woods: 1976 (age 1)"+System.lineSeparator()+"Tiger Woods: 1977 (age 2)"+System.lineSeparator()+"Tiger Woods: 1978 (age 3)"+System.lineSeparator()+"Tiger Woods: 1979 (age 4)"+System.lineSeparator()+"Tiger Woods: 1980 (age 5)"+System.lineSeparator()+"Tiger Woods: 1981 (age 6)"+System.lineSeparator()+"Tiger Woods: 1982 (age 7)"+System.lineSeparator()+"Tiger Woods: 1983 (age 8)"+System.lineSeparator()+"Tiger Woods: 1984 (age 9)"+System.lineSeparator()+"Tiger Woods: 1985 (age 10)"+System.lineSeparator()+"Tiger Woods: 1986 (age 11)"+System.lineSeparator()+"Tiger Woods: 1987 (age 12)"+System.lineSeparator()+"Tiger Woods: 1988 (age 13)"+System.lineSeparator()+"Tiger Woods: 1989 (age 14)"+System.lineSeparator()+"Tiger Woods: 1990 (age 15)"+System.lineSeparator()+"Tiger Woods: 1991 (age 16)"+System.lineSeparator()+"Tiger Woods: 1992 (age 17)"+System.lineSeparator()+"Tiger Woods: 1993 (age 18)"+System.lineSeparator()+"Tiger Woods: 1994 (age 19)"+System.lineSeparator()+"Tiger Woods: 1995 (age 20)"+System.lineSeparator()+"Tiger Woods: 1996 (age 21)"+System.lineSeparator()+"Tiger Woods: 1997 (age 22)"+System.lineSeparator()+"Tiger Woods: 1998 (age 23)"+System.lineSeparator()+"Tiger Woods: 1999 (age 24)"+System.lineSeparator()+"Tiger Woods: 2000 (age 25)"+System.lineSeparator()+"Tiger Woods: 2001 (age 26)"+System.lineSeparator()+"Tiger Woods: 2002 (age 27)"+System.lineSeparator()+"Tiger Woods: 2003 (age 28)"+System.lineSeparator()+"Tiger Woods: 2004 (age 29)"+System.lineSeparator()+"Tiger Woods: 2005 (age 30)"+System.lineSeparator()+"Tiger Woods: 2006 (age 31)"+System.lineSeparator()+"Tiger Woods: 2007 (age 32)"+System.lineSeparator()+"Tiger Woods: 2008 (age 33)"+System.lineSeparator()+"Tiger Woods: 2009 (age 34)"+System.lineSeparator()+"Tiger Woods: 2010 (age 35)"+System.lineSeparator()+"Tiger Woods: 2011 (age 36)"+System.lineSeparator()+"Tiger Woods: 2012 (age 37)"+System.lineSeparator()+"Tiger Woods: 2013 (age 38)"+System.lineSeparator()+"Tiger Woods: 2014 (age 39)"+System.lineSeparator()+"Tiger Woods: 2015 (age 40)"+System.lineSeparator()+"Tiger Woods: 2016 (age 41)"+System.lineSeparator()+"Tiger Woods: 2017 (age 42)"+System.lineSeparator()+"Tiger Woods: 2018 (age 43)"+System.lineSeparator()+"Tiger Woods: 2019 (age 44)"+System.lineSeparator()+"Tiger Woods: 2020 (age 45)"+System.lineSeparator()+"Tiger Woods: 2021 (age 46)"+System.lineSeparator()+"Tiger Woods: 2022 (age 47)"+System.lineSeparator()+"Albert Einstein: 1879 (age 0)"+System.lineSeparator()+"Albert Einstein: 1880 (age 1)"+System.lineSeparator()+"Albert Einstein: 1881 (age 2)"+System.lineSeparator()+"Albert Einstein: 1882 (age 3)"+System.lineSeparator()+"Albert Einstein: 1883 (age 4)"+System.lineSeparator()+"Albert Einstein: 1884 (age 5)"+System.lineSeparator()+"Albert Einstein: 1885 (age 6)"+System.lineSeparator()+"Albert Einstein: 1886 (age 7)"+System.lineSeparator()+"Albert Einstein: 1887 (age 8)"+System.lineSeparator()+"Albert Einstein: 1888 (age 9)"+System.lineSeparator()+"Albert Einstein: 1889 (age 10)"+System.lineSeparator()+"Albert Einstein: 1890 (age 11)"+System.lineSeparator()+"Albert Einstein: 1891 (age 12)"+System.lineSeparator()+"Albert Einstein: 1892 (age 13)"+System.lineSeparator()+"Albert Einstein: 1893 (age 14)"+System.lineSeparator()+"Albert Einstein: 1894 (age 15)"+System.lineSeparator()+"Albert Einstein: 1895 (age 16)"+System.lineSeparator()+"Albert Einstein: 1896 (age 17)"+System.lineSeparator()+"Albert Einstein: 1897 (age 18)"+System.lineSeparator()+"Albert Einstein: 1898 (age 19)"+System.lineSeparator()+"Albert Einstein: 1899 (age 20)"+System.lineSeparator()+"Albert Einstein: 1900 (age 21)"+System.lineSeparator()+"Albert Einstein: 1901 (age 22)"+System.lineSeparator()+"Albert Einstein: 1902 (age 23)"+System.lineSeparator()+"Albert Einstein: 1903 (age 24)"+System.lineSeparator()+"Albert Einstein: 1904 (age 25)"+System.lineSeparator()+"Albert Einstein: 1905 (age 26)"+System.lineSeparator()+"Albert Einstein: 1906 (age 27)"+System.lineSeparator()+"Albert Einstein: 1907 (age 28)"+System.lineSeparator()+"Albert Einstein: 1908 (age 29)"+System.lineSeparator()+"Albert Einstein: 1909 (age 30)"+System.lineSeparator()+"Albert Einstein: 1910 (age 31)"+System.lineSeparator()+"Albert Einstein: 1911 (age 32)"+System.lineSeparator()+"Albert Einstein: 1912 (age 33)"+System.lineSeparator()+"Albert Einstein: 1913 (age 34)"+System.lineSeparator()+"Albert Einstein: 1914 (age 35)"+System.lineSeparator()+"Albert Einstein: 1915 (age 36)"+System.lineSeparator()+"Albert Einstein: 1916 (age 37)"+System.lineSeparator()+"Albert Einstein: 1917 (age 38)"+System.lineSeparator()+"Albert Einstein: 1918 (age 39)"+System.lineSeparator()+"Albert Einstein: 1919 (age 40)"+System.lineSeparator()+"Albert Einstein: 1920 (age 41)"+System.lineSeparator()+"Albert Einstein: 1921 (age 42)"+System.lineSeparator()+"Albert Einstein: 1922 (age 43)"+System.lineSeparator()+"Albert Einstein: 1923 (age 44)"+System.lineSeparator()+"Albert Einstein: 1924 (age 45)"+System.lineSeparator()+"Albert Einstein: 1925 (age 46)"+System.lineSeparator()+"Albert Einstein: 1926 (age 47)"+System.lineSeparator()+"Albert Einstein: 1927 (age 48)"+System.lineSeparator()+"Albert Einstein: 1928 (age 49)"+System.lineSeparator()+"Albert Einstein: 1929 (age 50)"+System.lineSeparator()+"Albert Einstein: 1930 (age 51)"+System.lineSeparator()+"Albert Einstein: 1931 (age 52)"+System.lineSeparator()+"Albert Einstein: 1932 (age 53)"+System.lineSeparator()+"Albert Einstein: 1933 (age 54)"+System.lineSeparator()+"Albert Einstein: 1934 (age 55)"+System.lineSeparator()+"Albert Einstein: 1935 (age 56)"+System.lineSeparator()+"Albert Einstein: 1936 (age 57)"+System.lineSeparator()+"Albert Einstein: 1937 (age 58)"+System.lineSeparator()+"Albert Einstein: 1938 (age 59)"+System.lineSeparator()+"Albert Einstein: 1939 (age 60)"+System.lineSeparator()+"Albert Einstein: 1940 (age 61)"+System.lineSeparator()+"Albert Einstein: 1941 (age 62)"+System.lineSeparator()+"Albert Einstein: 1942 (age 63)"+System.lineSeparator()+"Albert Einstein: 1943 (age 64)"+System.lineSeparator()+"Albert Einstein: 1944 (age 65)"+System.lineSeparator()+"Albert Einstein: 1945 (age 66)"+System.lineSeparator()+"Albert Einstein: 1946 (age 67)"+System.lineSeparator()+"Albert Einstein: 1947 (age 68)"+System.lineSeparator()+"Albert Einstein: 1948 (age 69)"+System.lineSeparator()+"Albert Einstein: 1949 (age 70)"+System.lineSeparator()+"Albert Einstein: 1950 (age 71)"+System.lineSeparator()+"Albert Einstein: 1951 (age 72)"+System.lineSeparator()+"Albert Einstein: 1952 (age 73)"+System.lineSeparator()+"Albert Einstein: 1953 (age 74)"+System.lineSeparator()+"Albert Einstein: 1954 (age 75)"+System.lineSeparator()+"Albert Einstein: 1955 (age 76)"+System.lineSeparator()+"Elon Musk: 1971 (age 0)"+System.lineSeparator()+"Elon Musk: 1972 (age 1)"+System.lineSeparator()+"Elon Musk: 1973 (age 2)"+System.lineSeparator()+"Elon Musk: 1974 (age 3)"+System.lineSeparator()+"Elon Musk: 1975 (age 4)"+System.lineSeparator()+"Elon Musk: 1976 (age 5)"+System.lineSeparator()+"Elon Musk: 1977 (age 6)"+System.lineSeparator()+"Elon Musk: 1978 (age 7)"+System.lineSeparator()+"Elon Musk: 1979 (age 8)"+System.lineSeparator()+"Elon Musk: 1980 (age 9)"+System.lineSeparator()+"Elon Musk: 1981 (age 10)"+System.lineSeparator()+"Elon Musk: 1982 (age 11)"+System.lineSeparator()+"Elon Musk: 1983 (age 12)"+System.lineSeparator()+"Elon Musk: 1984 (age 13)"+System.lineSeparator()+"Elon Musk: 1985 (age 14)"+System.lineSeparator()+"Elon Musk: 1986 (age 15)"+System.lineSeparator()+"Elon Musk: 1987 (age 16)"+System.lineSeparator()+"Elon Musk: 1988 (age 17)"+System.lineSeparator()+"Elon Musk: 1989 (age 18)"+System.lineSeparator()+"Elon Musk: 1990 (age 19)"+System.lineSeparator()+"Elon Musk: 1991 (age 20)"+System.lineSeparator()+"Elon Musk: 1992 (age 21)"+System.lineSeparator()+"Elon Musk: 1993 (age 22)"+System.lineSeparator()+"Elon Musk: 1994 (age 23)"+System.lineSeparator()+"Elon Musk: 1995 (age 24)"+System.lineSeparator()+"Elon Musk: 1996 (age 25)"+System.lineSeparator()+"Elon Musk: 1997 (age 26)"+System.lineSeparator()+"Elon Musk: 1998 (age 27)"+System.lineSeparator()+"Elon Musk: 1999 (age 28)"+System.lineSeparator()+"Elon Musk: 2000 (age 29)"+System.lineSeparator()+"Elon Musk: 2001 (age 30)"+System.lineSeparator()+"Elon Musk: 2002 (age 31)"+System.lineSeparator()+"Elon Musk: 2003 (age 32)"+System.lineSeparator()+"Elon Musk: 2004 (age 33)"+System.lineSeparator()+"Elon Musk: 2005 (age 34)"+System.lineSeparator()+"Elon Musk: 2006 (age 35)"+System.lineSeparator()+"Elon Musk: 2007 (age 36)"+System.lineSeparator()+"Elon Musk: 2008 (age 37)"+System.lineSeparator()+"Elon Musk: 2009 (age 38)"+System.lineSeparator()+"Elon Musk: 2010 (age 39)"+System.lineSeparator()+"Elon Musk: 2011 (age 40)"+System.lineSeparator()+"Elon Musk: 2012 (age 41)"+System.lineSeparator()+"Elon Musk: 2013 (age 42)"+System.lineSeparator()+"Elon Musk: 2014 (age 43)"+System.lineSeparator()+"Elon Musk: 2015 (age 44)"+System.lineSeparator()+"Elon Musk: 2016 (age 45)"+System.lineSeparator()+"Elon Musk: 2017 (age 46)"+System.lineSeparator()+"Elon Musk: 2018 (age 47)"+System.lineSeparator()+"Elon Musk: 2019 (age 48)"+System.lineSeparator()+"Elon Musk: 2020 (age 49)"+System.lineSeparator()+"Elon Musk: 2021 (age 50)"+System.lineSeparator()+"Elon Musk: 2022 (age 51)"+System.lineSeparator()+"Bruce Lee: 1940 (age 0)"+System.lineSeparator()+"Bruce Lee: 1941 (age 1)"+System.lineSeparator()+"Bruce Lee: 1942 (age 2)"+System.lineSeparator()+"Bruce Lee: 1943 (age 3)"+System.lineSeparator()+"Bruce Lee: 1944 (age 4)"+System.lineSeparator()+"Bruce Lee: 1945 (age 5)"+System.lineSeparator()+"Bruce Lee: 1946 (age 6)"+System.lineSeparator()+"Bruce Lee: 1947 (age 7)"+System.lineSeparator()+"Bruce Lee: 1948 (age 8)"+System.lineSeparator()+"Bruce Lee: 1949 (age 9)"+System.lineSeparator()+"Bruce Lee: 1950 (age 10)"+System.lineSeparator()+"Bruce Lee: 1951 (age 11)"+System.lineSeparator()+"Bruce Lee: 1952 (age 12)"+System.lineSeparator()+"Bruce Lee: 1953 (age 13)"+System.lineSeparator()+"Bruce Lee: 1954 (age 14)"+System.lineSeparator()+"Bruce Lee: 1955 (age 15)"+System.lineSeparator()+"Bruce Lee: 1956 (age 16)"+System.lineSeparator()+"Bruce Lee: 1957 (age 17)"+System.lineSeparator()+"Bruce Lee: 1958 (age 18)"+System.lineSeparator()+"Bruce Lee: 1959 (age 19)"+System.lineSeparator()+"Bruce Lee: 1960 (age 20)"+System.lineSeparator()+"Bruce Lee: 1961 (age 21)"+System.lineSeparator()+"Bruce Lee: 1962 (age 22)"+System.lineSeparator()+"Bruce Lee: 1963 (age 23)"+System.lineSeparator()+"Bruce Lee: 1964 (age 24)"+System.lineSeparator()+"Bruce Lee: 1965 (age 25)"+System.lineSeparator()+"Bruce Lee: 1966 (age 26)"+System.lineSeparator()+"Bruce Lee: 1967 (age 27)"+System.lineSeparator()+"Bruce Lee: 1968 (age 28)"+System.lineSeparator()+"Bruce Lee: 1969 (age 29)"+System.lineSeparator()+"Bruce Lee: 1970 (age 30)"+System.lineSeparator()+"Bruce Lee: 1971 (age 31)"+System.lineSeparator()+"Bruce Lee: 1972 (age 32)"+System.lineSeparator()+"Bruce Lee: 1973 (age 33)"+System.lineSeparator()+"Oprah Winfrey: 1954 (age 0)"+System.lineSeparator()+"Oprah Winfrey: 1955 (age 1)"+System.lineSeparator()+"Oprah Winfrey: 1956 (age 2)"+System.lineSeparator()+"Oprah Winfrey: 1957 (age 3)"+System.lineSeparator()+"Oprah Winfrey: 1958 (age 4)"+System.lineSeparator()+"Oprah Winfrey: 1959 (age 5)"+System.lineSeparator()+"Oprah Winfrey: 1960 (age 6)"+System.lineSeparator()+"Oprah Winfrey: 1961 (age 7)"+System.lineSeparator()+"Oprah Winfrey: 1962 (age 8)"+System.lineSeparator()+"Oprah Winfrey: 1963 (age 9)"+System.lineSeparator()+"Oprah Winfrey: 1964 (age 10)"+System.lineSeparator()+"Oprah Winfrey: 1965 (age 11)"+System.lineSeparator()+"Oprah Winfrey: 1966 (age 12)"+System.lineSeparator()+"Oprah Winfrey: 1967 (age 13)"+System.lineSeparator()+"Oprah Winfrey: 1968 (age 14)"+System.lineSeparator()+"Oprah Winfrey: 1969 (age 15)"+System.lineSeparator()+"Oprah Winfrey: 1970 (age 16)"+System.lineSeparator()+"Oprah Winfrey: 1971 (age 17)"+System.lineSeparator()+"Oprah Winfrey: 1972 (age 18)"+System.lineSeparator()+"Oprah Winfrey: 1973 (age 19)"+System.lineSeparator()+"Oprah Winfrey: 1974 (age 20)"+System.lineSeparator()+"Oprah Winfrey: 1975 (age 21)"+System.lineSeparator()+"Oprah Winfrey: 1976 (age 22)"+System.lineSeparator()+"Oprah Winfrey: 1977 (age 23)"+System.lineSeparator()+"Oprah Winfrey: 1978 (age 24)"+System.lineSeparator()+"Oprah Winfrey: 1979 (age 25)"+System.lineSeparator()+"Oprah Winfrey: 1980 (age 26)"+System.lineSeparator()+"Oprah Winfrey: 1981 (age 27)"+System.lineSeparator()+"Oprah Winfrey: 1982 (age 28)"+System.lineSeparator()+"Oprah Winfrey: 1983 (age 29)"+System.lineSeparator()+"Oprah Winfrey: 1984 (age 30)"+System.lineSeparator()+"Oprah Winfrey: 1985 (age 31)"+System.lineSeparator()+"Oprah Winfrey: 1986 (age 32)"+System.lineSeparator()+"Oprah Winfrey: 1987 (age 33)"+System.lineSeparator()+"Oprah Winfrey: 1988 (age 34)"+System.lineSeparator()+"Oprah Winfrey: 1989 (age 35)"+System.lineSeparator()+"Oprah Winfrey: 1990 (age 36)"+System.lineSeparator()+"Oprah Winfrey: 1991 (age 37)"+System.lineSeparator()+"Oprah Winfrey: 1992 (age 38)"+System.lineSeparator()+"Oprah Winfrey: 1993 (age 39)"+System.lineSeparator()+"Oprah Winfrey: 1994 (age 40)"+System.lineSeparator()+"Oprah Winfrey: 1995 (age 41)"+System.lineSeparator()+"Oprah Winfrey: 1996 (age 42)"+System.lineSeparator()+"Oprah Winfrey: 1997 (age 43)"+System.lineSeparator()+"Oprah Winfrey: 1998 (age 44)"+System.lineSeparator()+"Oprah Winfrey: 1999 (age 45)"+System.lineSeparator()+"Oprah Winfrey: 2000 (age 46)"+System.lineSeparator()+"Oprah Winfrey: 2001 (age 47)"+System.lineSeparator()+"Oprah Winfrey: 2002 (age 48)"+System.lineSeparator()+"Oprah Winfrey: 2003 (age 49)"+System.lineSeparator()+"Oprah Winfrey: 2004 (age 50)"+System.lineSeparator()+"Oprah Winfrey: 2005 (age 51)"+System.lineSeparator()+"Oprah Winfrey: 2006 (age 52)"+System.lineSeparator()+"Oprah Winfrey: 2007 (age 53)"+System.lineSeparator()+"Oprah Winfrey: 2008 (age 54)"+System.lineSeparator()+"Oprah Winfrey: 2009 (age 55)"+System.lineSeparator()+"Oprah Winfrey: 2010 (age 56)"+System.lineSeparator()+"Oprah Winfrey: 2011 (age 57)"+System.lineSeparator()+"Oprah Winfrey: 2012 (age 58)"+System.lineSeparator()+"Oprah Winfrey: 2013 (age 59)"+System.lineSeparator()+"Oprah Winfrey: 2014 (age 60)"+System.lineSeparator()+"Oprah Winfrey: 2015 (age 61)"+System.lineSeparator()+"Oprah Winfrey: 2016 (age 62)"+System.lineSeparator()+"Oprah Winfrey: 2017 (age 63)"+System.lineSeparator()+"Oprah Winfrey: 2018 (age 64)"+System.lineSeparator()+"Oprah Winfrey: 2019 (age 65)"+System.lineSeparator()+"Oprah Winfrey: 2020 (age 66)"+System.lineSeparator()+"Oprah Winfrey: 2021 (age 67)"+System.lineSeparator()+"Oprah Winfrey: 2022 (age 68)"+System.lineSeparator()+"Srinivasa Ramanujan: 1887 (age 0)"+System.lineSeparator()+"Srinivasa Ramanujan: 1888 (age 1)"+System.lineSeparator()+"Srinivasa Ramanujan: 1889 (age 2)"+System.lineSeparator()+"Srinivasa Ramanujan: 1890 (age 3)"+System.lineSeparator()+"Srinivasa Ramanujan: 1891 (age 4)"+System.lineSeparator()+"Srinivasa Ramanujan: 1892 (age 5)"+System.lineSeparator()+"Srinivasa Ramanujan: 1893 (age 6)"+System.lineSeparator()+"Srinivasa Ramanujan: 1894 (age 7)"+System.lineSeparator()+"Srinivasa Ramanujan: 1895 (age 8)"+System.lineSeparator()+"Srinivasa Ramanujan: 1896 (age 9)"+System.lineSeparator()+"Srinivasa Ramanujan: 1897 (age 10)"+System.lineSeparator()+"Srinivasa Ramanujan: 1898 (age 11)"+System.lineSeparator()+"Srinivasa Ramanujan: 1899 (age 12)"+System.lineSeparator()+"Srinivasa Ramanujan: 1900 (age 13)"+System.lineSeparator()+"Srinivasa Ramanujan: 1901 (age 14)"+System.lineSeparator()+"Srinivasa Ramanujan: 1902 (age 15)"+System.lineSeparator()+"Srinivasa Ramanujan: 1903 (age 16)"+System.lineSeparator()+"Srinivasa Ramanujan: 1904 (age 17)"+System.lineSeparator()+"Srinivasa Ramanujan: 1905 (age 18)"+System.lineSeparator()+"Srinivasa Ramanujan: 1906 (age 19)"+System.lineSeparator()+"Srinivasa Ramanujan: 1907 (age 20)"+System.lineSeparator()+"Srinivasa Ramanujan: 1908 (age 21)"+System.lineSeparator()+"Srinivasa Ramanujan: 1909 (age 22)"+System.lineSeparator()+"Srinivasa Ramanujan: 1910 (age 23)"+System.lineSeparator()+"Srinivasa Ramanujan: 1911 (age 24)"+System.lineSeparator()+"Srinivasa Ramanujan: 1912 (age 25)"+System.lineSeparator()+"Srinivasa Ramanujan: 1913 (age 26)"+System.lineSeparator()+"Srinivasa Ramanujan: 1914 (age 27)"+System.lineSeparator()+"Srinivasa Ramanujan: 1915 (age 28)"+System.lineSeparator()+"Srinivasa Ramanujan: 1916 (age 29)"+System.lineSeparator()+"Srinivasa Ramanujan: 1917 (age 30)"+System.lineSeparator()+"Srinivasa Ramanujan: 1918 (age 31)"+System.lineSeparator()+"Srinivasa Ramanujan: 1919 (age 32)"+System.lineSeparator()+"Srinivasa Ramanujan: 1920 (age 33)"+System.lineSeparator()+"Wayne Gretzky: 1961 (age 0)"+System.lineSeparator()+"Wayne Gretzky: 1962 (age 1)"+System.lineSeparator()+"Wayne Gretzky: 1963 (age 2)"+System.lineSeparator()+"Wayne Gretzky: 1964 (age 3)"+System.lineSeparator()+"Wayne Gretzky: 1965 (age 4)"+System.lineSeparator()+"Wayne Gretzky: 1966 (age 5)"+System.lineSeparator()+"Wayne Gretzky: 1967 (age 6)"+System.lineSeparator()+"Wayne Gretzky: 1968 (age 7)"+System.lineSeparator()+"Wayne Gretzky: 1969 (age 8)"+System.lineSeparator()+"Wayne Gretzky: 1970 (age 9)"+System.lineSeparator()+"Wayne Gretzky: 1971 (age 10)"+System.lineSeparator()+"Wayne Gretzky: 1972 (age 11)"+System.lineSeparator()+"Wayne Gretzky: 1973 (age 12)"+System.lineSeparator()+"Wayne Gretzky: 1974 (age 13)"+System.lineSeparator()+"Wayne Gretzky: 1975 (age 14)"+System.lineSeparator()+"Wayne Gretzky: 1976 (age 15)"+System.lineSeparator()+"Wayne Gretzky: 1977 (age 16)"+System.lineSeparator()+"Wayne Gretzky: 1978 (age 17)"+System.lineSeparator()+"Wayne Gretzky: 1979 (age 18)"+System.lineSeparator()+"Wayne Gretzky: 1980 (age 19)"+System.lineSeparator()+"Wayne Gretzky: 1981 (age 20)"+System.lineSeparator()+"Wayne Gretzky: 1982 (age 21)"+System.lineSeparator()+"Wayne Gretzky: 1983 (age 22)"+System.lineSeparator()+"Wayne Gretzky: 1984 (age 23)"+System.lineSeparator()+"Wayne Gretzky: 1985 (age 24)"+System.lineSeparator()+"Wayne Gretzky: 1986 (age 25)"+System.lineSeparator()+"Wayne Gretzky: 1987 (age 26)"+System.lineSeparator()+"Wayne Gretzky: 1988 (age 27)"+System.lineSeparator()+"Wayne Gretzky: 1989 (age 28)"+System.lineSeparator()+"Wayne Gretzky: 1990 (age 29)"+System.lineSeparator()+"Wayne Gretzky: 1991 (age 30)"+System.lineSeparator()+"Wayne Gretzky: 1992 (age 31)"+System.lineSeparator()+"Wayne Gretzky: 1993 (age 32)"+System.lineSeparator()+"Wayne Gretzky: 1994 (age 33)"+System.lineSeparator()+"Wayne Gretzky: 1995 (age 34)"+System.lineSeparator()+"Wayne Gretzky: 1996 (age 35)"+System.lineSeparator()+"Wayne Gretzky: 1997 (age 36)"+System.lineSeparator()+"Wayne Gretzky: 1998 (age 37)"+System.lineSeparator()+"Wayne Gretzky: 1999 (age 38)"+System.lineSeparator()+"Wayne Gretzky: 2000 (age 39)"+System.lineSeparator()+"Wayne Gretzky: 2001 (age 40)"+System.lineSeparator()+"Wayne Gretzky: 2002 (age 41)"+System.lineSeparator()+"Wayne Gretzky: 2003 (age 42)"+System.lineSeparator()+"Wayne Gretzky: 2004 (age 43)"+System.lineSeparator()+"Wayne Gretzky: 2005 (age 44)"+System.lineSeparator()+"Wayne Gretzky: 2006 (age 45)"+System.lineSeparator()+"Wayne Gretzky: 2007 (age 46)"+System.lineSeparator()+"Wayne Gretzky: 2008 (age 47)"+System.lineSeparator()+"Wayne Gretzky: 2009 (age 48)"+System.lineSeparator()+"Wayne Gretzky: 2010 (age 49)"+System.lineSeparator()+"Wayne Gretzky: 2011 (age 50)"+System.lineSeparator()+"Wayne Gretzky: 2012 (age 51)"+System.lineSeparator()+"Wayne Gretzky: 2013 (age 52)"+System.lineSeparator()+"Wayne Gretzky: 2014 (age 53)"+System.lineSeparator()+"Wayne Gretzky: 2015 (age 54)"+System.lineSeparator()+"Wayne Gretzky: 2016 (age 55)"+System.lineSeparator()+"Wayne Gretzky: 2017 (age 56)"+System.lineSeparator()+"Wayne Gretzky: 2018 (age 57)"+System.lineSeparator()+"Wayne Gretzky: 2019 (age 58)"+System.lineSeparator()+"Wayne Gretzky: 2020 (age 59)"+System.lineSeparator()+"Wayne Gretzky: 2021 (age 60)"+System.lineSeparator()+"Wayne Gretzky: 2022 (age 61)"+System.lineSeparator()))
        {
            System.out.println("School printAgesAndYears() seems correct");
        }
        else
        {
            System.out.println("School class is wrong: code 5");
            return;
        }


        // testing saveDetails:
        File file = new File("people.txt");
        file.delete(); // start by removing any existing file.
        school.saveDetails();

        try
        {
            String fileContent = Files.readString(Path.of("people.txt"), StandardCharsets.UTF_8);
            //System.out.println("STRING content is " + fileContent);
            if(!fileContent.equals("Tiger Woods (T.W.) was born on Tuesday 1975-12-30."+System.lineSeparator() +
                    "Albert Einstein (A.E.) was born on Friday 1879-03-14 and died on Monday 1955-04-18."+System.lineSeparator() +
                    "Elon Musk (E.M.) was born on Monday 1971-06-28."+System.lineSeparator() +
                    "Bruce Lee (B.L.) was born on Wednesday 1940-11-27 and died on Friday 1973-07-20."+System.lineSeparator() +
                    "Oprah Winfrey (O.W.) was born on Friday 1954-01-29."+System.lineSeparator() +
                    "Srinivasa Ramanujan (S.R.) was born on Thursday 1887-12-22 and died on Monday 1920-04-26."+System.lineSeparator() +
                    "Wayne Gretzky (W.G.) was born on Thursday 1961-01-26."+System.lineSeparator()))
            {
                System.out.println("School saveDetails implementation is wrong: code 1");
                return;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("School class looks good: 25 marks");
        score += 25;

    }

    public static void testWriteable()
    {
        File file = new File("test-writeable.txt");
        file.delete(); // start by removing any existing file.

        Writeable w = (myString, min, max)-> {
            for(int i = min; i < max; i++)
            {
                FileReader reader;
                FileWriter writer;
                Scanner scanner;

                try
                {
                    writer = new FileWriter("test-writeable.txt", true);
                    writer.write(myString);
                    writer.close();
                }
                catch(final IOException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        };
        w.printData("BCIT", 10, 120);    // Writes BCIT to the file 110 times
        w.printData("bye", 1, 40);       // Writes bye to the file 39 times

        try
        {
            String fileContent = Files.readString(Path.of("test-writeable.txt"), StandardCharsets.UTF_8);
            // System.out.println("STRING IS " + fileContent);
            if(!fileContent.equals("BCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITBCITbyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebyebye"))
            {
                System.out.println("Writeable implementation is wrong: code 1");
                return;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Writeable implementation looks good: 10 marks");
        score += 10;
    }


    public static void main(final String[] args)
    {
        testDate();
        testName();
        testPerson();
        testStudent();
        testTeacher();
        testSchool();
        testWriteable();

        System.out.println("(Approximate) total score: " + score + " out of 110");
    }
}
