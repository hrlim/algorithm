package level_1;

/**
 * <b>행렬의 덧셈</b> 
 * https://school.programmers.co.kr/learn/courses/30/lessons/12950
 * 
 * @author hrlim
 * @version 최초 2022.08.14
 */
public class Q12950 {

	public int[][] solution(int[][] arr1, int[][] arr2) {
		int[][] answer = new int[arr1.length][arr1[0].length];

		for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < answer[0].length; j++) {
				answer[i][j] = arr1[i][j] + arr2[i][j];
			}
		}
		return answer;
	}

}
