import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_4 {
	public static void main(String[] args) {
//		int[][] jobs = { { 1, 5, 2, 3 }, { 2, 2, 3, 2 }, { 3, 1, 3, 3 }, { 5, 2, 1, 5 }, { 7, 1, 1, 1 }, { 9, 1, 1, 1 },
//				{ 10, 2, 2, 9 } };

//		int[][] jobs = { { 1, 2, 1, 5 }, { 2, 1, 2, 100 }, { 3, 2, 1, 5 }, { 5, 2, 1, 5 } };

//		int[][] jobs = { { 0, 2, 3, 1 }, { 5, 3, 3, 1 }, { 10, 2, 4, 1 } };

		int[][] jobs = { { 0, 5, 1, 1 }, { 2, 4, 3, 3 }, { 3, 4, 4, 5 }, { 5, 2, 3, 2 } };
		System.out.println(Arrays.toString(solution(jobs)));

	}

	public static int[] solution(int[][] jobs) {
		int[] answer = {};
		List<Job>[] jobList = new ArrayList[101];
		int[] importantSum = new int[101];

		for (int i = 0; i < jobList.length; i++) {
			jobList[i] = new ArrayList<>();
		}

		List<Integer> finishedNo = new ArrayList<>();

		int index = 0;
		int curTime = -1;
		int curWorkNo = -1;
		int workTime = 0;
		Job curJob = null;
		int completeJobCount = 0;
		while (true) {
			if (completeJobCount == jobs.length) {
				break;
			}

			curTime++;

			if (curJob != null) {
				workTime++;
			}

			if (curJob != null && curJob.jobTime == workTime) {
				curJob = null;
				completeJobCount++;
			}

			if (index < jobs.length) {
				int[] jobItem = jobs[index];
				int requestTime = jobItem[0];
				int jobTime = jobItem[1];
				int no = jobItem[2];
				int important = jobItem[3];

				if (requestTime <= curTime) {
					jobList[no].add(new Job(requestTime, jobTime, no, important));
					importantSum[no] += important;
					index++;
				}
			}

			if (curWorkNo == -1) {
				curWorkNo = getWorkNo(importantSum);
				if (curWorkNo != -1 && jobList[curWorkNo].size() != 0) {
					curJob = jobList[curWorkNo].remove(0);
					importantSum[curWorkNo] -= curJob.important;
					workTime = 0;
				}
			}

			if (curJob == null) {
				if (curWorkNo != -1 && jobList[curWorkNo].size() != 0) {
					curJob = jobList[curWorkNo].remove(0);
					importantSum[curWorkNo] -= curJob.important;
					workTime = 0;
				} else {
					if (curWorkNo != -1)
						finishedNo.add(curWorkNo);
					curWorkNo = getWorkNo(importantSum);
					if (curWorkNo != -1 && jobList[curWorkNo].size() != 0) {
						curJob = jobList[curWorkNo].remove(0);
						importantSum[curWorkNo] -= curJob.important;
						workTime = 0;
					}
				}
			}

		}

		for (int i = finishedNo.size() - 1; i > 0; i--) {
			if (finishedNo.get(i) == finishedNo.get(i - 1)) {
				finishedNo.remove(i);
			}
		}

		answer = new int[finishedNo.size()];
		for (int i = 0; i < finishedNo.size(); i++) {
			answer[i] = finishedNo.get(i);
		}

		return answer;
	}

	public static int getWorkNo(int[] importantSum) {
		List<Integer> anwser = new ArrayList<>();

		int maxCount = -1;
		for (int i = 1; i < importantSum.length; i++) {
			int important = importantSum[i];
			if (important == maxCount) {
				anwser.add(i);
			} else if (important > maxCount) {
				maxCount = important;
				anwser.clear();
				anwser.add(i);
			}
		}
		Collections.sort(anwser);

		if (anwser.size() == 0 || maxCount == 0) {
			return -1;
		}

		return anwser.get(0);
	}

	public static class Job implements Comparable<Job> {
		int requestTime; // 요청시간
		int jobTime; // 작업소요시간
		int no; // 작업 분류 번호
		int important; // 중요도

		public Job(int requestTime, int jobTime, int no, int important) {
			this.requestTime = requestTime;
			this.jobTime = jobTime;
			this.no = no;
			this.important = important;
		}

		@Override
		public int compareTo(Job job) {
			if (this.important == job.important) {
				return job.no - this.no;
			}

			return this.important - job.important;
		}
	}

}
