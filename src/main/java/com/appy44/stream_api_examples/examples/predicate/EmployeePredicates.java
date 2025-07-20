package com.appy44.stream_api_examples.examples.predicate;

import com.appy44.stream_api_examples.entity.data.Employee;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class EmployeePredicates {
    public static String validateData(Employee employee) {
        if (Objects.nonNull(employee)
                && Objects.nonNull(employee.getId())
                && Objects.nonNull(employee.getCompany())
                && Objects.nonNull(employee.getDesignation())
                && employee.getSalary() > 0
                && Objects.nonNull(employee.getAddress())
                && Objects.nonNull(employee.getAddress().getCity())
                && Objects.nonNull(employee.getAddress().getState())
                && Objects.nonNull(employee.getContact())
        ) {
            return "valid";
        } else {
            return "invalid";
        }
    }

    public static Predicate<Employee> isFromState(String state) {
        return e -> e.getAddress().getState().equalsIgnoreCase(state);
    }

    public static Predicate<Employee> checkSalaryIsGreater(int salary) {
        return employee -> employee.getSalary() > salary;
    }

    public static Predicate<Employee> getByDesignation(String designation) {
        return e -> e.getDesignation().equalsIgnoreCase(designation);
    }

    public static Predicate<Employee> getByCompany(String company) {
        return e -> e.getCompany().equalsIgnoreCase(company);
    }

    public static Predicate<Employee> getByState(List<String> states) {
        return states.stream()
                .map(EmployeePredicates::isFromState)
                .reduce(e -> false, Predicate::or);
//        e -> e.getAddress().getState();
    }

    public static Predicate<Employee> getByCompany(List<String> companies) {
        return companies.stream()
                .map(EmployeePredicates::getByCompany)
                .reduce(e -> false, Predicate::or);
    }
}
