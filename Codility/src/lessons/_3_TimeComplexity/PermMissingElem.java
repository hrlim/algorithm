package lessons._3_TimeComplexity;

import java.util.Arrays;

public class PermMissingElem {

	public int solution(int[] A) {
		int length = A.length;
		Arrays.sort(A);

		for (int i = 0; i < A.length; i++) {
			if (A[i] != i + 1) {
				return i + 1;
			}

		}
		return length + 1;
	}
	
	// Score : 80 %
	/* public int solution(int[] A) {

		int length = A.length;
		
		int sum = 0;
		
		// 등차수열의 합 n(n+1)/2
		int maxSum = (2 + length) * (1 + length) / 2;
		
		for (int num : A) {
			sum += num;
		}

		return maxSum - sum;
	}*/
}
