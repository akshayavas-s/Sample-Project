package com.exampleapi;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class A {

    public static void main(String[] args) {
        // Predicate Functional Interface
//        List<Integer> data = Arrays.asList(10, 20, 35, 36, 17);
//        List<Integer> val = data.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
//        System.out.println(val);

//        Predicate<Integer> val = x ->x>500;
//        boolean result = val.test(100);
//        System.out.println(result);

//        Predicate<String> v = t -> t.startsWith("a");
//        boolean result = v.test("ple");
//        System.out.println(result);

//        List<Integer> data= Arrays.asList(10,10,20,30,48,9,77,93,40);
//        data.stream().distinct().map(x -> x*x).filter(x -> x % 2 == 0).forEach(
//                x -> System.out.println(x));

        //In the given list take number above 50 and give 10% discount and give final discount
//        List<Integer> price = Arrays.asList(100, 75, 48, 500, 32, 50, 5000, 750);
//        price.stream().filter(x-> x >50).map(y-> y-(y*0.1)).forEach(
//                t-> System.out.println(t));

//        List<Employee> list = Arrays.asList(
//                new Employee(1, "John", 5000),
//                new Employee(2, "Alice", 6000),
//                new Employee(3, "Bob", 4000),
//                new Employee(4, "Charlie", 7000)
//        );
//
//        Map<Integer, List<Employee>> e = list.stream().collect(Collectors.groupingBy(x -> x.getSalary()));
//        Set<Integer> key = e.keySet();
//
//        for(Integer k: key){
//            System.out.println("Salary grouping for: " + k);
//            List<Employee> temp = e.get(k);
//            for(Employee d:temp){
//            System.out.println("ID: " + d.getId() + ", Name: " + d.getName() + ", Salary: " + d.getSalary());
//                System.out.println("----------------------------------------------------------------");
//            }
//        }

        List<String> list = Arrays.asList("mike", "stallin", "mike");
        Map<String, List<String>> c = list.stream().collect(Collectors.groupingBy(x -> "mike"));
        System.out.println(c);

    }
}
