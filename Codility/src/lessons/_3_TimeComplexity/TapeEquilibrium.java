package lessons._3_TimeComplexity;

public class TapeEquilibrium {

	public static void main(String[] args) {
		int[] testArr = new int[] { 3, 1, 2, 4, 3 };
		System.out.println(solution(testArr));

	}

	public static int solution(int[] A) {

		int[] prefixSum = new int[A.length];
		prefixSum[0] = A[0];

		for (int i = 1; i < A.length; i++) {
			prefixSum[i] = prefixSum[i - 1] + A[i];
		}

		int result = Integer.MAX_VALUE;
		int lastPrefixSum = prefixSum[prefixSum.length - 1];

		for (int i = 1; i < prefixSum.length; i++) {
			int diff = Math.abs(prefixSum[i - 1] - (lastPrefixSum - prefixSum[i - 1]));
			result = Math.min(result, diff);
		}
		return result;
	}
}
