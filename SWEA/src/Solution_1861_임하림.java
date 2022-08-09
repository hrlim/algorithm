import java.io.*;
import java.util.StringTokenizer;

/**
 * SWEA 1861 정사각형 방
 * 
 * @author hrlim
 * @version 1.0, 2022.08.08
 */
public class Solution_1861_임하림 {

	// 상 우 하 좌
	public static final int[] dx = { -1, 0, 1, 0 };
	public static final int[] dy = { 0, 1, 0, -1 };

	public static int[][] map;

	public static int maxMove = 0;
	public static int roomNo = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = stringToInt(br.readLine());

			map = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					map[i][j] = stringToInt(st.nextToken());
				}
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					check(i, j, 1, i, j);
				}
			}

			sb.append("#" + tc + " " + roomNo + " " + maxMove + "\n");
			maxMove = 0;
			roomNo = Integer.MAX_VALUE;
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void check(int x, int y, int cnt, int startX, int startY) {

		int value = map[x][y];

		for (int i = 0; i < 4; i++) {
			int tempX = x + dx[i];
			int tempY = y + dy[i];

			if (!isRange(tempX, tempY)) {
				continue;
			}

			if (map[tempX][tempY] - value == 1) {
				check(tempX, tempY, cnt + 1, startX, startY);
			}
		}

		if (cnt > maxMove) {
			maxMove = cnt;
			roomNo = map[startX][startY];
		} else if (cnt == maxMove) {
			roomNo = Math.min(map[startX][startY], roomNo);
		}

	}

	static boolean isRange(int x, int y) {
		if (x > 0 && x < map.length && y > 0 && y < map.length) {
			return true;
		}
		return false;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}