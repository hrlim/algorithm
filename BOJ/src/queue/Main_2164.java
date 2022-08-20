package queue;

import java.io.*;
import java.util.*;

/**
 * 카드 2
 */
public class Main_2164 {
        public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stringToInt(br.readLine());

        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= T; i++) {
            queue.offer(i);
        }

        while (queue.size() != 1) {
            queue.poll();
            int temp = queue.poll();
            queue.offer(temp);
        }
        System.out.println(queue.poll());
        
    }

	/**
	 * 문자형 타입을 숫자형 타입으로 변환해주는 메서드
	 * 
	 * @param s
	 * @return
	 */
    static int stringToInt(String s) {
        return Integer.parseInt(s);
    }
}

