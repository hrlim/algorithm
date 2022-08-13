package level_1;

/**
 * <b>평균 구하기</b> 
 * https://school.programmers.co.kr/learn/courses/30/lessons/12944
 * 
 * @author hrlim
 * @version 최초 2022.08.14
 */
public class Q12944 {

	public double solution(int[] arr) {
        double answer = 0;
        
        for (int i = 0; i < arr.length; i++) {
			answer += arr[i];
		}
        return answer / arr.length;
    }

}
