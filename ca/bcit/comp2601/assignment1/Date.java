package ca.bcit.comp2601.assignment1;

/**
 * Models a Date
 *
 * @author Logan Dutton-Anderson
 * @version 1.0
 */
public class Date implements Orderable, Comparable<Date> {
    private final int day;
    private final int month;
    private final int year;

    public static final int CURRENT_YEAR;
    public static final int INVALID_YEAR;
    public static final int YEAR_DIVISOR;
    public static final int MIN_MONTH;
    public static final int MONTHS_IN_YEAR;
    public static final int LEAP_YEAR_DIVISOR;
    public static final int DAYS_IN_WEEK;
    public static final int INITIALIZER;

    public static final int JANUARY;
    public static final int FEBRUARY;
    public static final int MARCH;
    public static final int APRIL;
    public static final int MAY;
    public static final int JUNE;
    public static final int JULY;
    public static final int AUGUST;
    public static final int SEPTEMBER;
    public static final int OCTOBER;
    public static final int NOVEMBER;
    public static final int DECEMBER;

    public static final int FEBRUARY_LEAP_YEAR_DAYS;
    public static final int FEBRUARY_NON_LEAP_YEAR_DAYS;
    public static final int DAYS_IN_SHORT_MONTH;
    public static final int DAYS_IN_LONG_MONTH;

    public static final int DAY_SUBTRACTION;
    public static final int DAY_ADDITION;
    public static final int MIN_DAY;

    public static final int SATURDAY;
    public static final int SUNDAY;
    public static final int MONDAY;
    public static final int TUESDAY;
    public static final int WEDNESDAY;
    public static final int THURSDAY;
    public static final int FRIDAY;

    public static final int EQUAL;

    public static final int SEVENTEENTH_CENT;
    public static final int EIGHTEENTH_CENT;
    public static final int NINETEENTH_CENT;
    public static final int TWENTIETH_CENT;
    public static final int TWENTYFIRST_CENT;
    public static final int TWENTYSECOND_CENT;

    public static final int LEAP_YEAR_OFFSET;
    public static final int SEVENTEENTH_CENT_OFFSET;
    public static final int EIGHTEENTH_CENT_OFFSET;
    public static final int NINETEENTH_CENT_OFFSET;
    public static final int TWENTYFIRST_CENT_OFFSET;
    public static final int TWENTYSECOND_CENT_OFFSET;

    public static final int DIVISIBLE_BY_4;
    public static final int DIVISIBLE_BY_100;
    public static final int DIVISIBLE_BY_400;
    public static final int NO_REMAINDER;

    public static final int JAN_OCT_CODE;
    public static final int FEB_MAR_NOV_CODE;
    public static final int APR_JUL_CODE;
    public static final int MAY_CODE;
    public static final int JUNE_CODE;
    public static final int AUGUST_CODE;
    public static final int SEP_DEC_CODE;

