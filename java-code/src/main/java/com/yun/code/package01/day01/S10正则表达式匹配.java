package com.yun.code.package01.day01;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
class S10正则表达式匹配 {
        public boolean isMatch(String s, String p) {
            if(s == null || p==null){
                return false;
            }
            return match(s,0,p,0);
        }
        private boolean match(String s,int s1,String p,int p1){
            boolean isStrDone = s1>=s.length();
            boolean isPatDone = p1>=p.length();

            if(isStrDone&&isPatDone){
                return true;
            }
            if(!isStrDone&&isPatDone){
                return false;
            }
            //一、下一个字符是*
            if(p1+1<p.length()&&p.charAt(p1+1)=='*'){
                if(isStrDone){
                    return match(s,s1,p,p1+2);
                }
                if(p.charAt(p1)==s.charAt(s1)||p.charAt(p1)=='.'&&s.charAt(s1)!='\0'){
                    return match(s,s1+1,p,p1)||match(s,s1,p,p1+2);
                }else{
                    return match(s,s1,p,p1+2);
                }
            }
            if(isStrDone&&!isPatDone){
                return false;
            }

            //二、下一个字符不是*
            if(p.charAt(p1)==s.charAt(s1)||p.charAt(p1)=='.'&&s.charAt(s1)!='\0'){
                return match(s,s1+1,p,p1+1);
            }
            return false;

        }
    }