import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 절대값 힙
 * 
 * @author hrlim
 * @version 1.0, 2022.08.12
 */
public class Main_11286 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();

		Queue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (Math.abs(o1) == Math.abs(o2)) {
					return o1 >= o2 ? 1 : -1;
				}
				return Math.abs(o1) - Math.abs(o2);
			}
		});

		int T = stringToInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int num = stringToInt(br.readLine());
			if (num == 0) {
				if (!q.isEmpty())
					sb.append(q.poll()).append(System.lineSeparator());
				else
					sb.append(0).append(System.lineSeparator());

				continue;
			}
			q.offer(num);
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
