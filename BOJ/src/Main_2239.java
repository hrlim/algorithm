import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * 스도쿠 - 골드4
 * 
 * @author hrlim
 * @version 1.0, 2022.10.10
 */
public class Main_2239 {

	private static int[][] map;
	private static boolean isFinished;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new int[9][9];
		
		for (int i = 0; i < 9; i++) {
			String input = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		playSudoku(0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	private static void playSudoku(int index) {
	
		if(index == 81) {
			isFinished = true;
			return;
		}
		
		int row = index / 9;
		int col = index % 9;
		
		if(map[row][col] == 0) {
			for (int inputNum = 1; inputNum <= 9; inputNum++) {
				if(spaceCheck(row, col, inputNum) && rowCheck(row, inputNum) && colCheck(col, inputNum)) {
					map[row][col] = inputNum;
					playSudoku(index + 1);
					if(isFinished) return;
					map[row][col] = 0;
					
				}
			}
		} else {
			playSudoku(index + 1);
		}
		
	}
	
	private static boolean spaceCheck(int row, int col, int inputNum) {
		int pivotRow = row / 3;
		int pivotCol = col / 3;
		
		for (int i = pivotRow * 3; i < (pivotRow * 3)  + 3; i++) {
			for (int j = pivotCol * 3 ; j < (pivotCol * 3) + 3; j++) {
				if(map[i][j] == inputNum) return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 스도쿠의 가로(행) 확인
	 * @param row
	 * @param col
	 * @param inputNum
	 * @return
	 */
	private static boolean rowCheck(int row, int inputNum) {
		for (int i = 0; i < map[row].length; i++) {
			if (map[row][i] == inputNum) return false;
		}
		return true;
	}

	/**
	 * 스도쿠의 세로(열) 확인
	 * @param row
	 * @param col
	 * @param inputNum
	 * @return
	 */
	private static boolean colCheck(int col, int inputNum) {
		for (int i = 0; i < map.length; i++) {
			if (map[i][col] == inputNum) return false;
		}
		return true;
	}
}
