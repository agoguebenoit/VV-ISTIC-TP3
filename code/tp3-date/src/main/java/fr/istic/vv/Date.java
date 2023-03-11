package fr.istic.vv;

import java.util.Objects;

class Date implements Comparable<Date> {

    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (day < 1 || day > 31) return false;

        if (month < 1 || month > 12) return false;

        if (month == 2) {
            if (isLeapYear(year)) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        }

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        }

        return true;
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public Date nextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;

        if (month == 2) {
            if (isLeapYear(year)) {
                if (day == 29) {
                    nextDay = 1;
                    nextMonth = 3;
                }
            } else {
                if (day == 28) {
                    nextDay = 1;
                    nextMonth = 3;
                }
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day == 30) {
                nextDay = 1;
                nextMonth = month + 1;
            }
        } else {
            if (day == 31) {
                nextDay = 1;
                nextMonth = month + 1;
            }
        }

        if (nextMonth == 13) {
            nextMonth = 1;
            nextYear++;
        }

        return new Date(nextDay, nextMonth, nextYear);
    }

    public Date previousDate() {
        int previousDay = day - 1;
        int previousMonth = month;
        int previousYear = year;

        if (month == 3) {
            if (isLeapYear(year)) {
                if (day == 1) {
                    previousDay = 29;
                    previousMonth = 2;
                }
            } else {
                if (day == 1) {
                    previousDay = 28;
                    previousMonth = 2;
                }
            }
        } else if (month == 5 || month == 7 || month == 10 || month == 12) {
            if (day == 1) {
                previousDay = 30;
                previousMonth = month - 1;
            }
        } else {
            if (day == 1) {
                previousDay = 31;
                previousMonth = month - 1;
            }
        }

        if (previousMonth == 0) {
            previousMonth = 12;
            previousYear = year - 1;
        }

        return new Date(previousDay, previousMonth, previousYear);
    }

    public int compareTo(Date other) {
        Objects.requireNonNull(other);

        if (year != other.year) {
            return other.year - year;
        } else if (month != other.month) {
            return other.month - month;
        } else {
            return other.day - day;
        }
    }
}