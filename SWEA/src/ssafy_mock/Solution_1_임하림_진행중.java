package ssafy_mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.sun.rowset.providers.RIOptimisticProvider;

public class Solution_1_임하림_진행중 {

	static class FishingSpot {

		int gate, waiting;

		public FishingSpot(int gate, int waiting) {
			super();
			this.gate = gate;
			this.waiting = waiting;
		}

	}

	static int minPath = Integer.MAX_VALUE;
	static FishingSpot[] spots;
	static int N; // 낚시터의 갯수
	static int waitingSum = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = stringToInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			minPath = Integer.MAX_VALUE;
			waitingSum = 0;
			
			N = stringToInt(br.readLine());
			spots = new FishingSpot[3];
			for (int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				int gate = stringToInt(st.nextToken());
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
	 * @param output
	 * @param current
	 * @param visited
	 */
	static void perm(int[] output, int current, boolean[] visited) {
		if (current == output.length) {
			overlapPerm(output, new int[3], 0);
//			System.out.println(Arrays.toString(output));
			return;
		}

		for (int i = 0; i < 3; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			output[current] = i;
			perm(output, current + 1, visited);
			visited[i] = false;
		}
	}

	/**
	 * 중복 순열로 게이트의 왼쪽 오른쪽 방향 정하기 0 : 왼쪽, 1 : 오른쪽
	 * 
	 * @param output
	 * @param dir
	 * @param current
	 */
	static void overlapPerm(int[] output, int[] dir, int current) {
		if (current == dir.length) {
			enter(output, dir);
//			System.out.println(Arrays.toString(dir));
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
	 * @param output
	 * @param dir
	 */
	static void enter(int[] output, int[] dir) {

		int sum = 0;
		boolean[] isVisited = new boolean[N + 1];
		int count = 0;
		for (int i = 0; i < output.length; i++) {
			FishingSpot spot = spots[output[i]];
			int gate = spot.gate;
			int waiting = spot.waiting;

			for (int j = 0; j < waiting; j++) {
				int point = findCloseIndex(isVisited, gate, dir[i]);
				if (point >= 1 && point <= N && !isVisited[point]) {
					isVisited[point] = true;
					sum += Math.abs(gate - point) + 1;
					count++;
				}
			}
		}
		if(waitingSum == count)
			minPath = Math.min(sum, minPath);
	}

	/**
	 * 
	 * @param visited
	 * @param gate
	 * @param flag
	 * @return
	 */
	static int findCloseIndex(boolean[] visited, int gate, int flag) {
		int result = -1;
		
		int left = gate - 1;
		int right = gate + 1;

		if(!visited[gate]) return gate;
		
		for (int i = left; i >= 0; i--) {
			if (!visited[i]) {
				left = i;
				break;
			}
		}

		for (int i = right; i < N; i++) {
			if (!visited[i]) {
				right = i;
				break;
			}
		}

		int leftDiff = gate - left;
		int rightDiff = right - gate;
		
		if(leftDiff == rightDiff) {
			// 왼쪽 기준
			if (flag == 0) {
				if(left >= 0)
					return left;
				else if(right < N)
					return right;
				
			}
			// 오른쪽 기준
			else {
				if(right < N)
					return right;
				else if(left >= 0)
					return left;
			}
		} else {
			result = leftDiff > rightDiff ? right : left;
		}
		
		return result;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}
