import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 나무 높이 - D2
 * 
 * @author hrlim
 * @version 1.0, 2022.10.14
 */
public class Solution_14510_DP {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			int N = sc.nextInt();

			List<Integer> trees = new ArrayList<Integer>();
			int pivotHeight = 0;
			for (int i = 0; i < N; i++) {
				trees.add(sc.nextInt());
				pivotHeight = Math.max(pivotHeight, trees.get(i));
			}

			Collections.sort(trees);

			int time = 0;
			int waterAmount = 0;
			while (removeTree(trees, pivotHeight).size() != 0) {
				
				time++;
				if (time % 2 == 0) {
					waterAmount = 2;
				} else {
					waterAmount = 1;
				}

				int lastIdx = trees.size() - 1;
				int diff = pivotHeight - trees.get(lastIdx);

				if (waterAmount == 1 && diff == 2 && trees.size() == 1) {
					continue;
				} else if (waterAmount == 2 && diff == 1 && trees.size() == 1) {
					continue;
				} else if(trees.get(lastIdx) + waterAmount <= pivotHeight) {
					trees.set(lastIdx, trees.get(lastIdx) + waterAmount);
				} else if(trees.get(0) + waterAmount <= pivotHeight) {
					trees.set(0, trees.get(0) + waterAmount);
				}
				
				Collections.sort(trees);
			}

			System.out.println("#" + test_case + " " + time);
		}

	}

	private static List<Integer> removeTree(List<Integer> trees, int pivotHeight) {
		for (int i = trees.size() - 1; i >= 0; i--) {
			if (trees.get(i) == pivotHeight)
				trees.remove(i);
		}

		return trees;
	}

}
