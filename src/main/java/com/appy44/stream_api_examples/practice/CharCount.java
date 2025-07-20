package com.appy44.stream_api_examples.practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class CharCount {

    public static void main(String[] args) {
        String testString = "just developer thing!JUST DEVELOPER THINGS!\ntest string with some spaces and new lines.@#";
        CharCount streamApiExamplesApplication = new CharCount();
        streamApiExamplesApplication.charCount1test(testString);
    }

    private void charCount1test(String testString) {
        IntPredicate filter = c -> Character.isAlphabetic(c)
                || Character.isDigit(c)
                || !Character.isWhitespace(c);
        Map<Object, Long> collect = testString.chars()
                .filter(filter)
                .mapToObj(c -> (char) Character.toUpperCase(c)).
                collect(Collectors.groupingBy(c -> (char) c, Collectors.counting()));

        collect.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });
    }


    private void charCount1(String testString) {
        IntPredicate filter = c -> !Character.isWhitespace(c);
        IntFunction<Character> function = c -> Character.toUpperCase((char) c);
        Map<Character, Long> collect = testString.chars()
                .filter(filter)
                .mapToObj(function)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(stringToJSON(collect));
    }

    private String stringToJSON(Map<Character, Long> collect) {
        try {
            return new ObjectMapper().writeValueAsString(collect);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
