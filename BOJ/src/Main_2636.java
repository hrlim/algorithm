import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 치즈 - 골드 4
 * 
 * @author hrlim
 * @version 1.0, 2022.10.11
 *
 */
public class Main_2636 {

	// 상하좌우
	public static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stringToInt(st.nextToken());
		M = stringToInt(st.nextToken());

		int cheeseTotalCnt = 0;
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stringToInt(st.nextToken());
				if (map[i][j] == 1)
					cheeseTotalCnt++;
			}
		}

		int meltTime = 0;
		int cheeseCnt = 0;
		boolean[][] isVisited = new boolean[N][M];
		
		while (cheeseTotalCnt != 0) {
			
			for (boolean[] visited : isVisited) {
				Arrays.fill(visited, false);
			}

			meltTime++;
			cheeseCnt = 0;

			Queue<int[]> queue = new LinkedList<>();
			queue.add(new int[] { 0, 0 });
			isVisited[0][0] = true;

			while (!queue.isEmpty()) {
				
				int[] position = queue.poll();
				int row = position[0];
				int col = position[1];

				for (int dir = 0; dir < dirs.length; dir++) {
					int dx = row + dirs[dir][0];
					int dy = col + dirs[dir][1];

					if (!isRange(dx, dy)) continue;
					if (isVisited[dx][dy]) continue;

					isVisited[dx][dy] = true;
					if (map[dx][dy] == 1) {
						map[dx][dy] = 0;
						cheeseCnt++;
					} else if (map[dx][dy] == 0) {
						queue.add(new int[] { dx, dy });
					}
				}
			}

			cheeseTotalCnt -= cheeseCnt;
		}

		System.out.println(meltTime);
		System.out.println(cheeseCnt);
	}

	private static boolean isRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 & col < M;
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
