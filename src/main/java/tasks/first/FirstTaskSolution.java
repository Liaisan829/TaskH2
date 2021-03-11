package tasks.first;

import java.util.ArrayDeque;
import java.util.HashSet;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        HashSet<Integer> set = new HashSet<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        String str = "";

        set.add(startIndex);
        queue.offerFirst(startIndex);

        while (!queue.isEmpty()) {
            int parent = queue.peekFirst();

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[parent][i] && !set.contains(i)) {
                    set.add(i);
                    queue.offerLast(i);
                }
            }
            str = str + queue.pollFirst() + ", ";
        }
        return str;
    }


    @Override
    public Boolean validateBrackets(String s) {
        String openBrackets = "([{";
        String closeBrackets = ")]}";

        if (s.isEmpty()) return false;
        char[] chars = s.toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (Character c : chars) {
            if (openBrackets.contains(c.toString())) {
                stack.push(c);
                continue;
            }

            if (closeBrackets.contains(c.toString())) {
                if (stack.isEmpty()) return false;

                if (closeBrackets.indexOf(c) == openBrackets.indexOf(stack.peekFirst())) {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int number1;
        int number2;

        String[] splittedString = s.split(" ");

        if (splittedString[0].equals(" ")) throw new IllegalArgumentException();

        for (String string : splittedString) {
            if (isDigit(string)) {
                stack.addFirst(Integer.parseInt(string));
                continue;
            }
            if (!stack.isEmpty()) {
                number1 = Integer.parseInt(String.valueOf(stack.poll()));
                number2 = Integer.parseInt(String.valueOf(stack.poll()));

                switch (string) {
                    case "*":
                        stack.addFirst(number1 * number2);
                        break;

                    case "/":
                        stack.addFirst(number1 / number2);
                        break;

                    case "+":
                        stack.addFirst(number1 + number2);
                        break;
                    case "-":
                        stack.addFirst(number1 - number2);
                        break;
                    default:
                        throw new IllegalArgumentException();

                }
                if (stack.size() == 1 && isDigit(String.valueOf(stack.peekFirst()))) {
                    return Long.valueOf(stack.getFirst());
                }
            }
        }
        throw new IllegalArgumentException("I need only digits");
    }

    public static boolean isDigit(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

