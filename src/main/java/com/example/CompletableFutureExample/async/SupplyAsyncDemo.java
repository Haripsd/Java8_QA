package com.example.CompletableFutureExample.async;

import com.example.CompletableFutureExample.async.database.EmployeeDatabase;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SupplyAsyncDemo {

    public List<Employee> getEmployees() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        CompletableFuture<List<Employee>> listEmployess = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Executed By Thread : " + Thread.currentThread().getName());
                    return EmployeeDatabase.getEmployeesDetails();
                }, executor);
        return listEmployess.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SupplyAsyncDemo supplyAsyncDemo = new SupplyAsyncDemo();
        List<Employee> employees = supplyAsyncDemo.getEmployees();
       // employees.stream().forEach(System.out::println);
        System.out.println(employees.size());
    }
}
