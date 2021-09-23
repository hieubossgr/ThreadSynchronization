/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap4;

import static baitap4.Main.getFileSearch;
import java.util.List;

/**
 *
 * @author hieub
 */
public class ThreadC implements Runnable{
    private Main bt;

    public ThreadC(Main bt) {
        this.bt = bt;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is start");
        List<Integer> list = bt.ReadFile(getFileSearch());
        bt.findNumberInFile(bt.getFileOut(), list);
    }
}
