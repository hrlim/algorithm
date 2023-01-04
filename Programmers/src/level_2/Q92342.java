package level_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <b>양궁대회</b> https://school.programmers.co.kr/learn/courses/30/lessons/92342
 * 
 * @author hrlim
 * @version 수정 2023.01.04
 */
public class Q92342 {

	public static List<int[]> answers = new ArrayList<int[]>();
	private static int maxDiff = -1;

	public static void main(String[] args) {
		int n = 10;
		int[] info = { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 };

		System.out.println(Arrays.toString(solution(n, info)));
	}

	public static int[] solution(int n, int[] info) {

		perm(n, info, new int[11], 0);
		if (maxDiff == -1) return new int[] { -1 };

		// 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 
		// 가장 낮은 점수를 더 많이 맞힌 경우를 리턴하기 위해 정렬을 수행함
		Collections.sort(answers, (o1, o2) -> {
			for (int i = 10; i >= 0; i--) {
				if (o1[i] != o2[i])
					return o2[i] - o1[i];
			}
			return 0;
		});

		return answers.get(0);
	}

	/**
	 * 중복 조합으로 라이언이 이길 수 있는 경우의 수를 구한다. 
	 * @param n
	 * @param apeachScores
	 * @param candidate
	 * @param curIndex
	 */
	public static void perm(int n, int[] apeachScores, int[] candidate, int curIndex) {

		if (curIndex == n) {
			int diff = diffInCaseWinLion(apeachScores, candidate);
			if (diff > maxDiff) {
				maxDiff = diff;
				answers.clear();
				answers.add(candidate.clone());
			} else if(diff == maxDiff) {
				answers.add(candidate.clone());
			}
			return;
		}

		// 라이언과 어피치의 과녁을 맞춘 횟수가 동일할 경우, 어피치가 점수를 가져감으로 동일한 경우까지만 수행할 수 있도록 조건 달기
		for (int i = 0; i < candidate.length && candidate[i] <= apeachScores[i]; i++) {
			candidate[i]++;
			perm(n, apeachScores, candidate, curIndex + 1);
			candidate[i]--;
		}
	}

	/**
	 * 어피치 점수와 중복조합을 통한 경우에 대한 라이언의 점수의 차이를 구하는 메소드
	 * @param apeachScores
	 * @param candidate
	 * @return
	 */
	public static int diffInCaseWinLion(int[] apeachScores, int[] candidate) {
		int apeachScoreSum = 0;
		int ryanScoreSum = 0;

		for (int i = 0; i < 11; i++) {
			if (apeachScores[i] == 0 && candidate[i] == 0)
				continue;
			if (apeachScores[i] >= candidate[i]) {
				apeachScoreSum += (10 - i);
			} else {
				ryanScoreSum += (10 - i);
			}
		}

		return apeachScoreSum < ryanScoreSum ? ryanScoreSum - apeachScoreSum : -1;
	}

}
