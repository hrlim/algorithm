package recursive;
import java.util.Scanner;

/**
 * 
 * BOJ 17478 재귀함수가 뭔가요? - 실버 5
 * <b>구현, 재귀</b>
 * 
 * @author hrlim
 * @version 1.0, 2022.08.01
 */
public class Main_17478 {

	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		recursive(N, "");
		System.out.println(sb);
	}

	/**
	 * @param N		반복횟수
	 * @param tap	반복할 때마다 들여쓰기
	 */
	static void recursive(int N, String tap) {
		sb.append(tap).append("\"재귀함수가 뭔가요?\"\n");
		if (N == 0) {
			sb.append(tap).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
			sb.append(tap).append("라고 답변하였지.\n");
			return;
		}
		sb.append(tap).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
		sb.append(tap).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
		sb.append(tap).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
		recursive(N - 1, tap + "____");
		sb.append(tap).append("라고 답변하였지.\n");
	}
}
