/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap4again;

import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author hieub
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException, FileNotFoundException {
        int n=3;
        Handle handle = new Handle();
        CountDownLatch latch = new CountDownLatch(n);
        ExecutorService executor = Executors.newFixedThreadPool(n);
        ThreadA thA = new ThreadA(handle, latch);
        ThreadB thB = new ThreadB(handle, latch);
        ThreadC thC = new ThreadC(handle, latch);
        
        Future future1 = executor.submit(thB);
        future1.get();
        Future future2 = executor.submit(thC);
        future2.get();
        Future future3 = executor.submit(thA);
        latch.await();
        System.out.println("Done");
        executor.shutdown();
        
    }
    
}
