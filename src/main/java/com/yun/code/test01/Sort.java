package com.yun.code.test01;

public class Sort {
    public static void main(String[] args) {
        int[] a = new int[]{39,38,65,97,76,13,27,49};
                          //27,38,65,97,76,13,27,49
                          //27,38,65,97,76,13,65,49
                          //27,38,13,97,76,13,65,49
                          //27,38,13,97,76,97,65,49
        print(a);
        sort(a,0,a.length-1);
        print(a);
    }
    private static void sort(int[] a,int low ,int high){
        if(low>high){
            return;
        }
        int i=low,j=high;
        int index=a[i];
        while (i<j){
            while (i<j&& a[j]>index){
                j--;
            }
            if(i<j){
                a[i++]=a[j];
            }
            while (i<j && a[i]<index){
                i++;
            }
            if(i<j){
                a[j--]=a[i];
            }
        }
        a[i]=index;
        sort(a,low,i-1);
        sort(a,i+1,high);
    }

    private static void print(int[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+",");
        }
        System.out.println();
    }
}