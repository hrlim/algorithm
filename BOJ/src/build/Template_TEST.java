package build;
import java.io.*;
import java.util.StringTokenizer;

public class Template_TEST {
	
	public static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = stringToInt(st.nextToken());
        int M = stringToInt(st.nextToken());
        int R = stringToInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j <M; j++) {
				map[i][j] = stringToInt(st.nextToken());
			}
		}
    }

    static void rotation90() {
    	int tmp = 0;
    	for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {

				// 왼쪽 위 꼭지점
				if (j - 1 < i && i + 1 <= map.length - i) {
					map[i + 1][j] = map[i][j];
				}
				// 왼쪽 아래 꼭지점
				if(i + 1 >= map.length - i && ) {
					// i = 3, lenght = 4
					// 4 >= map.legth - i
					// i = 2, length = 4 
					// 3 >= 2
					// i = 2, j 
					map[i][j + 1] = map[i][j];
				}
				// 오른쪽 아래 꼭지점
				if( j + 1 >= map[i].length - i) {
					map[i-1][j] = map[i][j];
				}
				// 오른쪽 위 꼭지점
				if( i - 1 < j) {
					map[i][j - 1] = map[i][j];
				}
			}
		}
    }
    
    static int stringToInt(String s){
        return Integer.parseInt(s);
    }
    
    
    
//	if(j - 1 < i && i + 1 >= map.length) {
//		tmp = map[i + 1][j];
//		map[i + 1][j] = map[i][j];
//	} else if (j - 1 < i) {
//		map[i + 1][j] = map[i][j];
//	}




}
