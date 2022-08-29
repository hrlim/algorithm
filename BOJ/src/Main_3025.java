import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3025 {

	static char[][] map; // 현재 map 의 정보 (R * C)
	static int R, C, N; // map 의 크기 R, C, 날아올 화산탄의 갯수 N
	static int[] availRows; // 해당 가늫한 행의 높이를 빠르게 적용하기 위한 변수
							// 인덱스는 해당열을 의미하고, 값은 가능한 행의 높이를 의미
	static int[] stones; // 돌의 위치 정보
						 // 인덱스는 해당열을 의미하고, 돌의 위치(행의 위치가 가장 작은) 를 의미

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위함
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 출력을 하기 위함

		StringBuilder sb = new StringBuilder(); // 문자열을 더하여 출력하기 위함
		StringTokenizer st = new StringTokenizer(br.readLine()); // 입력받은 문자를 " "단위로 분리

		R = stringToInt(st.nextToken()); // map의 행크기
		C = stringToInt(st.nextToken()); // map의 열크기
		map = new char[R][C]; // map 크기 지정
		availRows = new int[C]; // 해당가능한 행의 높이를 빠르게 적용하기 위한 변수 초기화
		stones = new int[C]; // 돌의 위치 정보

		Arrays.fill(availRows, R - 1);
		Arrays.fill(stones, -1);

		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j); // map 에 값 저장
				if (map[i][j] == 'X' && stones[j] == -1) {
					stones[j] = i;
				}
			}
		}

		for (int i = 0; i < C; i++) {
			if (availRows[i] - stones[i] < 2) {
				availRows[i] = stones[i] - 1;
			}
		}

		N = stringToInt(br.readLine()); // 화산탄의 갯수

		for (int i = 0; i < N; i++) {
			int col = stringToInt(br.readLine()) - 1;
			if (stones[col] != -1) {
				dropRock(stones[col] - 1, col);
			} else {
				dropRock(availRows[col], col);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	static boolean dropRock(int row, int col) {

		if (!isRange(row, col)) return false;

		if (row == R - 1) {
			map[row][col] = 'O';
			changeAvailRow(col);
			return true;
		}

		int dRow = row, dCol = col;
		while(true) {
			
			if (dRow + 1 < R && map[dRow + 1][dCol] == 'X') {
				stones[dCol] -= 1;
				break;
			}
			
			else if (isRange(dRow, dCol - 1) && map[dRow][dCol - 1] == '.'
					&& isRange(dRow + 1, dCol - 1) && map[dRow + 1][dCol - 1] == '.') {
				dCol--;
			} 
			// 오른쪽 가능한지 확인
			else if (isRange(dRow, dCol + 1) && map[dRow][dCol + 1] == '.' 
					&& isRange(dRow + 1, dCol + 1) && map[dRow + 1][dCol + 1] == '.') {
				dCol++;
			}
			else break;
			dRow = availRows[dCol];
		}

		map[dRow][dCol] = 'O';
		changeAvailRow(dCol);
		return true;
		
	}

	static void changeAvailRow(int col) {
		if (stones[col] != -1 && isRange(availRows[col] - 2, col) && map[availRows[col] - 2][col] == 'X') {
			availRows[col] = stones[col] - 1;
		} else if(stones[col] == availRows[col]) {
			availRows[col] -= 1;
		}
	    else {
			availRows[col] -= 1;
		}
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

	static boolean isRange(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}
}
