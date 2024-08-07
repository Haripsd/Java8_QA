package com.example.CompletableFutureExample.async;

import com.example.CompletableFutureExample.async.database.EmployeeDatabase;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class EmployeeReminderService {

    public CompletableFuture<Void> sendReminderToEmployee() {
        Executor executor = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("fetch Employee : " + Thread.currentThread().getName());
            return EmployeeDatabase.getEmployeesDetails();
        }, executor).thenApplyAsync((employees) -> {
            System.out.println("Filter New Joined Employee : " + Thread.currentThread().getName());
            return employees.stream()
                    .filter(employee -> "TRUE".equals(employee.getNewJoiner()))
                    .collect(Collectors.toList());
        }, executor).thenApplyAsync((employees) -> {
            System.out.println("Learning Pending Employee : " + Thread.currentThread().getName());
            return employees.stream()
                    .filter((employee) -> "TRUE".equals(employee.getLearningPending()))
                    .collect(Collectors.toList());
        }, executor).thenApplyAsync((employees) -> {
            System.out.println("Get Employees Email who are pending in Training : " + Thread.currentThread().getName());
            return employees.stream().map(Employee::getEmail).collect(Collectors.toList());
        }, executor).thenAcceptAsync((emails) -> {
            System.out.println("Sending reminder emails : " + Thread.currentThread().getName());
            emails.forEach(EmployeeReminderService::sendEmail);
        }, executor);
        return voidCompletableFuture;
    }

    public static void sendEmail(String email) {
        System.out.println("sending email reminder to training pending employees : "+email);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EmployeeReminderService service = new EmployeeReminderService();
        service.sendReminderToEmployee().get();
    }
}
