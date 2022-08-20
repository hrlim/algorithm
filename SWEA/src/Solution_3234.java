import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 
 * SWEA 3234 준환이의 양팔저울
 * 
 * @author hrlim
 * @version 1.0, 2022.08.19
 */
public class Solution_3234 {

	public static int answer;
	public static Integer[] weights;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();

		StringTokenizer st = null;
		int TC = stringToInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int N = stringToInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			weights = new Integer[N];

			for (int i = 0; i < N; i++) {
				weights[i] = stringToInt(st.nextToken());
			}

			Arrays.sort(weights, Comparator.reverseOrder());
			perm(0, new int[N], 0);
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
			answer = 0;
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	/**
	 * 왼쪽 접시에 담을 순열
	 * 
	 * @param cnt
	 * @param output
	 */
	private static void perm(int cnt, int[] output, int flag) {

		if (cnt == output.length) {
			makeSubSet(output, 0, 0, 0);
			return;
		}

		for (int i = 0; i < weights.length; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			output[cnt] = weights[i];
			perm(cnt + 1, output, flag | 1 << i);

		}
	}

	/**
	 * 왼쪽, 오른쪽 무게 측정하면서 가능성 확인
	 * 
	 * @param output
	 * @param left
	 * @param right
	 * @param cnt
	 */
	private static void makeSubSet(int[] output, int left, int right, int cnt) {
		if(right > left) {
			return;
		}
		
		if (cnt == output.length) {
			answer++;
			return;
		}

		makeSubSet(output, left + output[cnt], right, cnt + 1);
		makeSubSet(output, left, right + output[cnt], cnt + 1);

	}
	
	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}
