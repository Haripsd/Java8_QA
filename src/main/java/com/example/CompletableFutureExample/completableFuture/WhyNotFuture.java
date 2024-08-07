package com.example.CompletableFutureExample.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class WhyNotFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<List<Integer>> future = service.submit(() -> {
            // We are calling some API Call
            delay(1);
            System.out.println("Thread: "+Thread.currentThread().getName());
          return Arrays.asList(1, 2, 3, 4, 5);
        });

        List<Integer> numbers = future.get();
        System.out.println(numbers);

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.get();
        completableFuture.complete("return some dummy data");
    }


    public static void delay(int minutes) {
        try {
            TimeUnit.MINUTES.sleep(minutes);
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
    }
}
