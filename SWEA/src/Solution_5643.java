import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [Professional] 키 순서 - D4
 * 
 * @author hrlim
 * @version 1.0, 2022.10.11
 */
public class Solution_5643 {

	static int N, M;
	static List<Integer>[] taller;
	static List<Integer>[] shorter;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = stringToInt(br.readLine());
			M = stringToInt(br.readLine());

			taller = new ArrayList[N + 1];
			shorter = new ArrayList[N + 1];
			
			for (int i = 0; i <= N; i++) {
				taller[i] = new ArrayList<Integer>();
				shorter[i] = new ArrayList<Integer>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int shortNum = stringToInt(st.nextToken());
				int tallNum = stringToInt(st.nextToken());
				taller[shortNum].add(tallNum);
				shorter[tallNum].add(shortNum);
			}

			int result = 0;
			for (int i = 1; i <= N; i++) {
				int mycnt = count(i);
				if (mycnt == N - 1)
					result++;
			}
			System.out.println("#" + test_case + " " + result);
		}
	}

	private static int count(int num) {
		Queue<Integer> que = new LinkedList<>();
		que.offer(num);
		boolean visited[] = new boolean[N + 1];

		visited[num] = true;
		int count = 0;
		
		while (!que.isEmpty()) {
			int now = que.poll();
			for (int i : taller[now]) {
				if (visited[i]) continue;
				visited[i] = true;
				count++;
				que.offer(i);
			}
		}
		
		que.offer(num);
		while (!que.isEmpty()) {
			int now = que.poll();
			for (int i : shorter[now]) {
				if (visited[i]) continue;
				visited[i] = true;
				count++;
				que.offer(i);
			}
		}
		return count;
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
