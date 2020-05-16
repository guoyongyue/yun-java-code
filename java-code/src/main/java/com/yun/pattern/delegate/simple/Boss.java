package com.yun.pattern.delegate.simple;

public class Boss {
    public void common(String common,Leader leader){
        leader.doing(common);
    }
}
