package test;

public class Test {

	public static void main(String[] args) {
		int r = 0;

		int coverage = 2;
		int half = coverage / 2;

		for (int row = r - coverage; row < r + coverage; row++) {
			for (int col = half - Math.abs(half - row); col <= r + coverage - (half - Math.abs(half - row)); col++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
