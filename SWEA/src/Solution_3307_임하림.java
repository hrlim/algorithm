import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 3307 최장 증가 부분 수열
 * 
 * @author hrlim
 * @version 1.0, 2022.10.06
 */
public class Solution_3307_임하림 {

	public static int[] arr;
	public static int[] dp;
	public static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			max = 0;

			arr = new int[N];
			dp = new int[N];

			for (int i = 0; i < N; i++) {
				arr[i] = stringToInt(st.nextToken());
				dp[i] = 1;
			}

			for (int i = 1; i < N; i++) {
				for (int j = 0; j < i; j++) {
					if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
					}
				}
				max = Math.max(max, dp[i]);
			}

			System.out.println("#" + tc + " " + max);
		}

	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}
