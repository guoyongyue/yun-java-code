package com.yun.code.test02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.TreeMap;

public class Test2 {

    private static TreeMap map = new TreeMap<Integer,Integer>();

    public static void main(String[] args) {

        File inputFile = new File("D:/input.txt");
        Test2.readKeywordFile(inputFile);

        if(map!=null && map.size()>0){
            Iterator it = map.keySet().iterator();
            int i=0;
            while (it.hasNext()) {
                System.out.println(i+"-"+(i+99)+" "+map.get(it.next()));
                i=i+100;
            }
        }
    }
    public static void readKeywordFile(File file){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            String s = null;

            int i=0;
            while((s = br.readLine())!=null){
                int key = Integer.valueOf(s);
                int region  = key / 100;
                if(map.containsKey(region)){
                    int prevenientValue=(int)map.get(region);
                    map.put(region,prevenientValue+1);
                }else {
                    map.put(region,1);
                }
                i++;
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
