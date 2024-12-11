Stream API Cheet Sheet
======================
EmployeeCheatSheetTO.java
=========================
public class EmployeeCheatSheetTO {

    private Integer id;
    private String name;
    private String dept;
    private List<Project> projects;
    private Double salary;
    private String gender;
	// Constructor
	// setters and getters
	}
	
Project.java
=============
public class Project {

    private String projectCode;
    private String projectName;
    private String client;
    private String buLeadName;
	// Constructor
	// setters and getters

}	

EmployeeDataBase.java
======================
package com.example.qa;

import java.util.Arrays;
import java.util.List;

public class EmployeeDataBase {

    public static List<EmployeeCheatSheetTO> getAllEmployees() {
        Project p1 = new Project("P001", "Alpha", "ABC Corp", "Alice");
        Project p2 = new Project("P002", "Beta", "XYZ Ltd", "Bob");
        Project p3 = new Project("P003", "Gamma", "ABC Corp", "Alice");
        Project p4 = new Project("P004", "Delta", "TechWorld", "Charlie");
        Project p5 = new Project("P005", "Epsilon", "MoneyMatters", "Daniel");
        Project p6 = new Project("P006", "Zeta", "SmartTech", "Eva");
        Project p7 = new Project("P007", "Eta", "BrandBoost", "George");
        Project p8 = new Project("P008", "Theta", "InnoSoft", "Hannah");
        Project p9 = new Project("P009", "Iota", "FastTrack", "Ian");
        Project p10 = new Project("P010", "Kappa", "DigitalWave", "Jessica");

        // Employee instances
        EmployeeCheatSheetTO e1 = new EmployeeCheatSheetTO(1, "John Doe", "Development", Arrays.asList(p1, p2), 80000d, "Male");
        EmployeeCheatSheetTO e2 = new EmployeeCheatSheetTO(2, "Jane Smith", "Development", Arrays.asList(p3), 80000d, "Female");
        EmployeeCheatSheetTO e3 = new EmployeeCheatSheetTO(3, "Robert Brown", "Sales", Arrays.asList(p4), 60000d, "Male");
        EmployeeCheatSheetTO e4 = new EmployeeCheatSheetTO(4, "Lisa White", "HR", Arrays.asList(p1), 55000d, "Female");
        EmployeeCheatSheetTO e5 = new EmployeeCheatSheetTO(5, "Michael Green", "Finance", Arrays.asList(p5), 90000d, "Male");
        EmployeeCheatSheetTO e6 = new EmployeeCheatSheetTO(6, "Sophia Brown", "Development", Arrays.asList(p6), 85000d, "Female");
        EmployeeCheatSheetTO e7 = new EmployeeCheatSheetTO(7, "James Wilson", "Marketing", Arrays.asList(p7), 72000d, "Male");
        EmployeeCheatSheetTO e8 = new EmployeeCheatSheetTO(8, "Olivia Harris", "Development", Arrays.asList(p8), 88000d, "Female");
        EmployeeCheatSheetTO e9 = new EmployeeCheatSheetTO(9, "William Lee", "Sales", Arrays.asList(p9), 78000d, "Male");
        EmployeeCheatSheetTO e10 = new EmployeeCheatSheetTO(10, "Emily Clark", "Development", Arrays.asList(p10), 95000d, "Female");

        return Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }
}

