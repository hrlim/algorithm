package level_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>양과 늑대</b> https://school.programmers.co.kr/learn/courses/30/lessons/92343
 * 
 * @author hrlim
 * @version 수정 2023.01.04
 */
public class Q92343 {

	private static int maxCnt;
	private static Map<Integer, List<Integer>> nodes;
	
	public static void main(String[] args) {
		// 0 : 양, 1 : 늑대
		int[] info = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
				{ 4, 6 }, { 8, 9 } };
		System.out.println(solution(info, edges));
	}

	public static int solution(int[] info, int[][] edges) {
		
		nodes = new HashMap<>();
		
		for (int[] edge : edges) {
			if(!nodes.containsKey(edge[0])) 
				nodes.put(edge[0], new ArrayList<>());
			nodes.get(edge[0]).add(edge[1]);
		}
		
		dfs(0, 0, 0, info);
		return maxCnt;
	
	}

	public static void dfs(int cur, int sheepCnt, int wolfCnt, int[] info) {
		
		if(info[cur] == 0) sheepCnt += 1;
		else wolfCnt += 1;
		
		if(sheepCnt <= wolfCnt) return;
		
		maxCnt = Math.max(maxCnt, sheepCnt);
		
		if(nodes.containsKey(cur)) {
			for (int node : nodes.get(cur)) {
				dfs(node, sheepCnt, wolfCnt, info);
			}
		}
	}
}
