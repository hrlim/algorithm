package level_3;

/**
 * <b>파괴되지 않은 건물</b>
 * https://school.programmers.co.kr/learn/courses/30/lessons/92344
 * 
 * @author hrlim
 * @version 생성 2023.01.05
 */
public class Q92344 {

	public static void main(String[] args) {
		// 0 : 양, 1 : 늑대
		int[][] board = { { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 } };
		int[][] skill = { { 1, 0, 0, 3, 4, 4 }, { 1, 2, 0, 2, 3, 2 }, { 2, 1, 0, 3, 1, 2 }, { 1, 0, 1, 3, 3, 1 } };
		System.out.println(solution(board, skill));
	}
	
	/**
	 * 적의 공격 혹은 아군의 회복 스킬이 모두 끝난 뒤 파괴되지 않은 건물의 개수를 리턴하는 메소드
	 * @param board
	 * @param skill [type, r1, c1, r2, c2, degree] 
	 * @return
	 */
	public static int solution(int[][] board, int[][] skill) {
	
		for (int[] unitSkill : skill) {
			int type = unitSkill[0];
			int startRow = unitSkill[1];
			int startCol = unitSkill[2];
			int endRow = unitSkill[3];
			int endCol = unitSkill[4];
			int degree = (type == 1) ? -unitSkill[5] : unitSkill[5];
			
			for (int i = unitSkill[1]; i <= unitSkill[3]; i++) {
				for (int j = unitSkill[2] ; j <= unitSkill[4]; j++) {
					if(type == 1)  { // 적군
						board[i][j] -= unitSkill[5];
					} else {
						board[i][j] += unitSkill[5];
					}
				}
			}
		}
		return getSafeBuildingCount(board);
	}
	
	/**
	 * 파괴되지 않는 건물의 개수를 리턴하는 메소드
	 * @param board
	 * @return
	 */
	public static int getSafeBuildingCount(int[][] board) {
		int result = 0;
		for (int[] row : board) {
			for (int cell : row) {
				if(cell > 0) {
					result++;
				}
			}
		}
		return result;
	}

}
