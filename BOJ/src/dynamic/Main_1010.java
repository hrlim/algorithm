package dynamic;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * BOJ 1010 다리 놓기 - 실버 5
 * <b>수학, DP, 조합론</b>
 * 
 * @author hrlim
 * @version 1.0, 2022.08.10
 *
 */
public class Main_1010 {

	public static int totalCnt = 0;
	public static int[][] dp = new int[30][30];
	public static long[] fact = new long[30];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		StringBuilder sb = new StringBuilder();

		int T = stringToInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());

			int N = stringToInt(st.nextToken());
			int M = stringToInt(st.nextToken());

//			comb(new int[N], 0, 1, M); //  시간초과
			sb.append(dynamic(M, N) + "\n"); 

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int dynamic(int n, int r) {

		if (dp[n][r] > 0) {
			return dp[n][r];
		}
		if (n == r || r == 0) {
			return dp[n][r] = 1;
		}
		return dp[n][r] = dynamic(n - 1, r - 1) + dynamic(n - 1, r);
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

	static void comb(int[] output, int cnt, int start, int M) {
		if (cnt == output.length) {
			totalCnt++;
			return;
		}

		for (int i = start; i <= M; i++) {
			output[cnt] = i;
			comb(output, cnt + 1, i + 1, M);
		}
	}
}