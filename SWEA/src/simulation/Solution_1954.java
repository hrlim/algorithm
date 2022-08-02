package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 
 * SWEA 1954 달팽이 숫자
 * 
 * @author hrlim
 * @version 1.0, 2022.08.02
 */

public class Solution_1954 {

	// 0 : 오른쪽, 1 : 아래, 2 : 왼쪽 , 3 : 위
	public static final int[] dx = { 0, 1, 0, -1 };
	public static final int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			if (N == 1) {
				System.out.println("#" + test_case);
				System.out.println("1");
				continue;
			}

			int[][] arr = new int[N][N];
			int direction = 0;
			int curX = 0, curY = 0;
			
			for (int i = 0; i < N * N; i++) {
				arr[curX][curY] = i + 1;
				curX += dx[direction];
				curY += dy[direction];
				if ((curX < 0 || curX >= N || curY < 0 || curY >= N) || arr[curX][curY] != 0) {
					curX -= dx[direction];
					curY -= dy[direction];

					direction = (direction + 1) % 4;

					curX += dx[direction];
					curY += dy[direction];
				}
			}
			
			System.out.println("#" + test_case);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
