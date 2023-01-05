import java.util.Arrays;

public class Solution_3 {
	public static void main(String[] args) {
		String logs = "2019/05/01 00:59:19\n"
				+ "2019/06/01 01:59:19\n"
				+ "2019/05/01 02:59:19\n"
				+ "2019/05/01 02:59:19\n"
				+ "2019/05/01 03:59:19\n"
				+ "2019/05/01 04:59:19\n"
				+ "2019/05/01 06:59:19\n"
				+ "2019/05/01 08:59:19\n"
				+ "2019/05/01 08:59:19\n"
				+ "2019/05/01 08:59:19\n"
				+ "2019/05/01 10:59:19\n"
				+ "2019/05/01 11:59:19\n";
		
		System.out.println(Arrays.toString(solution(logs)));
		
	}

	public static int[] solution(String logs) {
		int[] answer = new int[24];
		String[] logArr = logs.split("\n");
		for (int i = 0, size = logArr.length; i < size; i++) {
			String logUnit = logArr[i];
			Integer time = Integer.parseInt(logUnit.split(" ")[1].split(":")[0]) + 9;
			answer[time % 24]++;
		}
		
		return answer;
	}

}
