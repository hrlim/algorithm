package lessons._4_CountingElements;

import java.util.Arrays;

public class MaxCounters {

	public static void main(String[] args) {
		int[] A = new int[] { 3, 4, 4, 6, 1, 4, 4 };
		int N = 5;
		System.out.println(Arrays.toString(solution(N, A)));
	}

	// Correctness : 100 %
	// Performance 60 %
	// Task Score : 77 점
	/*
	 * public static int[] solution(int N, int[] A) { int[] result = new int[N]; int
	 * max = 0;
	 * 
	 * for (int i = 0; i < A.length; i++) { if (A[i] > N) { Arrays.fill(result,
	 * max); continue; }
	 * 
	 * result[A[i] - 1]++; if (max < result[A[i] - 1]) { max = result[A[i] - 1]; } }
	 * return result; }
	 */

	public static int[] solution(int N, int[] A) {
		int[] result = new int[N];
		int currentMax = 0;
		int lastMax = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] > N) {
				lastMax = currentMax;
				continue;
			}
			
		
			int index = A[i] - 1;
			if (lastMax > result[index]) {
				result[index] = lastMax + 1;
			} else {
				result[index]++;
			}
			
			if (currentMax < result[index]) {
				currentMax = result[index];
			}
		}
		
		for (int i = 0; i < result.length; i++) {
			if (result[i] < lastMax)
				result[i] = lastMax;
		}

		return result;
	}
}
