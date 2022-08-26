import java.io.*;
import java.util.StringTokenizer;

/**
 * 배열 돌리기 1 - 실버 1
 * 
 * @author hrlim
 * @version 1.0, 2022.08.27
 *
 */

public class Main_16926 {

	public static int N, M, R, P;
	public static int[][] map;

	// 우 하 좌 상 or
	public static int[][] dir4 = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stringToInt(st.nextToken());
		M = stringToInt(st.nextToken());
		R = stringToInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stringToInt(st.nextToken());
			}
		}

		int depth = Math.min(N, M) / 2;
		for (int i = 0; i < depth; i++) {
			P = i;
			rotate(i, i, 1, i);
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void rotate(int row, int col, int rnum, int depth) {

		int start = map[row][col];
		int dRow, dCol;

		int dir = 0;

		while (true) {
			dRow = row + dir4[dir][0];
			dCol = col + dir4[dir][1];

			// 경계를 벗어났거나, 테두리의 값인지 확인 후 방향 전환
			if (!isRange(dRow, dCol, depth)) {
				dir = (dir + 1) % 4;
				// 방향 전환 된 위치를 다시 셋팅 필요
				dRow = row + dir4[dir][0];
				dCol = col + dir4[dir][1];
			}

			map[row][col] = map[dRow][dCol];
			row = dRow;
			col = dCol;

			if (row == P && col == P) {
				map[P + 1][P] = start;
				break;
			}
		}
		if (rnum < R) rotate(row, col, rnum + 1, depth);

	}

	static boolean isRange(int row, int col, int depth) {
		return row >= 0 + depth && row < N - depth && col >= 0 + depth && col < M - depth;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
