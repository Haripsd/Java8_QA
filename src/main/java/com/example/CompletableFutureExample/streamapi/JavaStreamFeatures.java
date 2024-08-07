package com.example.CompletableFutureExample.streamapi;

import com.example.CompletableFutureExample.async.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreamFeatures {


    public static void main(String[] args) {

        // 1. Stream.ofNullable
        // 2. Stream.iterate
        // 3. Collectors.collectingAndThen
        // 4. Stream.takenWhile and Stream.dropWhile
        // 5. Collectors.teeing() JDK12
        // 6. Stream.concat()
        // 7. Collectors.partitioningBy
        // 8. IntStreamForRanges

        // 1. Stream.ofNullable
        List<String> names = Arrays.asList("Sachin", "Ganguly", null, "Sehwag", null, "Dravid", "Laxman");
        List<String> namesResult = names.stream().filter(name -> name != null).collect(Collectors.toList());
        System.out.println(namesResult);
        List<String> namesResult1 = names.stream().flatMap(Stream::ofNullable).collect(Collectors.toList());
        System.out.println(namesResult1);

        // 2. Stream.iterate
        Stream.iterate(0, n->n+2)
                .limit(5)
                .forEach(System.out::println);

        // 3. Collectors.collectingAndThen
        List<Employee> employeeList = Arrays.asList(new Employee("Alice", 50000L),
                new Employee("Bob", 65000L),
                new Employee("Charlie", 78000L),
                new Employee("Sarah", 45000L));

        Long avgSalary = employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .boxed()
                .collect(Collectors.collectingAndThen(
                        Collectors.averagingDouble(Double::doubleValue), // 1st Collector
                        Math::round
                ));
        System.out.println("Average Salary : "+avgSalary);

        // 4. Stream.takenWhile and Stream.dropWhile
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> num1 = numbers.stream().takeWhile(num -> num < 5)
                .collect(Collectors.toList());
        System.out.println(num1);
        List<Integer> num2 = numbers.stream().dropWhile(num -> num < 5)
                .collect(Collectors.toList());
        System.out.println(num2);
        List<Integer> num3 = numbers.stream()
                .dropWhile(num -> num < 3)
                .takeWhile(num -> num < 7)
                .collect(Collectors.toList());
        System.out.println(num3);

        // 5. Collectors.teeing()
        Map<String, Integer> teeingMap = numbers.stream().collect(Collectors.teeing(
                Collectors.maxBy(Integer::compareTo), // 1st Collector
                Collectors.minBy(Integer::compareTo), // 2nd Collector
                (e1, e2) -> Map.of("max", e1.get(), "min", e2.get())
        ));
        System.out.println(teeingMap);

        // 6. Stream.concat()
        Stream<Integer> stream1 = Stream.of(1,2,3);
        Stream<Integer> stream2 = Stream.of(4,5,6);
        Stream<Integer> concatStream = Stream.concat(stream1, stream2);
        long totalSum = concatStream.mapToInt(Integer::intValue).sum();
        System.out.println(totalSum);

        // 7. Collectors.partitioningBy
        Map<Boolean, List<Integer>> partitioningMap = numbers.stream().collect(Collectors.partitioningBy(num -> num % 2 == 0));
        System.out.println("Odd Numbers : "+partitioningMap.get(Boolean.FALSE));
        System.out.println("Even Numbers : "+partitioningMap.get(Boolean.TRUE));


        // 8. IntStreamForRanges
        List<Integer> intStream1 = IntStream.range(1, 20).boxed().collect(Collectors.toList());
        System.out.println(intStream1);

        List<Integer> intStream2 = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        System.out.println(intStream2);


    }
}
