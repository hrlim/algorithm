package lessons._5_PrefixSums;

public class PassingCars {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	// 방법 1 
	/*
	public int solution(int[] A) {
        int result = 0;
        
        int[] prefixSum = new int[A.length];
        prefixSum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
			prefixSum[i] = A[i] + prefixSum[i - 1];
		}
        
        for (int i = 0; i < prefixSum.length; i++) {
			if(A[i] == 0) {
				result += prefixSum[prefixSum.length - 1] - prefixSum[i];
				if(result > 1_000_000_000) return -1;
			}
			
		}
        return result;
    }
	*/
	
	// 방법 2
	public int solution(int[] A) {
		int result = 0;
		int sum = 0;
		
		for (int i = 0; i < A.length; i++) {
			if(A[i] == 0) sum++;
			else {
				result += sum;
				if(result > 1_000_000_000) return -1;
			}
		}
		return result;
	}
}
