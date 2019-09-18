package code.test02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Test1 {

    private static String[] keyword=null;

    public static void main(String[] args) {
        File inputFile = new File("D:/input.txt");
        File keywordFile = new File(":/keyword.conf");
        Test1.readKeywordFile(keywordFile);
        if(keyword!=null){
            Test1.readInputFile(inputFile);
        }
    }

    public static void readKeywordFile(File file){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            int i=0;
            while((s = br.readLine())!=null){
                keyword[i]=s;
                i++;
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void readInputFile(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = br.readLine())!=null){
                String[] split = s.split(" ");
                if(split!=null && split.length==2){
                    for (int i = 0; i < keyword.length; i++) {
                        if(split[0].contains(keyword[i])){
                            System.out.println(split[0]+" "+split[1]);
                        }
                    }
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
