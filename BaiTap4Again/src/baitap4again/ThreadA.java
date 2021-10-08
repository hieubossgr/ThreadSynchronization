/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap4again;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieub
 */
public class ThreadA implements Runnable {
    private Handle handle;
    private CountDownLatch latch;

    public ThreadA(Handle handle, CountDownLatch latch) {
        this.handle = handle;
        this.latch = latch;
    }

    @Override
    public void run() {
        List<String> mes = handle.getMes();
        for(String info:mes) {
            System.out.println(info);
        }
        latch.countDown();
    }
    
}
