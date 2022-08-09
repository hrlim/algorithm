import java.io.*;
import java.util.StringTokenizer;

/**
 * SWEA 6808 규영이와 인영이의 카드게임
 * 
 * @author hrlim
 * @version 1.0, 2022.08.08
 */
public class Solution_6808 {

	// 규영이 카드
	public static int[] Acards = new int[9];

	public static int winCnt = 0;
	public static int loseCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int[] Bcards = new int[9];
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				Acards[i] = Integer.parseInt(st.nextToken());
			}

			int index = 0;
			for (int i = 1; i <= 18; i++) {
				boolean isBcard = true;
				for (int j = 0; j < Acards.length; j++) {
					if (Acards[j] == i) {
						isBcard = false;
						break;
					}
				}
				if (isBcard) {
					Bcards[index] = i;
					index++;
				}
			}
			
			perm(Bcards, new int[9], new boolean[9], 0);
			
			sb.append("#" + tc + " " + winCnt + " " + loseCnt + "\n");
			winCnt = 0;
			loseCnt = 0;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void perm(int[] Bcards, int[] output, boolean[] isSelected, int cnt) {
		if (cnt == Bcards.length) {
			int res = fight(output);
			if (res == 1) {
				winCnt++;
			} else if (res == 2) {
				loseCnt++;
			}
			return;
		}

		for (int i = 0; i < Bcards.length; i++) {
			if (isSelected[i])
				continue;
			output[cnt] = Bcards[i];
			isSelected[i] = true;
			perm(Bcards, output, isSelected, cnt + 1);
			isSelected[i] = false;
		}
	}

	static int fight(int[] Bcards) {
		int Ascore = 0;
		int Bscore = 0;
		for (int i = 0; i < 9; i++) {
			if (Acards[i] > Bcards[i]) {
				Ascore += Acards[i] + Bcards[i];
			} else if (Bcards[i] > Acards[i]) {
				Bscore += Bcards[i] + Acards[i];
			}
		}
		if (Ascore > Bscore) {
			return 1;
		} else if (Bscore > Ascore) {
			return 2;
		} else {
			return 3;
		}
	}
}