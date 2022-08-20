import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 캐슬 디펜스
 * 조합, 구현
 * @author hrlim
 * @version 1.0, 2022.08.19
 *
 */

public class Main_17135 {

	private static int maxKill;

	private static int N, M, D;
	private static char[][] map;

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

	/**
	 * 궁수의 위치를 뽑는 조합 메서드
	 * @param cnt 		현재까지 뽑은 궁수의 수
	 * @param start 	다음 궁수뽑기위한 시작 인덱스
	 * @param output	현재까지 뽑힌 궁수들의 인덱스
	 */
	static void comb(int cnt, int start, int[] output) {
		if (cnt == 3) {
			maxKill = Math.max(maxKill, kill(output));
			return;
		}

		for (int i = start; i < M; i++) {
			output[cnt] = i;
			comb(cnt + 1, i + 1, output);
		}
	}

	/**
	 * 궁수를 배치하여 적을 죽이는 메서드
	 * @param output	조합된 궁수의 위치
	 * @return
	 */
	private static int kill(int[] output) {

		char[][] tempMap = deepCopy(map);
		int[] killCoordinate = new int[6]; // 궁수마다 죽이려고 하는 적의 좌표

		int killCount = 0;
		for (int row = N; row >= 1; row--) { // 궁수가 올라가면서 적을 처지

			for (int i = 0; i < output.length; i++) {
				int minDistance = D;
				int minRow = N;
				int minCol = M;

				for (int enemyRow = row - 1; enemyRow >= row - D; enemyRow--) {	// 가장 가까운 적부터 검사
					if (enemyRow < 0)
						continue;
					for (int enemyCol = 0; enemyCol < M; enemyCol++) {
						if (tempMap[enemyRow][enemyCol] == '1') {
							int distance = getDistance(enemyRow, enemyCol, row, output[i]);
							if (minDistance > distance) {
								minDistance = distance;
								minRow = enemyRow;
								minCol = enemyCol;
							} else if(minDistance == distance && minCol > enemyCol) {
								minRow = enemyRow;
								minCol = enemyCol;
							}
						}
					}
				}
				// 사정거리 안에 적이 있으면 해당 적의 위치를, 없으면 -1 을 저장
				if (minDistance <= D && minRow != N && minCol != M) {
					killCoordinate[i * 2] = minRow;
					killCoordinate[i * 2 + 1] = minCol;
				} else {
					killCoordinate[i * 2] = -1;
					killCoordinate[i * 2 + 1] = -1;
				}
			}

			for (int i = 0; i < output.length; i++) {
				int dRow = killCoordinate[i * 2];
				int dCol = killCoordinate[i * 2 + 1];

				if (dRow != -1 && tempMap[dRow][dCol] == '1') {
					killCount++;
					tempMap[dRow][dCol] = '2';
				}
			}
		}
		return killCount;
	}

	/**
	 * 두 좌표간의 거리를 측정하기 위한 메서드 (맨해튼 거리 측정법)
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	/**
	 * 이차원 배열의 범위를 벚어났는지 확인하는 메서드
	 * @param row
	 * @param col
	 * @return
	 */
	static boolean isRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

	/**
	 * 이차원 배열을 깊은 복사하는 메서드
	 * @param origin
	 * @return
	 */
	static char[][] deepCopy(char[][] origin) {
		char[][] res = new char[N][M];
		for (int i = 0; i < res.length; i++) {
			res[i] = Arrays.copyOf(origin[i], M);
		}
		return res;
	}

	/**
	 * 문자열 타입을 숫자형으로 변환하는 메서드
	 * @param s
	 * @return
	 */
	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
