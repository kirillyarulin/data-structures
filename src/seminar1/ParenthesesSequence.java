package seminar1;

import seminar1.collections.IStack;
import seminar1.collections.LinkedStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. пустая строка — правильная скобочная последовательность;
 * 2. правильная скобочная последовательность,
 * взятая в скобки одного типа — правильная скобочная последовательность;
 * 3. правильная скобочная последовательность,
 * к которой приписана слева или справа правильная скобочная последовательность
 * — тоже правильная скобочная последовательность.
 */
public class ParenthesesSequence {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';

    // sequence = "()()" | "((((" | ")()(" | ...
    private static boolean isBalanced(String sequence) {
        if (sequence.length() == 0) return true;
        int level = 0;
        for (int i = 0; i < sequence.length();i++) {
            char currentSymbol = sequence.charAt(i);

            if (currentSymbol == LEFT_PAREN) {
                level++;
            } else if (currentSymbol == RIGHT_PAREN) {
                level--;
            }

            if (level<0) {
                break;
            }
        }

        return level == 0;
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(isBalanced(sequence) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
