
package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * SWEA 2001 파리 퇴치
 * 
 * @author hrlim
 * @version 1.0, 2022.08.03
 */
public class Solution_2001 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = stringToInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = stringToInt(st.nextToken());
			int M = stringToInt(st.nextToken());

			int[][] map = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					map[i][j] = map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1] + Integer.parseInt(st.nextToken());
				}
			}

			int max = Integer.MIN_VALUE;
			for (int i = M; i <= N; i++) {
				for (int j = M ; j <= N; j++) {
					int sum = map[i][j] - (map[i - M][j] + map[i][j - M] - map[i - M][j - M]);
					if (max < sum) {
						max = sum;
					}
				}
			}
			System.out.println("#" + test_case + " " + max);

		}
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
