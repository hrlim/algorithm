import java.io.*;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * 배열 돌리기 3
 * 
 * @author hrlim
 * @version 1.0, 2022.08.10
 *
 */

public class Main_16935_ {

	public static int N, M;
	public static int[][] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stringToInt(st.nextToken());
		M = stringToInt(st.nextToken());
		int R = stringToInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stringToInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int operation = stringToInt(st.nextToken());

		result = selectMode(operation, map);
		for (int i = 1; i < R ; i++) {
			result = selectMode(stringToInt(st.nextToken()), result);
		}

		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				sb.append(result[i][j] + " ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int[][] selectMode(int mode, int[][] map) {
		if (mode == 1) {
			return reverseUpDown(map);
		} else if (mode == 2) {
			return reverseLeftRight(map);
		} else if (mode == 3) {
			int tmp = N;
			N = M;
			M = tmp;
			return rotationRight(map);
		} else if (mode == 4) {
			int tmp = N;
			N = M;
			M = tmp;
			return rotationLeft(map);
		} else if (mode == 5) {
			return rotationSetRight(map);
		} 
		return rotationSetLeft(map);
	}

	static int[][] reverseUpDown(int[][] arr) {
		int[][] result = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[i][j] = arr[N - i - 1][j];
			}
		}

		return result;
	}

	static int[][] reverseLeftRight(int[][] arr) {
		int[][] result = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[i][j] = arr[i][M - j - 1];
			}
		}
		return result;
	}

	static int[][] rotationRight(int[][] arr) {
		int[][] result = new int[N][M];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				result[j][M - i - 1] = arr[i][j];
			}
		}
		return result;
	}

	static int[][] rotationLeft(int[][] arr) {
		int[][] result = new int[N][M];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				result[N - j - 1][i] = arr[i][j];
			}
		}

		return result;
	}

	static int[][] rotationSetRight(int[][] arr) {
		int[][] result = new int[N][M];
		int halfW = M / 2;
		int halfH = N / 2;

		for (int i = 0; i < halfH; i++) {
			for (int j = 0; j < halfW; j++) {
				result[i][j] = arr[i + halfH][j];
			}
		}

		for (int i = 0; i < halfH; i++) {
			for (int j = halfW; j < M; j++) {
				result[i][j] = arr[i][j - halfW];
			}
		}

		for (int i = halfH; i < N; i++) {
			for (int j = 0; j < halfW; j++) {
				result[i][j] = arr[i][j + halfW];
			}
		}

		for (int i = halfH; i < N; i++) {
			for (int j = halfW; j < M; j++) {
				result[i][j] = arr[i - halfH][j];
			}
		}

		return result;
	}

	static int[][] rotationSetLeft(int[][] arr) {
		int[][] result = new int[N][M];
		int halfW = M / 2;
		int halfH = N / 2;

		for (int i = 0; i < halfH; i++) {
			for (int j = 0; j < halfW; j++) {
				result[i][j] = arr[i][j + halfW];
			}
		}

		for (int i = 0; i < halfH; i++) {
			for (int j = halfW; j < M; j++) {
				result[i][j] = arr[i + halfH][j];
			}
		}

		for (int i = halfH; i < N; i++) {
			for (int j = 0; j < halfW; j++) {
				result[i][j] = arr[i - halfH][j];
			}
		}

		for (int i = halfH; i < N; i++) {
			for (int j = halfW; j < M; j++) {
				result[i][j] = arr[i][j - halfW];
			}
		}

		return result;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
