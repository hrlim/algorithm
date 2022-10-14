package lessons._7_StacksAndQueues;

import java.util.Stack;

public class Brackets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solution(String S) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0, size = S.length(); i < size; i++) {
			char c = S.charAt(i);
			if (c == '{' || c == '[' || c == '(') {
				stack.push(c);
				continue;
			} else if (!stack.isEmpty()) {
				char compare = stack.peek();
				if( c == ')' && compare != '(') {
					return 0;
				}
				if( c == '}' && compare != '{') {
					return 0;
				}
				if( c == ']' && compare != '[') {
					return 0;
				}
				stack.pop();
				continue;
			}
			return 0;
		}

		if(stack.size() != 0) return 0;
		return 1;
	}
}
