import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 알파벳
 * 
 * @author hrlim
 * @version 1.0, 2022.08.19
 *
 */
public class Main_1987 {

	public static int R, C;

	// 상, 우, 하, 좌
	public static int[][] dir4 = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static boolean[] alpha = new boolean[26];
	public static int[][] map;
	public static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = stringToInt(st.nextToken());
		C = stringToInt(st.nextToken());

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) - 'A';
			}
		}

		dfs(0, 0, 0);

		sb.append(answer);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void dfs(int row, int col, int cnt) {
		
		if(alpha[map[row][col]]) {
			answer = Math.max(answer, cnt);
			return;
		}
		
		alpha[map[row][col]] = true;
		
		for (int i = 0; i < dir4.length; i++) {
			int dRow = row + dir4[i][0];
			int dCol = col + dir4[i][1];

			if (isRange(dRow, dCol)) {
				dfs(dRow, dCol, cnt + 1);
			}
		}
		
		alpha[map[row][col]] = false;
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