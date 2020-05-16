package com.yun.pattern.proxy.staticproxy;

public class Father {

    private Person person;

    public Father(Person person){
        this.person = person;
    }

    public void findGirlFriend(){
        System.out.println("父亲物色对象");
        person.findGirlFriend();
        System.out.println("下周领证");
    }
}
