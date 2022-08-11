import java.io.*;

/**
 * 백설 공주와 일곱 난쟁이
 * 
 * @author hrlim
 * @version 1.0, 2022.08.11
 */
public class Main_3040 {

	public static int[] output;
	public static int[] numbers = new int[9];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			numbers[i] = stringToInt(br.readLine());
		}

		comb(new int[7], 0, 0, 0);
		
		for (int num : output) {
			sb.append(num).append(System.lineSeparator());
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void comb(int[] selected, int start, int current, int sum) {
		if (current == selected.length) {
			if(sum == 100)
				output = selected.clone();
			return;
		}
		
		for (int i = start; i < 9; i++) {
			selected[current] = numbers[i];
			comb(selected, i + 1, current + 1, sum + numbers[i]);
		}
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
