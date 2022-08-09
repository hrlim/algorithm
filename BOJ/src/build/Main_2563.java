package build;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * BOJ 2563 색종이 비밀번호
 * 
 * @author hrlim
 * @version 1.0, 2022.08.09
 */
public class Main_2563 {

	public static int[] pivot;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = stringToInt(st.nextToken());
		int[][] map = new int[100][100];

		for (int text_case = 1; text_case <= T; text_case++) {
			st = new StringTokenizer(br.readLine());
			int x = stringToInt(st.nextToken());
			int y = stringToInt(st.nextToken());
			for (int i = 0; i < 10 ; i++) {
				for (int j = 0; j < 10; j++) {
					map[x + i][y + j] = 1;
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < 100 ; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[i][j] == 1) {
					sum ++;
				}
			}
		}
		
		System.out.println(sum);
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
