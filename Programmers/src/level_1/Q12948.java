package level_1;

/**
 * <b>핸드폰 번호 가리기</b> 
 * https://school.programmers.co.kr/learn/courses/30/lessons/12948
 * 
 * @author hrlim
 * @version 최초 2022.08.14
 */
public class Q12948 {
	public static void main(String[] args) {
		System.out.println(solution("01033334444"));
	}

	public static String solution(String phone_number) {
        String answer = "";
        for (int i = 0; i < phone_number.length() - 4; i++) {
			answer += "*";
		}
        answer += phone_number.substring(phone_number.length() - 4);
        return answer;
    }

}
