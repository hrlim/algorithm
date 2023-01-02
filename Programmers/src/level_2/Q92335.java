package level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <b>k진수에서 소수 개수 구하기</b>
 * https://school.programmers.co.kr/learn/courses/30/lessons/92335
 * 
 * @author hrlim
 * @version 수정 2023.01.01
 */
public class Q92335 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = stringToInt(st.nextToken());
		int k = stringToInt(st.nextToken());

		System.out.println(solution(n, k));
	}

	/**
	 * 양의 정수 n을 k진수로 바꿨을 때, 변환된 수 안에 조건에 맞는 소수(Prime number) 갯수를 반환하는 메소드
	 * 
	 * @param n 양의 정수
	 * @param k 진수
	 * @return
	 */
	public static int solution(int n, int k) {
		int answer = 0;
		String num = Integer.toString(n, k); // 십진수인 n 을 k 진수로 변환
		String[] numArr = num.split("0");
		for (String digit : numArr) {
			if(digit.isEmpty()) continue;
			if(isPrime(Long.parseLong(digit))) {
				answer++;
			}
		}
		
		return answer;
	}
	
	/**
	 * 십진수 n의 수를 k진수로 변환하는 메소드
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	private static String decimalToK(int n, int k) {
		StringBuilder result = new StringBuilder();
		while (n != 0) {
			result.insert(0, n % k);
			n /= k;
		}
		return result.toString();
	}

	/**
	 * 소수 (Prime Number) 인지 반환하는 메소드
	 * @param num
	 */
	public static boolean isPrime(long num) {
		if(num < 2) // 0과 1은 소수가 아님
	    	return false;
		
		for(int i = 2; i <= Math.sqrt(num); i++) {
	   		if(num % i == 0) // 소수가 아닐 경우
	        	return false;
		}

		return true;
	}
	
	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}
