import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 치킨 배달
 * 
 * @author hrlim
 * @version 1.0, 2022.08.12
 */
public class Main_15686 {

	private static List<int[]> houseList = new ArrayList<>();
	private static List<int[]> chickenList = new ArrayList<>();
	
	private static int minCityChicken = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stringToInt(st.nextToken());
		int M = stringToInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new  StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stringToInt(st.nextToken());
				if(map[i][j] == 1) {
					houseList.add(new int[]{i, j});
				} else if(map[i][j] == 2) {
					chickenList.add(new int[]{i, j});
				}
			}
		}
		for (int i = 1; i <= M ; i++) {
			comb( 0, i, 0, new int[i][]);
		}
		
		System.out.println(minCityChicken);
	}
	
	static void comb(int current, int max, int start, int[][] output) {
		if(current == max) {
			check(output);
			return;
		}
		
		for (int i = start; i < chickenList.size(); i++) {
			output[current] = chickenList.get(i);
			comb( current + 1, max, i + 1, output);
		}
	}
	
	static void check(int[][] arr) {
		int sum = 0;
		for (int[] house : houseList) {
			int houseR = house[0];
			int houseC = house[1];
			
			int chickenClose = Integer.MAX_VALUE;
			for (int[] chicken : arr) {
				int chickenR = chicken[0];
				int chickenC = chicken[1];
				
				int distance = Math.abs(houseR - chickenR) + Math.abs(houseC - chickenC); 
				chickenClose = Math.min(chickenClose, distance);
			}
			sum += chickenClose;
		}
		if(sum < minCityChicken) {
			minCityChicken = sum;
		}
	}
	
	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
