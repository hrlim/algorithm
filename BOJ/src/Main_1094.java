import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_1094 {

	private static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int X = stringToInt(br.readLine());
		List<Integer> nums = new ArrayList<>();
		nums.add(64);

		while (isNotSame(nums, X)) {
			int num = nums.remove(0);
			// 1. 가지고 있는 막대 중 길이가 가장 짧은 막대를 이등분한다.
			nums.add(0, num / 2);
			nums.add(0, num / 2);

			// 2. 만약, 위에서 이등분한 막대 중 하나를 제외한 나머지의 모든 막대의 길이의
			// 합이 Xcm보다 크거나 같다면, 제외한 조각을 버린
			if (getSum(nums) - num / 2 >= X) {
				nums.remove(0);
			}
		}

		if (X == 64)
			answer = 1;
		else
			answer = nums.size();
		System.out.println(answer);

	}

	private static boolean isNotSame(List<Integer> nums, int pivot) {
		return getSum(nums) == pivot ? false : true;
	}

	private static int getSum(List<Integer> nums) {
		int sum = 0;
		for (int i = 0; i < nums.size(); i++) {
			sum += nums.get(i);
		}
		return sum;
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}