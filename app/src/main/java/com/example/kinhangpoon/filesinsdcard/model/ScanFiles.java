package com.example.kinhangpoon.filesinsdcard.model;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by KinhangPoon on 10/2/2018.
 */

public class ScanFiles {
    File root;
    ArrayList<File> fileList;
    public void initialize(){
         root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
         fileList = new ArrayList<>();
         getfile(root);

    }

    public ArrayList<File> getfile(File dir){
        File[] listFile = dir.listFiles();
        if(listFile!=null && listFile.length > 0){
            for(int i=0;i<listFile.length;i++){
                if(listFile[i].isDirectory()){
                    getfile(listFile[i]);
                }
                else{
                    fileList.add(listFile[i]);
                }
            }
        }
        return fileList;
    }
    // Top 10
    public class TenBig{
        public LinkedList<String> fileNames;
        public LinkedList<String> sizes;

        public TenBig(LinkedList<String> fileNames, LinkedList<String> sizes) {
            this.fileNames = fileNames;
            this.sizes = sizes;
        }
    }
    public TenBig getBigName(){
        LinkedList<String> result_names = new LinkedList<>();
        LinkedList<String> result_sizes = new LinkedList<>();
        PriorityQueue<File> queue = new PriorityQueue<>(10, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return (int) ((o1.length()-o2.length())/1024.0);
            }
        });
        for(File file:fileList){
            queue.offer(file);
            if(queue.size()>10){
                queue.poll();
            }
        }
        while(!queue.isEmpty()){
            File cur = queue.poll();
            result_names.addFirst(cur.getName());
            result_sizes.addFirst(cur.length()/1024.0+"");
        }
        return new TenBig(result_names,result_sizes);
    }

    // Average:
    public double average(){
        double result =0;
        for(File file:fileList){
            result+=file.length()/1024.0;
        }
        return (result+0.0)/fileList.size();
    }

    //Frequent:
    public class FiveFrequent{
        public LinkedList<String> fileExtension;
        public LinkedList<Integer> frequency;

        public FiveFrequent(LinkedList<String> fileExtension, LinkedList<Integer> frequency) {
            this.fileExtension = fileExtension;
            this.frequency = frequency;
        }
    }

    public FiveFrequent getFrequent(){
        LinkedList<String> result_names = new LinkedList<>();
        LinkedList<Integer> result_frequency = new LinkedList<>();
        HashMap<String,Integer> map = new HashMap<>();
        for(File file:fileList){
            String filename = file.getName();
            int dotIndex = filename.indexOf(".");
            String extension = filename.substring(dotIndex+1);
            Log.i("name",file.getName());

            if(!map.containsKey(extension)){
                map.put(extension,1);
            }
            else{
                map.put(extension,map.get(extension)+1);
            }
        }
        final HashMap<String,Integer> final_map = map;
        PriorityQueue<String> queue = new PriorityQueue<>(5, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return final_map.get(o1)-final_map.get(o2);
            }
        });
        for(String key:final_map.keySet()){
            queue.offer(key);
            Log.i("times",map.get(key)+"");
            if(queue.size()>5){
                queue.poll();
            }
        }
        while(!queue.isEmpty()){
            String cur = queue.poll();
            result_names.addFirst(cur);
            result_frequency.addFirst(final_map.get(cur));
        }
        return new FiveFrequent(result_names,result_frequency);
    }
}
