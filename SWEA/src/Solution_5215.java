
import java.io.*;
import java.util.StringTokenizer;

/**
 * 햄버거 다이어트
 * 
 * @author hrlim
 * @version 1.0, 2022.08.11
 */
public class Solution_5215 {

	public static int maxCalory;
	public static Evaluation[] arr;
	public static int maxScore;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = stringToInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());

			int N = stringToInt(st.nextToken());
			maxCalory = stringToInt(st.nextToken());
			maxScore = 0;

			arr = new Evaluation[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int t = stringToInt(st.nextToken());
				int k = stringToInt(st.nextToken());
				arr[i] = new Evaluation(i, t, k);
			}

			comb(0, 0, 0, 0);

			sb.append("#" + test_case + " " + maxScore + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	static void comb(int start, int current, int sumCal, int sumScore) {
		if (sumCal > maxCalory) {
			return;
		}

		maxScore = Math.max(sumScore, maxScore);

		for (int i = start; i < arr.length; i++) {
			comb(i + 1, current + 1, sumCal + arr[i].K, sumScore + arr[i].T);
		}
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}

class Evaluation {

	int no;

	// 맛 점수
	int T;

	// 칼로리
	int K;

	public Evaluation(int no, int t, int k) {
		this.no = no;
		this.T = t;
		this.K = k;
	}

	@Override
	public String toString() {
		return no + "";
	}

//	@Override
//	public String toString() {
//		return "Evaluation [no=" + no + ", T=" + T + ", K=" + K + "]";
//	}

}