    static {
        CURRENT_YEAR = 2022;
        INVALID_YEAR = 0;
        YEAR_DIVISOR = 100;
        MIN_MONTH = 1;
        MONTHS_IN_YEAR = 12;
        LEAP_YEAR_DIVISOR = 4;
        DAYS_IN_WEEK = 7;
        INITIALIZER = 0;

        JANUARY = 1;
        FEBRUARY = 2;
        MARCH = 3;
        APRIL = 4;
        MAY = 5;
        JUNE = 6;
        JULY = 7;
        AUGUST = 8;
        SEPTEMBER = 9;
        OCTOBER = 10;
        NOVEMBER = 11;
        DECEMBER = 12;

        FEBRUARY_LEAP_YEAR_DAYS = 29;
        FEBRUARY_NON_LEAP_YEAR_DAYS = 28;
        DAYS_IN_SHORT_MONTH = 30;
        DAYS_IN_LONG_MONTH = 31;

        DAY_SUBTRACTION = 1;
        DAY_ADDITION = 1;
        MIN_DAY = 1;

        SATURDAY = 0;
        SUNDAY = 1;
        MONDAY = 2;
        TUESDAY = 3;
        WEDNESDAY = 4;
        THURSDAY = 5;
        FRIDAY = 6;

        EQUAL = 0;

        SEVENTEENTH_CENT = 1600;
        EIGHTEENTH_CENT = 1700;
        NINETEENTH_CENT = 1800;
        TWENTIETH_CENT = 1900;
        TWENTYFIRST_CENT = 2000;
        TWENTYSECOND_CENT = 2100;

        LEAP_YEAR_OFFSET = 6;
        SEVENTEENTH_CENT_OFFSET = 6;
        EIGHTEENTH_CENT_OFFSET = 4;
        NINETEENTH_CENT_OFFSET = 2;
        TWENTYFIRST_CENT_OFFSET = 6;
        TWENTYSECOND_CENT_OFFSET = 4;

        DIVISIBLE_BY_4 = 4;
        DIVISIBLE_BY_100 = 100;
        DIVISIBLE_BY_400 = 400;
        NO_REMAINDER = 0;

        JAN_OCT_CODE = 1;
        FEB_MAR_NOV_CODE = 4;
        APR_JUL_CODE = 0;
        MAY_CODE = 2;
        JUNE_CODE = 5;
        AUGUST_CODE = 3;
        SEP_DEC_CODE = 6;
    }

    /**
     * Constructs a date
     *
     * @param day   the day of the date
     * @param month the month of the date
     * @param year  the year of the date
     */
    public Date(final int day,
                final int month,
                final int year) {

        validateYear(year);
        validateMonth(month);
        validateDay(day, month, year);

        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Validates the year value.
     *
     * @param year the year value to validate
     * @throws IllegalArgumentException if the year is INVALID_YEAR
     */
    private static void validateYear(int year) {
        if (year == INVALID_YEAR) {
            throw new IllegalArgumentException("invalid year");
        }
    }

    /**
     * Validates the month value.
     *
     * @param month the month value to validate
     * @throws IllegalArgumentException if the month is not in the range of MIN_MONTH - MAX_MONTH
     */
    private static void validateMonth(int month) {
        if (month < MIN_MONTH || month > MONTHS_IN_YEAR) {
            throw new IllegalArgumentException("invalid month");
        }
    }

    /**
     * Validates the day value based on the month.
     *
     * @param day the day value to validate
     * @throws IllegalArgumentException if the day is not valid for the specified month
     */
    private static void validateDay(final int day, final int month, final int year) {
        int daysInMonth;
        daysInMonth = getDaysInMonth(month, year);

        if (day < MIN_DAY || day > daysInMonth) {
            throw new IllegalArgumentException("invalid day of the month");
        }
    }

    /**
     * Returns the number of days in the current month.
     *
     * @return The number of days in the current month.
     */
    private static int getDaysInMonth(final int month, final int year) {
        if (month == FEBRUARY) {
            if (isLeapYear(year)) {
                return FEBRUARY_LEAP_YEAR_DAYS;
            } else {
                return FEBRUARY_NON_LEAP_YEAR_DAYS;
            }
        }
        if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
            return DAYS_IN_SHORT_MONTH;
        } else {
            return DAYS_IN_LONG_MONTH;
        }
    }

    /**
     * Returns a String representation of the date in "yyyy-mm-dd" format.
     *
     * @return The date as a string in "yyyy-mm-dd" format.
     */
    public String getYyyyMmDd() {

        String formattedMonth;
        String formattedDay;
        String formattedDate;

        formattedMonth = String.format("%02d", month);
        formattedDay = String.format("%02d", day);

        formattedDate = year + "-" + formattedMonth + "-" + formattedDay;
        return formattedDate;
    }

