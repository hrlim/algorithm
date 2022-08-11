package lessons._3_TimeComplexity;

public class FrogJmp {
	
	public int solution(int X, int Y, int D) {
        return (int) Math.ceil((Y - X) / (double) D);
    }
	
	// Score : 44 %
	/* public int solution(int X, int Y, int D) {
		int jumpCnt = 0;
		while (true) {
			if (X >= Y) {
				break;
			}
			X += D;
			jumpCnt++;
		}
		return jumpCnt;
	} */
}