Java8MethodCheatSheet.java
==========================
package com.example.qa;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8MethodCheatSheet {

    public static void main(String[] args) {
        List<EmployeeCheatSheetTO> allEmployees = EmployeeDataBase.getAllEmployees();
        /*for(EmployeeCheatSheetTO employees : allEmployees) {
            System.out.println(employees);
        }*/
        // 1. forEach()
        System.out.println("================= Stream forEach =====================");
        allEmployees.stream().forEach(System.out::println);

        System.out.println("================== filter ===========================");
        // 2. filter()
        Map<Integer, String> developmentDept = allEmployees.stream()
                .filter(e -> e.getDept().equals("Development") && e.getSalary() > 65000)
                .collect(Collectors.toMap(EmployeeCheatSheetTO::getId, EmployeeCheatSheetTO::getName));
        System.out.println(developmentDept);

        System.out.println("========================== Map ======================");
        // 3. map()
        // distinct()
        List<String> departments = allEmployees.stream()
                .map(EmployeeCheatSheetTO::getDept)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(departments);

        // 4. FlatMap
        System.out.println("======================= flatMap ========================");
        List<Stream<String>> streamStream = allEmployees.stream()
                .map(e -> e.getProjects().stream().map(p -> p.getProjectName())).collect(Collectors.toList());
        List<String> projectNames = allEmployees.stream()
                .flatMap(e -> e.getProjects().stream()
                .map(p -> p.getProjectName()))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(projectNames);

        // 5. sort()
        // Asc
        System.out.println("====================== sort ============================");
        List<EmployeeCheatSheetTO> sortedSalaryAsc = allEmployees.stream()
                .sorted(Comparator.comparing(EmployeeCheatSheetTO::getSalary)).toList();
        sortedSalaryAsc.forEach(System.out::println);

        System.out.println("=================== DESC ORDER ====================");
        List<EmployeeCheatSheetTO> sortedSalaryDesc = allEmployees.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(EmployeeCheatSheetTO::getSalary))).toList();
        sortedSalaryDesc.forEach(System.out::println);

        // 6. Min And Max
        System.out.println("======================== Min And Max ========================");
        Optional<EmployeeCheatSheetTO> minPaidSalary = allEmployees.stream().min(Comparator.comparingDouble(EmployeeCheatSheetTO::getSalary));
        Optional<EmployeeCheatSheetTO> maxPaidSalary = allEmployees.stream().max(Comparator.comparingDouble(EmployeeCheatSheetTO::getSalary));
        System.out.println("Highest Paid Salary : "+minPaidSalary.get().getSalary());
        System.out.println("Lowest Paid Salary : "+maxPaidSalary.get().getSalary());

        // 7. groupBy()
        System.out.println("========================== GroupBy ===========================");
        // count()
        Map<String, Long> empGroupCountMap = allEmployees.stream().collect(Collectors.groupingBy(EmployeeCheatSheetTO::getGender, Collectors.counting()));
        // Male and Female Employees names
        Map<String, List<String>> genderName = allEmployees.stream()
                .collect(Collectors.groupingBy(EmployeeCheatSheetTO::getGender, Collectors.mapping(EmployeeCheatSheetTO::getName, Collectors.toList())));
        System.out.println(empGroupCountMap);
        System.out.println(genderName);

        // 8. FindFirst()
        System.out.println("===================== FindFirst =========================");
        EmployeeCheatSheetTO optDevelopmentDept = allEmployees.stream()
                .filter(e -> e.getDept().equals("Development"))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Employye Object not found"));
//        System.out.println(optDevelopmentDept.get()); // NPE
//        if(optDevelopmentDept.isPresent()) {
//            System.out.println(optDevelopmentDept.get());
//        }
//        optDevelopmentDept.ifPresent(e-> System.out.println(e.getName()));
        System.out.println(optDevelopmentDept);

        // 9.findAny()
        EmployeeCheatSheetTO findAnyOptObject = allEmployees.stream()
                .filter(e -> e.getDept().equals("Development"))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Employees Objec not found."));
        System.out.println(findAnyOptObject);

        // 10. anyMatch(), allMatch(), noneMatch()
        System.out.println("====================== anyMatch(), allMatch(), noneMatch() ==========================");
        boolean anyMatchPredicate = allEmployees.stream().anyMatch(e -> e.getDept().equals("Development"));
        boolean allMatchPredicate = allEmployees.stream().allMatch(e -> e.getSalary() > 50000);
        boolean noneMatchPredicate = allEmployees.stream().noneMatch(e -> e.getDept().equals("xyz"));
        System.out.println(anyMatchPredicate);
        System.out.println(allMatchPredicate);
        System.out.println(noneMatchPredicate);

        // 11. limit() and skip()
        System.out.println("========================= limit() and skip() ====================");
        List<EmployeeCheatSheetTO> topPaidEmployee = allEmployees.stream()
                .sorted(Comparator.comparing(EmployeeCheatSheetTO::getSalary).reversed())
                .limit(4)
                .toList();
        topPaidEmployee.forEach(e-> System.out.println(e.getName()));
        System.out.println("---------");
        List<EmployeeCheatSheetTO> skipEmployee = allEmployees.stream()
                .skip(5)
                .toList();
        skipEmployee.forEach(System.out::println);


    }
}
