import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [S/W 문제해결 기본] 7일차 - 미로2
 * 
 * @author hrlim
 * @version 1.0, 2022.08.11
 */
public class Solution_1227_BFS {

	public static int[][] map = new int[100][100];

	// 상, 하, 좌, 우
	public static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {
			br.readLine();

			int startR = 0;
			int startC = 0;

			for (int i = 0; i < 100; i++) {
				String input = br.readLine();
				for (int j = 0; j < 100; j++) {
					map[i][j] = input.charAt(j);
					if (map[i][j] == '2') {
						startR = i;
						startC = j;
					}
				}
			}

			sb.append("#").append(test_case).append(" ").append(bfs(startR, startC)).append("\n");

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static int bfs(int startR, int startC) {
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(startR);
		queue.add(startC);

		int answer = 0;

		int dr = 0;
		int dc = 0;
		while (!queue.isEmpty()) {
			int r = queue.poll();
			int c = queue.poll();

			if (map[r][c] == '3') {
				answer = 1;
				break;
			}

			map[r][c] = '1'; // 방문처리

			for (int i = 0; i < deltas.length; i++) {
				dr = r + deltas[i][0];
				dc = c + deltas[i][1];

				if (!isRange(dr, dc) || map[dr][dc] == '1') {
					continue;
				}

				queue.add(dr);
				queue.add(dc);
			}
		}

		return answer;
	}

	static boolean isRange(int x, int y) {
		if (x >= 0 && x < map.length && y >= 0 && y < map[0].length) {
			return true;
		}
		return false;
	}

}