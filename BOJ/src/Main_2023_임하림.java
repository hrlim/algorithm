import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * BOJ 12891 DNA 비밀번호
 * 
 * @author hrlim
 * @version 1.0, 2022.08.05
 */
public class Main_2023_임하림 {

	public static int[] pivot;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = stringToInt(st.nextToken());
		int P = stringToInt(st.nextToken());

		char[] arr = new char[S];
		String input = br.readLine();
		for (int i = 0; i < S; i++) {
			arr[i] = input.charAt(i);
		}

		pivot = new int[4];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			pivot[i] = stringToInt(st.nextToken());
		}

		// A, C, G, T
		int[] cnt = new int[4];
		int vaildValue = 0;

		for (int i = 0; i < S; i++) {
			

			cnt[getCountIndex(arr[i])]++;
			
			//슬라이딩 윈도우 시작시 빼
			if (i >= P) {
				cnt[getCountIndex(arr[i - P])] -= 1;
			}
			
			// 비밀번호 가능성 확인
			if ((i + 1) / P >= 1) {
				if (isValid(cnt)) {
					vaildValue++;
				}
			}
		}
		System.out.println(vaildValue);
	}

	static boolean isValid(int[] cnt) {
		for (int i = 0; i < 4; i++) {
			if (cnt[i] < pivot[i]) {
				return false;
			}
		}
		return true;
	}

	static int getCountIndex(char c) {
		if (c == 'A')
			return 0;
		if (c == 'C')
			return 1;
		if (c == 'G')
			return 2;
		if (c == 'T')
			return 3;
		return -1;
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
