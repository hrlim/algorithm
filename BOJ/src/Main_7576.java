
import java.io.*;
import java.util.*;

/**
 * 토마토 - 골드 5 그래프 이론, 깊이 우선 탐색
 * 
 * @author hrlim
 * @version 1.0, 2022.08.23
 */

public class Main_7576 {

	static int[][] map;
	static int N, M;

	// 상하좌우
	static int[][] dirs4 = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = stringToInt(st.nextToken());
		N = stringToInt(st.nextToken());

		map = new int[N][M];
		List<int[]> startList = new ArrayList<int[]>();

		int pivotCount = 0; // 덜익은 토마토 갯수
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stringToInt(st.nextToken());
				if (map[i][j] == 0) {
					pivotCount++;
				} else if (map[i][j] == 1) {
					startList.add(new int[] { i, j });
				}
			}
		}

		sb.append(bfs(startList, pivotCount)).append("");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int bfs(List<int[]> startList, int pivotCount) {
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < startList.size(); i++) {
			int[] start = startList.get(i);
			queue.offer(start[0]);
			queue.offer(start[1]);
		}

		int cnt = 0;
		int maxTime = 0;
		while (!queue.isEmpty()) {
			int curRow = queue.poll();
			int curCol = queue.poll();

			if (cnt == pivotCount) {
				break;
			}

			maxTime = Math.max(maxTime, map[curRow][curCol]);
			for (int i = 0; i < dirs4.length; i++) {
				int dRow = curRow + dirs4[i][0];
				int dCol = curCol + dirs4[i][1];

				if (isRange(dRow, dCol) && map[dRow][dCol] == 0) {
					map[dRow][dCol] = map[curRow][curCol] + 1;
					queue.offer(dRow);
					queue.offer(dCol);
					cnt++;
				}
			}
		}
		if (cnt == pivotCount) {
			return maxTime;
		} else {
			return -1;
		}
	}

	static boolean isRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
