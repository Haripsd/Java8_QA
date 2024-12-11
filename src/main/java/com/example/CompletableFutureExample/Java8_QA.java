Java8 QA
=========
1.) What are all features of Java8 did you used.?
A.) i.) Functional Interface (Including default and static method)
    ii.) Lambda Expression
	iii.) Stream API
	iv.) CompletableFuture
	v.) Java DateTime API

2.) What is Functional Interface .?	
A.) An Interface that contains only one abstract method is known as Functional interface. It can have any number of default and static methods.

3,) Can You tell the Functional interfaces which are already there before Java8 ?
A.) Runnable
    Callable
	Comparator (In Comparator we have equals( ) method but that is overriding from Java.lang.Object 
	
4.) can we extend One Functional Interface with another Functional interface ? 
A.) Yes, it is possible But, it act as a normal interfaces inheritace because two abstract methods are not possible by extending the Functional Interface.

5.) What are all functional interface introduced in Java8.? 
A.) Function
	Predicate
	Consumer
	Supplier

6.) What is Lambda expression.?
A.) Lamda expressions basically express instances of functional interfaces, in other word it provide a clear and concise way to represent method of a functional using an expression

7.) What is the Advantages and disadvantages of using Lambda expression.?
advantages
==========
1. Avoid writing Annonymous Impl.
2. It saves a lot of code.
3. The Code is directly readable without interpretation.

DisAdvantages
=============
1. Hard to use without IDE.
2. Complex to debug.

8.) What is Stream API.?
A.) Stream API introduced in Java8 and it is used to process collections of objects with functional style of coding using Lamda expression.

9.) What is Stream in Java8.?
A.) A Stream is a sequence of objects that supports various methods which can be pipelined to produce the desired result.

The Features of Java Stream are - 
A Stream is not a datastructure instead it takes input from the Collections, Arrays (or) I/O Channels.
Stream don't change the original datastructure, they only provide the result as per the piplelined methods.

10.) What is MethodReference in Java8.?
A.) The MethodReference is a shorthand notation of lambda expression to call a method.
Ex: numList.stream().filter(n->n>5).sorted().forEach(System.out::println);  	

11.) Spell few Stream method used in your Project.?
A.) filter
    forEach
	sorted
	map
	flatMap
	reduce
	groupingBy
	count
	collect
12.) When to use Map and flatMap.?
A.) Map is using for there is 1-1 relation in collections
If 1-Many Relation we will use flatMap for parallel operations.

13.) WAP using stream to find the frequency of each Character in a given String.?
A.) String str = "HariPrasad";
        Map<String, Long> eachCharFrequency = Arrays.stream(str.split("")).collect(groupingBy(Function.identity(), counting()));
        System.out.println(eachCharFrequency);
		
O/P:
===
{P=1, a=3, r=2, s=1, d=1, H=1, i=1}		

14.) Assume you have a list of employees in various dept, WAP to find highest paid employee from each dept. ?

	@Data
	@AllArgsConstructor
	@TOString
A.) public class Employee {
    private Integer id;
    private String name;
    private String dept;
    private Integer salary;
	
	// Constructor
	// setters and getters
	// toString
	}
	
	public class EmployeeTest {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Anil", "DEV", 5000),
                new Employee(8, "Sunil", "DEV", 8000),
                new Employee(3, "Tejesh", "QA", 6000),
                new Employee(9, "Siva", "QA", 9000),
                new Employee(4, "Bobby", "DEVOPS", 4000)
                );
        // Approach 1
        Comparator<Employee> comparedBySalary = Comparator.comparing(Employee::getSalary);
        Map<String, Optional<Employee>> highstPaidSalary = employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.reducing(BinaryOperator.maxBy(comparedBySalary))));
        System.out.println(highstPaidSalary);

        // Approach 2
        Map<String, Employee> higstSalaryEachDept = employees.stream().collect(Collectors.groupingBy(
                Employee::getDept,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::getSalary)), Optional::get)));
        System.out.println(higstSalaryEachDept);

        // Approach 3
        Map<String, Optional<Employee>> highestPaidDept = employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        System.out.println(highestPaidDept);
    }
}

