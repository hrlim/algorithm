import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 달이 차오른다, 가자 - 골드 1
 * 
 * @author hrlim
 * @version 1.0, 2022.10.15
 *
 */
public class Main_1194_임하림 {
	private static int N, M;
	private static char[][] map;
	private static List<Coordinate> destinationList;
	private static Set<Character> keys;
	private static int minMoveCount;

	// 상하좌우
	private static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	private static class Coordinate {
		int row, col;
		int depth;

		public Coordinate(int row, int col, int depth) {
			this.row = row;
			this.col = col;
			this.depth = depth;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stringToInt(st.nextToken());
		M = stringToInt(st.nextToken());
		destinationList = new ArrayList<>();
		keys = new HashSet<>();
		minMoveCount = -1;

		int startRow = -1;
		int startCol = -1;

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == '0') {
					startRow = i;
					startCol = j;
					map[i][j] = '.';
				} else if (map[i][j] == '1') {
					destinationList.add(new Coordinate(i, j, -1));
				}
			}
		}
		for (int i = 0; i < destinationList.size(); i++) {
			Coordinate destination = destinationList.get(i);
			goMoon(startRow, startCol, destination);
		}
		System.out.println(minMoveCount);
	}

	private static void goMoon(int startRow, int startCol, Coordinate destination) {

		Queue<Coordinate> queue = new LinkedList<>();
		queue.add(new Coordinate(startRow, startCol, 0));
		
		while(!queue.isEmpty()) {
			Coordinate c = queue.poll();
			if (c.row == destination.row && c.col == destination.col) {
				minMoveCount = Math.min(minMoveCount, c.depth);
			}
			
			for (int i = 0; i < dirs.length; i++) {
				int dx = startRow + dirs[i][0];
				int dy = startCol + dirs[i][1];
				
				if (!isRange(dx, dy)) continue;
				char space = map[dx][dy];
				if (space == '#') continue;
				
			}
		}
		
	}

	private static boolean isAvailablePass(char space) {
		if (space == 'A')
			return keys.contains('a');
		if (space == 'B')
			return keys.contains('b');
		if (space == 'C')
			return keys.contains('c');
		if (space == 'D')
			return keys.contains('d');
		if (space == 'E')
			return keys.contains('e');
		if (space == 'F')
			return keys.contains('f');
		return true;

	}

	private static boolean isKeyType(char space) {
		if (space == 'a') {
			keys.add('a');
			return true;
		} else if (space == 'b') {
			keys.add('b');
			return true;
		} else if (space == 'c') {
			keys.add('c');
			return true;
		} else if (space == 'd') {
			keys.add('d');
			return true;
		} else if (space == 'e') {
			keys.add('e');
			return true;
		} else if (space == 'f') {
			keys.add('f');
			return true;
		}
		return false;
	}

	private static boolean isRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
