
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * SWEA 3289 서로소 집합 - D4
 * 
 * @author hrlim
 * @version 1.0, 2022.08.24
 */
public class Solution_3289 {

	static int[] paraents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = stringToInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = stringToInt(st.nextToken());
			int M = stringToInt(st.nextToken());

			paraents = new int[N + 1];

			makeSet();

			sb.append("#").append(test_case).append(" ");
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int command = stringToInt(st.nextToken());
				int from = stringToInt(st.nextToken());
				int to = stringToInt(st.nextToken());
				if (command == 0) {
					union(from, to);
				} else if (command == 1) {
					sb.append(findSet(from) == findSet(to) ? 1 : 0);
				}
			}

			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void makeSet() {
		for (int i = 0; i < paraents.length; i++) {
			paraents[i] = i;
		}
	}

	static int findSet(int num) {
		if (paraents[num] == num)
			return num;
		return paraents[num] = findSet(paraents[num]);
	}

	static boolean union(int num1, int num2) {
		int set1 = findSet(num1);
		int set2 = findSet(num2);

		if (set1 == set2) return false;

		paraents[set2] = set1;
		return true;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
