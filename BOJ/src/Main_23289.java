import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 온풍기 안녕! - 플래티넘 5
 * 
 * 1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
 * 2. 온도가 조절됨
 * 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
 * 4. 초콜릿을 하나 먹는다. 
 * 5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K이상이면 테스트를 중단하고, 아니면 1부터 다시 시작한다.
 * 
 * @author hrlim
 * @version 1.0, 2022.08.28
 */
public class Main_23289 {

	static class Coordinate {
		int row, col;

		public Coordinate(int row, int col) {
			this.row = row;
			this.col = col;

		}
	}

	static class Hitter extends Coordinate{
		int type;
		
		public Hitter(int row, int col, int type) {
			super(row, col);
			this.type = type;
		}
	}
	
	
	// 상하좌우
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int R, C, K; // 맵의 크기 ( R * C ), 기준 온도 K
	static int[][] map;
	static int[][] spreadMap;
	
	static int eatChocolate;
	
	static ArrayList<Coordinate> checkRooms;
	static ArrayList<Hitter> hitters;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = stringToInt(st.nextToken());
		C = stringToInt(st.nextToken());
		K = stringToInt(st.nextToken());

		map = new int[R][C];
		checkRooms = new ArrayList<>();
		spreadMap = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = stringToInt(st.nextToken());
				if(1 <= map[i][j] && map[i][j] <= 5) hitters.add(new Hitter(i, j, map[i][j]));
				else if (map[i][j] == 5) checkRooms.add(new Coordinate(i, j));
			}
		}
		
		
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result += map[i][j];
			}
		}
		System.out.println(eatChocolate > 100 ? -1 : eatChocolate);
	}

	/**
	 * 1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
	 */
	static void hitterWind() {
		
	}
	
	/**
	 * 2. 온도가 조절됨
	 * 
	 * @param airCleanerR
	 * @param spreadMap
	 * @return
	 */
	static int[][] spread(int airCleanerR) {
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// step 1. 주변에서 나에게 오는 값 정의
				int plus = 0;
				for (int dir = 0; dir < dirs.length; dir++) {
					int dRow = i + dirs[dir][0];
					int dCol = j + dirs[dir][1];

					if (!isRange(dRow, dCol)) continue;
					plus += map[dRow][dCol] / 4;
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

				int result = map[i][j] - (map[i][j] / 4) * minusCount + plus;
				spreadMap[i][j] = result;
			}
		}
		return circum(spreadMap, airCleanerR);
	}

	/**
	 * 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
	 * @param spreadMap
	 * @param airCleanerR
	 */
	static int[][] circum(int[][] spreadMap, int airCleanerR) {
		
		return spreadMap;
	}

	
	static boolean isAllRoomSatisfied(int[][] spreadMap) {
		
		return true;
	}
	static boolean isRange(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
