package com.yun.pattern.adapter.loginadapter;

/**
 * @class name:  Member <br/>
 * @description: 适配器模式<br/>
 * @date: 2020/5/21 23:24<br/>
 * @author: yun<br />
 */
public class Member {

    private String username;

    private String password;

    private String mid;

    private String info;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Member(String username, String password, String mid, String info) {
        this.username = username;
        this.password = password;
        this.mid = mid;
        this.info = info;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
