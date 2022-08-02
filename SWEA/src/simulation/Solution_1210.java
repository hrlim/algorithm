package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * SWEA 1210 Ladder1
 * 
 * @author hrlim
 * @version 1.0, 2022.08.02
 */

public class Solution_1210 {

	public static int[][] dirs2 = { { 0, 1 }, { 0, -1 } };

	public static int[][] map = new int[100][100];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) {
			br.readLine();

			int startX = -1;
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int length = st.countTokens();
				for (int j = 0; j < length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 2) {
						startX = j;
					}
				}
			}

			for (int curY = 98; curY > 0; curY--) {
				int movedX = getMovedX(startX, curY, 100);
				if (movedX == startX) {
					continue;
				}
				startX = movedX;
			}
			System.out.println("#" + test_case + " " + startX);
		}

	}

	static int getMovedX(int x, int y, int N) {
		int movedX = x;
		for (int i = 0; i < dirs2.length; i++) {
			int tempX = x + dirs2[i][1];
			while (tempX >= 0 && tempX < N && map[y][tempX] != 0) {
				movedX = tempX;
				tempX += dirs2[i][1];
			}
		}
		return movedX;
	}

}
