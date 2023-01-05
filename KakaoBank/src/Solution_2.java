
public class Solution_2 {

	static int[] parents;

	static void makeSet(int length) {

		parents = new int[length];

		for (int i = 0; i < length; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int num) {
		if (parents[num] == num)
			return num;
		return parents[num] = findSet(parents[num]);
	}

	static boolean union(int num1, int num2) {
		int set1 = findSet(num1);
		int set2 = findSet(num2);

		if (set1 == set2)
			return false;

		parents[set2] = set1;
		return true;
	}

	public static void main(String[] args) {

//		int[] rooms = { 3, 1, 2, 4 };

		int[] rooms = { 2, 3, 4, 5, 1 };

//		int[] rooms = { 1, 2, 3, 4, 5, 6 };

		System.out.println(solution(rooms));

	}

	public static int solution(int[] rooms) {
		makeSet(rooms.length + 1);

		int answer = 0;
		for (int i = 0; i < rooms.length; i++) {
			if (union(i + 1, rooms[i])) {
				answer++;
			}
		}

		if (rooms.length - answer <= 1) {
			answer = 1;
		} else {
			answer = rooms.length - answer - 1;
		}

		return answer;
	}

}
