/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap4;


/**
 *
 * @author hieub
 */
public class ThreadA implements Runnable{
    private Main bt;

    public ThreadA(Main bt) {
        this.bt = bt;
    }
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is start");
        bt.showMessage();
        System.out.println(Thread.currentThread().getName() + " is done");
    }
}
