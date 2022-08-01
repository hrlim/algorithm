package simulation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * BOJ 1244 스위치 켜고 끄기
 * 
 * @author hrlim
 * @version 1.0, 2022.08.01
 */
public class Main_1244 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int size = st.countTokens();
		for (int j = 1; j <= size; j++) {
			arr[j] = Integer.parseInt(st.nextToken());
		}

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			String[] temp = br.readLine().split(" ");
			int sex = Integer.parseInt(temp[0]);
			int pos = Integer.parseInt(temp[1]);

			// sex 1: 남학생, 2: 여학생
			if (sex == 1) {
				for (int j = pos; j < arr.length; j += pos) {
					arr[j] = arr[j] == 1 ? 0 : 1;
				}
			} else if (sex == 2) {
				int left = pos - 1;
				int right = pos + 1;
				while (left >= 1 && right <= N) {
					if (arr[left] == arr[right]) {
						left--;
						right++;
					} else {
						break;
					}
				}
				for (int j = left + 1; j < right; j++) {
					arr[j] = (arr[j] == 1) ? 0 : 1;
				}
			}
		}
		StringBuilder sb = new StringBuilder();

		// 한 줄에 20개씩 출력
		for (int i = 1; i <= N; i++) {
			sb.append(arr[i]).append(" ");
			if (i % 20 == 0)
				sb.append("\n");
		}
		System.out.println(sb.toString().trim());

	}
}
