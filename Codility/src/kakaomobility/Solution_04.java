package kakaomobility;

import java.util.Arrays;

public class Solution_04 {

	public static void main(String[] args) {
//		int N = 2;
//		String S = "1A 2F 1C";

		int N = 22;
		String S = "1A 3C 2B 20G 5A";

		System.out.println(solution(N, S));
	}

	public static int solution(int N, String S) {
		int answer = 0;
		
		// 복도 2개 포함한 좌석
		// 0 : 가능한 좌석, 1 : 예약된 좌석, 2 : 복도
		int[][] seats = new int[N][12];
		int[] hallways = {3, 8};
		
		// 복도자리 표기
		for (int row = 0; row < N; row++) {
			for (int hallIndex = 0; hallIndex < hallways.length; hallIndex++) {
				seats[row][hallways[hallIndex]] = 2;
			}	
		}
		
		// 예약 완료된 좌석 표기
		if (!S.isEmpty()) {
			String[] reservedSeats = S.split(" ");

			for (String reservedSeat : reservedSeats) {
				int row = Integer.parseInt(reservedSeat.substring(0, reservedSeat.length() - 1)) - 1;
				int col = reservedSeat.charAt(reservedSeat.length() - 1) - 'A';
				seats[row][col] = 1;
			}

		}
		
		// 4명의 가족이 앉을 수 있는 경우의 수 체크
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < 10 - 4; col++) {
				boolean isAvailableGroup = true;
				if(seats[row][col] != 0) continue;
				for (int familyCnt = 0; familyCnt < 4; familyCnt++) {
					if (seats[row][col + familyCnt] == 2) {
						if(familyCnt < 2) {
							isAvailableGroup = false;
							break;
						}
					} else if(seats[row][col + familyCnt] == 1) {
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