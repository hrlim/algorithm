
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * SWEA 1208 Flatten
 * 
 * @author hrlim
 * @version 1.0, 2022.08.02
 */
public class Solution_8382 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();

			int dx = Math.abs(x2 - x1);
			int dy = Math.abs(y2 - y1);

			int min = Math.min(dx, dy);
			int max = Math.max(dx, dy);

			int answer = min * 2 + (max - min) * 2 - ((max - min) % 2 == 0 ? 0 : 1);

			System.out.println(answer);
		}
	}
}