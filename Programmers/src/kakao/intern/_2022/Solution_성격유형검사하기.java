package kakao.intern._2022;

import java.util.HashMap;
import java.util.Map;

/**
 * Level 1
 * 
 * @author hrlim
 *
 */
public class Solution_성격유형검사하기 {

	public static Map<Character, Integer> pivotCountMap;

	public final static Character[] types = { 'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N' };

	public final static String[] pivots = { "RT", "CF", "JM", "AN" };

	public static void main(String[] args) {
		String[] survey = { "AN", "CF", "MJ", "RT", "NA" };
		int[] choices = { 5, 3, 2, 7, 5 };

		System.out.println(solution(survey, choices));

	}

	public static void init() {
		pivotCountMap = new HashMap<Character, Integer>();
		for (Character type : types) {
			pivotCountMap.put(type, 0);
		}
	}

	public static String solution(String[] survey, int[] choices) {

		init();

		int surveyCount = survey.length;
		for (int i = 0; i < surveyCount; i++) {
			char pivotUnit1 = survey[i].charAt(0);
			char pivotUnit2 = survey[i].charAt(1);

			int choice = choices[i];
			if (choice > 0 && choice < 4) {
				pivotCountMap.put(pivotUnit1, pivotCountMap.get(pivotUnit1) + (4 - choice));
			} else if (choice > 4) {
				pivotCountMap.put(pivotUnit2, pivotCountMap.get(pivotUnit2) + (choice - 4));
			}

		}
		return getAnswer();
	}

	public static String getAnswer() {
		StringBuilder answer = new StringBuilder();

		for (String pivot : pivots) {
			char pivotUnit1 = pivot.charAt(0);
			char pivotUnit2 = pivot.charAt(1);

			int unit1Count = pivotCountMap.get(pivotUnit1);
			int unit2Count = pivotCountMap.get(pivotUnit2);

			if (unit1Count == unit2Count) {
				answer.append(pivotUnit1 < pivotUnit2 ? pivotUnit1 : pivotUnit2);
				continue;
			}
			if (unit1Count > unit2Count) {
				answer.append(pivotUnit1);
			} else {
				answer.append(pivotUnit2);
			}
		}
		return answer.toString();
	}

}
