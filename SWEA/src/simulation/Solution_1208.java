package simulation;

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
public class Solution_1208 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);

		for (int test_case = 1; test_case <= 10; test_case++) {
			int N = sc.nextInt();

			int[] arr = new int[100];

			for (int i = 0; i < 100; i++) {
				arr[i] = sc.nextInt();
			}

			for (int count = 0; count < N; count++) {
				Arrays.sort(arr);
				arr[arr.length - 1] -= 1;
				arr[0] += 1;
			}
			Arrays.sort(arr);
			System.out.printf("#%d %d\n", test_case, arr[arr.length - 1] - arr[0]);

		}
	}
}