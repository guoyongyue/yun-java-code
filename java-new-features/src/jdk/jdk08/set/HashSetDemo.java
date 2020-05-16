package jdk.jdk08.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * @author: yun<\br>
 * @description: <\br>
 * @date:  2020/5/11 9:48<\br>
*/
public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("5");
        set.add("2");
        set.add("3");
        set.add("3");
        set.add("1");

        HashSetDemo.print(set);

    }

    public static void print(Set<String> set) {
        for (String str: set) {
            System.out.println(str);
        }


        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String str = it.next();
//            System.out.println(str);
        }

    }
}
