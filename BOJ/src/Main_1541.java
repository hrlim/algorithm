import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 잃어버린 괄호
 * 
 * @author hrlim
 * @version 1.0, 2022.08.18
 *
 */
public class Main_1541 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer input = new StringTokenizer(br.readLine(), "-");
		StringTokenizer inner = null;
		List<Integer> plusSum = new ArrayList<>();

		int minusCont = input.countTokens();
		
		
		for (int i = 0; i < minusCont; i++) {
			inner = new StringTokenizer(input.nextToken(), "+");
			int sum = 0;
			for(int j = 0; j< inner.countTokens();) {
				sum += stringToInt(inner.nextToken());
			}
			plusSum.add(sum);
		}
		
		int answer = plusSum.get(0);
		for (int i = 1; i < plusSum.size(); i++) {
			answer -= plusSum.get(i);
		}
		System.out.println(answer);

	}

	static int stringToInt(String s) {
		return Integer.parseInt(s);
	}
}