
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
public class Solution_7465_FindUnion {

	static int[] parent;

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
			parent = new int[N + 1];
			
			makeSet();
			
			int answer = N;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = stringToInt(st.nextToken());
				int to = stringToInt(st.nextToken());
				if(union(from, to)) {
					answer -= 1;
				}
			}

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	/**
	 * 서로소 집합 만드는 메서드
	 */
	static void makeSet() {
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
	}

	/**
	 * 두 개의 집합을 합치기 위한 메서드
	 */
	static boolean union(int num1, int num2) {
		if(findSet(num1) == findSet(num2)) return false;
		parent[findSet(num2)] = findSet(num1);
		return true;
	}
	
	/**
	 * num 의 대표자(부모)를 찾기 위한 메서드
	 * @param num
	 * @return
	 */
	static int findSet(int num) {
		if(parent[num] == num) return num;
		// 검색의 단계를 줄이기 위함 
		return parent[num] = findSet(parent[num]);
	}
	
	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
