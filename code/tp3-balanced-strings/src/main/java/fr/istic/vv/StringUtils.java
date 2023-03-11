package fr.istic.vv;

import java.util.ArrayDeque;
import java.util.Deque;

public class StringUtils {

    public static boolean isBalanced(String str) {
        Deque<Character> stack = new ArrayDeque<>();

        // Traversing the Expression
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);

            if (charAt == '(' || charAt == '[' || charAt == '{') {
                stack.push(charAt);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            char check;
            switch (charAt) {
                case ')':
                    check = stack.pop();
                    if (check != '(')
                        return false;
                    break;
                case '}':
                    check = stack.pop();
                    if (check != '{')
                        return false;
                    break;
                case ']':
                    check = stack.pop();
                    if (check != '[')
                        return false;
                    break;
            }
        }

        return stack.isEmpty();
    }

}
