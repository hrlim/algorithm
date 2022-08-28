import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 탈출 - 골드 4
 * 너비우선탐색
 * 
 * @author hrlim
 * @version 1.0, 2022.08.24
 */
class Coordinate {
	int row; // 행
	int col; // 열
	char type; // 고습도치인지 물인지 

	public Coordinate(int row, int col, char type) {
		this.row = row;
		this.col = col;
		this.type = type;
	}
}

public class Main_3055 {

	// 상하좌우
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int R, C; // 지도의 가로와 세로길이
	static char[][] map; // 지도 정보

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = stringToInt(st.nextToken());
		C = stringToInt(st.nextToken());

		map = new char[R][C];
		
		Coordinate start = null;
		List<Coordinate> waterPos = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'S') start = new Coordinate(i, j, 'S');
				else if (map[i][j] == '*') waterPos.add(new Coordinate(i, j, '*'));
			}
		}
		
		System.out.println(bfs(start, waterPos));
	}

	static Object bfs(Coordinate start, List<Coordinate> waters) {
		Queue<Coordinate> queue = new ArrayDeque<>();
		// 방문순서를 확인하기 위함
		int[][] visited = new int[R][C];

		for (Coordinate water : waters) {
			queue.offer(water);
			visited[water.row][water.col] = 1;
		}

		queue.offer(start);
		visited[start.row][start.col] = 1;

		while (!queue.isEmpty()) {
			Coordinate cur = queue.poll();
			int row = cur.row;
			int col = cur.col;
			char type = cur.type;

			for (int i = 0, length = dirs.length; i < length; i++) {
				int dRow = row + dirs[i][0];
				int dCol = col + dirs[i][1];

				if (!isRange(dRow, dCol)) continue;
				if (map[dRow][dCol] == 'X') continue;
				if (visited[dRow][dCol] != 0) continue;
				if (type == '*' && map[dRow][dCol] == 'D') continue;
				if (type == 'S' && map[dRow][dCol] == 'D') return visited[row][col];
				
				visited[dRow][dCol] = visited[row][col] + 1;
				map[dRow][dCol] = type;
				queue.offer(new Coordinate(dRow, dCol, type));

			}
		}
		return "KAKTUS";
	}

	static boolean isRange(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}
