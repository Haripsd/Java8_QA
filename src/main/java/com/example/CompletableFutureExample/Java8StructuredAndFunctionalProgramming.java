======================
Structured Programming
======================
package com.example.CompletableFutureEx.Java8.FunctionalProgramming;

import java.util.List;

public class StructuredProgramming {

    public static void main(String[] args) {

        // 1. Traditional Approach
        List<Integer> list = List.of(11, 13, 14, 15, 16, 17, 19, 20);
        printList(list);
        System.out.println("Even Numbers Filter");
    }

    public static void printList(List<Integer> list) {
        for(Integer intVal : list) {
            isEven(intVal);
        }
    }

    public static void isEven(int number) {
         if(number%2 == 0) {
             System.out.println(number);
         }
    }


}

=====================
FunctionalProgramming
=====================
package com.example.CompletableFutureEx.Java8.FunctionalProgramming;

import java.util.List;

public class FunctionalProgramming {

    public static void main(String[] args) {
        printListFunctionalApproach(List.of(11, 13, 14, 15, 16, 17, 19, 20));
        printEvenNumbers(List.of(11, 13, 14, 15, 16, 17, 19, 20));
    }

    public static void printListFunctionalApproach(List<Integer> list) {
        list.stream().forEach(FunctionalProgramming::printNumber);
        list.stream().forEach(System.out::println);
    }

    public static void printNumber(int number) {
        System.out.println(number);
    }

    public static void printEvenNumbers(List<Integer> evenList) {
        System.out.println("Even Numbers ");
        evenList.stream().filter(n->n%2==0).forEach(System.out::println); // 1st approach
        System.out.println("============================");
        evenList.stream().filter(FunctionalProgramming::isEven).forEach(System.out::println);  // 2nd approach
        System.out.println("===========================");
        evenList.forEach(n-> {     // 3rd approach
            if(n % 2 == 0) {
                System.out.println("even numbers : "+n);
            }
        });
    }

    public static boolean isEven(int number) {
        return number%2 == 0;
    }
}

====================
Functional Interface
====================
package com.example.CompletableFutureEx.Java8.FunctionalProgramming;

interface Calculator{
  //  void switchOn();  // 0 -argument
  //  void totalValue(int value);  // one argument
    int sum(int a, int b);  // Two Arguments
}
public class CalculatorImplementation {

    public static void main(String[] args) {
//           Calculator calculator = ()->System.out.println("Switch On");
//        calculator.switchOn();

//        Calculator calculator = (num) -> System.out.println(num);
//        calculator.totalValue(5);

        Calculator calculator2 = (a, b) -> a+b;
        System.out.println(calculator2.sum(5,10));

        Calculator calculator3 = ((a, b) -> {
            if (a<b) {
                throw new RuntimeException("Exception occurred ");
            } else {
                return a+b;
            }
        });
        System.out.println(calculator3.sum(14,10));
    }


}

================
Filter And Map
================
package com.example.CompletableFutureEx.Java8.FunctionalProgramming;

import java.util.Arrays;
import java.util.List;

public class FunctionalExcersize {

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Hibernate", "Java", ".net", "J2EE", "JPA", "MicroServices", "Spring Boot", "J2EE");
        List<String> courses1 = Arrays.asList("Spring", "Hibernate", "Java", ".net", "J2EE", "JPA", "MicroServices");
        courses.stream().forEach(System.out::println); // Printing all courses
        System.out.println("=======================");
        courses.stream().filter(course -> course.contains("Spring")).forEach(System.out::println); // Printing starting with Spring
        System.out.println("=======================");
        courses.stream().filter(course -> course.length() <= 4).forEach(System.out::println); // printing lessthan length 4
        System.out.println("============================");
        courses.stream().map(course -> course + " : " + course.length()).forEach(System.out::println);
        System.out.println("============================");
        courses.stream().distinct().forEach(System.out::println);
        System.out.println("========= Sorting Order ==================");
        courses.stream().distinct().sorted().forEach(System.out::println); //both distinct and sorting order
        System.out.println("============= Using Comparator Natural and Reverse Order ==============");
        courses.stream().distinct().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println("====================");
        courses.stream().distinct().sorted(Comparator.comparing(str->str.length())).forEach(System.out::println);
    }
}

