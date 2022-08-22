import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 암호 만들기 - 골드 5 <b>조합론, 백트래킹, 브루트포스 알고리즘</b>
 * 
 * @author hrlim
 * @version 1.0, 2022.08.22
 *
 */
public class Main_1759 {

	static char[] alpha;
	static int L, K;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = stringToInt(st.nextToken());
		K = stringToInt(st.nextToken());

		alpha = new char[K];

		String input = br.readLine();
		for (int i = 0, index = 0; i < input.length(); i += 2, index++) {
			alpha[index] = input.charAt(i);
		}

		Arrays.sort(alpha);
		comb(0, 0, 0, 0, new char[L]);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	/**
	 * N 개의 배열에서 R 개의 조합을 추출하여 규칙 확인
	 * @param cnt
	 * @param start
	 * @param vowelSum
	 * @param etcSum
	 * @param arr
	 */
	static void comb(int cnt, int start, int vowelSum, int etcSum, char[] arr) {

		if (cnt == L) {
			if (vowelSum >= 1 && etcSum >= 2) {
				for (int i = 0; i < arr.length; i++) {
					sb.append(arr[i]);
				}
				sb.append("\n");
			}
			return;
		}
		
		for (int i = start; i < alpha.length; i++) {
			arr[cnt] = alpha[i];
			if (alpha[i] == 'a' || alpha[i] == 'e' || alpha[i] == 'i' || alpha[i] == 'o' || alpha[i] == 'u')
				comb(cnt + 1, i + 1, vowelSum + 1, etcSum, arr);
			else
				comb(cnt + 1, i + 1, vowelSum, etcSum + 1, arr);
		}
		
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

}
