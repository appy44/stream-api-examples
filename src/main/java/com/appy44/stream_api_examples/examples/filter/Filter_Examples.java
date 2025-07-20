package com.appy44.stream_api_examples.examples.filter;

import com.appy44.stream_api_examples.entity.data.Employee;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
public class Filter_Examples {

    /*

    🔹 1. filter()

    Easy
    -----
    1. Get all employees with salary > ₹800,000
    2. Find all employees older than 30
    3. Filter employees from the city “Pune”

    Medium
    -------
    4. Employees with both salary > ₹600,000 and age < 35
    5. Employees whose names start with a vowel
    6. Employees with at least 2 skills

    Complex
    --------
    7. Filter employees who are currently working (based on `CompanyExperience`)
    8. Employees who have "Java" skill with proficiency ≥ 8
    9. Filter employees who have a personal email and alternate contact number

    */

    public void employeesWithAtLeastNSkills(List<Employee> data, int numberOfSkills) {
        data.stream().filter(e -> e.getSkills().size() >= numberOfSkills).forEach(e -> {
            System.out.println(e.getName() + " : " + e.getSkills().size());
        });
    }

    public void employeesWhoseNamesStartWithAVowel(List<Employee> data) {
        List<String> vowels = Arrays.asList("a", "e", "i", "o", "u", "u");
        data.stream().filter(n -> (
                vowels.contains(n.getName().substring(0, 1).toLowerCase())
        )).forEach(n -> System.out.println(n.getName()));
    }

    public void employeesWithBothSalary(List<Employee> data, int salary, int age) {
        data.stream().filter(emp -> (emp.getSalary() > salary && emp.getAge() < age))
                .sorted(Comparator.comparing(Employee::getAge)
                        .thenComparing(Employee::getSalary, Comparator.reverseOrder())).forEach(emp -> {
                    System.out.printf("%s Age is %s and salary is %s", emp.getName(), emp.getAge(), emp.getSalary());
                });
    }

    public void filterEmployeesFromTheEmail(List<Employee> data, String email) {
        List<Employee> result = data.stream().filter(n -> n.getEmails().stream().anyMatch(objEmail -> objEmail.getAddress().contains(email))).toList();
        System.out.printf("Employees having %s in email\n", email);
        result.forEach(n -> System.out.println(n.getName()));
    }

    public void filterEmployeesFromTheCity(List<Employee> data, String cityName) {
//        List<Employee> result = data.stream().filter(n ->
//                        n.getAddress().stream()
//                                .anyMatch(city -> city.getCity().equalsIgnoreCase(cityName)))
//                .toList();
//        System.out.println("Employees from " + cityName);
//        result.forEach(n -> System.out.println(n.getName()));
    }

    public void findAllEmployeesOlderThan(List<Employee> data, int age) {
        data.stream().filter(n -> n.getAge() > age)
                //.sorted((n1, n2) -> n1.getAge() - n2.getAge()).
                .sorted(Comparator.comparing(Employee::getAge)).
                forEach(n -> System.out.println(n.getName() + " : " + n.getAge()));
    }

    public void getAllEmployeesWithSalary(List<Employee> data, int salary) {
        data.stream().filter(n -> n.getSalary() > salary).forEach(n -> {
            System.out.println(n.getName() + " : " + n.getSalary());
        });
    }
}
