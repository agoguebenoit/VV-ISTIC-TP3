package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    @Test
    void test_isBalanced_empty() {
        assertTrue(isBalanced(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"()", "[]", "{}", "()[]{}", "({[]})"})
    void test_isBalanced(String str) {
        assertTrue(isBalanced(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"(", ")", "[", "]", "{", "}", "({}])", "[)", "[}"})
    void test_isNotBalanced(String str) {
        assertFalse(isBalanced(str));
    }

}