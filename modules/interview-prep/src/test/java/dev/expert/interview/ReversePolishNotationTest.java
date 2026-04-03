package dev.expert.interview;

import org.junit.jupiter.api.Test;

import java.util.Stack;
import java.util.function.IntBinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversePolishNotationTest {

    /*
     * Prompt:
     * You are given an array of strings representing a sequence of numbers and operators
     * in Reverse Polish Notation (RPN).
     *
     * Write an algorithm to evaluate the expression and return the result.
     *
     * Examples:
     * ["2", "3", "+"] → 5
     * ["2", "3", "+", "5", "+"] → 10
     * ["2", "3", "+", "5", "8", "-"] → 2
     * ["3", "4", "5", "+", "-"] → -6
     * ["1", "2", "*", "3", "4", "/", "4", "/", "1", "-", "23", "+", "4", "-", "+"] -> 20
     *
     * Approach:
     * - Use a stack of integers
     * - For each token:
     *     - If number → push
     *     - If operator → pop b, then a → compute (a op b) → push result
     * - Final result is the only value left in the stack
     *
     * Notes:
     * - Operators: +, -, *, /
     * - Integer division (truncate toward zero)
     * - Input is valid
     * - Time: O(n), Space: O(n)
     *
     * Optional:
     * - A second stack can be used to build the expression string for debugging.
     *   NOTE: *NOT* part of the interview requirements
     */

    @Test
    public void runTest() {
        var instructions = new String[] {
                "1", "2", "*", "3", "4", "/", "4", "/", "1", "-", "23", "+", "4", "-", "+"
        };

        var stack = new Stack<Integer>();
        var formula = new Stack<String>(); // optional debug

        for (var token : instructions) {
            var parsed = tryParseInt(token);

            if (parsed.isInt) {
                stack.push(parsed.value);
                formula.push(String.valueOf(parsed.value));
                continue;
            }

            IntBinaryOperator op = switch (token) {
                case "+" -> Integer::sum;
                case "-" -> (a, b) -> a - b;
                case "*" -> (a, b) -> a * b;
                case "/" -> (a, b) -> a / b;
                default -> throw new UnsupportedOperationException("Unsupported op: " + token);
            };

            var b = stack.pop();
            var a = stack.pop();

            var result = op.applyAsInt(a, b);
            stack.push(result);

            // debug formula
            var right = formula.pop();
            var left = formula.pop();
            formula.push("(" + left + " " + token + " " + right + ")");
        }

        var finalResult = stack.pop();
        var finalFormula = formula.pop();

        System.out.println("Result: " + finalResult);
        System.out.println("Formula: " + finalFormula);

        assertEquals(20, finalResult);
    }

    record ParseResult(boolean isInt, int value) {}

    private ParseResult tryParseInt(String s) {
        try {
            return new ParseResult(true, Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return new ParseResult(false, 0);
        }
    }
}


//using System;
//using System.Collections.Generic;
//using Xunit;
//
//namespace Dev.Expert.Interview
//{
//    public class ReversePolishNotationTest
//    {
//        /*
//         * Prompt:
//         * You are given an array of strings representing a sequence of numbers and operators
//         * in Reverse Polish Notation (RPN).
//         *
//         * Write an algorithm to evaluate the expression and return the result.
//         *
//         * Examples:
//         * ["2", "3", "+"] → 5
//         * ["2", "3", "+", "5", "+"] → 10
//         * ["2", "3", "+", "5", "8", "-"] → 2
//         * ["3", "4", "5", "+", "-"] → -6
//         * ["1", "2", "*", "3", "4", "/", "4", "/", "1", "-", "23", "+", "4", "-", "+"]
//         * → 20
//         *
//         * Approach:
//         * - Use a stack of integers
//         * - For each token:
//         *     - If number → push
//         *     - If operator → pop b, then a → compute (a op b) → push result
//         * - Final result is the only value left in the stack
//         *
//         * Notes:
//         * - Operators: +, -, *, /
//         * - Integer division (truncate toward zero)
//         * - Input is valid
//         * - Time: O(n), Space: O(n)
//         *
//         * Optional:
//         * - A second stack can be used to build the expression string for debugging
//         */
//
//        [Fact]
//        public void RunTest()
//        {
//            var instructions = new[]
//            {
//                "1", "2", "*", "3", "4", "/", "4", "/", "1", "-", "23", "+", "4", "-", "+"
//            };
//
//            var stack = new Stack<int>();
//            var formula = new Stack<string>(); // optional debug
//
//            foreach (var token in instructions)
//            {
//                if (int.TryParse(token, out var value))
//                {
//                    stack.Push(value);
//                    formula.Push(value.ToString());
//                    continue;
//                }
//
//                var b = stack.Pop();
//                var a = stack.Pop();
//
//                var result = token switch
//                {
//                    "+" => a + b,
//                    "-" => a - b,
//                    "*" => a * b,
//                    "/" => a / b,
//                    _ => throw new NotSupportedException($"Unsupported op: {token}")
//                };
//
//                stack.Push(result);
//
//                // debug formula
//                var right = formula.Pop();
//                var left = formula.Pop();
//                formula.Push($"({left} {token} {right})");
//            }
//
//            var finalResult = stack.Pop();
//            var finalFormula = formula.Pop();
//
//            Console.WriteLine($"Result: {finalResult}");
//            Console.WriteLine($"Formula: {finalFormula}");
//
//            Assert.Equal(20, finalResult);
//        }
//    }
//}
