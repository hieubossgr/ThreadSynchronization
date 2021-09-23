/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap4;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author hieub
 */
public class ThreadB implements Runnable{
    private main bt;
        
    public ThreadB(main bt) {
        this.bt = bt;
    }
        
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is start");
        List<Integer> list = bt.ReadFile(bt.getFileIn());
        bt.WriteFile(bt.getFileOut(), list);
        System.out.println(Thread.currentThread().getName() + " is done!!!");
    }
}
