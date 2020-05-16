package com.yun.code.day08;

import java.util.Stack;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * <p>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * <p>
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 */
public class S71简化路径 {
    public static void main(String[] args) {

    }

    public static String simplifyPath(String path) {
        String[] split = path.split("/");
        Stack<String> pathStack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            if (!split[i].isEmpty() && split[i].equals("..")) {
                pathStack.pop();
            } else if (!split[i].equals("") && !split[i].equals(".") && !split[i].equals("..")) {
                pathStack.push(split[i]);
            }
        }
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < pathStack.size(); i++) {
            res.append("/" + pathStack.get(i));
        }
        return res.toString();

    }

}
