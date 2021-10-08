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
public class ThreadB implements Callable<File>{
    private Handle handle;
    private CountDownLatch latch;

    public ThreadB(Handle handle, CountDownLatch latch) {
        this.handle = handle;
        this.latch = latch;
    }
    
    @Override
    public File call() throws Exception {
        List<Integer> list = new ArrayList<>();
        File input = handle.getInput();
        File output = handle.getOutput();
        try {
            handle.readFile(input, list);
            handle.sortArray(list);
            handle.writeFile(output, list);
            latch.countDown();
        } catch (IOException ex) {
            Logger.getLogger(ThreadB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }

    
}
