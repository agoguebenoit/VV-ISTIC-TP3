# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

The `Date` class is implemented [here](../code/tp3-date/src/main/java/fr/istic/vv/Date.java)

The test suite is implemented [here](../code/tp3-date/src/test/java/fr/istic/vv/DateTest.java)

### 1. Input space partitioning

* isLeapYear :

| Characteristic | Block 1   | Block 2                 |
|----------------|-----------|-------------------------|
| Value of year  | leap year | not leap year           |

* isValidDate :

| Characteristic | Block 1   | Block 2                      | Block 3                     | Block 4            | Block 5 |
|----------------|-----------|------------------------------|-----------------------------|--------------------|---------|
| Value of year  | leap year | not leap year                |                             |                    |         |
| Value of month | < 1       | 30 days months               | 31 days months              | february           | > 12    |
| Value of day   | < 1       | >= 1 && <= max(month, year)  | > max(month, year)          |                    |         |

max(month, year) = 31 if month is 1, 3, 5, 7, 8, 10, 12; 30 if month is 4, 6, 9, 11; 28 if month is 2 and year is not leap year; 29 if month is 2 and year is leap year.

Other test have the same input as isValidDate but encapsulated in a Date object.

### 2. Statement coverage

We got 100% statement coverage with our first test suite.

### 3. Base choice coverage

We have a lot of predicates with more than two boolean operators.
The isLeapYear one did not satisfy base choice coverage.
So we modified the input space partitioning as :

| Characteristic | Block 1            | Block 2                         | Block 3                           | Block 4                           |
|----------------|--------------------|---------------------------------|-----------------------------------|-----------------------------------|
| Value of year  | Not divisible by 4 | Divisible by 4 but not by 100   | Divisible by 4 By 100 and by 400  | Divisible by 4, 100 and not 400   |

### 4. Mutation score

With our first test suite, 71 of the 74 mutants were killed.

* Mutant still alive on isValidDate
Testing that dates of 30 days month didn't have more than 30 days did not test day 31.
So a condition was not tested.
	-> Added new test input to test that case
	
* Mutant still alive on nextDate
Forgot test for nominal case (just increasing day).
	-> Added new test input to test that case
	
* Mutant still alive on previousDate
Forgot test for nominal case (just decreasing day).
	-> Added new test input to test that case

After that 74/74 mutation killed