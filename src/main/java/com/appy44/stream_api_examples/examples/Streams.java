package com.appy44.stream_api_examples.examples;

import com.appy44.stream_api_examples.entity.data.Employee;
import com.appy44.stream_api_examples.entity.data.MockData;
import com.appy44.stream_api_examples.examples.predicate.AssignmentPredicates;
import com.appy44.stream_api_examples.examples.predicate.CustomPredicate;
import com.appy44.stream_api_examples.utility.UtilityPredicate;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Streams {

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

    /*    🔹 2. map()

    Easy
    -----
    1. Get a list of employee names
    2. Convert all employee names to uppercase
    3. Extract email addresses into a list

    Medium
    -------
    4. Convert List<CustomData> to List<String> of “name - designation”
    5. Get all phone numbers (flatten from contacts)
    6. Create a list of full addresses (city, state)

    Complex
    --------
    7. Convert list of employee experiences into a list of current company names
    8. Extract skill names of all employees and flatten into a single list
    9. Convert CustomData list to a list of DTOs (Custom summary object)

    */

    /*🔹 3. collect()

    Easy
    -----
    1. Collect names into a List
    2. Collect unique cities into a Set
    3. Group employees by designation

    Medium
    -------
    4. Collect salaries into a TreeSet
    5. Group employees by state
    6. Collect emails as a comma-separated string

    Complex
    --------
    7. Group by city and find average salary
    8. Group by designation and count
    9. Partition employees into above and below ₹10L salary

    */

    /*🔹 4. sorted()

    Easy
    -----
    1. Sort employees by age
    2. Sort by name alphabetically

    Medium
    -------
    3. Sort by salary descending
    4. Sort by city and then by name
    5. Sort by number of skills

    Complex
    --------
    6. Sort by current company’s join date
    7. Sort by experience duration
    8. Custom comparator: sort by age then salary

    */

    /*🔹 5. flatMap()

    Easy
    -----
    1. Get all skill names from all employees
    2. Flatten all email addresses into a list

    Medium
    -------
    3. Flatten all contacts into a list
    4. Get all unique states across all addresses
    5. Flatten company names from experiences

    Complex
    --------
    6. Get all skills with proficiency > 7 across employees
    7. Extract all alternate contact numbers
    8. Flatten all profession roles across all employees

    */

    /*🔹 6. reduce()

    Easy
    -----
    1. Sum of all salaries
    2. Get total number of skills across employees

    Medium
    -------
    3. Find the employee with highest salary using reduce
    4. Get longest name using reduce
    5. Get max age

    Complex
    --------
    6. Create a single comma-separated string of all names
    7. Reduce list of contacts to unique types
    8. Sum experience duration across employees

    */

    /*🔹 7. anyMatch / allMatch / noneMatch

    Easy
    -----
    1. Check if any employee is from “Mumbai”
    2. Check if all employees earn more than ₹300,000

    Medium
    -------
    3. Check if no employee has more than 3 emails
    4. Any employee with no contacts?
    5. All employees have at least one skill?

    Complex
    --------
    6. No employee has salary over ₹2,000,000
    7. All employees are assigned to a current company
    8. Anyone with contact number starting with "999"?

    */

    /*🔹 8. count / min / max

    Easy
    -----
    1. Count all employees
    2. Get employee with min age

    Medium
    -------
    3. Count of employees in each city
    4. Employee with max number of experiences
    5. Min salary in “Pune”

    Complex
    --------
    6. Designation with max average salary
    7. Employee with min skill proficiency in "Java"
    8. Count employees grouped by skill count

    */

    /*🔹 9. Advanced Collectors

    Medium
    -------
    1. Group by city → list of employee names
    2. Group by state → average salary
    3. ToMap: ID → Name

    Complex
    --------
    4. Group by profession → Set of names
    5. Collect names into a LinkedHashSet
    6. Nested group: state → city → employees

    */

    /*============================
    🔸 Combined Stream Challenges
    ============================

    Easy
    -----
    1. List names of employees older than 30 and living in Maharashtra.
    2. Get all cities where employees with salary > ₹6L reside.
    3. Extract a list of all personal email IDs.
    4. Count how many employees have more than one skill.
    5. Get a list of employee names whose alternate contact exists.

    Medium
    -------
    6. Find the average salary of employees living in Pune and having "Java" as a skill.
    7. List names of employees whose current company is not null and who have more than 2 skills.
    8. Get all phone numbers of employees living in Mumbai, grouped by city.
    9. List the names and designations of employees with salary > ₹8L sorted by age.
    10. Get a Map<String, Long> of designation to count of employees earning > ₹5L.

    Complex
    --------
    11. Get a Map<String, Double> of state → average salary of employees who have more than one experience and skill proficiency > 7.
    12. Flatten and collect all skill names of employees with a "Backend" profession working in a current company.
    13. From all employees, find the one with the longest full name and highest skill count.
    14. Group employees by city, then within each group, list all email addresses of those earning > ₹10L.
    15. Create a nested map: state → designation → list of employee names where skill contains "Spring Boot".

    */
    public static void main(String[] args) {
        List<Employee> data = MockData.getSampleEmployees();
        System.out.println("Employee data size: " + data.size());
        System.out.println(UtilityPredicate.LINE_BREAK);

        System.out.println("Sort by name length asc, skip 1, limit 3, then print longest of those");
        data.stream()
                .sorted(Comparator.comparingInt((Employee e) -> e.getName().length()).reversed())
                .skip(1)
                .limit(15)
                .map(Employee::getName)
                .peek(n -> System.out.println(n + " | " + n.length()))

                .reduce((a, b) -> a.length() > b.length() ? a : b)
                .ifPresent(System.out::println);

        System.out.println(UtilityPredicate.LINE_BREAK);

        System.out.println("Sort by name length desc, print each name|age, then group-by-age counts");


        data.stream()
                .sorted(Comparator.comparingInt((Employee e) -> e.getName().length()))
                .skip(12)
                .limit(10)
                .peek(n -> System.out.println(n.getName() + " | " + n.getAge()))
                .collect(Collectors.groupingBy(Employee::getAge))
                .forEach((k, v) -> {
                    System.out.println(k + " | " + v.size());
                });
        System.out.println(UtilityPredicate.LINE_BREAK);

        System.out.println("1. Get all employees with salary > ₹800,000");
        data.stream()
                .filter(CustomPredicate.filterWithSalary(AssignmentPredicates.GT, 800000))
                .peek(n -> System.out.println(n.getName() + " | " + n.getSalary()))
                .toList();
        System.out.println(UtilityPredicate.LINE_BREAK);

        System.out.println("2. Find all employees older than 30");
        data.stream()
                .filter(CustomPredicate.filterWithAge(AssignmentPredicates.GT, 30))
                .peek(n -> System.out.println(n.getName() + " | " + n.getAge()))
                .toList();
        System.out.println(UtilityPredicate.LINE_BREAK);

        System.out.println("3. Filter employees from the city “Pune”");
        List<String> cities = List.of("Pune");
        data.stream()
                .filter(CustomPredicate.filterWithCity(cities))
                .peek(n -> System.out.println(n.getName() + " | " + n.getAddress().getCity()))
                .toList();
        System.out.println(UtilityPredicate.LINE_BREAK);

        System.out.println("4. Employees with both salary > ₹600,000 and age < 35");
        Predicate<Employee> customFilterForSalaryAndAge = CustomPredicate.filterWithSalary(AssignmentPredicates.GT, 600000)
                .and(CustomPredicate.filterWithAge(AssignmentPredicates.LT, 35));

        data.stream()
                .filter(customFilterForSalaryAndAge)
                .peek(n -> System.out.println(n.getName() + " | " + n.getSalary() + " | " + n.getAge()))
                .toList();
        System.out.println(UtilityPredicate.LINE_BREAK);

        System.out.println("5. Employees whose names start with a vowel");
        data.stream()
                .filter(CustomPredicate::filterNameWthVowels).peek(n -> System.out.println(n.getName()))
                .toList();
    }

}
