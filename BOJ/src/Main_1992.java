import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 쿼드트리
 * 
 * @author hrlim
 * @version 1.0, 2022.08.18
 *
 */
public class Main_1992 {

	public static char[][] map;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = stringToInt(br.readLine());
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < input.length(); j++) {
				map[i][j] = input.charAt(j);
			}
		}

		dfs(0, 0, N);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int i, int j, int size) {

		if (isPossible(i, j, size)) {
			sb.append(map[i][j]);
			return;
		}

		int half = size / 2;

		sb.append("(");

		dfs(i, j, half);
		dfs(i, j + half, half);
		dfs(i + half, j, half);
		dfs(i + half, j + half, half);

		sb.append(")");

	}

	private static boolean isPossible(int row, int col, int size) {
		int pivot = map[row][col];

		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (pivot != map[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}