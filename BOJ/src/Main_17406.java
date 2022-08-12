import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 배열 돌리기 4
 * 
 * @author hrlim
 * @version 1.0, 2022.08.10
 *
 */
public class Main_17406 {

	static int N, M, R;
	static int P;
	static int[][] arr;
	static int[][] copy;
	static Rotation[] command;

	// 하 우 상 좌
	public static int[] dr = { 1, 0, -1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };

	public static int minSum = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		// 배열 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		copy = deepCopy(arr);

		command = new Rotation[R];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stringToInt(st.nextToken());
			int y = stringToInt(st.nextToken());
			int rCnt = stringToInt(st.nextToken());
			command[i] = new Rotation(x, y, rCnt);
		}

		perm(new int[R], new boolean[R], 0);

		for (int[] a : arr) {
			for (int el : a) {
				sb.append(el + " ");
			}
			sb.append("\n");
		}
		System.out.println(minSum);
	}

	private static void perm(int[] output, boolean[] isSelected, int cnt) {
		if (output.length == cnt) {
			checkArray(output);
			return;
		}
		for (int i = 0; i < R; i++) {
			if (isSelected[i])
				continue;
			isSelected[i] = true;
			output[cnt] = i;
			perm(output, isSelected, cnt + 1);
			isSelected[i] = false;
		}
	}

	private static void checkArray(int[] output) {

		for (int i = 0; i < output.length; i++) {
			Rotation tmp = command[output[i]];
			rotate(tmp.x - 1, tmp.y - 1, tmp.rCnt);
		}

		for (int i = 0; i < copy.length; i++) {
			int sum = 0;
			for (int j = 0; j < copy[i].length; j++) {
				sum += copy[i][j];
			}
			if (sum < minSum) {
				minSum = sum;
			}
		}

		copy = deepCopy(arr);
	}

	private static void rotate(int r, int c, int rnum) {
		int nr;
		int nc;
		// 하 우 상 좌
		int startR = r - rnum;
		int startC = c - rnum;
		int start = copy[startR][startC];
		int dir = 0;
		while (true) {
			nr = startR + dr[dir]; // 이동할 좌표
			nc = startC + dc[dir];

			// 경계를 벗어났거나, 테두리의 값인지? 방향전환
			if (nr < r - rnum || nr > r + rnum || nc < c - rnum || nc > c + rnum) {
				dir = ++dir % 4;
				nr = startR + dr[dir]; // 방향전환된 위치를 다시 셋팅 필요
				nc = startC + dc[dir];
			}

			copy[startR][startC] = copy[nr][nc];
			startR = nr;
			startC = nc;

			if (r - rnum == startR && c - rnum == startC) {
				copy[startR][startC + 1] = start;
				break;
			}
		}
		if (rnum > 1)
			rotate(r, c, rnum - 1);

	}

	private static int[][] deepCopy(int[][] original) {
		if (original == null)
			return null;
		int[][] result = new int[original.length][original[0].length];

		for (int i = 0; i < original.length; i++) {
			System.arraycopy(original[i], 0, result[i], 0, original[0].length);
		}

		return result;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}

class Rotation {
	int x;
	int y;
	int rCnt;

	public Rotation(int x, int y, int rCnt) {
		this.x = x;
		this.y = y;
		this.rCnt = rCnt;
	}
}