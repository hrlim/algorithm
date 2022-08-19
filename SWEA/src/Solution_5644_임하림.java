import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_5644_임하림 {

	static int[] dx = { 0, -1, 0, 1, -1 };
	static int[] dy = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = stringToInt(br.readLine());

		for (int test_case = 1; test_case < T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int M = stringToInt(st.nextToken());
			int A = stringToInt(st.nextToken());

			Coordinate[] moveInfoA = new Coordinate[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < moveInfoA.length; i++) {
				moveInfoA[i] = new Coordinate(dx[stringToInt(st.nextToken())], dy[stringToInt(st.nextToken())]);
			}

			Coordinate[] moveInfoB = new Coordinate[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < moveInfoB.length; i++) {
				moveInfoB[i] = new Coordinate(dx[stringToInt(st.nextToken())], dy[stringToInt(st.nextToken())]);
			}

			BatteryCharger[] bc = new BatteryCharger[A + 1];

			for (int i = 0; i < bc.length; i++) {
				st = new StringTokenizer(br.readLine());
				int r = stringToInt(st.nextToken());
				int c = stringToInt(st.nextToken());
				int coverage = stringToInt(st.nextToken());
				int performance = stringToInt(st.nextToken());

				bc[i] = new BatteryCharger(r, c, coverage, performance);
			}

			int rowA = 0, rowB = 9;
			int colA = 0, colB = 9;
			int sumA = 0, sumB = 0;

			for (int i = 0; i < M; i++) {

				// ����� A
				rowA += moveInfoA[i].row;
				colA += moveInfoA[i].col;

				// �ÿ��� B
				rowB += moveInfoB[i].row;
				colB += moveInfoB[i].col;

				if (rowA == rowB && colA == colB) {
					BatteryCharger first = getBatteryCharger(rowA, colA, bc, 1);
					BatteryCharger second = getBatteryCharger(rowB, colB, bc, 2);
					
//					while (tempA != tempB && order++ < bc.length) {
//						tempA = getBatteryCharger(rowA, colA, bc, order);
//						tempB = getBatteryCharger(rowB, colB, bc, order);
//					}
//					sumA += tempA.performance;
//					sumB += tempB.performance;
					
				} else {
					sumA += getBatteryCharger(rowA, colA, bc, 1).performance;
					sumB += getBatteryCharger(rowB, colB, bc, 1).performance;
				}
			}

			sb.append("#").append(test_case).append(" ").append(sumA + sumB).append(System.lineSeparator());
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static BatteryCharger getBatteryCharger(int currentRow, int currentCol, BatteryCharger[] bc, int order) {
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		for (int j = 0; j < bc.length; j++) {
			int r = bc[j].pos.row;
			int c = bc[j].pos.col;
			int coverage = bc[j].coverage;
			int half = coverage / 2;

			for (int row = r - coverage; row < r + coverage; row++) {
				for (int col = half - Math.abs(half - row); col < half + Math.abs(half - row); col++) {
					if (isRange(row, col) && currentRow == row && currentCol == col) {
						max = Math.max(max, bc[j].performance);
						maxIndex = j;
					}
				}
			}
		}
		if (maxIndex != -1) {
			return bc[maxIndex];
		}
		return null;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

	static boolean isRange(int x, int y) {
		if (x > 0 && x <= 10 && y > 0 && y <= 10) {
			return true;
		}
		return false;
	}
}

//class BatteryCharger {
//	Coordinate pos;
//	int coverage;
//	int performance;
//
//	public BatteryCharger(int row, int col, int coverage, int performance) {
//		super();
//		this.pos = new Coordinate(row, col);
//		this.coverage = coverage;
//		this.performance = performance;
//	}
//}
//
//class Coordinate {
//	int row;
//	int col;
//
//	public Coordinate(int row, int col) {
//		super();
//		this.row = row;
//		this.col = col;
//	}
//
//}