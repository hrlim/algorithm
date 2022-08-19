import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 캐슬 디펜스
 * 
 * @author hrlim
 * @version 1.0, 2022.08.19
 *
 */

public class Main_17135_임하림 {

	private static int maxKill;

	private static int N, M, D;
	private static char[][] map;

	private static int[][] dir3 = { { -1, -1 }, { -1, 0 }, { -1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stringToInt(st.nextToken());
		M = stringToInt(st.nextToken());
		D = stringToInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}

		}

		comb(0, 0, new int[3]);
		System.out.println(maxKill);
	}

	static void comb(int cnt, int start, int[] output) {
		if (cnt == 3) {
			System.out.println("========== " + Arrays.toString(output));
			int count = kill(output);
			maxKill = Math.max(maxKill, count);
			System.out.println("===========================" + count);
			return;
		}

		for (int i = start; i < M; i++) {
			output[cnt] = i;
			comb(cnt + 1, i + 1, output);
		}
	}

	private static int kill(int[] output) {

		char[][] tempMap = copyArr(map);

		int killCount = 0;
		for (int row = N; row > 0; row--) {

			for (int i = 0; i < output.length; i++) {
				int minDistance = D + 1;
				int minRow = -1;
				int minCol = -1;

				for (int enemyRow = row - D; enemyRow < row; enemyRow++) {
					if(enemyRow < 0) continue;
					for (int enemyCol = 0; enemyCol < M; enemyCol++) {
						if (tempMap[enemyRow][enemyCol] == '1') {
							if (minDistance > getDistance(enemyRow, enemyCol, row, output[i])) {
								minDistance = getDistance(enemyRow, enemyCol, row, output[i]);
								minRow = enemyRow;
								minCol = enemyCol;
							}
						}
					}
				}
				if (minDistance <= D && minRow != -1 && minCol != -1) {
					if(i == 0) {
						tempMap[minRow][minCol] = 'A';
					} if(i == 1)
					tempMap[minRow][minCol] = 'B';
					if(i == 2)
						tempMap[minRow][minCol] = 'C';
					killCount++;
				}

			}

			for (int i = 0; i < tempMap.length; i++) {
				System.out.println(Arrays.toString(tempMap[i]));
			}
			System.out.println("================== 궁수 위치 ==" + row);
		}

		return killCount;

	}

	static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	static boolean isRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

	static char[][] copyArr(char[][] origin) {
		char[][] res = new char[N][M];
		for (int i = 0; i < res.length; i++) {
			res[i] = Arrays.copyOf(origin[i], M);
		}
		return res;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
