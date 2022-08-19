import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_5644_임하림_2 {

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
			int sumA = 0, sumB = 0;
			int sumC = 0;

			PriorityQueue<BatteryCharger> bcA = null;
			PriorityQueue<BatteryCharger> bcB = null;
			BatteryCharger testA = null, testB = null;
			if (map[rowA][colA].size() > 0) {
				sumA += map[rowA][colA].peek().performance;
			}
			if (map[rowB][colB].size() > 0) {
				sumB += map[rowB][colB].peek().performance;
			}

			String[][] test = new String[10][10];
			for (int i = 0; i < test.length; i++) {
				for (int j = 0; j < test.length; j++) {
					test[i][j]="";
				}
				
			}
			for (int i = 0; i < M; i++) {

				// ����� A
				rowA += moveInfoA[i].row;
				colA += moveInfoA[i].col;

				// �ÿ��� B
				rowB += moveInfoB[i].row;
				colB += moveInfoB[i].col;

				bcA = map[rowA][colA];
				bcB = map[rowB][colB];

				test[rowA][colA] += "A";
				test[rowB][colB] += "B";

				if (bcA.size() > 0 && bcB.size() > 0 && bcA.peek().equals(bcB.peek())) {
					int max = bcA.peek().performance / 2;

					int minSize = Math.min(bcA.size(), bcB.size());
					// 0 �̸� B ����, 1 �̸� A ����
					int pivot = bcA.size() >= bcB.size() ? 1 : 0;
					Object[] arrA = bcA.toArray();
					Object[] arrB = bcB.toArray();

					int nextMax = 0;
					for (int j = 0; j < minSize; j++) {
						testA = (BatteryCharger) arrA[j];
						testB = (BatteryCharger) arrB[j];
						if (!testA.equals(arrB[j])) {
							if (testA.performance >= max) {
								nextMax = testA.performance;
							} else if (testB.performance >= max) {
								nextMax = testB.performance;
							}
							break;
						}
					}
					if (arrA.length > minSize) {
						testA = (BatteryCharger) arrA[minSize];
						if (testA.performance >= max) {
							nextMax = testA.performance;
						}
					} else if (arrB.length > minSize) {
						testB = (BatteryCharger) arrB[minSize];
						if (testB.performance >= max) {
							nextMax = testB.performance;
						}
					}
					sumC += (max * 2) + nextMax;

				} else {
					if (bcA.size() > 0) {
						sumA += bcA.peek().performance;
					}
					if (bcB.size() > 0) {
						sumB += bcB.peek().performance;
					}
				}

			}

			for (int i = 0; i < test.length; i++) {
				for (int j = 0; j < test.length; j++) {
					if(test[i][j].equals("")) System.out.print(" \t");
					System.out.print(test[i][j] + "\t");
				}
				System.out.println();
			}
			System.out.println(sumA + sumB + sumC);
			sb.append("#").append(test_case).append(" ").append(sumA + sumB + sumC).append(System.lineSeparator());

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

class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}