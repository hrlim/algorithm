package ssafy_mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.sun.rowset.providers.RIOptimisticProvider;

public class Solution_낚시터자리잡기 {

	static class FishingSpot {

		int gate, waiting;

		public FishingSpot(int gate, int waiting) {
			super();
			this.gate = gate;
			this.waiting = waiting;
		}

	}

	static int minPath;
	static FishingSpot[] spots;
	static int N; // 낚시터의 갯수
	static int waitingSum; // 입장하기 위한 낚시꾼들의 총 수

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = stringToInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {

			N = stringToInt(br.readLine());
			spots = new FishingSpot[3];

			minPath = Integer.MAX_VALUE;
			waitingSum = 0;

			for (int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				int gate = stringToInt(st.nextToken()) - 1;
				int waiting = stringToInt(st.nextToken());

				spots[i] = new FishingSpot(gate, waiting);
				waitingSum += waiting;
			}

			perm(new int[3], 0, new boolean[3]);
			sb.append("#").append(testCase).append(" ").append(minPath).append("\n");
		}

		System.out.println(sb.toString());

	}

	/**
	 * 순열로 입장 순서 정하기
	 * 
	 * @param output	게이트 순서를 저장
	 * @param current	현재까지 저장해진 횟수 (Count)
	 * @param visited	방문여부 확인
	 */
	static void perm(int[] output, int current, boolean[] visited) {
		if (current == output.length) {
			overlapPerm(output, new int[3], 0);
			return;
		}

		for (int i = 0; i < 3; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			output[current] = i;
			perm(output, current + 1, visited);
			visited[i] = false;
		}
	}

	/**
	 * 중복 순열로 게이트의 왼쪽 오른쪽 방향 정하기 0 : 왼쪽, 1 : 오른쪽
	 * 
	 * @param output	게이트 순서
	 * @param dir		왼쪽/ 오른쪽 방향을 저장
	 * @param current	현재까지 저장된 수(Count)
	 */
	static void overlapPerm(int[] output, int[] dir, int current) {
		if (current == dir.length) {
			enter(output, dir);
			return;
		}

		for (int i = 0; i < 2; i++) {
			dir[current] = i;
			overlapPerm(output, dir, current + 1);
		}
	}

	/**
	 * 순서대로 입장하기
	 * 
	 * @param output	게이트 입장 순서
	 * @param dir		게이트별로 입장 우선 방향
	 */
	static void enter(int[] output, int[] dir) {

		boolean[] isVisited = new boolean[N];
		int count = 0, sum = 0;
		for (int i = 0; i < output.length; i++) {
			FishingSpot spot = spots[output[i]];
			int gate = spot.gate;
			int waiting = spot.waiting;

			for (int j = 0; j < waiting; j++) {
				int point = findCloseIndex(isVisited, gate, dir[i]);
				if (point == -1) return;
				isVisited[point] = true;
				sum += Math.abs(gate - point) + 1;
				count++;
			}
		}
		
		if (waitingSum == count) minPath = Math.min(sum, minPath);
	}

	/**
	 * 거리가 가까운 위치에 자리 선점
	 * 양 쪽 거리가 동일한 경우에는 flag (0: 왼쪽우선/ 1: 오른쪽 우선) 기준으로 처리
	 * 
	 * @param visited	해당 낚시터 위치가 비어있는지 확인하는 변수
	 * @param gate		게이트 번호(낚시터 출입구 번호)
	 * @param flag		왼쪽-오른쪽 기준
	 * @return
	 */
	static int findCloseIndex(boolean[] visited, int gate, int flag) {

		if (!visited[gate]) return gate;

		int left = -1, right = -1;
		int leftDiff = 0, rightDiff = 0;

		// 왼쪽 확인
		for (int i = gate - 1; i >= 0; i--) {
			if (!visited[i]) {
				left = i;
				break;
			}
		}

		// 오른쪽 확인
		for (int i = gate + 1; i < N; i++) {
			if (!visited[i]) {
				right = i;
				break;
			}
		}

		// 한 쪽으로 밖에 넣을 수 없는 경우
		if (left == -1) {
			if (isRange(right)) return right;
		} else if (right == -1) {
			if (isRange(left)) return left;
		}
		
		leftDiff = gate - left;
		rightDiff = right - gate;
		
		// 양 쪽에 넣을 수 (배정할 수) 있는 거리가 동일한 경우
		if (leftDiff == rightDiff) {
			// 왼쪽 기준
			if (flag == 0) {
				if (isRange(left)) return left;
				if (isRange(right)) return right;
			}
			// 오른쪽 기준
			if (flag == 1) {
				if (isRange(right)) return right;
				if (isRange(left)) return left;
			}
		} else {
			// 오른쪽 거리가 더 가까운 경우
			if (leftDiff > rightDiff) {
				if (isRange(right)) return right;
				else if (isRange(left)) return left;
			} 
			// 왼쪽 거리가 더 가까운 경우
			else {
				if (isRange(left)) return left;
				if (isRange(right)) return right;
			}
		}

		return -1;
	}

	/**
	 * 범위를 벗어나는지 확인하는 메서드
	 * 
	 * @param num
	 * @return
	 */
	static boolean isRange(int num) {
		return num >= 0 && num < N;
	}

	/**
	 * 문자열 타입을 숫자형으로 변환하는 메서드
	 * 
	 * @param s
	 * @return
	 */
	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}
