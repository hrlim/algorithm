import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Coordinate {
	public int row;
	public int col;

	public Coordinate(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

}

public class Solution_5644_임하림_진행중 {

	static int[] dx = { 0, 0, 1, 0, -1 };
	static int[] dy = { 0, -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = stringToInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int M = stringToInt(st.nextToken());
			int A = stringToInt(st.nextToken());

			Coordinate[] moveInfoA = new Coordinate[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < moveInfoA.length; i++) {
				int temp = stringToInt(st.nextToken());
				moveInfoA[i] = new Coordinate(dy[temp], dx[temp]);
			}

			Coordinate[] moveInfoB = new Coordinate[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < moveInfoB.length; i++) {
				int temp = stringToInt(st.nextToken());
				moveInfoB[i] = new Coordinate(dy[temp], dx[temp]);
			}

			PriorityQueue<BatteryCharger>[][] map = new PriorityQueue[10][10];

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					map[i][j] = new PriorityQueue<>();
				}
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int c = stringToInt(st.nextToken());
				int r = stringToInt(st.nextToken());
				int coverage = stringToInt(st.nextToken());
				int performance = stringToInt(st.nextToken());

				for (int row = r - coverage - 1; row < r + coverage; row++) {
					for (int col = c - coverage - 1; col < c + coverage; col++) {
						if (isCovered(row, col, r, c, coverage)) {
							map[row][col].add(new BatteryCharger(r, c, coverage, performance));
						}
					}
				}
			}

			int rowA = 0, colA = 0;
			int rowB = 9, colB = 9;
			int sum = 0;

			PriorityQueue<BatteryCharger> bcA = null;
			PriorityQueue<BatteryCharger> bcB = null;

			BatteryCharger testA = null, testB = null;

			if (map[rowA][colA].size() > 0) {
				sum += map[rowA][colA].peek().performance;
			}
			if (map[rowB][colB].size() > 0) {
				sum += map[rowB][colB].peek().performance;
			}

			for (int i = 0; i < M; i++) {

				// 사용자 A
				rowA += moveInfoA[i].row;
				colA += moveInfoA[i].col;

				// 시용자 B
				rowB += moveInfoB[i].row;
				colB += moveInfoB[i].col;

				bcA = map[rowA][colA];
				bcB = map[rowB][colB];

				int max = 0;
				if (!bcA.isEmpty() && !bcB.isEmpty()) {
					Object[] arrA = bcA.toArray();
					Object[] arrB = bcB.toArray();

					if (bcA.peek().equals(bcB.peek())) {
						if (arrA.length > 1) {
							testA = (BatteryCharger) arrA[1];
							max = Math.max(testA.performance + bcB.peek().performance, max);
						}
						if (arrB.length > 1) {
							testB = (BatteryCharger) arrB[1];
							max = Math.max(testB.performance + bcA.peek().performance, max);
						}
					} else {
						max = bcA.peek().performance + bcB.peek().performance;
					}

				}
				if (!bcA.isEmpty()) {
					max = Math.max(bcA.peek().performance, max);
				}
				if (!bcB.isEmpty()) {
					max = Math.max(bcB.peek().performance, max);
				}

				sum += max;
			}

			sb.append("#").append(test_case).append(" ").append(sum).append(System.lineSeparator());

		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static boolean isCovered(int row, int col, int bcRow, int bcCol, int coverage) {
		if (row >= 0 && row < 10 && col >= 0 && col < 10
				&& coverage >= Math.abs(bcRow - row - 1) + Math.abs(bcCol - col - 1)) {
			return true;
		}
		return false;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}

class BatteryCharger implements Comparable<BatteryCharger> {
	Coordinate pos;
	int coverage;
	int performance;

	public BatteryCharger(int row, int col, int coverage, int performance) {
		super();
		this.pos = new Coordinate(row, col);
		this.coverage = coverage;
		this.performance = performance;
	}

	@Override
	public int compareTo(BatteryCharger o1) {
		// TODO Auto-generated method stub
		return o1.performance - this.performance;
	}

	@Override
	public boolean equals(Object obj) {
		BatteryCharger tmp = (BatteryCharger) obj;
		if (pos.row == tmp.pos.row && pos.col == tmp.pos.col) {
			return true;
		}
		return false;
	}
}