O/P:
===
Spring
Hibernate
Java
.net
J2EE
JPA
MicroServices
Spring Boot
J2EE
=======================
Spring
Spring Boot
=======================
Java
.net
J2EE
JPA
J2EE
============================
Spring : 6
Hibernate : 9
Java : 4
.net : 4
J2EE : 4
JPA : 3
MicroServices : 13
Spring Boot : 11
J2EE : 4
============================
Spring
Hibernate
Java
.net
J2EE
JPA
MicroServices
Spring Boot
========= Sorting Order ==================
.net
Hibernate
J2EE
JPA
Java
MicroServices
Spring
Spring Boot
============= Using Comparator Natural and Reverse Order ==============
Spring Boot
Spring
MicroServices
Java
JPA
J2EE
Hibernate
.net
====================
JPA
Java
.net
J2EE
Spring
Hibernate
Spring Boot
MicroServices


List<Integer> list = List.of(11, 13, 15, 14, 16, 10, 2, 6);
        list.stream().filter(n -> n % 2 == 0).map(n -> n * n).forEach(System.out::println);
        System.out.println("-------------------------------");
        list.stream().filter(n->n%2!=0).map(n->n*n*n).forEach(System.out::println);

O/P
===
196
256
100
4
36
-------------------------------
1331
2197
3375

==============
Reduce Example
==============
 List<Integer> list = List.of(11,12,3,4,6,7,16,8);
       // int sum = addListFunctionalInterface(list);
     //   System.out.println(sum);
        int sum1 = addListFunctional(list);
        System.out.println(sum1);

    }

    private static int addListFunctionalInterface(List<Integer> numbers) {
       // return numbers.stream().reduce(0, ReduceEx::sum);
	   // return numbers.stream().reduce(0, (a, b)-> a+b);
	   return numbers.stream().reduce(0, (a,b)-> a>b ? a : b); // Highest value in the List

        // Stream of number -> one result value
        // Combine them into one result => One value
        // 0 and ReduceEx::sum
    }

   /* private static int sum(int a, int b) {
        return a+b;
    }*/

    private static int sum(int aggregate, int next) {
        System.out.println(aggregate+"  "+next);
        return aggregate+next;
    }

    private static int addListFunctional(List<Integer> numbers) {
     //   return numbers.stream().reduce(0, ReduceEx::sum);
	 return numbers.stream().reduce(0, Integer::sum);

       // Instead of 0 we can use Integer.MIN_VALUE
        // Stream of number -> one result value
        // Combine them into one result => one Value
        // 0 and ReduceEx::sum
    }
	
O/P
===
0  11
11  12
23  3
26  4
30  6
36  7
43  16
59  8
67	

==========================================
Lambda Expression with FunctionalInterface
==========================================

package com.example.CompletableFutureEx.Java8.FunctionalProgramming;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* Behind each Lambda expression we have Functional Interface*/
public class FP01FunctionalInterfaces {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(11, 2, 22, 3, 23, 4, 56, 7);
        Predicate<Integer> isEvenPredicate  = x->x%2==0;

        Predicate<Integer> isEvenPredicateInteger = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer%2==0;
            }
        };

        Function<Integer, Integer> squareFunction = x->x*x;
        Function<Integer, Integer> squareFunction1 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x * x;
            }
        };

        Consumer<Integer> consumer = System.out::println;
        Consumer<Integer> consumer2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer x) {
                System.out.println(x);
            }
        };

        numbers.stream().filter(isEvenPredicate).map(squareFunction).forEach(consumer);
        numbers.stream().filter(isEvenPredicateInteger).map(squareFunction1).forEach(consumer2);

    }

}

O/P:
===
4
484
16
3136
4
484
16
3136

=================================
FunctionalInterfaceBinaryOperator
=================================

package com.example.CompletableFutureEx.Java8.FunctionalProgramming;

import java.util.List;
import java.util.function.BinaryOperator;

public class FunctionalInterfaceBinaryOperator {

