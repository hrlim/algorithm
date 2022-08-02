
package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * SWEA 2805 농작물 수확하기
 * 
 * @author hrlim
 * @version 1.0, 2022.08.02
 */
public class Solution_2805 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < input.length(); j++) {
					map[i][j] = input.charAt(j) - '0';
				}
			}

			int sum = 0;
			for (int i = 0; i < N; i++) {
				
				if (i <= N / 2 ) {
					int start = Math.abs(N / 2 - i);
					int end = Math.abs(N / 2 + i);
					for (int j = start; j <= end; j++) {
						sum += map[i][j];
					}
					
				}
				else {
					int start = Math.abs(i - N / 2);
					int end = Math.abs(N - (i - N / 2) );

					for (int j = start; j < end; j++) {
						sum += map[i][j];
					}
				}
			}
			System.out.println("#" + test_case + " " + sum);
		}
	}
}
