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
public class Main_11659_임하림 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = StringToInt(st.nextToken());
		int M = StringToInt(st.nextToken());

		int[] arr = new int[N + 1];
		int[] sum = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = StringToInt(st.nextToken());
			sum[i] = sum[i - 1] + arr[i];
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int start = StringToInt(st.nextToken());
			int end = StringToInt(st.nextToken());

			System.out.println(sum[end] - sum[start - 1]);

		}
	}

	static int StringToInt(String s) {
		return Integer.parseInt(s);
	}
}
