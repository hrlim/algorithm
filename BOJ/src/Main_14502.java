import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 연구소 - 골드 4
 * 
 * @author hrlim
 * @version 1.0, 2022.10.15
 *
 */
public class Main_14502 {

	private static class Cooridate {
		int row, col;
		public Cooridate(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static int[][] map;
	private static int maxSafeArea;
	private static int N, M;
	private static List<Cooridate> emptySpace;

	// 상하좌우
	private static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stringToInf(st.nextToken());
		M = stringToInf(st.nextToken());

		map = new int[N][M];
		maxSafeArea = Integer.MIN_VALUE;
		emptySpace = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stringToInf(st.nextToken());
				if(map[i][j] == 0) emptySpace.add(new Cooridate(i, j));
			}
		}
		
		buildFireWall(0, 0, new Cooridate[3]);
		System.out.println(maxSafeArea);
	}

	
	private static void buildFireWall(int start, int cnt, Cooridate[] cooridates) {
		if(cnt == cooridates.length) {
			int[][] newMap = deepCopy();
			for (Cooridate cooridate : cooridates) {
				newMap[cooridate.row][cooridate.col] = 1;
			}
			spreadVirus(newMap);
			return;
		}
		
		for (int i = start; i < emptySpace.size(); i++) {
			cooridates[cnt] = emptySpace.get(i);
			buildFireWall(i + 1, cnt + 1, cooridates);
		}
	}
	
	private static void spreadVirus(int[][] curMap) {
		Queue<Cooridate> queue = new LinkedList<>();;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(curMap[i][j] == 2) {
					queue.add(new Cooridate(i, j));
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Cooridate c = queue.poll();
			for (int i = 0; i < dirs.length; i++) {
				int dx = c.row + dirs[i][0];
				int dy = c.col + dirs[i][1];
				if(!isRange(dx, dy)) continue;
				if(curMap[dx][dy] != 0) continue;
				curMap[dx][dy] = 2;
				queue.add(new Cooridate(dx, dy));
			}
		}
		countSafeArea(curMap);
	}
	
	private static void countSafeArea(int[][] curMap) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(curMap[i][j] == 0) count ++;
			}
		}
		
		maxSafeArea = Math.max(maxSafeArea, count);
	}
	
	private static int[][] deepCopy() {
		int[][] result = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[i][j] = map[i][j];
			}
		}
		return result;
	}
	
	private static boolean isRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
	
	private static int stringToInf(String s) {
		return Integer.parseInt(s);
	}
}
