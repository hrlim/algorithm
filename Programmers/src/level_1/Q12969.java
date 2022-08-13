package level_1;

/**
 * <b>x만큼 간격이 있는 n개의 숫자</b>
 * https://school.programmers.co.kr/learn/courses/30/lessons/12954
 * 
 * @author hrlim
 * @version 최초 2022.08.14
 */
public class Q12969 {

	
	public long[] solution(int x, int n) {
		long[] answer = new long[n];
		answer[0] = x;

		for (int i = 1; i < answer.length; i++) {
			answer[i] = answer[i - 1] + x;
		}
		
		// x는 -10_000_000 이상, 10_000_000 이하인 정수입니다.
		// n은 1000 이하인 자연수입니다.
		// 따라서 아래와 같은 코드를 작성할 경우, 이상한 값으로 의도치 않게 변경됩니다.
		// int 형의 범위는  –2_147_483_648 ~ 2_147_483_647 입니다.
		/*
			for (int i = 0; i < answer.length; i++) {
				answer[i] = x * i;
			}
		*/
		return answer;
	}

}
