package perm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * BOJ 2023 신기한 소수
 * 
 * @author hrlim
 * @version 1.0, 2022.08.05
 */
public class Main_2023 {
	public static List<String> combination = new ArrayList<>();

	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		//long startTime = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = stringToInt(br.readLine());

		int[] primeArr = { 2, 3, 5, 7 };
		int[] oddArr = { 1, 3, 7, 9 };

		for (int primeIndex = 0; primeIndex < primeArr.length; primeIndex++) {
			int firstPrime = primeArr[primeIndex];
			getPerm(oddArr, new int[T], 0, T - 1, firstPrime);
		}

		// long endTime = System.currentTimeMillis() - startTime;
		// System.out.println(endTime + " 시간 걸립니다.");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	public static int stringToInt(String s) {
		return Integer.parseInt(s);
	}

	public static boolean isPrime(int n) {
		for (int i = 2; i <= (int) Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	static void getPerm(int[] arr, int[] output, int depth, int r, int firstPrime) {
		String res = String.valueOf(firstPrime);
		for (int i = 0; i < depth; i++) {
			res += String.valueOf(output[i]);
		}

		if (!isPrime(stringToInt(res))) {
			return;
		}

		if (depth == r) {
			sb.append(res).append("\n");
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			output[depth] = arr[i];
			getPerm(arr, output, depth + 1, r, firstPrime);
		}
	}

}
