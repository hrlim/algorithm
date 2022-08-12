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
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		minPrefer = Integer.MAX_VALUE;
		ingredient = new int[N][2];
		visited = new boolean[N];

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

	static void makeSet(int current) {
		int N = ingredient.length;
		// 바이너리 카운팅을 이용한 부분집합
		// N : 1 ~ 10
		// 0000000000 ~ 1111111111
		// 10000000000 = 부분 집합의 개수 2^N
		for (int i = 0; i < (1 << N); i++) {
			int sour = 1;
			int bitter = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) != 0) {
					sour *= ingredient[j][0];
					bitter += ingredient[j][1];
				}
			}
			if (!(sour == 1 && bitter == 0) && minPrefer > Math.abs(sour - bitter))
				minPrefer = Math.abs(sour - sour);
		}
	}

//	static void makeSet(int current) {
//		if (current == ingredient.length) {
//			int sour = 1; // 곱 연산 진행되기 때문에 1에서 시작
//			int bitter = 0; // 합 연산 진행되기 때문에 0에서 시작
//			int cnt = 0;
//			for (int i = 0; i < visited.length; i++) {
//
//				if (visited[i])
//					cnt++;
//			}
//		}
//	}
}
