package seminar1;

import seminar1.collections.IStack;
import seminar1.collections.LinkedStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) ) ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class Solver {

    private static final String QUIT = "q";
    static private Node root = new Node();
    static private IStack<Node> stack = new LinkedStack<>();
    static private String[] tokens;
    static private int it = 0;

    private static final String LEFT_PAREN   = "(";
    private static final String RIGHT_PAREN  = ")";
    private static final String PLUS         = "+";
    private static final String MINUS        = "-";
    private static final String TIMES        = "*";
    private static final String DIVISION     = "/";

    private static double evaluate(String[] values) {
        tokens=values;
        infix(root);
        return result(root);
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void infix(Node nd) {
        if (hasNextToken()) {
            String str = readToken();
            if (str.equals(LEFT_PAREN)) {
                nd.leftChild = new Node();
                infix(nd.leftChild);
                nd.data=readToken(); //читаем символ операции
                nd.rightChild = new Node();
                infix(nd.rightChild);
                readToken(); //читаем закрывающую скобку
            } else {
                nd.data=str; //записываем в вершину операнд
            }
        }
    }

    private static String readToken() {
        if (hasNextToken()) {
            it++;
            return tokens[it - 1];
        } else {
            return null;
        }
    }

    private static boolean hasNextToken() { return it<tokens.length; }

    private static double result(Node nd) {
        switch (nd.data) {
            case TIMES:
                return result(nd.leftChild) * result(nd.rightChild);
            case DIVISION:
                return result(nd.leftChild) / result(nd.rightChild);
            case PLUS:
                return result(nd.leftChild) + result(nd.rightChild);
            case MINUS:
                return result(nd.leftChild) - result(nd.rightChild);
            default:
                return Double.parseDouble(nd.data);
        }
    }


    private static class Node {
        String data;
        Node leftChild;
        Node rightChild;

        Node() {}
        Node(String data) {
            this.data=data;
        }
    }

}
