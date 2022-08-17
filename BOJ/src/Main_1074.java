import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Z
 */
public class Main_1074 {

	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = stringToInt(st.nextToken());
		int r = stringToInt(st.nextToken());
		int c = stringToInt(st.nextToken());

		visit((int) Math.pow(2, N), r, c);
		System.out.println(answer);
	}

	static void visit(int size, int row, int col) {
		if (size == 1)
			return;

		int half = size / 2;

		// 왼쪽상단
		if (row < half && col < half) {
			visit(half, row, col);
		}
		// 오른쪽상단
		else if (row < size / 2 && col >= half) {
			answer += (size * size / 4);
			visit(half, row, col - size / 2);
		}
		// 왼쪽하단
		else if (row >= half && col < half) {
			answer += (size * size / 4) * 2;
			visit(half, row - half, col);
		}
		// 오른쪽하단
		else {
			answer += (size * size / 4) * 3;
			visit(half, row - half, col - half);
		}

	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}