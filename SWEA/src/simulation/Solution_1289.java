
package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * SWEA 1289 원재의 메모리 복구하기
 * 
 * @author hrlim
 * @version 1.0, 2022.08.02
 */
public class Solution_1289 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		// read() : 리턴타입 int, 문자 하나 읽어서 정수형으로 리턴
		// --> 문자를 읽어서 그에 해당하는 아스키 코드 정수값을 리턴하는 형태
		// 0 ~ 9 숫자를 읽어오면 48 ~ 57 로 리턴
		// readLine() : 리턴타입 String, 한줄의 문자열 읽음
		for (int test_case = 1; test_case <= T; test_case++) {

			String input = br.readLine();
			int length = input.length();

			int[] org = new int[length];
			int[] arr = new int[length];

			for (int i = 0; i < length; i++) {
				org[i] = Integer.parseInt(input.substring(i, i + 1));
			}

			int cnt = 0;
			for (int i = 0; i < length; i++) {
				if (org[i] != arr[i]) {
					Arrays.fill(arr, i, length, org[i]);
					cnt++;
				}
			}
			System.out.println("#" + test_case + " " + cnt);
		}
	}
}
