import java.io.*;
import java.util.StringTokenizer;

/**
 * SWEA 9229 한빈이와 Spot Mart
 * @author hrlim
 * @version 1.0, 2022.08.08
 */
public class Solution_9229_임하림 {
	
	static int maxSum;
	static int chooseMaxNum = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] snackWeight = new int[N];
			int[] numbers = new int[2];
			maxSum = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				snackWeight[i] = Integer.parseInt(st.nextToken());
			}

			calcMaxComb(snackWeight, numbers, 0, 0, M);
			if (maxSum == 0)
				bw.write("#" + tc + " " + (-1) + "\n");
			else {
				bw.write("#" + tc + " " + maxSum + "\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static void calcMaxComb(int[] arr, int[] output, int cnt, int start, int M) {
		int sum = 0;
		if (cnt == chooseMaxNum) {
			for (int i = 0; i < chooseMaxNum; i++) {
				sum += output[i];
			}
			if (sum <= M)
				maxSum = Math.max(maxSum, sum);
			return;
		}
		for (int i = start; i < arr.length; i++) {
			output[cnt] = arr[i];
			calcMaxComb(arr, output, cnt + 1, i + 1, M);
		}
	}
}