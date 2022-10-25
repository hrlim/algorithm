import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 가스관 - 골드 2
 * 
 * @author hrlim
 *
 */
public class Main_2931_진행중50 {

	private static class Position {
		int row, col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static class Pipe extends Position {
		
		char type;
		
		public Pipe(int row, int col, char type) {
			super(row, col);
			this.type = type;
		}
	}

	private static int R, C;
	private static int answerRow, answerCol;
	private static char answerPipe;
	private static Position start, end;
	
	private static char[][] map;
	private static int[][] moveCount;
	
	private static char[] pipeTypes = {'|', '-', '1', '2', '3', '4'};
	private static final int UP = 0, DOWN = 1, LEFT =2, RIGHT = 3;
	
	// 상 하 좌 우
	private static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = stringToInt(st.nextToken());
		C = stringToInt(st.nextToken());
		map = new char[R][C];
		moveCount = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'M') start = new Position(i, j);
				if (map[i][j] == 'Z') end = new Position(i, j);
			}
		}
		goKindergarden();
		System.out.println((answerRow + 1) + " " + (answerCol + 1) + " " + answerPipe);
	}
	
	/**
	 * 지은이가 지운 칸 정보 채우는 메소드
	 * @return
	 */
	public static Position fillEmptyPosition(Position curr, boolean[][] isVisited) {
		Position empty = null;
		List<Position> availableNearbyPosition = new LinkedList<>();
		List<Pipe> availablePipeType = new ArrayList<>();
		
		for (int i = 0; i < dirs.length; i++) {
			int dx = curr.row + dirs[i][0];
			int dy = curr.col + dirs[i][1];
			
			if(!isRange(dx, dy)) continue;
			if(isVisited[dx][dy]) continue;
			if(map[dx][dy] != '.') continue;
			
			availableNearbyPosition.add(new Position(dx, dy));
		}
		
		for (int i = 0; i < availableNearbyPosition.size(); i++) {
			Position temp = availableNearbyPosition.get(i);
			for (int j = 0; j < pipeTypes.length; j++) {
				map[temp.row][temp.col] = pipeTypes[j];
				if(getNextPosition(curr, temp.row, temp.col, false) != null) {
					for (int dir = 0; dir < dirs.length; dir++) {
						int dx = temp.row + dirs[dir][0];
						int dy = temp.col + dirs[dir][1];
						if(!isRange(dx, dy)) continue;
						if(isVisited[dx][dy]) if(map[dx][dy] != '+') continue;
						if(map[dx][dy] == '.') continue;
						if(getNextPosition(temp, dx, dy, false) == null) continue;
						availablePipeType.add(new Pipe(temp.row, temp.col, pipeTypes[j]));
					}
				}
			}
		}
		
		
		
		if(availablePipeType.size() == 1) {

			empty = new Position(answerRow, answerCol);
			answerPipe = availablePipeType.get(0).type;

			answerRow = availablePipeType.get(0).row;
			answerCol = availablePipeType.get(0).col;
			map[answerRow][answerCol] = answerPipe;
		} else if (availablePipeType.size() > 1){
			empty = new Position(answerRow, answerCol);
			answerPipe = '+';
			answerRow = availablePipeType.get(0).row;
			answerCol = availablePipeType.get(0).col;
			map[answerRow][answerCol] = answerPipe;
			getNextPosition(curr, answerRow, answerCol, true);
		}
		
		
 		return empty;
	}
	

	/**
	 * 유치원으로 이동하기
	 * @return
	 */
	public static void goKindergarden() {
		Queue<Position> queue = new LinkedList<>();
		boolean[][] isVisited = new boolean[R][C];
		
		queue.add(start);
		isVisited[start.row][start.col] = true;
		
 		Loop : while (!queue.isEmpty()) {
			Position curr = queue.poll();
			int currRow = curr.row;
			int currCol = curr.col;
			
			boolean isNotErase = true;
			for (int dir = 0; dir < dirs.length; dir++) {
				int dx = currRow + dirs[dir][0];
				int dy = currCol + dirs[dir][1];
				
				if(dx == end.row && dy == end.col) break Loop;
				if(!isRange(dx, dy)) continue;
				
				if(map[dx][dy] == '+' && moveCount[dx][dy] == 3) continue; 
				if((moveCount[dx][dy] == 2 && dir == UP) || (moveCount[dx][dy] == 2 && dir == DOWN)) continue;
				if((moveCount[dx][dy] == 1 && dir == LEFT) || (moveCount[dx][dy] == 1 && dir == RIGHT)) continue;
				
				if (isVisited[dx][dy]) if(map[dx][dy] != '+') continue;
				if(map[dx][dy] == '.') continue;
				Position nextPosition = getNextPosition(curr, dx, dy, true);
				if(nextPosition == null) continue;
				isVisited[dx][dy] = true;
				queue.add(nextPosition);
				isNotErase = false;
				break;
			}
			
			if(isNotErase) {
				Position empty = fillEmptyPosition(curr, isVisited);
				if(empty != null) {
					queue.add(empty);
					isVisited[empty.row][empty.col] = true;
				}
			}
		}
	}

	private static Position getNextPosition(Position curr, int dx, int dy, boolean isCheck) {
		Position nextPosition = null;
		
		char currPipeType = map[curr.row][curr.col];
		char pipeType = map[dx][dy];

		// 파이프의 위치가 상 하 좌 우
		if (curr.row > dx && curr.col == dy) {
			if(currPipeType == 'M' || currPipeType == '|' || currPipeType == '+' || currPipeType == '2' || currPipeType == '3')
			if (pipeType == '|' || pipeType == '1' || pipeType == '4') {
				nextPosition = new Position(curr.row - 1, curr.col);
			} 
			else if(pipeType == '+') {
				if(moveCount[dx][dy] == 1 || moveCount[dx][dy] == 0) {
					if(isCheck) {
						moveCount[dx][dy] += 2;
					}
					nextPosition = new Position(curr.row - 1, curr.col);
					
				} 
			}
		} else if (curr.row < dx && curr.col == dy) {
			if(currPipeType == 'M' || currPipeType == '|' || currPipeType == '+' || currPipeType == '1' || currPipeType == '4')
			if (pipeType == '|' || pipeType == '2' || pipeType == '3') {
				nextPosition = new Position(curr.row + 1, curr.col);
			} 
			else if(pipeType == '+') {
				if(moveCount[dx][dy] == 1 || moveCount[dx][dy] == 0) {
					if(isCheck) {
						moveCount[dx][dy] += 2;
					}
					nextPosition = new Position(curr.row + 1, curr.col);
				} 
			}
		} else if (curr.row == dx && curr.col > dy) {
			if(currPipeType == 'M' || currPipeType == '-' || currPipeType == '+' || currPipeType == '3' || currPipeType == '4')
			if (pipeType == '-' || pipeType == '1' || pipeType == '2') {
				nextPosition = new Position(curr.row, curr.col - 1);
			} 
			else if(pipeType == '+') {
				if(moveCount[dx][dy] == 2 || moveCount[dx][dy] == 0) {
					if(isCheck) {
						moveCount[dx][dy] += 1;
					}
					nextPosition = new Position(curr.row, curr.col - 1);
				} 
			}
		} else if (curr.row == dx && curr.col < dy) {
			if(currPipeType == 'M' || currPipeType == '-' || currPipeType == '+' || currPipeType == '1' || currPipeType == '2')
			if (pipeType == '-' ||  pipeType == '3' || pipeType == '4') {
				nextPosition = new Position(curr.row, curr.col + 1);
			} 
			else if(pipeType == '+') {
				if(moveCount[dx][dy] == 2 || moveCount[dx][dy] == 0) {
					if(isCheck) {
						moveCount[dx][dy] += 1;
					}
					nextPosition = new Position(curr.row, curr.col + 1);
				} 
			}
		}

		return nextPosition;
	}
	
	private static boolean isRange(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}

	private static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}