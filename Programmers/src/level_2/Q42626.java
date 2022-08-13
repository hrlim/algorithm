package level_2;

import java.util.PriorityQueue;

/**
 * <b>더 맵게</b>
 * https://school.programmers.co.kr/learn/courses/30/lessons/42626
 * 
 * @author hrlim
 * @version 최초 2022.08.14
 */

public class Q42626 {

	public static int solution(int[] scoville, int K) {
		int answer = 0;
		
		//int 형 priorityQueue 선언 (우선순위가 낮은 숫자 순)
		PriorityQueue<Integer> foods = new PriorityQueue<Integer>();
		for (Integer food : scoville) {
			foods.add(food);
		}
		
		while(foods.peek() < K) {
			int first = foods.poll();
			if(foods.peek() == null) return -1;
			
			int second = foods.poll();
			int newSco = first + second * 2;
			foods.add(newSco);
			answer++;
		}
		return answer;
	}
	
}
