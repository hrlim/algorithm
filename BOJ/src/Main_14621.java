import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 나만 안되는 연애 - 골드3
 * 
 * @author hrlim
 * @version 1.0, 2022.08.26
 */
public class Main_14621 {

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
		char type;

		public Vertex(int no, int weight, char type) {
			this.no = no;
			this.weight = weight;
			this.type = type;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = stringToInt(st.nextToken());
		int E = stringToInt(st.nextToken());

		Node[] adjList = new Node[V + 1];
		Vertex[] vertexArr = new Vertex[V + 1];
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= V; i++) {
			char sex = st.nextToken().charAt(0);
			vertexArr[i] = new Vertex(i, 0, sex);
		}
	
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = stringToInt(st.nextToken());
			int to = stringToInt(st.nextToken());
			int weight = stringToInt(st.nextToken());

			if (vertexArr[from].type != vertexArr[to].type) {
				adjList[from] = new Node(to, weight, adjList[from]);
				adjList[to] = new Node(from, weight, adjList[to]);
			}
		}

		// 프림 알고리즘에 필요한 자료구조
		int[] minEdge = new int[V + 1]; // 각 정점 입장에서 신장트리에 포함된 정점과의 간선 비용중 최소비용
		boolean[] visited = new boolean[V + 1];

		Arrays.fill(minEdge, Integer.MAX_VALUE);

		// 1. 임의의 시작점 처리, 0 빈정점을 신장트리의 구성의 시작점
		minEdge[1] = 0;
		int result = 0; // 최소신장트리 비용 누적
		
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
		pQueue.offer(vertexArr[1]);

		int cnt = 0;// 신장트리에 추가된 정점 수
		while (!pQueue.isEmpty()) { // V개의 정점 처리하면 끝
			// step1. 신장트리의 구성에 포함되지 않은 정점 중 최소비용 정점 선택
			Vertex minVertex = pQueue.poll();

			if (visited[minVertex.no])
				continue;
			// step2. 신장트리에 추가
			visited[minVertex.no] = true;
			result += minVertex.weight;
			if (++cnt == V)
				break;

			// step3. 신장트리에 새롭게 추가되는 정점과 신장트리에 포함되지 않은 정점들의 기존 최소비용과 비교해서 갱신
			// 신장트리에 새롭게 추가되는 정점의 모든 인접정점 들여다보며 처리
			for (Node temp = adjList[minVertex.no]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
					minEdge[temp.vertex] = temp.weight;
					pQueue.offer(new Vertex(temp.vertex, minEdge[temp.vertex], vertexArr[temp.vertex].type));
				}
			}
		}
		for (int i = 1; i < visited.length; i++) {
			if(!visited[i]) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(result);
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
