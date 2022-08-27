package mock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 5650. [모의 SW 역량테스트] 핀볼 게임
 * 
 * @author hrlim
 *
 */
public class Solution_5650 {

	static class Coordinate {
		int row, col;

		public Coordinate(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static int N;
	static int[][] map;
	static int maxCount;

	// 상 우 하 좌
	static int[][] dirs4 = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }};

	// 웜홀
	static Map<Integer, ArrayList<Coordinate>> wormHole;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = stringToInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {

			N = stringToInt(br.readLine());
			map = new int[N][N];
			wormHole = new HashMap<>();
			maxCount = Integer.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = stringToInt(st.nextToken());
					if (6 <= map[i][j] && map[i][j] <= 10) {
						if (!wormHole.containsKey(map[i][j])) {
							wormHole.put(map[i][j], new ArrayList<>());
						}
						ArrayList<Coordinate> c = wormHole.get(map[i][j]);
						c.add(new Coordinate(i, j));
						wormHole.put(map[i][j], c);
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						for (int dir = 0; dir < dirs4.length; dir++) {
							go(i, j, dir);
						}
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(maxCount).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	static void go(int row, int col, int direction) {
		int jumsu = 0;

		int dRow = row;
		int dCol = col;
		while (true) {

			dRow += dirs4[direction][0];
			dCol += dirs4[direction][1];

			// 범위를 벗어났을 때
			if (!isRange(dRow, dCol)) {
				jumsu++;
				direction = changeDirection(map[row][col], direction);
				continue;
			}
			
			// 블록을 만났을 때
			if((1 <= map[dRow][dCol] && map[dRow][dCol] <= 5)) {
				jumsu++;
				direction = changeDirection(map[dRow][dCol], direction);
				continue;
			}
			
			// 웜홀을 만났을 때
			if (map[dRow][dCol] >= 6) {
				Coordinate c = moveTo(map[dRow][dCol], dRow, dCol);
				dRow = c.row;
				dCol = c.col;
			}
			// 시작점이나 블랙홀을 만났을 때
			if ((dRow == row && dCol == col) || map[dRow][dCol] == -1)
				break;
		}
		maxCount = Math.max(jumsu, maxCount);
	}
	
	static int changeDirection(int type, int direction) {
		if (type == 1) {
			if (direction == 2) return 1;
			else if (direction == 3) return 0;
		} else if (type == 2) {
			if (direction == 0) return 1;
			else if (direction == 3) return 2;
		} else if (type == 3) {
			if (direction == 1) return 2;
			else if (direction == 0) return 3;
		} else if (type == 4) {
			if (direction == 1) return 0;
			else if (direction == 2) return 3;
		}

		return (direction + 2) % 4;
	}

	static Coordinate moveTo(int type, int row, int col) {
		ArrayList<Coordinate> set = wormHole.get(type);

		for (Coordinate coordinate : set) {
			if (coordinate.row != row && coordinate.col != col)
				return coordinate;
		}

		return null;
	}

	static boolean isRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
