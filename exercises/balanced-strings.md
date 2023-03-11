# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written so far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

The method `isBalanced` is implemented [here](../code/tp3-balanced-strings/src/main/java/fr/istic/vv/StringUtils.java)

The test suite is implemented [here](../code/tp3-balanced-strings/src/test/java/fr/istic/vv/StringUtilsTest.java)

### 1. Input space partitioning

Partition blocks:

| Characteristic            | Block 1                   | Block 2                   |
|---------------------------|---------------------------|---------------------------|
| Length of input string    | 0                         | > 0                       |
| Balance of parenthesis    | Balanced parenthesis      | Unbalanced parenthesis    |
| Balance of brackets       | Balanced brackets         | Unbalanced brackets       |
| Balance of curly braces   | Balanced curly braces     | Unbalanced curly braces   |

### 2. Statement coverage

Our first test suite gave us a statement coverage of 80%.
We forgot two cases in our input that were described by or partitions. We added them to our test suite.

### 3. Base choice coverage

Our first if (to push in the stack) have two more than two boolean operator.
So far ou test cases satisfy the Base Choice Coverage.

### 4. Mutation score

17 out of 17 mutants were killed.

