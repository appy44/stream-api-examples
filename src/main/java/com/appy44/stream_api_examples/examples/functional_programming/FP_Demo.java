package com.appy44.stream_api_examples.examples.functional_programming;

import com.appy44.stream_api_examples.entity.data.Employee;
import com.appy44.stream_api_examples.entity.data.MockData;
import com.appy44.stream_api_examples.examples.predicate.AssignmentPredicates;
import com.appy44.stream_api_examples.examples.predicate.EmployeePredicates;
import com.appy44.stream_api_examples.utility.UtilityPredicate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.appy44.stream_api_examples.examples.predicate.AssignmentPredicates.GT;

public final class FP_Demo {

    public static void main(String[] args) throws JsonProcessingException {
        List<String> states = List.of("Maharashtra", "Telangana", "Tamil Nadu");

        List<String> companies = List.of("Fluffington LLC", "FunkyTech", "HCL");
        Predicate<? super Employee> filter = EmployeePredicates.checkSalaryIsGreater(1).and(EmployeePredicates.getByState(states)).or(EmployeePredicates.getByCompany(companies));

        Map<?, List<Employee>> map = MockData.getSampleEmployees().stream()
                .collect(Collectors.groupingBy(EmployeePredicates::validateData));
//        System.out.println(UtilityPredicate.printJSON(map));
//        System.out.println(UtilityPredicate.LINE_BREAK);
//        map.forEach((k, v) -> System.out.println(k + " : " + v.size()));


        //return this back in response
        List<Employee> invalidList = map.getOrDefault("invalid", List.of()).stream()
                .peek(e -> System.out.println(e.getName()))
                .toList();
        System.out.println("inValid list count : " + invalidList.size());
        System.out.println(new ObjectMapper().writeValueAsString(invalidList));
        System.out.println(UtilityPredicate.LINE_BREAK);


        List<Employee> employeeList = map.getOrDefault("valid", List.of()).stream()
                .filter(filter)
                .peek(e -> System.out.println(e.getName() + " | " + e.getCompany() + " | " + e.getAddress().getState() + " | " + e.getSalary()))
                .toList();
        System.out.println("Valid list count :" + employeeList.size());
//        System.out.println(UtilityPredicate.printJSON(employeeList));
        System.out.println(UtilityPredicate.LINE_BREAK);


        System.out.println("Valid list count cities:");
        Map<String, List<Employee>> employeeMapByState = employeeList.stream()
                .filter(filter)
                .collect(Collectors.groupingBy(e -> e.getAddress().getCity()));
        System.out.println(new ObjectMapper().writeValueAsString(employeeMapByState));
        System.out.println(UtilityPredicate.LINE_BREAK);
//        List<Employee> employeeList = map.getOrDefault("valid", List.of()).stream().toList();
        assignments(employeeList);
    }

    private static void assignments(List<Employee> employeeList) throws JsonProcessingException {

//
////Q1.
        System.out.println("FInd Employee has salaray > 1500000");
        employeeList.stream().filter(emp -> emp.getSalary() > 1500000)
                .peek(e -> System.out.println(e.getName() + "\t\t| " + e.getSalary()))
                .toList();

        System.out.println(UtilityPredicate.LINE_BREAK);
//Q2.
        List<String> companies = List.of("BubbleCorp");
        List<String> states = List.of("Maharashtra");
        int salary = 1000000;
        System.out.println("FInd Employee has salaray > " + salary + " and from states " + new ObjectMapper().writeValueAsString(states) + "and from companies " + new ObjectMapper().writeValueAsString(companies));
        Predicate<? super Employee> filter = AssignmentPredicates.getByCompanies(companies).and(
                AssignmentPredicates.getBySalary(GT, salary)
                        .and(AssignmentPredicates.getByStates(states))
        );
        List<Employee> test = employeeList.stream()
                .filter(filter)
                .toList();
        System.out.println(new ObjectMapper().writeValueAsString(test));
        System.out.println(UtilityPredicate.LINE_BREAK);
//Q3.

//Q4.

//Q5.
        System.out.println("Find state's employee count where \nEmployee has salaray > " + salary + " \n from states " + new ObjectMapper().writeValueAsString(states) + "and from companies " + new ObjectMapper().writeValueAsString(companies));
        Map<String, Long> map1 = employeeList.stream()
                .filter(filter)
                .peek(e -> System.out.println(
                        e.getName() + "\t\t| "
                                + e.getCompany() + "\t\t| "
                                + e.getAddress().getState() + "\t\t| "
                                + e.getSalary()
                ))
                .collect(Collectors.groupingBy(e -> e.getAddress().getState(), Collectors.counting()));
        System.out.println(new ObjectMapper().writeValueAsString(map1));
//
///
        System.out.println(UtilityPredicate.LINE_BREAK);
//Q6.
        System.out.println("Print out odd numbers from given numbers");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 500, 19, 111);
        numbers.stream()
                .filter(n -> n % 2 != 0)
                .forEach(n -> System.out.printf(n + ","));

        System.out.println();
        System.out.println(UtilityPredicate.LINE_BREAK);
//Q7.
        numbers.stream()
                .collect(Collectors.groupingBy(AssignmentPredicates::evenOdd))
                .forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println(UtilityPredicate.LINE_BREAK);
//Q8.
        numbers.stream().collect(Collectors.groupingBy(AssignmentPredicates::primeNumbers))
                .forEach((k, v) -> {
                    System.out.println(k + " : " + v);
                });
        System.out.println(UtilityPredicate.LINE_BREAK);
//Q9.
        System.out.println("Find out employee who is from maharashtra");
        employeeList.stream()
                .filter(n -> n.getAddress().getState().equalsIgnoreCase("Maharashtra"))
                .map(UtilityPredicate::printJSON)
                .map(UtilityPredicate::testJSON)
                .toList()
                .forEach(System.out::println);
        System.out.println(UtilityPredicate.LINE_BREAK);

        Optional<Employee> obj = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println("Find out employee who has Maximum Salary");
        obj.ifPresent(n -> System.out.println(n.getName() + " | " + n.getDesignation() + " | " + n.getSalary()));
        System.out.println(UtilityPredicate.LINE_BREAK);


        System.out.println("Find out employee who has Minimum Salary");
        employeeList.stream().collect(
                        Collectors.minBy(Comparator.comparingDouble(Employee::getSalary))).stream()
                .forEach(n -> System.out.println(n.getName() + " | " + n.getDesignation() + " | " + n.getSalary()));
        System.out.println(UtilityPredicate.LINE_BREAK);

        String designation = "Software Engineer";
        System.out.println("Find out employee  having maximum salary by designation : " + designation);
        Employee maxSalaryByDesg = employeeList.stream()
                .filter(n -> n.getDesignation().equalsIgnoreCase(designation))
                .peek(n -> System.out.println(n.getName() + " | " + n.getDesignation() + " | " + n.getSalary()))
                .max(Comparator.comparingDouble(Employee::getSalary)
                ).orElse(null);
        System.out.println("Maximum Salary by designation : " + maxSalaryByDesg.getName() + " | " + maxSalaryByDesg.getDesignation() + " | " + maxSalaryByDesg.getSalary());
        System.out.println(UtilityPredicate.LINE_BREAK);


        Employee min = employeeList.stream()
                .filter(n -> n.getDesignation().equalsIgnoreCase(designation))
                .min(Comparator.comparingDouble(Employee::getSalary)).orElse(null);
        System.out.println("Minimum Salary by designation : " + min.getName() + " | " + min.getDesignation() + " | " + min.getSalary());

        System.out.println(UtilityPredicate.LINE_BREAK);
    }
}