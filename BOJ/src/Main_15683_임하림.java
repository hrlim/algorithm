import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 감시
 * 
 * @author hrlim
 * @version 1.0, 2022.08.18
 *
 */
public class Main_15683_임하림 {

	public static int R, C;

	public static final int CAMERA_TYPE_1 = 1;
	public static final int CAMERA_TYPE_2 = 2;
	public static final int CAMERA_TYPE_3 = 3;
	public static final int CAMERA_TYPE_4 = 4;
	public static final int CAMERA_TYPE_5 = 5;

	// 우하좌상
	// 방향 순서가 중요 !
	public static int[][] dir4 = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static int[][] map;
	public static int maxWatch = Integer.MIN_VALUE;
	
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
			watch(0, 0);
		} else {
			maxWatch = getWatchedCount(map);
		}

		sb.append(maxWatch);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void watch(int currentCameraIndex, int currentDirection) {
		
	}
	
	

	static boolean isRange(int row, int col) {
		if (row >= 0 && row < R && col >= 0 && col < C)
			return true;
		return false;
	}
	
	static int getWatchedCount(int[][] arr) {
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(arr[i][j] == 0) {
					result++;
				}
			}
		}
		return result;
	}

	static int[][] copyArrays(int[][] arr) {
		int[][] result = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result[i][j] = arr[i][j];
			}
		}
		return result;
	}

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