O/P:
===
{QA=Optional[Employee{id=9, name='Siva', dept='QA', salary=9000}], DEVOPS=Optional[Employee{id=4, name='Bobby', dept='DEVOPS', salary=4000}], DEV=Optional[Employee{id=8, name='Sunil', dept='DEV', salary=8000}]}


15.) Stream VS Parallel Stream .?
A.) Sequential Stream -> Using for Single core of the system.
    Parallel Strem  -> Using for Multiple core of the System.
	
	Ex:
	==
	IntStream.rangeClosed(1, 10).forEach(t -> System.out.println(Thread.currentThread().getName() + ":" + t));
    System.out.println("=========================================================");
    IntStream.rangeClosed(1, 10).parallel().forEach(t -> System.out.println(Thread.currentThread().getName() + ":" + t));
	
O/P:
====
main:1
main:2
main:3
main:4
main:5
main:6
main:7
main:8
main:9
main:10
=========================================================
main:7
main:6
main:9
main:10
main:8
ForkJoinPool.commonPool-worker-2:4
ForkJoinPool.commonPool-worker-4:1
ForkJoinPool.commonPool-worker-1:3
main:2
ForkJoinPool.commonPool-worker-3:5

16.) What is CompletableFuture.?
A.) CompletableFuture used for asynchronous programming in Java. Asynchronous Programming is a means of writing non-blocking code by running a task on a seperate Thread than the main application thread and notifying the main thread about its progress, completion or failure.

17.) Why CompletableFuture why not Future.?
A.) 1.) It cannot be manually completed.
    2.) Multiple futures cannot be chained together.
	3.) You cannot combine multiple futures together.
	4.) No Exception handling.
	
18.) How to decide ThreadPool size.?
A.) CPU Intensive tasks
    IO Intensive tasks
19.) WAP to print even and odd using 2 Threads.?
A.) With Java7
package com.example.qa;

public class EvenOddPrintThread implements Runnable {

    Object object;
    static int i = 1;

    public EvenOddPrintThread(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        while (i <= 10) {
            if (i % 2 == 0 && Thread.currentThread().getName().equals("even")) {
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    i++;
                    try {
                        object.wait();
                    } catch (InterruptedException ex) {
                        ex.getMessage();
                    }
                }
            }

            if (i % 2 != 0 && Thread.currentThread().getName().equals("odd")) {
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    i++;
                    object.notify();
                }
            }
        }

    }

    public static void main(String[] args) {
        Object obj = new Object();
        Runnable th1 = new EvenOddPrintThread(obj);
        Runnable th2 = new EvenOddPrintThread(obj);
        new Thread(th1, "even").start();
        new Thread(th2, "odd").start();
    }
}

O/P:
===
odd : 1
even:2
odd : 3
even:4
odd : 5
even:6
odd : 7
even:8
odd : 9
even:10
odd : 11

with CompletableFuture
========================
package com.example.qa;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class EvenOddPrinter {

    private static Object object = new Object();
    private static IntPredicate evenCondition = e -> e % 2 == 0;
    private static IntPredicate oddCondition = e -> e % 2 != 0;

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.runAsync(() -> EvenOddPrinter.printNumber(oddCondition));
        CompletableFuture.runAsync(() -> EvenOddPrinter.printNumber(evenCondition));
        Thread.sleep(1000);
    }

    public static void printNumber(IntPredicate condition) {
        IntStream.rangeClosed(1, 10).filter(condition).forEach(EvenOddPrinter::execute);
    }

    public static void execute(int number) {
        synchronized (object) {
            try {
                System.out.println(Thread.currentThread().getName() + " : " + number);
                object.notify();
                object.wait();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
    }
}

O/P:
=====
ForkJoinPool.commonPool-worker-1 : 1
ForkJoinPool.commonPool-worker-2 : 2
ForkJoinPool.commonPool-worker-1 : 3
ForkJoinPool.commonPool-worker-2 : 4
ForkJoinPool.commonPool-worker-1 : 5
ForkJoinPool.commonPool-worker-2 : 6
ForkJoinPool.commonPool-worker-1 : 7
ForkJoinPool.commonPool-worker-2 : 8
ForkJoinPool.commonPool-worker-1 : 9
ForkJoinPool.commonPool-worker-2 : 10
	
	