import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA 1233 사칙연산 유효성 검사
 * 
 * @author hrlim
 * @version 1.0, 2022.08.09
 */
public class Solution_1233 {

	static String command = "+-*/";

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = 10;

		// 연산의 왼쪽은 i * 2 , 연산의 오른쪽에는 i * 2 + 1
		// 높이는 Math.sqrt(노드의 갯수) - 1
		// 노드의 최대 갯수는 2 ^ (높이 + 1) - 1

		for (int test_case = 1; test_case <= T; test_case++) {

			int N = stringToInt(br.readLine());
			int treeHeight = (int) (Math.sqrt(N));

			String[] arr = new String[N + 1];
			boolean isValid = true;

			for (int i = 1; i <= N; i++) {
				arr[i] = br.readLine().split(" ")[1];
			}

			for (int i = 1; i <= treeHeight - 2; i++) {
				if (!command.contains(arr[i * 2]) || !command.contains(arr[i * 2 + 1])) {
					isValid = false;
					break;
				}
			}

			int pivot = (int) Math.pow(treeHeight - 1, 2);
			for (int i = 0; i < pivot; i++) {
				int left = pivot * 2;
				int right = pivot * 2 + 1;
				
				if (left < arr.length && right < arr.length) {
					if(!command.contains(arr[pivot]) || command.contains(arr[left]) || command.contains(arr[right])) {
						isValid = false;
						break;
					}
				} else if (command.contains(arr[pivot])) {
					isValid = false;
					break;
				}
			}

			if (isValid) {
				sb.append("#" + test_case + " 1\n");
			} else {
				sb.append("#" + test_case + " 0\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
