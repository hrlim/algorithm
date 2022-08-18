import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 빵집
 * 
 * @author hrlim
 * @version 1.0, 2022.08.18
 *
 */
public class Main_3109 {

	public static int R, C;

	// 오른쪽대각선위, 오른쪽, 오른쪽대각선아래
	// 방향 순서가 중요 ! 
	public static int[][] dir3 = {{ -1, 1 }, { 0, 1 }, { 1, 1 }};

	public static char[][] map;
	public static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = stringToInt(st.nextToken());
		C = stringToInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < R ; i++) {
			dfs(i, 0);
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static boolean dfs(int row, int col) {

		if (col == C - 1) {
			answer++;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < map.length; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("---------------");
			return true;
		}

		for (int i = 0; i < dir3.length; i++) {
			int dRow = row + dir3[i][0];
			int dCol = col + dir3[i][1];

			if (isRange(dRow, dCol) && map[dRow][dCol] == '.') {
				map[dRow][dCol] = '-';
				if (dfs(dRow, dCol)) return true;
			}
		}
		return false;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

	static boolean isRange(int row, int col) {
		if (row >= 0 && row < R && col >= 0 && col < C)
			return true;
		return false;
	}
}