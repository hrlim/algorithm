import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 구간 합 구하기 5
 * 
 * @author hrlim
 * @version 1.0, 2022.08.03
 */
public class Main_11660_임하림 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = StringToInt(st.nextToken());
		int M = StringToInt(st.nextToken());

		int[][] arr = new int[N][N + 1];
		int[] sum = new int[N * N + 1];
		int[][] rowSum = new int[N + 1][N + 1];

		int preX = 0;
		int preY = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int length = st.countTokens();
			for (int j = 1; j <= length; j++) {
				arr[i][j] = StringToInt(st.nextToken());
				sum[i * N + j] = sum[preX * N + preY] + arr[i][j];
				rowSum[i + 1][j] = rowSum[i][j] + arr[i][j];

				preX = i;
				preY = j;
			}
		}

		//for (int i = 0; i < rowSum.length; i++) {
		//	for (int j = 1; j < rowSum.length; j++) {
		//		System.out.print(rowSum[i][j] + " ");
		//	}
		//	System.out.println();

		//}

		//System.out.println(Arrays.toString(sum));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = StringToInt(st.nextToken());
			int y1 = StringToInt(st.nextToken());
			int x2 = StringToInt(st.nextToken());
			int y2 = StringToInt(st.nextToken());

			if (x1 == x2 && y1 == y2) {
				System.out.println(arr[x1 - 1][y1]);
				continue;
			}

			int start = sum[(x1 - 1) * N + y1 - 1];
			int end = sum[(x2 - 1) * N + y2];
			int mid = 0;

			for (int j = y1 - 1; j > 0; j--) {
				mid += rowSum[x2][j] - rowSum[x1][j];
				//System.out.println(j + " " + rowSum[x2][j] + " 왼쪽 " + rowSum[x1][j]);
			}

			for (int j = N; j > y2; j--) {
				mid += rowSum[x2 - 1][j] - rowSum[x1 - 1][j];
				//System.out.println(j + " " + rowSum[x2 - 1][j] + " 오른쪽 " + rowSum[x1 - 1][j]);
			}
			sb.append(end - start - mid).append("\n");

		}
		System.out.println(sb);
	}

	static int StringToInt(String s) {
		return Integer.parseInt(s);
	}
}
