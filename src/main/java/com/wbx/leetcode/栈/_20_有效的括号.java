package com.wbx.leetcode.栈;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @describe： https://leetcode-cn.com/problems/valid-parentheses/
 *    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * @Date：2019/12/29 22:43
 * @author：wbx
 */
public class _20_有效的括号 {


    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap(3){
            {
                put('(',')');
                put('{','}');
                put('[',']');
            }
        };

        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {// 左括号
                stack.push(c);
            } else {// 右括号
                if (stack.isEmpty()) {
                    return false;
                }

                Character left = stack.pop();
                if (map.get(left) != c) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


    /**
     * 1. 遇见左字符，将左字符入栈
     * 2. 遇见右字符
     * 如果栈是空的，说明括号无效
     * 如果栈不为空，将栈顶字符出栈，与右字符之匹配
     * ✓ 如果左右字符不匹配，说明括号无效
     * ✓ 如果左右字符匹配，继续扫描下一个字符
     * 3. 所有字符扫描完毕后
     * ✓ 栈为空，说明括号有效
     * ✓ 栈不为空，说明括号无效
     */
    public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {// 左括号
                stack.push(c);
            } else {// 右括号
                if (stack.isEmpty()) {
                    return false;
                }

                Character left = stack.pop();
                if (left == '(' && c != ')') {
                    return false;
                }
                if (left == '{' && c != '}') {
                    return false;
                }
                if (left == '[' && c != ']') {
                    return false;
                }

            }
        }

        return stack.isEmpty();
    }



    /**
     * 初学者
     */
    public boolean isValid2(String s) {
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        }
        return s.isEmpty();
    }




}
