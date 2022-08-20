import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [S/W 문제해결 응용] 3일차 - 최적 경로
 * 
 * @author hrlim
 * @version 1.0, 2022.08.19
 *
 */

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

public class Solution_1247_임하림 {

	private static int minRoot = Integer.MAX_VALUE;

	private static Coordinate company;
	private static Coordinate home;
	private static Coordinate[] customer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int TC = stringToInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {

			int N = stringToInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			company = new Coordinate(stringToInt(st.nextToken()), stringToInt(st.nextToken()));
			home = new Coordinate(stringToInt(st.nextToken()), stringToInt(st.nextToken()));

			customer = new Coordinate[N];
			for (int i = 0; i < N; i++) {
				customer[i] = new Coordinate(stringToInt(st.nextToken()), stringToInt(st.nextToken()));
			}

			perm(company.row, company.col, 0, 0, 0);
			sb.append("#").append(testCase).append(" ").append(minRoot).append("\n");
			minRoot = Integer.MAX_VALUE;
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void perm(int startRow, int startCol, int cnt, int distance, int flag) {
		if (cnt == customer.length) {
			distance += getDistance(startRow, startCol, home.row, home.col);
			minRoot = Math.min(distance, minRoot);
			return;
		}

		for (int i = 0; i < customer.length; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			distance += getDistance(startRow, startCol, customer[i].row, customer[i].col);
			perm(customer[i].row, customer[i].col, cnt + 1, distance, flag | 1 << i);

		}
	}

	static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}
