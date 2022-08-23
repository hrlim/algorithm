
import java.io.*;
import java.util.*;

/**
 * 아기상어 - 골드 3 
 * 그래프 이론, 깊이 우선 탐색
 * 
 * @author hrlim
 * @version 1.0, 2022.08.23
 */
class Fish implements Comparable<Fish> {
	int row;
	int col;
	int distance;

	public Fish(int row, int col, int distance) {
		super();

		this.row = row;
		this.col = col;
		this.distance = distance;
	}

	@Override
	public int compareTo(Fish o) {
		if (this.distance == o.distance) {
			if (this.row == o.row)
				return this.col - o.col;
			return this.row - o.row;
		}

		return this.distance - o.distance;
	}
}

public class Main_16236 {

	static int[][] map;
	static int N;

	static int sharkSize = 2;

	// 상하좌우
	static int[][] dirs4 = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = stringToInt(br.readLine());

		map = new int[N][N];
		int sharkRow = 0, sharkCol = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stringToInt(st.nextToken());
				if (map[i][j] == 9) {
					sharkRow = i;
					sharkCol = j;
				}
			}
		}

		sb.append(bfs(sharkRow, sharkCol));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int bfs(int row, int col) {

		List<Fish> nearbyFishs;

		int time = 0;
		int eatFishCount = 0;

		while (true) {
			nearbyFishs = getEatableFishList(row, col);

			if (nearbyFishs.size() == 0)
				break;

			Collections.sort(nearbyFishs);
			Fish fish = nearbyFishs.get(0);

			eatFishCount++;

			if (eatFishCount == sharkSize) {
				sharkSize++;
				eatFishCount = 0;
			}

			time += fish.distance;
			map[row][col] = 0;

			row = fish.row;
			col = fish.col;
			map[row][col] = 9;

		}
		return time;
	}

	static List<Fish> getEatableFishList(int sharkRow, int sharkCol) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(sharkRow);
		queue.offer(sharkCol);
		queue.offer(0);

		boolean[][] visited = new boolean[N][N];
		visited[sharkRow][sharkCol] = true;

		List<Fish> fishList = new ArrayList<Fish>();

		while (!queue.isEmpty()) {
			int curRow = queue.poll();
			int curCol = queue.poll();
			int curCnt = queue.poll();
			
			for (int i = 0; i < dirs4.length; i++) {
				int dRow = curRow + dirs4[i][0];
				int dCol = curCol + dirs4[i][1];

				if (!isRange(dRow, dCol))
					continue;
				if (visited[dRow][dCol])
					continue;
				if (map[dRow][dCol] > sharkSize)
					continue;

				visited[dRow][dCol] = true;
				queue.offer(dRow);
				queue.offer(dCol);
				queue.offer(curCnt + 1);
				if (map[dRow][dCol] > 0 && map[dRow][dCol] < sharkSize) {
					fishList.add(new Fish(dRow, dCol, curCnt + 1));
				}
			}
		}
		return fishList;
	}

	static boolean isRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
