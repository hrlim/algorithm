import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * SWEA 2819 격자판의 숫자 이어 붙이기
 * 
 * @author hrlim
 * @version 1.0, 2022.08.22
 */
public class Solution_2819 {

	static int[][] map;
	static HashSet<Integer> set;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = stringToInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			map = new int[4][4];
			set = new HashSet<>();
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					map[i][j] = stringToInt(st.nextToken());
				}
			}

			for (int r = 0; r < 4; r++) {
				for (int c = 0; c < 4; c++) {
					dfs(r, c, "" + map[r][c]);
				}
			}

			sb.append("#").append(test_case).append(" ").append(set.size()).append("\n");

		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	static void dfs(int row, int col, String num) {
		if (num.length() == 7) {
			set.add(stringToInt(num));
			return;
		}

		for (int i = 0; i < 4; i++) {
			int dRow = row + dr[i];
			int dCol = col + dc[i];

			if (isRange(dRow, dCol)) {
				dfs(dRow, dCol, num + map[dRow][dCol]);
			}
		}
	}

	static boolean isRange(int row, int col) {
		return row >= 0 && row < 4 && col >= 0 && col < 4;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
