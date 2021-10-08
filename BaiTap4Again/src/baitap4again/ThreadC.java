/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap4again;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieub
 */
public class ThreadC implements Callable<List<String>>{
    private Handle handle;
    private CountDownLatch latch;

    public ThreadC(Handle handle, CountDownLatch latch) {
        this.handle = handle;
        this.latch = latch;
    }
    
    @Override
    public List<String> call() throws Exception {
        File search = handle.getSearch();
        File output = handle.getOutput();
        List<Integer> listInteger = new ArrayList<>();
        try {
            handle.readFile(search, listInteger);
            handle.searchFile(output, listInteger, handle.getMes());
            latch.countDown();
        } catch (IOException ex) {
            Logger.getLogger(ThreadC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return handle.getMes();
    }
    
}
