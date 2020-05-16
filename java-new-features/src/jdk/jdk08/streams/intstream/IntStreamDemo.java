package jdk.jdk08.streams.intstream;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStreamDemo {
    public static void main(String[] args) {


        System.out.println("random begin========>");
        IntStreamDemo:random();
        System.out.println();

        System.out.println("filter begin========>");
        IntStreamDemo:filter();
        System.out.println();

        System.out.println("average begin========>");
        IntStreamDemo:average();
        System.out.println();

        System.out.println("boxed begin========>");
        IntStreamDemo:boxed();
        System.out.println();

        System.out.println("peek begin========>");
        IntStreamDemo:peek();
        System.out.println();

    }

    private static void random() {
        IntStream range = IntStream.range(0, 10);
        range.forEach(i -> System.out.print(i+","));
    }

    private static void filter(){
        Arrays.asList(1,2,3,4,5,6,7,8,8).stream().
                filter(i -> i%2==0).
                distinct().
                collect(Collectors.toList())
                .forEach(integer -> System.out.print(integer));
    }

    private static void average(){
        IntStream range = IntStream.range(0, 10);
        System.out.print(range.average().getAsDouble());
//        System.out.println(range.max());
//        System.out.println(range.min());
//        System.out.println(range.skip(2));
//        System.out.println(range.limit(2));
//        System.out.println(range.sorted());
//        System.out.println(range.noneMatch(i -> i==10));
//        System.out.println(range.parallel());
    }

    private static void boxed(){
        IntStream.range(0, 10).boxed().forEach(i-> System.out.print(i+","));
    }

    private static void peek(){
        IntStream.range(1, 20).skip(2).limit(5).peek(i -> System.out.print(i+",")).sum();
    }
}
