package com.appy44.stream_api_examples.examples.predicate;

import com.appy44.stream_api_examples.entity.data.Employee;

import java.util.List;
import java.util.function.Predicate;

import static com.appy44.stream_api_examples.examples.predicate.AssignmentPredicates.GT;
import static com.appy44.stream_api_examples.examples.predicate.AssignmentPredicates.LT;

/**
 *
 */
public class CustomPredicate {
    public static Predicate<Employee> filterWithAge(int value, int age) {
        switch (value) {
            case GT -> {
                return employee -> employee.getAge() >= age;
            }
            case LT -> {
                return employee -> employee.getAge() <= age;
            }
            default -> {
                return employee -> employee.getAge() == age;
            }
        }
    }

    public static Predicate<Employee> filterWithCity(List<String> cities) {
        return emp ->
                cities.stream()
                        .anyMatch(city -> city.equalsIgnoreCase(emp.getAddress().getCity()));
    }

    public static Predicate<Employee> filterWithSalary(int value, int salary) {
        switch (value) {
            case GT -> {
                return employee -> employee.getSalary() >= salary;
            }
            default -> {
                return employee -> employee.getSalary() == salary;
            }
        }
    }

    public static boolean filterNameWthVowels(Employee employee) {
        List<String> vowelsList = List.of("a", "o", "e", "i", "u");
        String firstLetter = String.valueOf(employee.getName().toLowerCase().charAt(0));
        return vowelsList.contains(firstLetter);
    }
}
