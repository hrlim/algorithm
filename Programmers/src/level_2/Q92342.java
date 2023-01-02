package level_2;

import java.util.Arrays;

/**
 * <b>양궁대회</b> https://school.programmers.co.kr/learn/courses/30/lessons/92342
 * 
 * @author hrlim
 * @version 수정 2023.01.03
 */
public class Q92342 {

	private static int[] answer;
	private static int maxDiff;

	public static void main(String[] args) {
		int n = 10;
		int[] info = { 0,0,0,0,0,0,0,0,3,4,3 };

		System.out.println(Arrays.toString(solution(n, info)));
	}

	public static int[] solution(int n, int[] info) {

		maxDiff = -1;
		perm(n, info, new int[11], 0);
		return maxDiff != -1 ? answer : new int[] { -1 };
	}

	public static void perm(int n, int[] apeachScores, int[] candidate, int curIndex) {

		if (curIndex == n) {
			int diff = diffInCaseWinLion(apeachScores, candidate);
			if (diff > maxDiff) {
				maxDiff = diff;
				answer = candidate.clone();
			}
			return;
		}

		for (int i = 0; i < candidate.length; i++) {
			candidate[i]++;
			perm(n, apeachScores, candidate, curIndex + 1);
			candidate[i]--;
		}
		
		return;
	}

	public static int diffInCaseWinLion(int[] apeachScores, int[] candidate) {
		int apeachScoreSum = 0;
		int lionScoreSum = 0;

		for (int i = 0; i < 11; i++) {
			if (apeachScores[i] == 0 && candidate[i] == 0) continue;
			if (apeachScores[i] >= candidate[i]) {
				apeachScoreSum += (10 - i);
			} else {
				lionScoreSum += (10 - i);
			}
		}

		return apeachScoreSum < lionScoreSum ? lionScoreSum - apeachScoreSum : -1;
	}

}
