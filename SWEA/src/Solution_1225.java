

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * SWEA 1225 암호생성기
 * 
 * @author hrlim
 * @version 1.0, 2022.08.05
 */
public class Solution_1225 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			Deque<Integer> que = new ArrayDeque<>();
			for (int i = 0; i < 8; i++) {
				que.offer(stringToInt(st.nextToken()));
			}

			boolean isNotFinished = true;
			while (isNotFinished) {
				for (int i = 1; i <= 5; i++) {
					int temp = que.pollFirst() - i;
					if (temp <= 0) {
						que.offerLast(0);
						isNotFinished = false;
						break;
					} else {
						que.offerLast(temp);
					}
				}
			}

			sb.append("#" + test_case + " ");
			for (int temp : que) {
				sb.append(temp + " ");
			}
			bw.write(sb.toString() + "\n");
			sb.setLength(0);
		}

		bw.flush();
		bw.close();
		br.close();
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
