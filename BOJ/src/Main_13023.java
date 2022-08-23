import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ABCDE - 골드 5
 * 그래프 이론, 깊이 우선 탐색
 * 
 * @author hrlim
 * @version 1.0, 2022.08.23
 */

class Node {
	public int data;
	public Node next;

	public Node(int data, Node next) {
		this.data = data;
		this.next = next;
	}
}

public class Main_13023 {

	static Node[] adjList;

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stringToInt(st.nextToken());
		M = stringToInt(st.nextToken());

		adjList = new Node[N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int personA = stringToInt(st.nextToken());
			int personB = stringToInt(st.nextToken());

			adjList[personA] = new Node(personB, adjList[personA]);
			adjList[personB] = new Node(personA, adjList[personB]);
		}

		boolean isFriend = false;
		for (int i = 0; i < N; i++) {
			if (dfs(i, new boolean[N], 0)) {
				isFriend = true;
				break;
			}
		}

		System.out.println(isFriend ? 1 : 0);
	}

	static boolean dfs(int current, boolean[] isVisited, int count) {
		if(count == 5) {
			return true;
		}
		for (Node node = adjList[current]; node != null ; node = node.next) {
			if(isVisited[node.data]) continue;
			isVisited[node.data] = true;
			if(dfs(node.data, isVisited, count + 1)) return true;
			isVisited[node.data] = false;
		}
		return false;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}