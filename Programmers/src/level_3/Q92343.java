package level_3;

import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.Arrays;
import java.util.Collections;
>>>>>>> 84b5bac9889d5e6d0e95bbc41e2bb3bf7b86ac5c
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
<<<<<<< HEAD
 * <b>양과 늑대</b> 
 * https://school.programmers.co.kr/learn/courses/30/lessons/92343
=======
 * <b>양과 늑대</b> https://school.programmers.co.kr/learn/courses/30/lessons/92343
>>>>>>> 84b5bac9889d5e6d0e95bbc41e2bb3bf7b86ac5c
 * 
 * @author hrlim
 * @version 수정 2023.01.04
 */
public class Q92343 {

	private static int maxCnt;
<<<<<<< HEAD
	private static Map<Integer, List<Integer>> edgeMap;

	public static void main(String[] args) {
		// 0 : 양, 1 : 늑대
		int[] info = { 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 };
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 4, 8 }, { 6, 9 }, { 9, 10 } };
=======
	private static Map<Integer, List<Integer>> nodes;
	
	public static void main(String[] args) {
		// 0 : 양, 1 : 늑대
		int[] info = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
				{ 4, 6 }, { 8, 9 } };
>>>>>>> 84b5bac9889d5e6d0e95bbc41e2bb3bf7b86ac5c
		System.out.println(solution(info, edges));
	}

	public static int solution(int[] info, int[][] edges) {
<<<<<<< HEAD

		edgeMap = new HashMap<>();

		for (int[] edge : edges) {
			if (!edgeMap.containsKey(edge[0]))
				edgeMap.put(edge[0], new ArrayList<>());
			edgeMap.get(edge[0]).add(edge[1]);
		}

		List<Integer> nodes = new ArrayList<>();
		nodes.add(0);
		dfs(0, 0, 0, nodes, info);
		
		return maxCnt;

	}

	public static void dfs(int cur, int sheepCnt, int wolfCnt, List<Integer> nodes, int[] info) {

		if (info[cur] == 0)
			sheepCnt += 1;
		else
			wolfCnt += 1;

		if (sheepCnt <= wolfCnt)
			return;

		maxCnt = Math.max(maxCnt, sheepCnt);

		// 방문했지만 양의 갯수가 늑대의 갯수와 동일하거나 작은 경우 루트 노드의 다른 자식노드를 먼저 확인하기 위함.
		List<Integer> next = new ArrayList<>();
		next.addAll(nodes);
		
		if(edgeMap.containsKey(cur)) next.addAll(edgeMap.get(cur));
		next.remove(Integer.valueOf(cur));
		
		for(int n : next) {
			dfs(n, sheepCnt, wolfCnt, next, info);
		}
	}

=======
		
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
>>>>>>> 84b5bac9889d5e6d0e95bbc41e2bb3bf7b86ac5c
}
