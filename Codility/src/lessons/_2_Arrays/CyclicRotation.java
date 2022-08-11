package lessons._2_Arrays;

public class CyclicRotation {
	public int[] solution(int[] A, int K) {

		int length = A.length;
		int[] result = new int[length];

		for (int i = 0; i < length; i++) {
			result[(i + K) % length] = A[i];

		}
		
		return result;
	}
	
	
}
