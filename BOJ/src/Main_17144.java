import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 미세먼지 안녕! - 골드4
 * 
 * @author hrlim
 * @version 1.0, 2022.08.26
 */
public class Main_17144 {

	static class Coordinate {
		int row, col;

		public Coordinate(int row, int col) {
			this.row = row;
			this.col = col;

		}
	}

	// 상하좌우
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int R, C, T;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = stringToInt(st.nextToken());
		C = stringToInt(st.nextToken());
		T = stringToInt(st.nextToken());

		map = new int[R][C];
		int airCleanerR = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = stringToInt(st.nextToken());
			}
			if (map[i][0] == -1) {
				airCleanerR = i;
			}
		}
		for (int i = 0; i < T; i++) {
			map = spread(airCleanerR - 1,  new int[R][C]);
		}
		
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result += map[i][j];
			}
		}
		System.out.println(result);
	}

	static int[][] spread(int airCleanerR, int[][] spreadMap) {
		
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// step 1. 주변에서 나에게 오는 값 정의
				int plus = 0;
				for (int dir = 0; dir < dirs.length; dir++) {
					int dRow = i + dirs[dir][0];
					int dCol = j + dirs[dir][1];

					if (!isRange(dRow, dCol)) continue;
					plus += map[dRow][dCol] / 5;
				}

				// step 2. 내가 주변에 주는 값 빼고 가지기
				int minusCount = 0;
				for (int dir = 0; dir < dirs.length; dir++) {
					int dRow = i + dirs[dir][0];
					int dCol = j + dirs[dir][1];

					if (!isRange(dRow, dCol))
						continue;
					if (map[dRow][dCol] == -1)
						continue;
					minusCount++;
				}

				int result = map[i][j] - (map[i][j] / 5) * minusCount + plus;
				spreadMap[i][j] = result;
			}
		}
		return circum(spreadMap, airCleanerR);
	}

	static int[][] circum(int[][] spreadMap, int airCleanerR) {
		
		
		int direction = 0;
		
		int row = airCleanerR;
		int col = 0;
		int dRow, dCol;
		
		int[][] upDir = {{-1, 0},{0, 1},{1, 0},{ 0 ,-1}};
		while(true) {
			dRow = row + upDir[direction][0];
			dCol = col + upDir[direction][1];
			
			if(dRow == airCleanerR && dCol == 0) break;
			if(!isRange(dRow, dCol) || dRow == airCleanerR + 1) {
				direction += 1;
				dRow = row + upDir[direction][0];
				dCol = col + upDir[direction][1];
			}
			spreadMap[row][col] = spreadMap[dRow][dCol];
			row = dRow;
			col = dCol;
		}
		
		row = airCleanerR + 1;
		col = 0;
		direction = 0;
		
		int[][] downDir = {{1, 0},{0, 1},{-1, 0},{ 0 ,-1}};
		while(true) {
			dRow = row + downDir[direction][0];
			dCol = col + downDir[direction][1];
			
			if(dRow == airCleanerR + 1 && dCol == 0) break;
			if(!isRange(dRow, dCol) || dRow == airCleanerR) {
				direction += 1;
				dRow = row + downDir[direction][0];
				dCol = col + downDir[direction][1];
			}
			spreadMap[row][col] = spreadMap[dRow][dCol];
			row = dRow;
			col = dCol;
		}
		
		spreadMap[airCleanerR][1] = 0;
		spreadMap[airCleanerR + 1][1] = 0;
		spreadMap[airCleanerR][0] = 0;
		spreadMap[airCleanerR + 1][0] = 0;

		return spreadMap;
	}

	static boolean isRange(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
