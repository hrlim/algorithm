import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 숨바꼭질
 * BFS
 * @author hrlim
 * @version 1.0, 2022.08.20
 */
public class Main_1697 {

	private static int[] visitedNum = new int[100_001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stringToInt(st.nextToken());
		int K = stringToInt(st.nextToken());

		bfs(N, K);
		System.out.println(visitedNum[K]);

	}

	/**
	 * 시작지점에서 +, -, * 연산을 통해 목표지점에 도착하면 종료하는 메서드
	 * 
	 * @param n 시작지점
	 * @param k 목표지점
	 */
	static void bfs(int n, int k) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(n);

		int minus, plus, multiple;
		while (!queue.isEmpty()) {
			int now = queue.poll();

			if (now == k) {
				return;
			}

			minus = now - 1;
			plus = now + 1;
			multiple = now * 2;

			if (isRange(minus) && visitedNum[minus] == 0) {
				queue.offer(minus);
				visitedNum[minus] = visitedNum[now] + 1;
			}

			if (isRange(plus) && visitedNum[plus] == 0) {
				queue.offer(plus);
				visitedNum[plus] = visitedNum[now] + 1;
			}

			if (isRange(multiple) && visitedNum[multiple] == 0) {
				queue.offer(multiple);
				visitedNum[multiple] = visitedNum[now] + 1;
			}
		}
	}

	/**
	 * 방문 순서를 저장하는 visitedNum 배열의 범위를 벗어나는지 확인하는 메서드
	 * 
	 * @param num
	 * @return
	 */
	static boolean isRange(int num) {
		return num >= 0 && num < visitedNum.length;
	}

	/**
	 * 문자형 타입을 숫자형 타입으로 변환해주는 메서드
	 * 
	 * @param s
	 * @return
	 */
	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
