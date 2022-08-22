
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 1238 Contact - D4
 * 
 * @author hrlim
 * @version 1.0, 2022.08.22
 */

class Node {
	int data; // 노드의 데이터
	Node next; // 뒤의 노드를 가리키는 주소

	public Node(int data, Node next) {
		this.data = data;
		this.next = next;
	}
}

public class Solution_1238 {

	static Node[] adjList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int testCase = 1; testCase <= 10; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = stringToInt(st.nextToken());
			int startNodeIndex = stringToInt(st.nextToken());

			adjList = new Node[101];
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				int from = stringToInt(st.nextToken());
				int to = stringToInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
			}

			sb.append("#").append(testCase).append(" ").append(bfs(startNodeIndex)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	/**
	 * 시작 지점으로 부터 넓이 우선 탑색하는 메소드 (BFS)
	 * @param start
	 * @return
	 */
	static int bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);

		boolean[] isVisited = new boolean[101];
		isVisited[start] = true;
		ArrayList<Integer> depthMax = new ArrayList<>();

		while (!queue.isEmpty()) {
			int size = queue.size();
			int max = 0;
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();
				for (Node node = adjList[cur]; node != null; node = node.next) {
					if (!isVisited[node.data]) {
						isVisited[node.data] = true;
						queue.offer(node.data);
						max = Math.max(max, node.data);
					}
				}
			}
			depthMax.add(max);
		}
		return depthMax.get(depthMax.size() - 2);
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
