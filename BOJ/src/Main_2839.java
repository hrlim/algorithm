import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 설탕 배달
 */
public class Main_2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean isOk = false;
        int cnt = 0;
        for (int i = N / 5; i >= 0; i--) {

            cnt = i;
            N -= 5 * i;

            if (N % 3 == 0) {
                cnt += (N / 3);
                isOk = true;
                break;
            }
            cnt = 0;
        }

        if (!isOk) {
            if (N % 3 == 0) {
                cnt = N / 3;
            } else {
                cnt = -1;
            }
        }

        System.out.println(cnt);
    }
}