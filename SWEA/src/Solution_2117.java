import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * SWEA 2117 홈 방범 서비스
 * 
 * @author hrlim
 * @version 1.0, 2022.08.18
 */
public class Solution_2117 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken()); // 도시의 크기 5 <= N <= 20
			int M = Integer.parseInt(st.nextToken()); // 집에서 지불할 비용 1 <= M <= 10

			List<int[]> home = new ArrayList<>(); // {행, 열}
			for (int r = 0; r < N; r++) {
				String s = br.readLine();
				for (int c = 0, index = 0; c < N; c++, index += 2) {
					if (s.charAt(index) == '1') {
						home.add(new int[] { r, c });
					}

				}
			}

			// 운영비용 = K * K + (K - 1) * (K - 1)
			int maxCntHome = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int K = 1; K <= 40; K++) {
						int cntHome = 0;
						for (int h = 0; h < home.size(); h++) {
							if (Math.abs(home.get(h)[0] - r) + Math.abs(home.get(h)[1] - c) < K) {
								cntHome++;
							}
						}
						if (cntHome * M >= K * K + (K - 1) * (K - 1)) {
							maxCntHome = Math.max(maxCntHome, cntHome);
						}
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(maxCntHome).append("\n");
		}

		System.out.println(sb.toString());

	} // end of main

} // end of class
