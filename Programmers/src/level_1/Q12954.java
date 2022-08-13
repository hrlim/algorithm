package level_1;

import java.util.Scanner;

/**
 * <b>직사각형 별찍기</b>
 * https://school.programmers.co.kr/learn/courses/30/lessons/12969
 * 
 * @author hrlim
 * @version 최초 2022.08.14
 */
public class Q12954 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();

		for (int i = 0; i < b; i++) {
			for (int j = 0; j < a; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
