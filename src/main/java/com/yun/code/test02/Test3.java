package com.yun.code.test02;

        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileReader;

public class Test3 {
    public static void main(String[] args) {
        File inputFile = new File("D:/domain.txt");
        String domain="roll.sports.sina.com.cn";
        Test3.readDomainFile(inputFile,domain);
    }

    public static void readDomainFile(File file,String domain){
        String maxDoMain = null;
        if(domain==null || domain.length()==0){
            return;
        }
        String[] split = domain.split(".");
        int splitSize=split.length;

        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = br.readLine())!=null){
                if(domain.contains(s)){
                    int length = s.split(".").length;
                    if((length+1)==splitSize){
                        System.out.println(s);
                        return;
                    }else {
                        if(s.length()>maxDoMain.length()){
                            maxDoMain=s;
                        }
                    }
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(maxDoMain);
    }
}
