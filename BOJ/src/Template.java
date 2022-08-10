import java.io.*;
import java.util.StringTokenizer;

public class Template {

	public static int[][] map;
	public static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stringToInt(st.nextToken());
		M = stringToInt(st.nextToken());
		int R = stringToInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stringToInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print( map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("HI");
		int[][] res = rotation90(map);
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				System.out.print( res[i][j] + " ");
			}
			System.out.println();
		}
	}

	static int[][] rotation90(int[][] arr) {
		int[][] result = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				System.out.println(i + " " + j);
				int tmp = arr[i][j];
				
				// 왼쪽 위 꼭지점
				if (j - 1 < i && i - 1 < j ) {
					result[i + 1][j] = arr[i][j];
				}
				// 왼쪽 아래 꼭지점
				else if (i + 1 >= N - i && j - 1 < i) {
					result[i][j + 1] = arr[i][j];
				}
				// 오른쪽 아래 꼭지점
				else if (j + 1 >= M - i && i + 1 >= N - j) {
					result[i][j - 1] = arr[i][j];
				}
				// 오른쪽 위 꼭지점
				else if (i - 1 < N - j && j >= M - i) {
					result[i][j - 1] = arr[i][j];
					// i = 1 j = 3 		// 0 < 1   3 >= 4
					// i = 1, j = 2       0< 2     3 >= 4
					// i = 0, j = 3 (O)    -1 < 1   4 >= 3
				} 
				// 왼쪽 변
				else if(i - j - 1 < 0) {
					result[i + 1][j] = arr[i][j];
				}
				// 아랫쪽 변
				else if(i + 1 >= N - i) {
					result[i][j + 1] = arr[i][j];
				}
				// 오른쪽 변
				else if(j + 1 >= M - i) {
					result[i - 1][j] = arr[i][j];
				}
				// 윗쪽 변
				else  {
					result[i][j - 1] = arr[i][j];
				}
				
				
			}
		}
		return result;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
