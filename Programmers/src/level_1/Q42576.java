package level_1;

import java.util.HashMap;

/**
 * <b>완주하지 못한 선수</b>
 * https://school.programmers.co.kr/learn/courses/30/lessons/42576
 * 
 * @author hrlim
 * @version 최초 2022.08.14
 */
public class Q42576 {

	public static void main(String[] args) {
		String[] participant = new String[] { "leo", "kiki", "eden" };
		String[] completion = new String[] { "eden", "kiki" };
		
		
		System.out.println(solution(participant, completion));
	}

	public static String solution(String[] participant, String[] completion) {
        String answer = "";

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String each : participant) {
			if (map.containsKey(each)) {
				map.put(each, map.get(each) + 1);
			} else {
				map.put(each, 1);
			}
		}
		
		for (String each : completion) {
			if(map.get(each) <= 1) {
				map.remove(each);
			} else {
				map.put(each, map.get(each) - 1);
			}
		}
		
		for (String key : map.keySet()) {
			answer += key;
		}
		return answer;
    }

}
