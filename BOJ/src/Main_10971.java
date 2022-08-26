import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 외판원 순회 2 - 실버 2
 * 
 * @author hrlim
 * @version 1.0, 2022.08.27
 */
public class Main_10971 {
	
	static int minPath = Integer.MAX_VALUE;
	static int[][] adjMatrix;
	static boolean[] visited;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = stringToInt(br.readLine());
		
		adjMatrix = new int[N][N];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = stringToInt(st.nextToken());
			}
		}
		
		// 모든 정점을 연결하는 것이기에 어느 정점에서 시작해도 동일
		// 따라서 0 번 정점에서 시작
		dfs(0, 0, 0, new int[N - 1], 0);
		System.out.println(minPath);
		
	}
	
	/**
	 * @param start		시작정점
	 * @param now		현재정점
	 * @param current	현재까지 뽑인 횟수
	 * @param output	현재까지 뽑인 정점 배열
	 * @param sum		현재까지 뽑인 간선들의 합
	 */
	static void dfs(int start, int now, int current, int[] output, int sum) {
		
		visited[now] = true;	
		
		if(current == output.length) {
			if(adjMatrix[output[output.length - 1]][start] == 0) return;
			sum += adjMatrix[output[output.length - 1]][start];
			minPath = Math.min(minPath, sum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			if(adjMatrix[now][i] == 0) continue;
			output[current] = i;
			dfs(start, i, current + 1, output, sum + adjMatrix[now][i]);
			visited[i] = false;
		}
		
	}
	
	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
