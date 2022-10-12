import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 낚시왕 - 골드 1
 * 
 * @author hrlim
 * @version 1.0, 2022.10.15
 *
 */
public class Main_17143 {

	private static class Shark implements Comparable<Shark> {
		int row, col;
		int speed;
		int direction;
		int size;

		public Shark(int row, int col, int speed, int direction, int size) {
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}

		@Override
		public int compareTo(Shark o) {
			return this.size - o.size;
		}
	}
 
	private static int R, C, M;
	private static int catchFishSize;
	private static Shark[][] map;
	private static List<Shark> sharks;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = stringToInt(st.nextToken());
		C = stringToInt(st.nextToken());
		M = stringToInt(st.nextToken());

		map = new Shark[R + 1][C + 1];
		sharks = new ArrayList<>();
		catchFishSize = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int row = stringToInt(st.nextToken());
			int col = stringToInt(st.nextToken());
			int speed = stringToInt(st.nextToken());
			int direction = stringToInt(st.nextToken());
			int size = stringToInt(st.nextToken());
			if (map[row][col] != null) {
				if (map[row][col].size < size) {
					map[row][col] = new Shark(row, col, speed, direction, size);
				}
			} else {
				map[row][col] = new Shark(row, col, speed, direction, size);
			}
		}

		for (int col = 1; col <= C; col++) {
			catchShark(col);
			moveShark();
		}

		System.out.println(catchFishSize);
	}

	private static void catchShark(int col) {
		for (int i = 1; i <= R; i++) {
			if (map[i][col] != null) {
				catchFishSize += map[i][col].size;
				map[i][col] = null;
				break;
			}
		}
	}

	private static void moveShark() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] != null) {
					Shark shark = map[i][j];
					map[i][j] = null;
					
					int row = shark.row;
					int col = shark.col;
					int speed = shark.speed;
					int direction = shark.direction;

					if (direction <= 2) {
						int loop = speed % ((R * 2) - 2);
						for (int l = 0; l < loop; l++) {
							if (direction == 1 && !isRangeRow(row - 1)) {
								direction = changeDirection(direction);
							}

							if (direction == 2 && !isRangeRow(row + 1)) {
								direction = changeDirection(direction);
							}

							if (direction == 1) {
								row--;
							}
							if (direction == 2) {
								row++;
							}
						}
					} else {
						int loop = speed % ((C * 2) - 2);
						for (int l = 0; l < loop; l++) {
							if (direction == 3 && !isRangeCol(col + 1)) {
								direction = changeDirection(direction);
							}

							if (direction == 4 && !isRangeCol(col - 1)) {
								direction = changeDirection(direction);
							}

							if (direction == 3) {
								col++;
							}
							if (direction == 4) {
								col--;
							}
						}
					}
					
					shark.direction = direction;
					shark.row = row;
					shark.col = col;
					sharks.add(shark);
				}
			}
		}
		
		eatShark(sharks);
	}

	private static void eatShark(List<Shark> sharks) {
		for (int i = 0; i < sharks.size(); i++) {
			Shark shark = sharks.get(i);
			if (map[shark.row][shark.col] != null) {
				if (map[shark.row][shark.col].size < shark.size) {
					map[shark.row][shark.col] = shark;
				}
			} else {
				map[shark.row][shark.col] = shark;
			}
		}
		sharks.clear();
	}

	private static int changeDirection(int curDirection) {
		if (curDirection == 1)
			return 2;
		if (curDirection == 2)
			return 1;
		if (curDirection == 3)
			return 4;
		return 3;
	}

	private static boolean isRangeRow(int row) {
		return row >= 1 && row <= R;
	}

	private static boolean isRangeCol(int col) {
		return col >= 1 && col <= C;
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
