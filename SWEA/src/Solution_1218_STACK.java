import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/**
 * [S/W 문제해결 기본] 4일차 - 괄호 짝짓기
 * 
 * @author hrlim
 * @version 1.0, 2022.08.04
 */
public class Solution_1218_STACK {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= 10; test_case++) {

			int n = stringToInt(br.readLine());
			String s = br.readLine();

			Stack<Character> stack = new Stack<>();

			for (int i = 0; i < n; i++) {

				if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}' || s.charAt(i) == '>') {
					if (s.charAt(i) - stack.peek() > 0 && s.charAt(i) - stack.peek() < 3) {
						// System.out.println(s.charAt(i) + " " + stack.peek() + " = " + (s.charAt(i) - stack.peek()));
						stack.pop();
					} else {
						break;
					}
				} else {
					stack.push(s.charAt(i));
				}
			}
			if (stack.size() == 0)
				sb.append("1\n");
			else
				sb.append("0\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
