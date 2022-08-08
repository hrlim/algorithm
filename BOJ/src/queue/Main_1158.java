package queue;

import java.io.*;
import java.util.*;

/**
 * 요세푸스 문제
 */
public class Main_1158 {
        public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = stringToInt(st.nextToken());
        int K = stringToInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        bw.write("<");
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }
        
        while(queue.size() != 1) {
        	for (int i = 0; i < K - 1; i++) {
				queue.offer(queue.poll());
			}
        	 bw.write(queue.poll() + ", ");
        }

        bw.write(queue.poll() + ">");
        bw.flush();
        bw.close();
        br.close();
        
    }

    static int stringToInt(String s) {
        return Integer.parseInt(s);
    }
}

