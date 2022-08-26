import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최단경로 - 골드4
 * 
 * @author hrlim
 * @version 1.0, 2022.08.26
 */
public class Main_1753 {

	static class Node {
		int vertex, weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}

	static class Vertex {
		int no, weight;

		public Vertex(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = stringToInt(st.nextToken());
		int E = stringToInt(st.nextToken());
		int start = stringToInt(br.readLine());

		Node[] adjList = new Node[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, weight, adjList[from]);
		}

		// 프림 알고리즘에 필요한 자료구조
		int[] minEdge = new int[V + 1]; // 각 정점 입장에서 신장트리에 포함된 정점과의 간선 비용중 최소비용
		boolean[] visited = new boolean[V + 1];

		Arrays.fill(minEdge, Integer.MAX_VALUE);

		// 1. 임의의 시작점 처리, 0 빈정점을 신장트리의 구성의 시작점
		minEdge[start] = 0;

		PriorityQueue<Vertex> pQueue = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
		pQueue.offer(new Vertex(start, 0));

		int cnt = 0;// 신장트리에 추가된 정점 수
		while (!pQueue.isEmpty()) { // V개의 정점 처리하면 끝
			// step1. 신장트리의 구성에 포함되지 않은 정점 중 최소비용 정점 선택
			Vertex minVertex = pQueue.poll();

			if (visited[minVertex.no]) continue;
			// step2. 신장트리에 추가
			visited[minVertex.no] = true;
			if (++cnt == V) break;

			// step3. 신장트리에 새롭게 추가되는 정점과 신장트리에 포함되지 않은 정점들의 기존 최소비용과 비교해서 갱신
			// 신장트리에 새롭게 추가되는 정점의 모든 인접정점 들여다보며 처리
			for (Node temp = adjList[minVertex.no]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && minEdge[temp.vertex] > minEdge[minVertex.no] + temp.weight) {
					minEdge[temp.vertex] = minEdge[minVertex.no] + temp.weight;
					pQueue.offer(new Vertex(temp.vertex, minEdge[temp.vertex]));
				}
			}
		}
		for (int i = 1; i < minEdge.length; i++) {
			System.out.println(minEdge[i] == Integer.MAX_VALUE ? "INF" : minEdge[i]);
		}
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}