//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yun.code.test03;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.invoke.MethodHandles.Lookup;

public class MyAtomicBoolean implements Serializable {
    private static final long serialVersionUID = 4654671469794556979L;
    private static final VarHandle VALUE;
    private volatile int value;

    public MyAtomicBoolean(boolean initialValue) {
        this.value = initialValue ? 1 : 0;
    }

    public MyAtomicBoolean() {
    }

    public final boolean get() {
        return this.value != 0;
    }

    public final boolean compareAndSet(boolean expectedValue, boolean newValue) {
        return VALUE.compareAndSet(this, expectedValue ? 1 : 0, newValue ? 1 : 0);
    }

    public String toString() {
        return Boolean.toString(this.get());
    }

    static {
        try {
            Lookup l = MethodHandles.lookup();
            VALUE = l.findVarHandle(MyAtomicBoolean.class, "value", Integer.TYPE);
        } catch (ReflectiveOperationException var1) {
            throw new ExceptionInInitializerError(var1);
        }
    }
}
