import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 
 * SWEA 4012 [모의 SW 역량테스트] 요리사
 * 
 * @author hrlim
 * @version 1.0, 2022.08.12
 */
public class Solution_4012 {

	private static int minDiff;
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = stringToInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = stringToInt(br.readLine());
			minDiff = Integer.MAX_VALUE;

			map = new int[N][N];

			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = stringToInt(st.nextToken());
				}
			}

			divideMaterial(0, N / 2, 0, new boolean[N]);
			sb.append("#").append(test_case).append(" ").append(minDiff).append(System.lineSeparator());
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void divideMaterial(int current, int max, int start, boolean[] isVisited) {
		if (current == max) {
			int foodA = 0, foodB = 0;

			for (int i = 0; i < isVisited.length; i++) {
				if (isVisited[i]) {
					for (int j = 0; j < isVisited.length; j++) {
						if (!isVisited[j])
							continue;
						foodA += map[i][j];
					}
				} else {
					for (int j = 0; j < isVisited.length; j++) {
						if (isVisited[j])
							continue;
						foodB += map[i][j];
					}
				}
			}
			minDiff = Math.min(Math.abs(foodA - foodB), minDiff);
			return;
		}

		for (int i = start; i < isVisited.length; i++) {
			isVisited[i] = true;
			divideMaterial(current + 1, max, i + 1, isVisited);
			isVisited[i] = false;
		}
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
