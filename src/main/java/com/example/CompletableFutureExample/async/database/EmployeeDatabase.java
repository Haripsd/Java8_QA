package com.example.CompletableFutureExample.async.database;

import com.example.CompletableFutureExample.async.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EmployeeDatabase {

    public static List<Employee> getEmployeesDetails() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Employee> employeeList = null;
        try {
            employeeList = objectMapper.readValue(new File("src/main/resources/employees.json"), new TypeReference<List<Employee>>() {
            });
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return employeeList;
    }
}
