import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 프로세서 연결하기
 * 
 * @author hrlim
 * @version 1.0, 2022.08.29
 */

public class Solution_1767 {

	// 상 하 좌 우
	static int[][] dirs4 = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

	static int[][] map;
	static int N;
	static int minEdge;
	static ArrayList<Coordinate> machines;

	static class Coordinate {
		int row, col;

		public Coordinate(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = stringToInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			N = stringToInt(br.readLine());

			machines = new ArrayList<>();
			minEdge = Integer.MAX_VALUE;
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = stringToInt(st.nextToken());
					if (map[i][j] == 0)
						continue;
					if (i == 0 || i == N - 1)
						continue;
					if (j == 0 || j == N - 1)
						continue;
					machines.add(new Coordinate(i, j));
				}
			}

			for (int i = machines.size() - 1; i > 0; i--) {
				comb(0, new int[i + 1], 0);

				if (minEdge != Integer.MAX_VALUE) {
					break;
				}
			}
			if(minEdge == Integer.MAX_VALUE) {
				sb.append("#").append(testCase).append(" ").append(0).append("\n");
			} else
			sb.append("#").append(testCase).append(" ").append(minEdge).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	// 조합
	static void comb(int current, int[] output, int start) {
		if (current == output.length) {
			perm(0, output, new int[output.length], 0);
			return;
		}
		for (int i = start; i < machines.size(); i++) {
			output[current] = i;
			comb(current + 1, output, i + 1);

		}
	}

	// 방향 정하면서 계산하기
	static void perm(int current, int[] selected, int[] dirs, int sum) {
		if (current == selected.length) {
			minEdge = Math.min(minEdge, sum);
			return;
		}

		for (int i = 0; i < dirs4.length; i++) {
			if (!isAvailable(machines.get(selected[current]), i))
				continue;
			dirs[current] = i;
			perm(current + 1, selected, dirs,
					sum + getDistance(machines.get(selected[current]).row, machines.get(current).col, i, true));
			getDistance(machines.get(selected[current]).row, machines.get(current).col, i, false);
		}
	}

	static boolean isAvailable(Coordinate machine, int dir) {

		switch (dir) {
		case 0:
			for (int i = 0; i < machine.row; i++) {
				if (map[i][machine.col] != 0)
					return false;
			}
			break;
		case 1:
			for (int i = N - 1; i > machine.row; i--) {
				if (map[i][machine.col] != 0)
					return false;
			}
			break;
		case 2:
			for (int i = 0; i < machine.col; i++) {
				if (map[machine.row][i] != 0)
					return false;
			}
			break;
		case 3:
			for (int i = N - 1; i > machine.col; i--) {
				if (map[machine.row][i] != 0)
					return false;
			}
			break;
		}

		return true;

	}

	static int getDistance(int row, int col, int dir, boolean flag) {
		int distance = 0;
		switch (dir) {
		case 0:
			for (int i = 0; i < row; i++) {
				if (map[i][col] == 1)
					break;
				if (flag) {
					map[i][col] = 2;
					distance++;
				}
				else
					map[i][col] = 0;
				
			}
			break;
		case 1:
			for (int i = N - 1; i > row; i--) {
				if (map[i][col] == 1)
					break;
				if (flag) {
					map[i][col] = 2;
					distance++;
				}
				else
					map[i][col] = 0;
			}
			break;
		case 2:
			for (int i = 0; i < col; i++) {
				if (map[row][i] == 1)
					break;
				if (flag) {
					map[row][i] = 2;
					distance++;
				}
				else
					map[row][i] = 0;
			}
			break;
		case 3:
			for (int i = N - 1; i > col; i--) {
				if (map[row][i] == 1)
					break;
				if (flag) {
					map[row][i] = 2;
					distance++;
				}
				else
					map[row][i] = 0;
			}
			break;
		}

		return distance;
	}

	static boolean isRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