    /**
     * Returns the previous date.
     *
     * @return The previous date.
     */
    @Override
    public Date previous() {
        int previousDay;
        int previousMonth;
        int previousYear;

        Date previousDate;

        previousDay = day - DAY_SUBTRACTION;
        previousMonth = month;
        previousYear = year;

        if (previousDay < MIN_DAY) {
            previousMonth--;
            if (previousMonth < MIN_MONTH) {
                previousMonth = MONTHS_IN_YEAR;
                previousYear--;
            }
            previousDay = getDaysInMonth(previousMonth, previousYear);
        }
        previousDate = new Date(previousDay, previousMonth, previousYear);
        return previousDate;
    }

    /**
     * Returns the next date.
     *
     * @return The next date.
     */
    @Override
    public Date next() {
        int nextDay;
        int nextMonth;
        int nextYear;

        int daysInMonth;

        Date nextDate;

        nextDay = day + DAY_ADDITION;
        nextMonth = month;
        nextYear = year;

        daysInMonth = getDaysInMonth(month, year);

        if (nextDay > daysInMonth) {
            nextDay = MIN_DAY;
            nextMonth++;
            if (nextMonth > MONTHS_IN_YEAR) {
                nextMonth = MIN_MONTH;
                nextYear++;
            }
        }

        nextDate = new Date(nextDay, nextMonth, nextYear);
        return nextDate;
    }

    /**
     * Compares this date to the specified date for sorting.
     * The comparison is based on the chronological order of the dates,
     * from oldest to most recent.
     *
     * @param otherDate the date to be compared
     * @return a negative integer if this date is older than the specified date,
     * zero if they are equal, or a positive integer if this date is more recent
     * @throws NullPointerException if the specified date is null
     */
    @Override
    public int compareTo(final Date otherDate) {
        if (otherDate == null) {
            throw new NullPointerException("Cannot compare to null");
        }
        int yearComparison;
        int monthComparison;
        int dayComparison;

        // Compare years first
        yearComparison = Integer.compare(year, otherDate.year);
        if (yearComparison != EQUAL) {
            return yearComparison;
        }

        // If years are equal, compare months
        monthComparison = Integer.compare(month, otherDate.month);
        if (monthComparison != EQUAL) {
            return monthComparison;
        }

        // If months are equal, compare days
        dayComparison = Integer.compare(day, otherDate.day);
        return dayComparison;
    }

