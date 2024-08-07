package com.example.CompletableFutureExample.streamapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NthHighestSalaryDemo {

    public static void main(String[] args) {
        Map<String, Integer> employeeMap = new HashMap<>();
        employeeMap.put("Anil", 1000);
        employeeMap.put("Bhavana", 1300);
        employeeMap.put("Micael", 1500);
        employeeMap.put("Tom", 1600);
        employeeMap.put("Ankit", 1200);
        employeeMap.put("Daniel", 1700);
        employeeMap.put("James", 1400);

        Map<String, Integer> employeeMap2 = new HashMap<>();
        employeeMap2.put("Anil", 1000);
        employeeMap2.put("Ankit", 1200);
        employeeMap2.put("Bhavana", 1200);
        employeeMap2.put("James", 1200);
        employeeMap2.put("Micael", 1000);
        employeeMap2.put("Tom", 1300);
        employeeMap2.put("Daniel", 1300);
        System.out.println("=============================================");
        System.out.println(getNthHighstSalary(2, employeeMap2));
        System.out.println("=============================================");

        /*Map.Entry<String, Integer> resultMap = employeeMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .toList().get(1);
        System.out.println(resultMap);*/
        System.out.println(getNthHighestSalary(4, employeeMap));

    }

    public static Map.Entry<String, Integer> getNthHighestSalary(int number, Map<String, Integer> employeeMap) {
        return employeeMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .toList().get(number-1);
    }

    public static Map.Entry<Integer, List<String>> getNthHighstSalary(int number, Map<String, Integer> employeeMap) {
        return employeeMap.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue,
                Collectors.mapping(Map.Entry::getKey, Collectors.toList())
        ))
                .entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toList()).get(number-1);
    }
}
