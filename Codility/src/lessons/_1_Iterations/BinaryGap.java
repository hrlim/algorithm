package lessons._1_Iterations;

import java.util.ArrayList;

public class BinaryGap {
	public int solution(int N) {
		ArrayList<Integer> binaryList = new ArrayList<>();
		
		while(N > 0) {
			binaryList.add(N % 2);
			N /= 2;
		}
		
		int cntGap = 0;
		int maxGap = 0;
		int size = binaryList.size();
		
		for (int i = size - 1; i >= 0; i--) {
			if(binaryList.get(i) == 1) {
				maxGap = Math.max(maxGap, cntGap);
				cntGap = 0;
			} else {
				cntGap++;
			}
			
		}
		
		return maxGap;
    }
}
