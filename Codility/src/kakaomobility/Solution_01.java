package kakaomobility;

class Solution_01 {
	public static void main(String[] args) {
		solution(25060);
	}

	public static void solution(int N) {
		int enable_print = N % 10;
		while (N > 0) {
			if (enable_print != 0) {
				enable_print = 1;
			} else break;
			if (enable_print == 1) {
				System.out.print(N % 10);
			}
			N = N / 10;
		}
	}
}
