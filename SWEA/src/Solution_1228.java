
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * SWEA 1228 [S/W 문제해결 기본] 8일차 - 암호문 1
 * 
 * @author hrlim
 * @version 1.0, 2022.08.08
 */
public class Solution_1228 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for (int test_case = 1; test_case <= 10; test_case++) {
			int N = stringToInt(br.readLine());

			List<String> list = new ArrayList<>();

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				list.add(st.nextToken());
			}

			int commandN = stringToInt(br.readLine());
			String[] commands = br.readLine().split("I");
			for (int count = 1; count <= commandN; count++) {
				st = new StringTokenizer(commands[count]);

				int x = stringToInt(st.nextToken());
				
				int y = stringToInt(st.nextToken());

				for (int i = x; i < y + x; i++) {
					list.add(i, st.nextToken());
				}
			}
			sb.append("#").append(test_case);

			int loop = 10;
			if (list.size() < 10) {
				loop = list.size();
			}
			
			for (int i = 0; i < loop; i++) {
				sb.append(" " + list.get(i));
			}
			
			sb.append("\n");
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