
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 3124 최소 스패닝 트리 - D4
 * 
 * @author hrlim
 * @version 1.0, 2022.08.24
 */
public class Solution_3124 {

	static int[] paraents;

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	static ArrayList<Edge> edgeList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = stringToInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int V = stringToInt(st.nextToken());
			int E = stringToInt(st.nextToken());

			paraents = new int[V + 1];
			edgeList = new ArrayList<>();

			makeSet();

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());

				int from = stringToInt(st.nextToken());
				int to = stringToInt(st.nextToken());
				int weight = stringToInt(st.nextToken());
				
				edgeList.add(new Edge(from, to, weight));
			}

			Collections.sort(edgeList);

			long result = 0;
			int count = 0;
			for (int i = 0; i < E; i++) {
				if (union(edgeList.get(i).from, edgeList.get(i).to)) {
					result += edgeList.get(i).weight;
					if (++count == V - 1) break;
				}
			}

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void makeSet() {
		for (int i = 0; i < paraents.length; i++) {
			paraents[i] = i;
		}
	}

	static int findSet(int num) {
		if (paraents[num] == num) return num;
		return paraents[num] = findSet(paraents[num]);
	}

	static boolean union(int num1, int num2) {
		int set1 = findSet(num1);
		int set2 = findSet(num2);

		if (set1 == set2) return false;

		paraents[set2] = set1;
		return true;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
