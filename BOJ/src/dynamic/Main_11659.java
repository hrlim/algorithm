package dynamic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 구간 합 구하기 4
 * 
 * @author hrlim
 * @version 1.0, 2022.08.03
 */
public class Main_11659 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = stringToInt(st.nextToken());
		int M = stringToInt(st.nextToken());

		int[] arr = new int[N + 1];
		int[] sum = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = stringToInt(st.nextToken());
			sum[i] = sum[i - 1] + arr[i];
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int start = stringToInt(st.nextToken());
			int end = stringToInt(st.nextToken());

			System.out.println(sum[end] - sum[start - 1]);

		}
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
