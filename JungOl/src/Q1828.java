import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Q1828 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[][] temperature = new int[N][2];

		for (int i = 0; i < temperature.length; i++) {
			temperature[i][0] = sc.nextInt();
			temperature[i][1] = sc.nextInt();
		}

		Arrays.sort(temperature, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[1] - o2[1];
			}
		});

		int count = 0;
		int maxDegree = -280;

		for (int i = 0; i < N; i++) {

			if (maxDegree < temperature[i][0]) {
				maxDegree = temperature[i][1];
				count++;
			}
		}
		
		System.out.println(count);
	}

}
