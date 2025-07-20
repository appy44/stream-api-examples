package com.appy44.stream_api_examples.examples.predicate;

import com.appy44.stream_api_examples.entity.data.Employee;

import java.util.List;
import java.util.function.Predicate;

public class AssignmentPredicates {
    public final static int GT = 1;
    public final static int LT = -1;
    public final static int EQ = 0;

    public static Predicate<Employee> getByCompany(String company) {
        return e -> e.getCompany().equalsIgnoreCase(company);
    }

    public static Predicate<Employee> getByCompanies(List<String> companies) {
        return companies.stream().map(AssignmentPredicates::getByCompany).reduce(e -> false, Predicate::or);
    }

    public static Predicate<Employee> getBySalary(int gt, int salary) {
        switch (gt) {
            case 0:
                return e -> e.getSalary() == salary;
            case 1:
                return e -> e.getSalary() > salary;
            case -1:
                return e -> e.getSalary() < salary;
            default:
                return e -> e.getSalary() == salary;
        }
    }

    public static Predicate<Employee> getByState(String state) {
        return e -> e.getAddress().getState().equalsIgnoreCase(state);
    }

    public static Predicate<Employee> getByStates(List<String> states) {
        return states.stream().map(AssignmentPredicates::getByState).reduce(e -> false, Predicate::or);
    }

    public static String evenOdd(Integer n) {
        if (n % 2 == 0) return "even";
        else return "odd";

    }

    public static String primeNumbers(Integer number) {
        String prime = "prime";
        String notPrime = "not prime";
        switch (number) {
            case 1:
                return notPrime;
            case 2:
                return prime;
            default:
                for (int i = 2; i <= Math.sqrt(number); i++) {
                    if (number % i == 0) {
                        return notPrime;
                    }

                }
                return prime;
        }

    }
}