    /**
     * Two Example dates: August 16, 1989 and March 20, 1950
     * Step 1: Only look at the last two digits of the year and determine how many twelves fit in it:
     * 7 twelves in 89 4 twelves in 50
     * Step 2: Determine the remainder of step 1's result:
     * 89 – (7 * 12) = 5 50 – 4 * 12 = 2
     * Step 3: Determine how many fours fit into the remainder (step 2's result):
     * There is 1 four in 5 There are 0 fours in 2
     * Step 4: Add the day of the month:
     * 16 for August 16th 20 for March 20th
     * Step 5: Add the month code from the table below
     * Jan = 1 Feb = 4 Mar = 4
     * Apr = 0 May = 2 Jun = 5
     * Jul = 0 Aug = 3 Sep = 6
     * Oct = 1 Nov = 4 Dec = 6
     * Step 6: Add all of the above highlighted numbers, and then mod by 7:
     * 7 + 5 + 1 + 16 + 3 = 32 4 + 2 + 0 + 20 + 4 = 30
     * 32 % 7 = 4 30 % 7 = 2
     * That is your day of the week, as follows:
     * Sat = 0 Sun = 1 Mon = 2 Tue = 3 Wed = 4 Thu = 5 Fri = 6
     * August 16, 1989 March 20, 1950
     * Wednesday Monday
     * NOTE: some dates require special offsets to add after step 5:
     * January and February dates in leap years: add 6 to step 5
     * All dates in the 1600s: add 6 to step 5
     * All dates in the 1700s: add 4 to step 5
     * All dates in the 1800s: add 2 to step 5
     * All dates in the 2000s: add 6 to step 5
     * All dates in the 2100s: add 4 to step 5
     *
     * @return the day of the week of the date;
     */
    public String getDayOfTheWeek() {
        int lastTwoDigits;
        int step1;
        int step2;
        int step3;
        int step4;
        int step5;
        int step6;
        int centuryOffset;

        lastTwoDigits = year % YEAR_DIVISOR;

        step1 = lastTwoDigits / MONTHS_IN_YEAR;
        step2 = lastTwoDigits - step1 * MONTHS_IN_YEAR;
        step3 = step2 / LEAP_YEAR_DIVISOR;
        step4 = day;
        step5 = getCodeForMonth(month);
        centuryOffset = INITIALIZER;

        if (month == JANUARY || month == FEBRUARY) {
            if (isLeapYear(year)) {
                step5 += LEAP_YEAR_OFFSET;
            }
        }

        if (year >= SEVENTEENTH_CENT && year < EIGHTEENTH_CENT) {
            centuryOffset = SEVENTEENTH_CENT_OFFSET;
        } else if (year >= EIGHTEENTH_CENT && year < NINETEENTH_CENT) {
            centuryOffset = EIGHTEENTH_CENT_OFFSET;
        } else if (year >= NINETEENTH_CENT && year < TWENTIETH_CENT) {
            centuryOffset = NINETEENTH_CENT_OFFSET;
        } else if (year >= TWENTYFIRST_CENT && year < TWENTYSECOND_CENT) {
            centuryOffset = TWENTYFIRST_CENT_OFFSET;
        }

        step6 = (step1 + step2 + step3 + step4 + step5 + centuryOffset) % 7;

        if (step6 == SATURDAY) {
            return "Saturday";
        } else if (step6 == SUNDAY) {
            return "Sunday";
        } else if (step6 == MONDAY) {
            return "Monday";
        } else if (step6 == TUESDAY) {
            return "Tuesday";
        } else if (step6 == WEDNESDAY) {
            return "Wednesday";
        } else if (step6 == THURSDAY) {
            return "Thursday";
        } else if (step6 == FRIDAY) {
            return "Friday";
        } else {
            return "Invalid Day";
        }
    }

    /**
     * Gets the code for the given month.
     * Jan = 1 Feb = 4 Mar = 4
     * Apr = 0 May = 2 Jun = 5
     * Jul = 0 Aug = 3 Sep = 6
     * Oct = 1 Nov = 4 Dec = 6
     *
     * @param month the month
     * @return the code for the month
     */
    private int getCodeForMonth(final int month) {
        if (month == JANUARY || month == OCTOBER) {
        return JAN_OCT_CODE;
        } else if (month == FEBRUARY || month == MARCH || month == NOVEMBER) {
            return FEB_MAR_NOV_CODE;
        } else if (month == APRIL || month == JULY) {
            return APR_JUL_CODE;
        } else if (month == MAY) {
            return MAY_CODE;
        } else if (month == JUNE) {
            return JUNE_CODE;
        } else if (month == AUGUST) {
            return AUGUST_CODE;
        } else if (month == DECEMBER || month == SEPTEMBER) {
            return SEP_DEC_CODE;
        } else {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    /**
     * Checks if the current year is a leap year.
     *
     * @return True if the current year is a leap year, false otherwise.
     */
    private static boolean isLeapYear(final int year) {
        return (year % DIVISIBLE_BY_4 == NO_REMAINDER && year % DIVISIBLE_BY_100 != NO_REMAINDER) ||
                (year % DIVISIBLE_BY_400 == NO_REMAINDER);
    }

    /**
     * Returns the day of the date.
     *
     * @return The day of the date.
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the month of the date.
     *
     * @return The month of the date.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the year of the date.
     *
     * @return The year of the date.
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns a String representation of the date.
     * Overrides the toString() method from Object class.
     *
     * @return The date as a string in "yyyy-mm-dd" format.
     */
    @Override
    public String toString() {
        return getYyyyMmDd();
    }
}
