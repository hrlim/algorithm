
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 7465 창용 마을 무리의 개수 - D4
 * 
 * @author hrlim
 * @version 1.0, 2022.08.23
 */
public class Solution_7465 {

	static ArrayList<Integer> adjList[];
	static boolean[] isSelected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = stringToInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = stringToInt(st.nextToken());
			int M = stringToInt(st.nextToken());

			isSelected = new boolean[N + 1];
			adjList = new ArrayList[N + 1];
			for (int i = 1; i < adjList.length; i++) {
				adjList[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = stringToInt(st.nextToken());
				int to = stringToInt(st.nextToken());

				adjList[from].add(to);
				adjList[to].add(from);
			}

			int answer = 0;
			for (int i = 1; i <= N; i++) {
				if (!isSelected[i]) {
					bfs(i);
					answer++;
				}
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		isSelected[start] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (Integer friend : adjList[cur]) {
				if(isSelected[friend]) continue;
				isSelected[friend] = true;
				queue.offer(friend);
			}
		}
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
