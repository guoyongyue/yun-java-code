package code.day02;
import java.util.Stack;
public class S20有效的括号 {
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(println("{}[](1)"));
    }
    public static boolean println(String values){
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i<values.length();i++ ) {
            if(stack.size()!=0){
                Character peek = stack.peek();
                if((peek=='{') && ('}'==values.charAt(i))){
                    stack.pop();
                    continue;
                }
                if((peek=='[') && (']'==values.charAt(i))){
                    stack.pop();
                    continue;
                }
                if((peek=='(') && (')'==values.charAt(i))){
                    stack.pop();
                    continue;
                }
                stack.push(values.charAt(i));
            }else {
                stack.push(values.charAt(i));
            }
        }
        if(stack.size()==0){
            return true;
        }else{
            return false;
        }
    }
}
