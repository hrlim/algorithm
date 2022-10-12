package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 파이프 옮기기1 - 골드5 DP
 * 
 * @author hrlim
 * @version 1.0, 2022.09.30
 */
public class Main_17070_임하림 {

	// 가로, 세로, 대각선
	private int[][][] dirs3 = { { { 1, 0 }, { 1, 1 } }, { { 0, 1 }, { 1, 1 } }, { { 1, 0 }, { 0, 1 }, { 1, 1 } } };

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		
		int N = stringToInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stringToInt(st.nextToken());
			}
		}
		
		// X좌표(가로), Y좌표, 방향(0: 가로, 1: 대각선, 2: 세로)
		int[][][] caseCount = new int[N + 1][N + 1][3];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				
//				if(map[i][j] != 1) {
//					
//				}
//				// 가로로 오는 경우  : 이전 (가로, 대각선)
//				caseCount[i][j][0] = caseCount[i - 1][j][0] + caseCount[i - 1][j][1];
//				
//				if(map[i -1 ][])
//				// 대각선으로 오는 경우 : 이전 (가로, 대각선, 세로)
//				caseCount[i][j][1] = caseCount[i - 1][j - 1][1] + caseCount[i - 1][j - 1][1] + caseCount[ i  - 1][j - 1][2];
//				
//				
//				// 세로로 오는 경우 : 이전 (대각선, 세로)
//				caseCount[i][j][2] = caseCount[i][j - 1][1] + caseCount[1][j - 1][2];
//				
				
			}
		}
		
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}
