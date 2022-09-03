package lessons._4_CountingElements;

import java.util.HashSet;

public class FrogRiverOne {

	public static void main(String[] args) {
		int[] arr = new int[] {2, 2, 2, 2, 2};
		System.out.println(solution(2, arr));
	}
	
	public static int solution(int X, int[] A) {
		int result = -1;
		HashSet<Integer> marks = new HashSet<>();
		
		for (int i = 0; i < A.length; i++) {
			if(A[i] <= X) {
				marks.add(A[i]);
			}
			if(marks.size() == X) {
				return i;
			}
		}
		return result;
    }
}
