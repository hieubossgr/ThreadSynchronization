/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap4again;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieub
 */
public class Handle {

    /**
     * @param args the command line arguments
     */
    private final File input = new File("input.txt");
    private final File output = new File("output.txt");
    private final File search = new File("search.txt");
    private List<String> mes = new ArrayList<>();

    public File getInput() {
        return input;
    }

    public File getOutput() {
        return output;
    }

    public File getSearch() {
        return search;
    }
    
    public List<String> getMes() {
        return mes;
    }

    public static void readFile(File file, List<Integer> list) throws IOException {
        synchronized(file) {
            FileInputStream inputStream = null;
            BufferedReader bufferedReader = null;
            try {
                inputStream = new FileInputStream(file);
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = bufferedReader.readLine();
                while(line!=null) {
                    String[] arr = line.split(" ");
                    for(String s:arr) {
                        int number = 0;
                        for(char c:s.toCharArray()) {
                            number = number*10 + Integer.parseInt(String.valueOf(c));
                        }
                        list.add(number);
                    }
                    line = bufferedReader.readLine();
                }
            } catch (Exception e) {
                System.out.println("Loi: " + e);
            } finally {
                inputStream.close();
                bufferedReader.close();
            }
        }
        
    }
    
    public static void writeFile(File file, List<Integer> list){
        Writer write = null;
        try {
            write = new FileWriter(file);
            for(int number:list) {
                write.write(number+ " ");
            }
            write.close();
        } catch (IOException ex) {
            Logger.getLogger(Handle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void sortArray(List<Integer> list) {
        Collections.sort(list);
    }

    public static void searchFile(File output, List<Integer> listInput, List<String> listOutput) throws FileNotFoundException {
        Scanner scan = new Scanner(output);
        while(scan.hasNext()) {
            String[] arr = scan.nextLine().split(" ");
            int length1 = listInput.size();
            int length2 = arr.length;
            int startOfListInput = 0;
            int startOfArr = 0;
            while(startOfListInput<length1) {
                int value = Integer.parseInt(arr[startOfArr]);
                int count = 0;
                while(value == listInput.get(startOfListInput)) {
                    count++; startOfArr++;
                    value = Integer.parseInt(arr[startOfArr]);
                }
                if(count>0) {
                    listOutput.add("Tìm thấy " + count + " ký tự  " + listInput.get(startOfListInput));
                }
                else listOutput.add("Không tìm thấy ký tự " + listInput.get(startOfListInput));
                startOfListInput++;
            }
        }
    }
    
    
}
