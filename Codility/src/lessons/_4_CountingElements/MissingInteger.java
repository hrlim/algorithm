package lessons._4_CountingElements;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MissingInteger {

	public static void main(String[] args) {
		int[] A = new int[] { -1, -3 };
		System.out.println(solution(A));
	}
	
	// 방법 1 - 통과
	/*
	public static int solution(int[] A) {
		int result = 1;
		int preValue = 1;

		Arrays.sort(A);
		for (int i = 0; i < A.length; i++) {
			if (A[i] > 0) {
				if (A[i] == result) {
					preValue = A[i];
					result = A[i] + 1;
				} else if (A[i] == preValue) {
					continue;
				} else {
					return result;
				}
			}
		}
		return result;
	}
	*/
	
	public static int solution(int[] A) {
		Set<Integer> foundNums = new HashSet<>();
		for (Integer num : A) {
			foundNums.add(num);
		}
		
		for (int i = 1; i <= Integer.MAX_VALUE; i++) {
			if(!foundNums.contains(i)) {
				return i;
			}
		}
		return -1;
	}
}