    public static void main(String[] args) {
        List<Integer> numbersList = List.of(11, 2, 22, 3, 23, 4, 56, 7);
        BinaryOperator<Integer> sumBinaryOperator = Integer::sum;
        BinaryOperator<Integer> sumBinaryOperator1 = (x,y)->x+y;
        BinaryOperator<Integer> sumBinaryOperator2 = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x+y;
            }
        };

        int sum =  numbersList.stream().reduce(0, sumBinaryOperator);  // Output is same for all scenarios
        int sum1 =  numbersList.stream().reduce(0, sumBinaryOperator1);
        int sum2 =  numbersList.stream().reduce(0, sumBinaryOperator2);
        System.out.println("========================");
        System.out.println(sum);
        System.out.println("========================");
        System.out.println(sum1);
        System.out.println("========================");
        System.out.println(sum2);
    }
}

O/P:
===
120

==========================
Behaviour Parameterization
==========================
package com.example.CompletableFutureEx.Java8.BehaviourParameterization;

import java.util.List;
import java.util.function.Predicate;

public class FP01FunctionalInterfaceBehaviourParameterization {


    public static void main(String[] args) {
        List<Integer> numbers = List.of(11, 2, 22, 3, 23, 4, 56, 7);
        Predicate<? super Integer> evenPredicate = x -> x % 2 == 0;
        numbers.stream()
                .filter(evenPredicate)
                .forEach(System.out::println);

        System.out.println("==================================");
        
        Predicate<? super Integer> oddPredicate = x->x%2!=0;
        numbers.stream()
                .filter(oddPredicate)
                .forEach(System.out::println);
    }
}

O/P:
===
2
22
4
56
==================================
11
3
23
7

===============================
Simplified Functional Interface
================================
public class SimplifiedWithFUnctionalInterface {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(11, 2, 22, 3, 23, 4, 56, 7);
        filterAndPrint(numbers, x -> x % 2 == 0);
        System.out.println("===============================");
        filterAndPrint(numbers, x -> x % 2 != 0);
    }

    private static void filterAndPrint(List<Integer> numbers, Predicate<? super Integer> predicate) {
            numbers.stream().filter(predicate).forEach(System.out::println);
    }
}


===========================
Supplier And Unary Operator
===========================
package com.example.CompletableFutureEx.Java8.BehaviourParameterization;

import java.util.Random;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class SupplierAndUnaryOperator {

    public static void main(String[] args) {

        // No Input Return something
        Supplier<Integer> randomIntegerSupplier = ()->2;
        Supplier<Integer> randomIntegerSupplier1 = () -> {
            Random random = new Random();
            return random.nextInt();
        };
        System.out.println(randomIntegerSupplier1.get());

        // Unary Operator
        UnaryOperator<Integer> unaryOperator = (x) -> 3*x;
        System.out.println(unaryOperator.apply(10));
    }
}

O/P:
===
-1616754551
30

===================================
BiPredicate, BiFunction, BiConsumer
===================================
package com.example.CompletableFutureEx.Java8.BehaviourParameterization;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class BiPredicateBiFunctionBiConsumer {

    public static void main(String[] args) {
        BiPredicate<Integer, String> biPredicate = (number,string) -> {
           return number < 16 && string.length() > 5;
        };
        System.out.println("================ BiPredicate =======================");
        System.out.println(biPredicate.test(15, "Hi Welcome to Java8"));

        BiFunction<Integer, String, String> biFunction = (number, str) -> {
                return number+"  "+str;
        };
        System.out.println("=================== BiFunction ====================");
        System.out.println(biFunction.apply(5, "Hi Welcome to Java8"));

        System.out.println("==================== BiConsumer ===================");
        BiConsumer<Integer, String> biConsumer = (s1,s2)->{
            System.out.println(s1);
            System.out.println(s2);
        };
       biConsumer.accept(10, "Hi! Welcome to Java8");
    }
}

O/P:
====
================ BiPredicate =======================
true
=================== BiFunction ====================
5  Hi Welcome to Java8
==================== BiConsumer ===================
10
Hi! Welcome to Java8

=================
Method Reference
=================
package com.example.CompletableFutureEx.Java8.MethodReferences;

import java.util.List;
import java.util.function.Supplier;

public class MethodReferencesEx {

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Hibernate", "Java", "J2EE", "JPA", "Spring Boot", "MicroServices");
        courses.stream().map(str -> str.toUpperCase()).forEach(str -> System.out.println(str));
        courses.stream().map(str->str.toUpperCase()).forEach(System.out::println);

