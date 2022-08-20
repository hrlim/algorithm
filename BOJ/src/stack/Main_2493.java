package stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * BOJ 2493 탑 - 골드 5
 * <b>자료구조, 스택</b>
 * 
 * @author hrlim
 * @version 1.0, 2022.08.05
 */
public class Main_2493 {

	public static void main(String[] args) throws IOException {

		StringBuilder sb = new StringBuilder();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = stringToInt(br.readLine());

		Stack<Top> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < T; i++) {
			int tmp = stringToInt(st.nextToken());
			if (stack.isEmpty()) {
				stack.push(new Top(i, tmp));
				sb.append("0 ");
			} else {
				if (stack.peek().height >= tmp) {
					sb.append((stack.peek().index + 1) + " ");
					stack.push(new Top(i, tmp));
				} else {
					while (!stack.isEmpty() && stack.peek().height < tmp) {
						stack.pop();
					}
					if (!stack.isEmpty()) {
						sb.append((stack.peek().index + 1) + " ");
					} else {
						sb.append("0 ");
					}
					stack.push(new Top(i, tmp));
				}

			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	/**
	 * 문자형 타입을 숫자형 타입으로 변환해주는 메서드
	 * 
	 * @param s
	 * @return
	 */
	public static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}

/**
 * 송전탑의 위치와 높이를 가지는 클래스
 * 
 * @author hrlim
 * @version 1.0, 2022.08.05
 */
class Top {
	int index;
	int height;

	public Top(int i, int h) {
		this.index = i;
		this.height = h;
	}
}
