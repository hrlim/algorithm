import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 게리맨더링 - 골드 4
 * FIND-UNION, BFS, COMBINATION
 * 
 * @author hrlim
 * @version 1.0, 2022.10.07
 *
 */
public class Main_17471 {

	private static class Node {
		int num;
		Node next;

		public Node(int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}

	private static int N;
	private static int[] nums; // 인구수
	private static Node[] adjList; // 인접리스트
	private static int answer;
	private static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = stringToInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		nums = new int[N];
		adjList = new Node[N];
		for (int i = 0; i < N; i++) {
			nums[i] = stringToInt(st.nextToken());
		}

		answer = Integer.MAX_VALUE;
		parents = new int[N];

		makeSet();
		for (int from = 0; from < N; from++) {
			st = new StringTokenizer(br.readLine());
			int count = stringToInt(st.nextToken());
			for (int j = 0; j < count; j++) {
				int to = stringToInt(st.nextToken()) - 1;
				adjList[from] = new Node(to, adjList[from]);
				adjList[to] = new Node(from, adjList[to]);
				union(from, to);
			}
		}

		for (int i = 1; i < N; i++) {
			comb(0, 0, new int[i]);
		}

		if (answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}

	private static boolean isAdjacentSameSet(int[] arr) {
		int pivot = parents[arr[0]];
		for (int i = 1; i < arr.length; i++) {
			if (pivot != findSet(arr[i]))
				return false;
		}

		Queue<Integer> q = new ArrayDeque<>();
		boolean[] isVisted = new boolean[N];
		q.add(arr[0]);

		while (!q.isEmpty()) {
			int num = q.poll();
			isVisted[num] = true;
			for (Node node = adjList[num]; node != null && node.next != null; node = node.next) {
				boolean isAdjacent = false;
				for (int j = 0; j < arr.length; j++) {
					if (arr[j] == node.num) {
						isAdjacent = true;
					}
				}
				if (isAdjacent && !isVisted[node.num]) {
					q.offer(node.num);
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (!isVisted[arr[i]])
				return false;
		}

		return true;
	}

	private static void comb(int start, int cnt, int[] wardA) {
		if (cnt == wardA.length) {

			if (isAdjacentSameSet(wardA)) {
				boolean[] isVisited = new boolean[N];
				for (int i = 0; i < wardA.length; i++) {
					isVisited[wardA[i]] = true;
				}

				int[] wardB = new int[N - wardA.length];
				int numWardB = 0;
				for (int i = 0; i < isVisited.length; i++) {
					if (!isVisited[i])
						wardB[numWardB++] = i;
				}

				if (isAdjacentSameSet(wardB)) {
					int countWardA = 0;
					int countWardB = 0;

					for (int i = 0; i < wardA.length; i++) {
						countWardA += nums[wardA[i]];
					}

					for (int i = 0; i < wardB.length; i++) {
						countWardB += nums[wardB[i]];
					}
					answer = Math.min(answer, Math.abs(countWardA - countWardB));
				}
			}
			return;
		}

		for (int i = start; i < N; i++) {
			wardA[cnt] = i;
			comb(i + 1, cnt + 1, wardA);
		}
	}

	private static void makeSet() {
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
	}

	private static int findSet(int num) {
		if (parents[num] == num)
			return num;
		return parents[num] = findSet(parents[num]);
	}

	private static boolean union(int A, int B) {
		int parentA = findSet(A);
		int parentB = findSet(B);

		if (parentA == parentB)
			return false;
		parents[parentB] = parentA;
		return true;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
