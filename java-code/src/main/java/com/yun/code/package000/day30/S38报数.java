package com.yun.code.package000.day30;

class S38报数 {
   public String countAndSay(int n) {
        return digui(n-1,"1");
    }

    public String digui(int n,String value){
        if (n == 0) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        int count = 1;
        if (value.length() ==1 ){
            sb.append(count).append((value.charAt(0)));
        }else{
            for (int i = 0; i < value.length(); i++) {
                if (i+1 == value.length()){
                    sb.append(count).append(value.charAt(i));
                }else {
                    if(value.charAt(i) == value.charAt(i+1)){
                        count++;
                    }else {
                        sb.append(count).append(value.charAt(i));
                        count = 1;
                    }
                }
            }
        }
        return digui(--n,sb.toString());
    }
}
