package com.example.CompletableFutureExample.streamapi;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class EvenAndOddPrinterByCompletableFuture {

    private static Object object = new Object();
    private static IntPredicate evenCondition = e -> e % 2 == 0;
    private static IntPredicate oddCondition = e -> e % 2 != 0;


    public static void printResult(IntPredicate condition) {
        IntStream.rangeClosed(1, 10)
                .filter(condition)
                .forEach(EvenAndOddPrinterByCompletableFuture::execute);
    }


    public static void execute(int i) {
        synchronized (object) {
            System.out.println("Thread Name : "+Thread.currentThread().getName()+" value :"+i);
            try {
                object.notify();
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.runAsync(()->EvenAndOddPrinterByCompletableFuture.printResult(evenCondition));
        CompletableFuture.runAsync(()->EvenAndOddPrinterByCompletableFuture.printResult(oddCondition));
        Thread.sleep(1000);
    }
}
