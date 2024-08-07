package com.example.CompletableFutureExample.async;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RunAsyncDemo {

    public void saveEmployee(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Employee> employeeList = objectMapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
                    });
                    // write Logic to save List Employees in Database

                    System.out.println("Thread : " + Thread.currentThread().getName());
                    employeeList.stream().forEach(System.out::println);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        runAsyncFuture.get();
    }

    public void saveEmployeesWithLambdaWithCustomExecutor(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        Executor executor = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(
                () -> {
                    try {
                        List<Employee> employeeList = objectMapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
                        });
                        // write Logic to save List Employees in Database
                        System.out.println("Thread : " + Thread.currentThread().getName());
                        System.out.println(employeeList.size());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }, executor);
        runAsyncFuture.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunAsyncDemo runAsyncDemo = new RunAsyncDemo();
        //runAsyncDemo.saveEmployee(new File("src/main/resources/employees.json"));
        runAsyncDemo.saveEmployeesWithLambdaWithCustomExecutor(new File("src/main/resources/employees.json"));
    }
}
