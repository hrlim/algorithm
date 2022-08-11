package lessons._2_Arrays;

public class OddOccurrencesInArray {
	public int solution(int[] A) {
		int answer = 0;
		for (int num : A) {
			answer ^= num;
		}
		return answer;
	}
}
