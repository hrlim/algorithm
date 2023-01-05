package kakaomobility;

import java.util.Arrays;

public class Solution_03 {

	public static void main(String[] args) {
//		int N = 2;
//		String S = "1A 2F 1C";

		int N = 22;
		String S = "1A 3C 2B 20G 5A";

		System.out.println(solution(N, S));
	}

	public static int solution(int N, String S) {
		int answer = 0;
		boolean[][] seats = new boolean[N][10];

		if (!S.isEmpty()) {
			String[] reservedSeats = S.split(" ");

			// 예약 좌석 표기
			for (String reservedSeat : reservedSeats) {
				int row = Integer.parseInt(reservedSeat.substring(0, reservedSeat.length() - 1)) - 1;
				int col = reservedSeat.charAt(reservedSeat.length() - 1) - 'A';
				seats[row][col] = true;
			}

		}
		// 4명의 가족이 앉을 수 있는 경우의 수 체크
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < 10 - 4; col++) {
				boolean isAvailableGroup = true;
				for (int familyCnt = 0; familyCnt < 4; familyCnt++) {
					if (seats[row][col + familyCnt]) {
						isAvailableGroup = false;
						break;
					}
				}

				if (isAvailableGroup && col % 2 == 1) {
					answer++;
					col += 3;
				}
			}
		}

		return answer;
	}
}