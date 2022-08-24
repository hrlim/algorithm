import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 적록색약
 * 
 * @author hrlim
 * @version 1.0, 2022.08.24
 */
public class Main_10026 {

	static char[][] map;
	static boolean[][] visited ;
	static int N;
	// 상 하 좌 우
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	final static int TYPE_NORMAL = 1;
	final static int TYPE_COLOR_BLIND = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = stringToInt(br.readLine());
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		visited = new boolean[N][N];
		int answerNormal = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j, TYPE_NORMAL);
					answerNormal++;
				}
			}
		}
		
		visited = new boolean[N][N];
		int answerColorness = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j, TYPE_COLOR_BLIND);
					answerColorness++;
				}
			}
		}
		System.out.println(answerNormal + " " + answerColorness);

	}

	static void dfs(int row, int col, int type) {
		visited[row][col] = true;
		
		for (int i = 0; i < dirs.length; i++) {
			
			int dRow = row + dirs[i][0];
			int dCol = col + dirs[i][1];
			
			if(!isRange(dRow, dCol)) continue;
			if(visited[dRow][dCol]) continue;
			if(!isSameColor(type, map[row][col], map[dRow][dCol])) continue;
			
			dfs(dRow, dCol, type);
			
		}
	}

	static boolean isSameColor(int type, char curColor, char compareColor) {
		if (type == TYPE_COLOR_BLIND && (curColor == 'R' || curColor == 'G')
				&& (compareColor == 'R' || compareColor == 'G')) {
			return true;
		}
		return curColor == compareColor;
	}

	static boolean isRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
