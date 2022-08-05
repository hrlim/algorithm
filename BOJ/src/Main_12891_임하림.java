import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 
 * BOJ 2023 신기한 소수
 * 
 * @author hrlim
 * @version 1.0, 2022.08.05
 */
public class Main_12891_임하림 {

	public static void main(String[] args) throws IOException {

		long startTime = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = stringToInt(br.readLine());

		int start = (int) Math.pow(10, T - 1);
		int end = (int) Math.pow(10, T);

		for (int i = start + start; i < end; i+=2) {
			int compare = i / start;
			if (compare == 2 || compare == 3 || compare == 5 || compare == 7) {
				boolean check = true;

				for (int j = 0; j < T; j++) {
					int temp = i / (int) Math.pow(10, j);
					if (!isPrime(temp)) {
						check = false;
						break;
					}
				}

				if (check) {
					sb.append(i).append("\n");
				}
			}

		}
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println(endTime + " 시간 걸립니다.");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	public static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

	public static boolean isPrime(int n) {
		if (n % 2 == 0)
			return false;
		for (int i = 2; i <= (int) Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

}
