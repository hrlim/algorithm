

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 상호의 배틀필드
 * 
 * @author hrlim
 * @version 1.0, 2022.08.03
 */
public class Solution_1873 {

	public static char[] carPosType = { '^', 'v', '<', '>' };

	public static int[][] dirs4 = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	public static int curDirection = -1;

	public static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			map = new char[H][W];
			int curPosX = -1;
			int curPosY = -1;
			for (int i = 0; i < H; i++) {
				String input = br.readLine();
				for (int j = 0; j < input.length(); j++) {
					map[i][j] = input.charAt(j);
					if (isCarPos(map[i][j])) {
						curPosX = j;
						curPosY = i;
						map[i][j] = '.';
					}
				}
			}

			int N = Integer.parseInt(br.readLine());
			char[] command = br.readLine().toCharArray();

			for (int i = 0; i < command.length; i++) {
				int dx = 0;
				int dy = 0;

				changeDirection(command[i]);

				dx = curPosX + dirs4[curDirection][0];
				dy = curPosY + dirs4[curDirection][1];

				if (command[i] == 'S') {
					while (true) {
						if (isRange(dy, dx)) {
							if (map[dy][dx] == '#') {
								break;
							} 
							if (map[dy][dx] == '*') {
								map[dy][dx] = '.';
								break;
							}
						} else {
							break;
						}

						dx += dirs4[curDirection][0];
						dy += dirs4[curDirection][1];
					}
				} else {
					if (isRange(dy, dx) && map[dy][dx] == '.') {
						curPosX = dx;
						curPosY = dy;
					}
				}
			}

			map[curPosY][curPosX] = carPosType[curDirection];
			System.out.print("#" + test_case + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}

	static boolean isCarPos(char c) {
		for (int i = 0; i < carPosType.length; i++) {
			if (carPosType[i] == c) {
				curDirection = i;
				return true;
			}
		}

		return false;
	}

	static boolean isRange(int x, int y) {
		if (x >= 0 && x < map.length && y >= 0 && y < map[0].length)
			return true;
		return false;
	}

	static void changeDirection(char c) {
		if (c == 'U') {
			curDirection = 0;
		} else if (c == 'D') {
			curDirection = 1;
		} else if (c == 'L') {
			curDirection = 2;
		} else if (c == 'R') {
			curDirection = 3;
		}
	}
}
