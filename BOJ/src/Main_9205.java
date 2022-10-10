import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 맥주 마시면서 걸어가기 - 실버 1
 * 
 * @author hrlim
 * @version 1.0, 2022.10.11
 *
 */
public class Main_9205 {

	private static class Coordinate{
		int row, col;
		public Coordinate(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static Coordinate departure, destination;
	private static Coordinate[] convenienceStores;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = stringToInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = stringToInt(br.readLine()); // 편의점 갯수
			
			st = new StringTokenizer(br.readLine());
			departure = new Coordinate(stringToInt(st.nextToken()), stringToInt(st.nextToken()));
			
			convenienceStores = new Coordinate[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				convenienceStores[i] = new Coordinate(stringToInt(st.nextToken()), stringToInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			destination = new Coordinate(stringToInt(st.nextToken()), stringToInt(st.nextToken()));
			
			System.out.println(goFestival());
		}
	}
	
	private static String goFestival() {
		Queue<Coordinate> queue = new LinkedList<>();
		queue.add(departure);
		boolean[] isVisited = new boolean[convenienceStores.length];
		
		while(!queue.isEmpty()) {
			Coordinate curPosition = queue.poll();
			if(getDistance(curPosition.row, curPosition.col, destination.row, destination.col) <= 1000) 
				return "happy";
			for (int i = 0; i < convenienceStores.length; i++) {
				if(isVisited[i]) continue;
				Coordinate convenience = convenienceStores[i];
				if(getDistance(curPosition.row, curPosition.col, convenience.row, convenience.col) <= 1000) {
					isVisited[i] = true;
					queue.add(convenience);
				}
			}
		}
		
		return "sad";
	}

	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
