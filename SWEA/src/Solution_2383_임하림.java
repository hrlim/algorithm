import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 2383 [모의 SW 역량테스트] 점심 식사시간
 * 
 * @author hrlim
 * @version 1.0, 2022.10.06
 */
public class Solution_2383_임하림 {

	private static class Coordinate {
		int row, col;

		public Coordinate(int row, int col) {
			this.row = row;
			this.col = col;

		}
	}

	private static class Stair extends Coordinate {
		int size;
		Queue<Integer> waiting;
		Queue<Integer> use;

		public Stair(int row, int col, int size) {
			super(row, col);
			this.size = size;
			this.waiting = new ArrayDeque<Integer>();
			this.use = new ArrayDeque<>();
		}
	}

	private static int N;
	private static int[][] map;
	private static Stair[] stairs;
	private static int minTime;
	private static List<Coordinate> people;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = stringToInt(br.readLine());
		StringTokenizer st;

		for (int tc = 1; tc <= T; tc++) {

			int stairCount = 0;
			stairs = new Stair[2];
			people = new ArrayList<Coordinate>();
			minTime = Integer.MAX_VALUE;
			
			N = stringToInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = stringToInt(st.nextToken());
					if (map[i][j] == 1) {
						people.add(new Coordinate(i, j));
					} else if (map[i][j] > 1) {
						stairs[stairCount++] = new Stair(i, j, map[i][j]);
					}
				}
			}

		}
	}

	private static void comb(int[] arr, int start, int cnt) {
		// if()
	}
	
	

	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
