package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    private static Stream<Arguments> provideDatesForThirtyDaysMonths() {
        return Stream.of(
                Arguments.of(1, 4, 2000),
                Arguments.of(5, 4, 2000),
                Arguments.of(25, 4, 2000),
                Arguments.of(30, 4, 2000),
                Arguments.of(1, 6, 2000),
                Arguments.of(5, 6, 2000),
                Arguments.of(25, 6, 2000),
                Arguments.of(30, 6, 2000),
                Arguments.of(1, 9, 2000),
                Arguments.of(5, 9, 2000),
                Arguments.of(25, 9, 2000),
                Arguments.of(30, 9, 2000),
                Arguments.of(1, 11, 2000),
                Arguments.of(5, 11, 2000),
                Arguments.of(25, 11, 2000),
                Arguments.of(30, 11, 2000)
        );
    }

    private static Stream<Arguments> provideDatesForThirtyOneDaysMonths() {
        return Stream.of(
                Arguments.of(1, 1, 2000),
                Arguments.of(5, 1, 2000),
                Arguments.of(25, 1, 2000),
                Arguments.of(31, 1, 2000),
                Arguments.of(1, 3, 2000),
                Arguments.of(5, 3, 2000),
                Arguments.of(25, 3, 2000),
                Arguments.of(31, 3, 2000),
                Arguments.of(1, 5, 2000),
                Arguments.of(5, 5, 2000),
                Arguments.of(25, 5, 2000),
                Arguments.of(31, 5, 2000),
                Arguments.of(1, 7, 2000),
                Arguments.of(5, 7, 2000),
                Arguments.of(25, 7, 2000),
                Arguments.of(31, 7, 2000),
                Arguments.of(1, 8, 2000),
                Arguments.of(5, 8, 2000),
                Arguments.of(25, 8, 2000),
                Arguments.of(31, 8, 2000),
                Arguments.of(1, 10, 2000),
                Arguments.of(5, 10, 2000),
                Arguments.of(25, 10, 2000),
                Arguments.of(31, 10, 2000),
                Arguments.of(1, 12, 2000),
                Arguments.of(5, 12, 2000),
                Arguments.of(25, 12, 2000),
                Arguments.of(31, 12, 2000)
        );
    }

    private static Stream<Arguments> provideDatesForNextDateNominalTest() {
        return Stream.of(
                Arguments.of(1, 1, 2000, 2, 1, 2000),
                Arguments.of(5, 5, 2000, 6, 5, 2000),
                Arguments.of(27, 9, 2000, 28, 9, 2000)
        );
    }

    private static Stream<Arguments> provideDatesForPreviousDateNominalTest() {
        return Stream.of(
                Arguments.of(2, 1, 2000, 1, 1, 2000),
                Arguments.of(6, 5, 2000, 5, 5, 2000),
                Arguments.of(28, 9, 2000, 27, 9, 2000)
        );
    }

    @Test
    void test_createInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> new Date(0, 0, 0));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 8, 16})
    void isLeapYear_whenDivisibleBy4ButNot100_shouldReturnTrue(int year) {
        assertTrue(Date.isLeapYear(year));
    }

    @ParameterizedTest
    @ValueSource(ints = {2000, 4000, 8000})
    void isLeapYear_whenDivisibleBy4By100And400_shouldReturnTrue(int year) {
        assertTrue(Date.isLeapYear(year));
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 700, 1500, 1700, 1800, 1900})
    void isLeapYear_whenDivisibleBy4By100ButNot400_shouldReturnFalse(int year) {
        assertFalse(Date.isLeapYear(year));
    }

    @ParameterizedTest
    @ValueSource(ints = {1941, 1977, 2001, 2017, 2049, 200})
    void isLeapYear_shouldReturnFalse(int year) {
        assertFalse(Date.isLeapYear(year));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0})
    void isValidDate_whenDayInferiorToOne_shouldReturnFalse(int day) {
        assertFalse(Date.isValidDate(day, 1, 2000));
    }

    @ParameterizedTest
    @ValueSource(ints = {32, 40, 50})
    void isValidDate_whenDaySuperiorToThirtyOneForThirtyOneDayMonth_shouldReturnFalse(int day) {
        assertFalse(Date.isValidDate(day, 1, 2000));
    }

    @ParameterizedTest
    @ValueSource(ints = {31, 40, 50})
    void isValidDate_whenDaySuperiorToThirtyForThirtyDayMonth_shouldReturnFalse(int day) {
        assertFalse(Date.isValidDate(day, 4, 2000));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0})
    void isValidDate_whenMonthInferiorToOne_shouldReturnFalse(int month) {
        assertFalse(Date.isValidDate(1, month, 2000));
    }

    @ParameterizedTest
    @ValueSource(ints = {13, 17, 20})
    void isValidDate_whenMonthSuperiorToTwelve_shouldReturnFalse(int month) {
        assertFalse(Date.isValidDate(1, month, 2000));
    }

    @ParameterizedTest
    @ValueSource(ints = {30, 35, 150})
    void isValidDate_whenFebruaryAndLeapYearAndDaySuperiorToTwentyNine_shouldReturnFalse(int day) {
        assertFalse(Date.isValidDate(day, 2, 2000));
    }

    @ParameterizedTest
    @ValueSource(ints = {29, 35, 150})
    void isValidDate_whenFebruaryAndNotLeapYearAndDaySuperiorToTwentyEight_shouldReturnFalse(int day) {
        assertFalse(Date.isValidDate(day, 2, 2001));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 7, 8, 10, 12, 25, 29})
    void isValidDate_whenFebruaryAndLeapYearAndDayLessThan30_shouldReturnTrue(int day) {
        assertTrue(Date.isValidDate(day, 2, 2000));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 7, 8, 10, 12, 25, 28})
    void isValidDate_whenFebruaryAndNotLeapYearAndDayIsLessThan29_shouldReturnTrue() {
        assertTrue(Date.isValidDate(28, 2, 2001));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 9, 11})
    void isValidDate_whenMonthHasThirtyOneDaysAndDaySuperiorToThirty_shouldReturnFalse(int month) {
        assertFalse(Date.isValidDate(31, month, 2000));
    }

    @ParameterizedTest
    @MethodSource("provideDatesForThirtyDaysMonths")
    void isValidDate_whenMonthHasThirtyDaysAndDayIsValid_shouldReturnTrue(int day, int month, int year) {
        assertTrue(Date.isValidDate(day, month, year));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 8, 10, 12})
    void isValidDate_whenMonthHasThirtyOneDaysAndDaySuperiorThirtyOne_shouldReturnFalse(int month) {
        assertFalse(Date.isValidDate(32, month, 2000));
    }

    @ParameterizedTest
    @MethodSource("provideDatesForThirtyOneDaysMonths")
    void isValidDate_whenMonthHasThirtyOneDaysAndDayIsThirtyOne_shouldReturnTrue(int day, int month, int year) {
        assertTrue(Date.isValidDate(day, month, year));
    }

    @Test
    void compareTo_whenSameDate_shouldReturnZero() {
        Date date = new Date(1, 1, 2000);
        Date sameDate = new Date(1, 1, 2000);
        assertEquals(0, date.compareTo(sameDate));
    }

    @Test
    void compareTo_whenYearIsBefore_shouldReturnNegative() {
        Date date = new Date(1, 1, 2000);
        Date dateBefore = new Date(1, 1, 1999);
        assertTrue(date.compareTo(dateBefore) < 0);
    }

    @Test
    void compareTo_whenYearIsAfter_shouldReturnPositive() {
        Date date = new Date(1, 1, 2000);
        Date dateAfter = new Date(1, 1, 2001);
        assertTrue(date.compareTo(dateAfter) > 0);
    }

    @Test
    void compareTo_whenMonthIsBefore_shouldReturnNegative() {
        Date date = new Date(1, 12, 2000);
        Date dateBefore = new Date(1, 11, 2000);
        assertTrue(date.compareTo(dateBefore) < 0);
    }

    @Test
    void compareTo_whenMonthIsAfter_shouldReturnPositive() {
        Date date = new Date(1, 11, 2000);
        Date dateAfter = new Date(1, 12, 2000);
        assertTrue(date.compareTo(dateAfter) > 0);
    }

    @Test
    void compareTo_whenDayIsBefore_shouldReturnNegative() {
        Date date = new Date(2, 12, 2000);
        Date dateBefore = new Date(1, 12, 2000);
        assertTrue(date.compareTo(dateBefore) < 0);
    }

    @Test
    void compareTo_whenDayIsAfter_shouldReturnPositive() {
        Date date = new Date(1, 12, 2000);
        Date dateAfter = new Date(2, 12, 2000);
        assertTrue(date.compareTo(dateAfter) > 0);
    }

    @ParameterizedTest
    @MethodSource("provideDatesForNextDateNominalTest")
    void nextDate_whenDateIsValid_shouldReturnNextDate(int day, int month, int year, int expectedDay, int expectedMonth, int expectedYear) {
        Date date = new Date(day, month, year);
        Date nextDate = date.nextDate();

        Date expectedDate = new Date(expectedDay, expectedMonth, expectedYear);
        assertEquals(0, nextDate.compareTo(expectedDate));
    }

    @Test
    void nextDate_whenDayIsToThirtyOneAndMonthIsToTwelve_shouldReturnFirstJanuaryNextYear() {
        Date date = new Date(31, 12, 2000);
        Date nextDate = date.nextDate();

        Date expectedDate = new Date(1, 1, 2001);
        assertEquals(0, nextDate.compareTo(expectedDate));
    }

    @Test
    void nextDate_whenMonthHasThirtyDaysAndDayIsToThirty_shouldReturnFirstNextMonth() {
        Date date = new Date(30, 4, 2000);

        Date nextDate = date.nextDate();
        Date expectedDate = new Date(1, 5, 2000);
        assertEquals(0, nextDate.compareTo(expectedDate));
    }

    @Test
    void nextDate_whenMonthHasThirtyOneDaysAndDayIsToThirtyOne_shouldReturnFirstNextMonth() {
        Date date = new Date(31, 1, 2000);
        Date nextDate = date.nextDate();

        Date expectedDate = new Date(1, 2, 2000);

        assertEquals(0, nextDate.compareTo(expectedDate));
    }

    @Test
    void nextDate_whenFebruaryAndLeapYearAndDayIsToTwentyNine_shouldReturnFirstMarch() {
        Date date = new Date(29, 2, 2000);
        Date nextDate = date.nextDate();

        Date expectedDate = new Date(1, 3, 2000);
        assertEquals(0, nextDate.compareTo(expectedDate));
    }

    @Test
    void nextDate_whenFebruaryAndNotLeapYearAndDayIsToTwentyEight_shouldReturnFirstMarch() {
        Date date = new Date(28, 2, 2001);
        Date nextDate = date.nextDate();

        Date expectedDate = new Date(1, 3, 2001);
        assertEquals(0, nextDate.compareTo(expectedDate));
    }

    @ParameterizedTest
    @MethodSource("provideDatesForPreviousDateNominalTest")
    void previousDate_whenDateIsValid_shouldReturnPreviousDate(int day, int month, int year, int expectedDay, int expectedMonth, int expectedYear) {
        Date date = new Date(day, month, year);
        Date previousDate = date.previousDate();

        Date expectedDate = new Date(expectedDay, expectedMonth, expectedYear);
        assertEquals(0, previousDate.compareTo(expectedDate));
    }

    @Test
    void previousDate_whenDayIsOneAndMonthIsOne_shouldReturnLastDayOfDecemberPreviousYear() {
        Date date = new Date(1, 1, 2000);
        Date previousDate = date.previousDate();

        Date expectedDate = new Date(31, 12, 1999);
        assertEquals(0, previousDate.compareTo(expectedDate));
    }

    @Test
    void previousDate_whenPreviousMonthHasThirtyDaysAndDayIsOne_shouldReturnLastDayOfPreviousMonth() {
        Date date = new Date(1, 5, 2000);
        Date previousDate = date.previousDate();

        Date expectedDate = new Date(30, 4, 2000);
        assertEquals(0, previousDate.compareTo(expectedDate));
    }

    @Test
    void previousDate_whenPreviousMonthHasThirtyOneDaysAndDayIsOne_shouldReturnLastDayOfPreviousMonth() {
        Date date = new Date(1, 2, 2000);
        Date previousDate = date.previousDate();

        Date expectedDate = new Date(31, 1, 2000);
        assertEquals(0, previousDate.compareTo(expectedDate));
    }

    @Test
    void previousDate_whenFebruaryAndLeapYearAndDayIsOne_shouldReturnLastDayOfJanuary() {
        Date date = new Date(1, 3, 2000);
        Date previousDate = date.previousDate();

        Date expectedDate = new Date(29, 2, 2000);
        assertEquals(0, previousDate.compareTo(expectedDate));
    }

    @Test
    void previousDate_whenFebruaryAndNotLeapYearAndDayIsOne_shouldReturnLastDayOfJanuary() {
        Date date = new Date(1, 3, 2001);
        Date previousDate = date.previousDate();

        Date expectedDate = new Date(28, 2, 2001);
        assertEquals(0, previousDate.compareTo(expectedDate));
    }

}