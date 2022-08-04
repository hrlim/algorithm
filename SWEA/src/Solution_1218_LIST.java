import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Solution_1218_LIST {

	public final static String brackets = "()[]{}<>";

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();

		int T = 10;
		List<Integer> stack = new ArrayList<>();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = stringToInt(br.readLine());
			String input = br.readLine();

			// 0 : 유효하지 않음, 1 : 유효함
			int isValid = 1;
			for (int i = 0; i < N; i++) {
				char c = input.charAt(i);
				int pos = brackets.indexOf(c);
				if (pos % 2 == 0) {
					stack.add(pos);
				} else {
					if (stack.size() > 0 && stack.get(stack.size() - 1) + 1 == pos) {
						stack.remove(stack.size() - 1);
					} else {
						isValid = 0;
						break;
					}
				}
			}
			sb.append("#" + test_case + " " + isValid + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}
