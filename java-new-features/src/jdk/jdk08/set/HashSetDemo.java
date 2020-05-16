package jdk.jdk08.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

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

        set = HashSetDemo.filter(set);
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

    public static Set<String> filter(Set<String> set) {
        return set.stream().filter(str->
            str.hashCode()%2==1
        ).collect(Collectors.toSet());
    }
}
