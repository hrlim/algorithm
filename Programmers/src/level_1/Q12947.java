package level_1;

/**
 * <b>하샤드 수</b> 
 * https://school.programmers.co.kr/learn/courses/30/lessons/12947
 * 
 * @author hrlim
 * @version 최초 2022.08.14
 */
public class Q12947 {

	public boolean solution(int x) {
		
		int digitSum = 0;
		int num = x;
		
		while (num > 0) {
			digitSum += num % 10;
			num /= 10;
        }
		
		return x % digitSum == 0 ? true : false;
	}

}
