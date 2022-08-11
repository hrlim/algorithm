package perm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 도영이가 만든 맛있는 음식
 * 
 * @author hrlim
 * @version 1.0, 2022.08.11
 */
public class Main_2961 {
	static int N, L;
	static int minPrefer;
	static int[][] ingredient;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		minPrefer = Integer.MAX_VALUE;
		ingredient = new int[N][2];

		for (int j = 0; j < N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < 2; k++) {
				ingredient[j][k] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			comb(0, 0, 1, 0, i);
		}

		System.out.println(minPrefer);

	}

	static void comb(int start, int current, int sumSour, int sumBitter, int N) {
		if (current == N)
			minPrefer = Math.min(minPrefer, Math.abs(sumBitter - sumSour));

		for (int i = start; i < ingredient.length; i++) {
			comb(i + 1, current + 1, sumSour * ingredient[i][0], sumBitter + ingredient[i][1], N);
		}
	}

}
