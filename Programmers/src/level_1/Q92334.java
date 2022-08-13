package level_1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * <b>신고 결과 받기</b>
 * https://school.programmers.co.kr/learn/courses/30/lessons/92334
 * 
 * @author hrlim
 * @version 수정 2022.08.14
 */
public class Q92334 {

	public static void main(String[] args) {
		String[] id_list = new String[] { "muzi", "frodo", "apeach", "neo" };
		String[] report = new String[] { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
		int[] result = solution(id_list, report, 2);
		
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];

		// reporter 신고자, Set<reported> 신고당한자
		Map<String, HashSet<String>> reportMap = new HashMap<>();

		// reported (신고) 당한 사람의 카운트 Map
		Map<String, Integer> reportedCountMap = new HashMap<>();
		for (int i = 0; i < report.length; i++) {
			String reporter = report[i].split(" ")[0];
			String reported = report[i].split(" ")[1];

			boolean FLAG = false;
			if (reportMap.containsKey(reporter)) {
				if (reportMap.get(reporter).contains(reported)) {
					FLAG = true;
				} else {
					HashSet<String> anotherReported = reportMap.get(reporter);
					anotherReported.add(reported);
					reportMap.put(reporter, anotherReported);
				}

			} else {
				reportMap.put(reporter, new HashSet<String>() {{ add(reported); }});
			}

			if (FLAG) {
				continue;
			} else if (reportedCountMap.containsKey(reported)) {
				reportedCountMap.put(reported, reportedCountMap.get(reported) + 1);
			} else {
				reportedCountMap.put(reported, 1);
			}
		}

		for (String reported : reportedCountMap.keySet()) {
			int reportedCount = reportedCountMap.get(reported);
			if (reportedCount >= k) {
				// 메일 발송 대상
				for (int i = 0; i < id_list.length; i++) {
					if (reportMap.get(id_list[i]) == null) {
						continue;
					} else if(reportMap.get(id_list[i]).contains(reported)) {
						answer[i]++;
					}
				}
			}
		}

		return answer;
	}

}
