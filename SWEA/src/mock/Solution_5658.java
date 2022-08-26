package mock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 5658. [모의 SW 역량테스트] 보물상자 비밀번호
 * 
 * @author hrlim
 *
 */
public class Solution_5658 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = stringToInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {

			st = new StringTokenizer(br.readLine());
			int N = stringToInt(st.nextToken());
			int K = stringToInt(st.nextToken());

			String input = br.readLine();

			int pivot = N / 4;

			List<Integer> numbers = new LinkedList<>();
			String change = input + input.substring(0, pivot - 1);
			for (int i = 0; i < N; i++) {
				String hex = change.substring(i, i + pivot);
				int num = Integer.parseInt(hex, 16);
				if (!numbers.contains(num)) numbers.add(num);
			}

			Collections.sort(numbers, Comparator.reverseOrder());

			sb.append("#").append(testCase).append(" ").append(numbers.get(K - 1)).append("\n");
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
