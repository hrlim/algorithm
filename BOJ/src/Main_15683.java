import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 15683 감시 - 골드 4 
 * <b>구현, 브루트포스 알고리즘, 시뮬레이션</b>
 * 
 * @author hrlim
 * @version 1.1, 2022.08.20
 *
 */
public class Main_15683 {

	public static int R, C;
	public static int[][] map;
	
	public static final int CAMERA_TYPE_1 = 1;
	public static final int CAMERA_TYPE_2 = 2;
	public static final int CAMERA_TYPE_3 = 3;
	public static final int CAMERA_TYPE_4 = 4;
	public static final int CAMERA_TYPE_5 = 5;

	// 우하좌상
	// 방향 순서가 중요 !
	public static int[][] dir4 = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static int maxWatch = Integer.MAX_VALUE;
	public static List<Camera> cameras = new ArrayList<Camera>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = stringToInt(st.nextToken());
		C = stringToInt(st.nextToken());

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = stringToInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					cameras.add(new Camera(map[i][j], i, j));
				}
			}

		}

		if (cameras.size() > 0) {
			makeDirection(0, new int[cameras.size()]);
		} else {
			maxWatch = getWatchedCount(map);
		}

		sb.append(maxWatch);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	/**
	 * 방향을 정해서 감시까지 수행하는 메서드 <b>중복순열 활용</b>
	 * 
	 * @param cnt
	 * @param output
	 */
	static void makeDirection(int cnt, int[] output) {
		if (cnt == cameras.size()) {
			watch(output, deepCopy(map));
			return;
		}

		for (int i = 0; i < 4; i++) {
			output[cnt] = i;
			makeDirection(cnt + 1, output);
		}
	}

	/**
	 * 정해진 방향대로 CCTV 수행하라고 명령하는 메서드
	 * 
	 * @param direction
	 * @param copyMap
	 */
	static void watch(int[] direction, int[][] copyMap) {
		for (int i = 0; i < cameras.size(); i++) {
			detect(direction[i], cameras.get(i), copyMap);
		}

		maxWatch = Math.min(maxWatch, getWatchedCount(copyMap));

	}

	/**
	 * 정해진 방향으로 CCTV 감시 수행하는 메서드
	 * 
	 * @param dir
	 * @param camera
	 * @param map
	 */
	static void detect(int dir, Camera camera, int[][] map) {
		int type = camera.type;
		int dRow, dCol;

		ArrayList<Integer> convert = convertDirection(type, dir);
		for (int i = 0; i < convert.size(); i++) {
			dRow = camera.row;
			dCol = camera.col;

			while (true) {
				dRow += dir4[convert.get(i)][0];
				dCol += dir4[convert.get(i)][1];

				if (!isRange(dRow, dCol) || map[dRow][dCol] == 6)
					break;
				map[dRow][dCol] = -1;
			}
		}
		
	}

	/**
	 * CCTV Type 에 맞는 방향 목록 가져오는 메서드 
	 * @param type
	 * @param dir
	 * @return
	 */
	static ArrayList<Integer> convertDirection(int type, int dir) {
		ArrayList<Integer> convert = new ArrayList<>();
		switch (type) {
		case CAMERA_TYPE_1:
			convert.add(dir);
			break;
		case CAMERA_TYPE_2:
			convert.add(dir);
			convert.add((dir + 2) % 4);
			break;
		case CAMERA_TYPE_3:
			for (int i = 0; i < 2; i++) {
				convert.add((dir++) % 4);
			}
			break;
		case CAMERA_TYPE_4:
			for (int i = 0; i < 3; i++) {
				convert.add((dir++) % 4);
			}
			break;
		case CAMERA_TYPE_5:
			for (int i = 0; i < 4; i++) {
				convert.add((dir++) % 4);
			}
			break;
		}

		return convert;
	}

	/**
	 * 이차원 배열의 범위 내의 좌표인지 확인하는 메서드
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	static boolean isRange(int row, int col) {
		if (row >= 0 && row < R && col >= 0 && col < C)
			return true;
		return false;
	}

	/**
	 * MAP(이차원 배열) 에서 CCTV 감시 불가능한 카운트 확인하는 메서드
	 * 
	 * @param arr
	 * @return
	 */
	static int getWatchedCount(int[][] arr) {
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == 0) {
					result++;
				}
			}
		}
		return result;
	}

	/**
	 * 이차원 배열을 깊은복사하는 메서드
	 * 
	 * @param arr
	 * @return
	 */
	static int[][] deepCopy(int[][] arr) {
		int[][] result = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result[i][j] = arr[i][j];
			}
		}
		return result;
	}

	/**
	 * 문자형 타입을 숫자형 타입으로 변환하여 반환하는 메서드
	 * 
	 * @param s
	 * @return
	 */
	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}

class Camera {
	int type;
	int row;
	int col;

	public Camera(int type, int row, int col) {
		super();
		this.type = type;
		this.row = row;
		this.col = col;
	}

}