        // By Using static method Reference
        courses.stream().map(str->str.toUpperCase()).forEach(MethodReferencesEx::print);

        // At map using Object Reference
        courses.stream().map(String::toUpperCase).forEach(MethodReferencesEx::print);

        // Constructor Reference
        Supplier<String> supplier = ()-> new String();
        Supplier<String > supplier1 = String::new;
    }

    private static void print(String str) {
        System.out.println(str);
    }
}

O/P:
===
SPRING
HIBERNATE
JAVA
J2EE
JPA
SPRING BOOT
MICROSERVICES
SPRING
HIBERNATE
JAVA
J2EE
JPA
SPRING BOOT
MICROSERVICES
SPRING
HIBERNATE
JAVA
J2EE
JPA
SPRING BOOT
MICROSERVICES
SPRING
HIBERNATE
JAVA
J2EE
JPA
SPRING BOOT
MICROSERVICES

=========================================================================
FunctionalProgramming With Custom Class (//allMatch, anyMatch, noneMatch)
=========================================================================
package com.example.CompletableFutureEx.Java8.FunctionalProgrammingWithCustomClass;

public class Course {
    private String name;
    private String category;
    private int reviewScore;
    private int noOfStudents;

    public Course(String name, String category, int reviewScore, int noOfStudents) {
        this.name = name;
        this.category = category;
        this.reviewScore = reviewScore;
        this.noOfStudents = noOfStudents;
    }

    // setters and getters
	// toString()
}



package com.example.CompletableFutureEx.Java8.FunctionalProgrammingWithCustomClass;

import java.util.List;
import java.util.function.Predicate;
import java.util.zip.CheckedOutputStream;

public class FP03CustomClasses {

    public static void main(String[] args) {
        List<Course> courses = List.of(new Course("Spring", "Framework", 98, 2000),
                new Course("Spring", "Framework", 91, 2000),
                new Course("AdvJava", "Framework", 90, 2000),
                new Course("API", "MicroServices", 92, 2000),
                new Course("MicroServices", "MicroServices", 96, 2000),
                new Course("Azure", "Cloud", 93, 2000),
                new Course("Aws", "Cloud", 99, 2000));

        // allMatch, noneMatch, anyMatch
        Predicate<? super  Course> reviewScoreGreaterthan95Predicate = course -> course.getReviewScore() > 95;
        Predicate<? super  Course> reviewScoreGreaterthan90Predicate = course -> course.getReviewScore() > 90;
        Predicate<? super  Course> reviewScorelessthan90Predicate = course -> course.getReviewScore() < 90;

        System.out.println(courses.stream().allMatch(reviewScoreGreaterthan95Predicate));
        System.out.println(courses.stream().noneMatch(reviewScorelessthan90Predicate));
        System.out.println(courses.stream().anyMatch(reviewScorelessthan90Predicate));
        System.out.println(courses.stream().anyMatch(reviewScoreGreaterthan90Predicate));

     }
}

O/P:
===
false
true
false
true

===================================================
Sorting Courses with sorted and creating Comparator
===================================================
package com.example.CompletableFutureEx.Java8.FunctionalProgrammingWithCustomClass;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FP03CustomClasses {

    public static void main(String[] args) {
        List<Course> courses = List.of(new Course("Spring", "Framework", 98, 1800),
                new Course("Spring", "Framework", 91, 2000),
                new Course("AdvJava", "Framework", 90, 1500),
                new Course("API", "MicroServices", 92, 2000),
                new Course("MicroServices", "MicroServices", 96, 1600),
                new Course("Azure", "Cloud", 93, 1700),
                new Course("Aws", "Cloud", 99, 2000));

        // allMatch, noneMatch, anyMatch
        Predicate<? super  Course> reviewScoreGreaterthan95Predicate = course -> course.getReviewScore() > 95;
        Predicate<? super  Course> reviewScoreGreaterthan90Predicate = course -> course.getReviewScore() > 90;
        Predicate<? super  Course> reviewScorelessthan90Predicate = course -> course.getReviewScore() < 90;

        System.out.println(courses.stream().allMatch(reviewScoreGreaterthan95Predicate));
        System.out.println(courses.stream().noneMatch(reviewScorelessthan90Predicate));
        System.out.println(courses.stream().anyMatch(reviewScorelessthan90Predicate));
        System.out.println(courses.stream().anyMatch(reviewScoreGreaterthan90Predicate));

        System.out.println("========================= comparingByNoOfStudentsIncreasing ======================");
        Comparator<Course> comparingByNoOfStudentsIncreasing = Comparator.comparing(Course::getNoOfStudents);
        System.out.println(courses.stream().sorted(comparingByNoOfStudentsIncreasing).collect(Collectors.toList()));
        // [Course{name='AdvJava', category='Framework', reviewScore=90, noOfStudents=1500}, Course{name='MicroServices', category='MicroServices', reviewScore=96, noOfStudents=1600}, Course{name='Azure', category='Cloud', reviewScore=93, noOfStudents=1700}, Course{name='Spring', category='Framework', reviewScore=98, noOfStudents=1800}, Course{name='Spring', category='Framework', reviewScore=91, noOfStudents=2000}, Course{name='API', category='MicroServices', reviewScore=92, noOfStudents=2000}, Course{name='Aws', category='Cloud', reviewScore=99, noOfStudents=2000}]
        System.out.println("======================= comparingByNoOfStudentsDecreasing ==========================");
        Comparator<Course> comparingByNoOfStudentsDecreasing = Comparator.comparing(Course::getNoOfStudents).reversed();
        System.out.println(courses.stream().sorted(comparingByNoOfStudentsDecreasing).collect(Collectors.toList()));
        // [Course{name='Spring', category='Framework', reviewScore=91, noOfStudents=2000}, Course{name='API', category='MicroServices', reviewScore=92, noOfStudents=2000}, Course{name='Aws', category='Cloud', reviewScore=99, noOfStudents=2000}, Course{name='Spring', category='Framework', reviewScore=98, noOfStudents=1800}, Course{name='Azure', category='Cloud', reviewScore=93, noOfStudents=1700}, Course{name='MicroServices', category='MicroServices', reviewScore=96, noOfStudents=1600}, Course{name='AdvJava', category='Framework', reviewScore=90, noOfStudents=1500}]
        System.out.println("=================================== comparingByNoOfStudentsAndNOOfReviews ========================");
        Comparator<Course> comparingByNoOfStudentsAndNOOfReviews = Comparator.comparing(Course::getNoOfStudents).thenComparing(Course::getReviewScore).reversed();
        System.out.println(courses.stream().sorted(comparingByNoOfStudentsAndNOOfReviews).collect(Collectors.toList()));
       // [Course{name='Aws', category='Cloud', reviewScore=99, noOfStudents=2000}, Course{name='API', category='MicroServices', reviewScore=92, noOfStudents=2000}, Course{name='Spring', category='Framework', reviewScore=91, noOfStudents=2000}, Course{name='Spring', category='Framework', reviewScore=98, noOfStudents=1800}, Course{name='Azure', category='Cloud', reviewScore=93, noOfStudents=1700}, Course{name='MicroServices', category='MicroServices', reviewScore=96, noOfStudents=1600}, Course{name='AdvJava', category='Framework', reviewScore=90, noOfStudents=1500}]
        System.out.println("====================================");
        System.out.println(courses.stream().limit(5).collect(Collectors.toList()));
        // [Course{name='Spring', category='Framework', reviewScore=98, noOfStudents=1800}, Course{name='Spring', category='Framework', reviewScore=91, noOfStudents=2000}, Course{name='AdvJava', category='Framework', reviewScore=90, noOfStudents=1500}, Course{name='API', category='MicroServices', reviewScore=92, noOfStudents=2000}, Course{name='MicroServices', category='MicroServices', reviewScore=96, noOfStudents=1600}]
        System.out.println("=================================");
        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsAndNOOfReviews)
                .skip(3) // Skip Top 3 Results
                .collect(Collectors.toList()));
        // [Course{name='Spring', category='Framework', reviewScore=98, noOfStudents=1800}, Course{name='Azure', category='Cloud', reviewScore=93, noOfStudents=1700}, Course{name='MicroServices', category='MicroServices', reviewScore=96, noOfStudents=1600}, Course{name='AdvJava', category='Framework', reviewScore=90, noOfStudents=1500}]
        System.out.println("=======================================");
        System.out.println(courses.stream()
                .sorted(comparingByNoOfStudentsAndNOOfReviews)
                .skip(3)
                .limit(5)
                .collect(Collectors.toList()));
        // [Course{name='Spring', category='Framework', reviewScore=98, noOfStudents=1800}, Course{name='Azure', category='Cloud', reviewScore=93, noOfStudents=1700}, Course{name='MicroServices', category='MicroServices', reviewScore=96, noOfStudents=1600}, Course{name='AdvJava', category='Framework', reviewScore=90, noOfStudents=1500}]
        System.out.println("=========== takeWhile =================");
        System.out.println(courses.stream().takeWhile(course -> course.getReviewScore() >=95).collect(Collectors.toList()));
        // [Course{name='Spring', category='Framework', reviewScore=98, noOfStudents=1800}]
        System.out.println("============= dropWhile =================");
        System.out.println(courses.stream().dropWhile(course -> course.getReviewScore() >=95).collect(Collectors.toList()));
        // [Course{name='Spring', category='Framework', reviewScore=91, noOfStudents=2000}, Course{name='AdvJava', category='Framework', reviewScore=90, noOfStudents=1500}, Course{name='API', category='MicroServices', reviewScore=92, noOfStudents=2000}, Course{name='MicroServices', category='MicroServices', reviewScore=96, noOfStudents=1600}, Course{name='Azure', category='Cloud', reviewScore=93, noOfStudents=1700}, Course{name='Aws', category='Cloud', reviewScore=99, noOfStudents=2000}]
        System.out.println("=============== MAX ====================");
        System.out.println(courses.stream().max(comparingByNoOfStudentsAndNOOfReviews));
        // Optional[Course{name='AdvJava', category='Framework', reviewScore=90, noOfStudents=1500}]
        System.out.println("================= MIN ====================");
        System.out.println(courses.stream().min(comparingByNoOfStudentsAndNOOfReviews));
        // Optional[Course{name='Aws', category='Cloud', reviewScore=99, noOfStudents=2000}]
        System.out.println("=================== Filter Min orElse =================");
        System.out.println(courses.stream().filter(reviewScorelessthan90Predicate).min(comparingByNoOfStudentsAndNOOfReviews).orElse(new Course("Aws", "Cloud", 99,2000)));
        // Course{name='Aws', category='Cloud', reviewScore=99, noOfStudents=2000}
        System.out.println("================= FindFirst ===================");
        System.out.println(courses.stream().filter(reviewScorelessthan90Predicate).findFirst());
        // Optional.empty
        System.out.println("============= ReviewScoreGreaterthan90Predicate FindFirst ===============");
        System.out.println(courses.stream().filter(reviewScoreGreaterthan90Predicate).findFirst());
        // Optional[Course{name='Spring', category='Framework', reviewScore=98, noOfStudents=1800}]
        System.out.println("========== ReviewScoreGreaterthan90Predicate FindAny ====================");
        System.out.println(courses.stream().filter(reviewScoreGreaterthan90Predicate).findAny());
        // Optional[Course{name='Spring', category='Framework', reviewScore=98, noOfStudents=1800}]
        /* Sum Avg Count*/
        System.out.println("================ Sum =======================");
        System.out.println(courses.stream().filter(reviewScoreGreaterthan90Predicate).mapToInt(Course::getNoOfStudents).sum());
        // 11100
        System.out.println("=================== Average ======================");
        System.out.println(courses.stream().filter(reviewScoreGreaterthan90Predicate).mapToInt(Course::getNoOfStudents).average());
        // OptionalDouble[1850.0]
        System.out.println("============== Count ===================");
        System.out.println(courses.stream().filter(reviewScoreGreaterthan90Predicate).mapToInt(Course::getNoOfStudents).count());
        // 6
        System.out.println("====================== MAX ========================");
        System.out.println(courses.stream().filter(reviewScorelessthan90Predicate).mapToInt(Course::getNoOfStudents).max());
        // OptionalInt.empty
     }
}







