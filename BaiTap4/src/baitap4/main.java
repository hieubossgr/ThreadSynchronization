/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap4;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author hieub
 */

public class main {
    
    private static File in = new File("input.txt");
    private static File out = new File("output.txt");
    private static File search = new File("search.txt");
    private static ThreadA A;
    private static ThreadB B;
    private static ThreadC C;
    public static List<String> mes = new ArrayList<>();
    public static boolean checkWriteFile = false;

    public static File getFileIn() {
        return in;
    }

    public static File getFileOut() {
        return out;
    }

    public static File getFileSearch() {
        return search;
    }
    

    /**
     * @param args the command line arguments
     */
    
    public static List<Integer> ReadFile(File file) {
        List<Integer> list = new ArrayList<>();
        RandomAccessFile raf;
        synchronized(file) {
            try {
                System.out.println(Thread.currentThread().getName() + " start read!!");
                raf = new RandomAccessFile(file, "r");
                String line = raf.readLine();
                while(line!=null) {
                    String[] arr = line.split(" ");
                    for(String s:arr) {
                        int number = 0;
                        for(char c:s.toCharArray()){
                                number = number*10 + Integer.parseInt(String.valueOf(c));
                        }
                        list.add(number);
                    }
                    line = raf.readLine();
                }  
                System.out.println(Thread.currentThread().getName() + " Read file done!!");
                raf.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(Thread.currentThread().getName().equals("Thread B")){
                System.out.println(Thread.currentThread().getName() + " start sort file!!!");
                Collections.sort(list);
                System.out.println(Thread.currentThread().getName() + " sorted file");
            }
        }
        
        return list;
    }
    
    public synchronized void WriteFile(File file, List<Integer> list) {
        RandomAccessFile raf;
        //synchronized(file) {
            try {
                System.out.println(Thread.currentThread().getName() + " Bat dau Write file");
                raf = new RandomAccessFile(file, "rw");
                for(int i:list) {
                    raf.writeBytes(String.valueOf(i) + " ");
                }
                System.out.println(Thread.currentThread().getName() + " Write File done");
                raf.close();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
            checkWriteFile = true;
            notify();
            checkWriteFile = false;
        //}
        
    }
    
    public synchronized void findNumberInFile(File file, List<Integer> list) {   // file = out
        System.out.println(Thread.currentThread().getName() + " start read file out");
        if(checkWriteFile == false) {
            try {
                System.out.println(Thread.currentThread().getName() + " is waitting...");
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Bị sao");
            }
        }
        System.out.println(Thread.currentThread().getName() + " start find");
        try {
            FileChannel f = new RandomAccessFile(file, "rw").getChannel();
            ByteBuffer bytes = f.map(FileChannel.MapMode.READ_WRITE, 0, f.size());
            Charset charset = Charset.forName("utf-8");
            CharBuffer chars = charset.decode(bytes);
            f.close();
                    
            Matcher matcher;
            mes = new ArrayList<>();
            for(int i:list) {
                Pattern pattern = Pattern.compile(String.valueOf(i), Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(chars);
                if(matcher.find()) 
                    mes.add("Đã tìm thấy ký tự " + i + " ở vị trí "+ ((matcher.start()+1)));
                else 
                    mes.add("Không tìm thấy " + i);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally{
            System.out.println(Thread.currentThread().getName() + " finded done!!!");
        }
        System.out.println(Thread.currentThread().getName() + " is done!!!");
    }
    
    public static void showMessage() {
        for(String i:mes) {
            System.out.println(i);
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        main bt = new main();
        
        B = new ThreadB(bt);
        Thread threadB = new Thread(B);
        threadB.setName("Thread B");
        
        C = new ThreadC(bt);
        Thread threadC = new Thread(C);
        threadC.setName("Thread C");
        
        A = new ThreadA(bt);
        Thread threadA = new Thread(A);
        threadA.setName("Thread A");
        
        threadB.start();
        threadC.start();
        threadC.join();
        threadA.start();
        
    }
    
}
