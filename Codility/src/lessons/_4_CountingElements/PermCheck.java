package lessons._4_CountingElements;

import java.util.Arrays;
import java.util.HashSet;

public class PermCheck {

	public static void main(String[] args) {
		int[] arr = new int[] {2, 2, 4, 1, 2};
		System.out.println(solution(arr));
	}
	
	// 방법 1
	public static int solution(int[] A) {
		
        HashSet<Integer> permutation= new HashSet<>();
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < A.length; i++) {
			permutation.add(A[i]);
			if(max <= A[i]) {
				max = A[i];
			}
		}
        if(permutation.size() == max && A.length == max) return 1;
        return 0;
    }
	
	// 방법 2
	/*
	public static int solution(int[] A) {
		
        Arrays.sort(A);
        for (int i = 1; i <= A.length; i++) {
			if(A[i - 1] != i) {
				return 0;
			}
		}
        return 1;
    }
	*/
}
