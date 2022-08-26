package ssafy_mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_헌터 {

	static class Coordinate {
		int row, col;
		int type; // 몬스터인지, 고객인지 (몬스터는 양수, 고객은 음수)

		public Coordinate(int row, int col, int type) {
			this.row = row;
			this.col = col;
			this.type = type;
		}
	}

	static int minTime; // 모든 작업을 완료할 수 있는 가장 빠른 시간
	static int N; // 맵의 한 변의 길이
	static int[][] map;

	static ArrayList<Coordinate> monsterAndCustomer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = stringToInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {

			N = stringToInt(br.readLine());
			map = new int[N][N];

			monsterAndCustomer = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = stringToInt(st.nextToken());
					if (map[i][j] != 0)
						monsterAndCustomer.add(new Coordinate(i, j, map[i][j]));
				}
			}

			minTime = Integer.MAX_VALUE;
			perm(new int[monsterAndCustomer.size()], 0, new boolean[monsterAndCustomer.size()]);

			sb.append("#").append(testCase).append(" ").append(minTime).append("\n");
		}
		System.out.println(sb.toString());

	}

	/**
	 * 순열을 통한 몬스터와 고객의 순서 정하기
	 * 
	 * @param output  순서 저장
	 * @param current 현재까지 저장해진 횟수 (Count)
	 */
	static void perm(int[] output, int current, boolean[] isVisited) {

		// 몬스터 먼저 죽이고 고객 방문 해야하기 때문에 가지치기
		if (current > 0 && !isAvailable(output, current)) return;

		if (current == output.length) {
			distanceCalculate(output);
			return;
		}

		for (int i = 0; i < output.length; i++) {
			if (isVisited[i]) continue;
			output[current] = i;
			isVisited[i] = true;
			perm(output, current + 1, isVisited);
			isVisited[i] = false;
		}
	}

	/**
	 * 현재까지 몬스터가 먼저 잡히고 고객 방문하는지 확인하는 메서드
	 * 
	 * @param output	현재까지 정해진 순서 (current 사이즈만큼만 유효함)
	 * @param current	현재까지 정해진 순서 횟수
	 * @return
	 */
	static boolean isAvailable(int[] output, int current) {

		int[] check = new int[current];
		boolean isAvailableAttack;
		for (int i = 0; i < check.length; i++) {
			check[i] = monsterAndCustomer.get(output[i]).type;
			if (check[i] < 0) {
				isAvailableAttack = false;
				for (int j = 0; j < i; j++) {
					if (check[j] == check[i] * -1) {
						isAvailableAttack = true;
						break;
					}
				}
				if (!isAvailableAttack) return false;
			}
		}

		return true;
	}

	/**
	 * 정해진 순서로 거리 구하기
	 * 
	 * @param output 정해진 순서(몬스터-고객)
	 */
	static void distanceCalculate(int[] output) {

		int sum = getDistance(0, 0, monsterAndCustomer.get(output[0]).row, monsterAndCustomer.get(output[0]).col);
		for (int i = 1; i < output.length; i++) {
			Coordinate first = monsterAndCustomer.get(output[i - 1]);
			Coordinate second = monsterAndCustomer.get(output[i]);
			sum += getDistance(first.row, first.col, second.row, second.col);
		}
		minTime = Math.min(minTime, sum);
	}

	/**
	 * 두 점사이의 거리 구하는 메서드
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}
