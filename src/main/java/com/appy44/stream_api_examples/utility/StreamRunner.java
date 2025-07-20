package com.appy44.stream_api_examples.utility;

import com.appy44.stream_api_examples.entity.data.Employee;
import com.appy44.stream_api_examples.entity.data.MockData;
import com.appy44.stream_api_examples.examples.predicate.AssignmentPredicates;
import com.appy44.stream_api_examples.examples.predicate.CustomPredicate;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class StreamRunner {

    private static final Map<String, Predicate<Employee>> TASKS = Map.of(
            // 1. Get all employees with salary > ₹800,000
            "Get all employees with salary > ₹800,000",
            CustomPredicate.filterWithSalary(AssignmentPredicates.GT, 800_000),

            // 2. Find all employees older than 30
            "Find all employees older than 30",
            CustomPredicate.filterWithAge(AssignmentPredicates.GT, 30),

            // 3. Filter employees from the city “Pune”
            "Filter employees from the city “Pune”",
            CustomPredicate.filterWithCity(List.of("Pune")),

            // 4. Employees with both salary > ₹600,000 and age < 35
            "Employees with both salary > ₹600,000 and age < 35",
            CustomPredicate
                    .filterWithSalary(AssignmentPredicates.GT, 600_000)
                    .and(CustomPredicate.filterWithAge(AssignmentPredicates.LT, 35)),

            // 5. Employees whose names start with a vowel
            "Employees whose names start with a vowel",
            CustomPredicate::filterNameWthVowels
    );

    public static void main(String[] args) {
        List<Employee> employees = MockData.getSampleEmployees();
        TASKS.forEach((description, action) -> {
            System.out.println(description);
            employees.stream()
                    .filter(action)                       // <- use the Predicate here
                    .map(e -> e.getName() + " | ₹" + e.getSalary())
                    .forEach(System.out::println);
            System.out.println(UtilityPredicate.LINE_BREAK);
        });
    }

    private static void salaryAbove800k(List<Employee> data) {
        data.stream()
                .filter(e -> e.getSalary() > 800_000)
                .map(Employee::getName)
                .forEach(System.out::println);
    }
    // …other methods as above…
}
