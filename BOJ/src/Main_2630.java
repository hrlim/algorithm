import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 색종이 만들기 
 * <b>분할정복, 재귀</b>
 * 
 * @author hrlim
 * @version 1.0, 2022.08.21
 */
public class Main_2630 {
	static int[][] map;
	static int whiteCnt, blueCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = stringToInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stringToInt(st.nextToken());
			}
		}

		dfs(N, 0, 0);
		System.out.println(whiteCnt);
		System.out.println(blueCnt);
	}

	/**
	 * 색종이를 4분면으로 잘라가며 확인하는 메서드
	 * 
	 * @param size
	 * @param row
	 * @param col
	 */
	static void dfs(int size, int row, int col) {

		if (isConfetti(size, row, col)) {
			if (map[row][col] == 1) {
				blueCnt++;
			} else {
				whiteCnt++;
			}
			return;
		}

		int half = size / 2;

		dfs(half, row, col);
		dfs(half, row + half, col);
		dfs(half, row, col + half);
		dfs(half, row + half, col + half);
	}

	/**
	 * 현재 영역의 컬러가 모두 같은지 확인하는 메서드
	 * @param size
	 * @param row
	 * @param col
	 * @return
	 */
	static boolean isConfetti(int size, int row, int col) {
		int pivot = map[row][col];
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (map[i][j] != pivot) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 문자형 타입을 숫자형 타입으로 변환해주는 메서드
	 * 
	 * @param s
	 * @return
	 */
	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}
