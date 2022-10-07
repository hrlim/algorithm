import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 다리만들기 - 골드 1
 * 
 * @author hrlim
 * @version 1.0, 2022.10.07
 *
 */
public class Main_17472 {

	private static class Coordinate {
		int row, col;

		public Coordinate(int row, int col) {
			this.row = row;
			this.col = col;

		}
	}

	private static class Island {
		int idx;
		List<Coordinate> spaces;

		public Island(int idx) {
			this.idx = idx;
			spaces = new ArrayList<>();
		}
	}

	private static int N, M;
	private static int[][] map;
	private static int answer;
	private static List<Island> islands;

	// 상하좌우
	private static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stringToInt(st.nextToken());
		M = stringToInt(st.nextToken());

		islands = new ArrayList<>();

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stringToInt(st.nextToken()) - 1;
			}
		}

		// 섬 구분하기
		boolean[][] isIsland = new boolean[N][M];
		int islandCnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					Island island = new Island(islandCnt);

					Queue<Coordinate> queue = new LinkedList<>();
					Coordinate start = new Coordinate(i, j);

					queue.add(start);
					map[i][j] = islandCnt;
					island.spaces.add(start);
					isIsland[i][j] = true;

					while (!queue.isEmpty()) {
						Coordinate coordinate = queue.poll();
						int row = coordinate.row;
						int col = coordinate.col;

						for (int k = 0; k < dirs.length; k++) {
							int dx = row + dirs[k][0];
							int dy = col + dirs[k][1];
							if (!isRange(dx, dy))
								continue;
							if (isIsland[dx][dy])
								continue;
							if (map[dx][dy] == 0) {
								Coordinate position = new Coordinate(dx, dy);
								queue.add(position);
								island.spaces.add(position);
								map[dx][dy] = islandCnt;
								isIsland[row][col] = true;
							}
						}
					}
					islands.add(island);
					islandCnt++;
				}
			}
		}

		// 각 섬간의 길이
		int[][] adjMatrix = new int[islandCnt][islandCnt];
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				adjMatrix[i][j] = Integer.MAX_VALUE;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					int fromIdx = map[i][j];
					// 세로
					int colCnt = 0;
					for (int k = i + 1; k < N; k++) {
						if (map[k][j] == -1) {
							colCnt++;
						} else if (map[k][j] == fromIdx) {
							if(colCnt > 0) break;
							continue;
						} else {
							if (colCnt == 1)
								break;
							int minDistance = Math.min(adjMatrix[fromIdx][map[k][j]], colCnt);
							adjMatrix[fromIdx][map[k][j]] = minDistance;
							adjMatrix[map[k][j]][fromIdx] = minDistance;
							break;
						}
					}

					// 가로
					int rowCnt = 0;
					for (int k = j + 1; k < M; k++) {
						if (map[i][k] == -1) {
							rowCnt++;
						} else if (map[i][k] == fromIdx) {
							if(rowCnt > 0) break;
							continue;
						} else {
							if (rowCnt == 1)
								break;
							int minDistance = Math.min(adjMatrix[fromIdx][map[i][k]], rowCnt);
							adjMatrix[fromIdx][map[i][k]] = minDistance;
							adjMatrix[map[i][k]][fromIdx] = minDistance;
							break;
						}
					}
				}
			}
		}

		boolean[] isVisited = new boolean[islands.size() + 1];
		PriorityQueue<int[]> qu = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});

		for (int i = 1; i < adjMatrix[1].length; i++) {
			if (adjMatrix[1][i] != Integer.MAX_VALUE) {
				qu.add(new int[] { i, adjMatrix[1][i] });
			}
		}

		isVisited[1] = true;

		int cnt = 0;
		int result = 0;
		while (!qu.isEmpty()) {
			int[] vertex = qu.poll();
			int no = vertex[0];
			int weight = vertex[1];

			if(isVisited[no]) continue;
			isVisited[no] = true;
			if(cnt == islands.size() - 1) break;
			result += weight;
			cnt++;
			for (int i = 1; i < adjMatrix[no].length; i++) {
				if (adjMatrix[no][i] != Integer.MAX_VALUE) {
					if (!isVisited[i]) {
						qu.offer(new int[] { i, adjMatrix[no][i] });
					}
				}
			}
		}

		boolean isConnected = true;
		for (int i = 1; i < isVisited.length; i++) {
			if(!isVisited[i]) isConnected = false;
		}
		if (result == 0 || !isConnected) result = -1;
		System.out.println(result);

	}

	static boolean isRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
