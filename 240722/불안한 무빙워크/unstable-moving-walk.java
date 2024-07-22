import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] walk = new int[2 * n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < 2 * n ; i++) {
            walk[i] = Integer.parseInt(st.nextToken());
        }

        int enter = 0;
        boolean[] check = new boolean[2 * n];

        int ans = 0;
        while (true) {
            ans++;

            // 1. 무빙워크 한 칸 회전
            enter = ((enter + 2 * n) - 1) % (2 * n);
            int exit = (enter + n - 1) % (2 * n);

            // 2. 무빙 워크 위의 모든 사람 한 칸 위로
            // 바로 앞 칸에 사람이 있거나 앞 칸 안정성 0이면 이동 x

            if (check[exit]) { // 회전 후 끝에 사람 도착하면 내리기
                check[exit] = false;
            }

            for (int i = n - 2 ; i >= 0 ; i--) {
                int idx = (enter + i) % (2 * n);
                int next = (idx + 1) % (2 * n);
                if (check[idx] && !check[next] && walk[next] > 0) { // 앞 칸에 사람이 없고 안정성 0 이상이면 이동
                    check[idx] = false;
                    check[next] = true;
                    walk[next]--;
                }
            }

            if (check[exit]) { // 앞으로 한 칸씩 이동 후 끝 칸 확인 후 내리기
                check[exit] = false;
            }

            // 3. 1번 칸에 사람 없고 안정성 0 아니면 사람 추가
            if (check[enter] == false && walk[enter] > 0) {
                check[enter] = true;
                walk[enter]--;
            }

            // 4. 안정성 0인 칸 k개 이상이면 종료
            int sum = 0;
            for (int i = 0 ; i < 2 * n ; i++) {
                if (walk[i] == 0) {
                    sum++;
                }
            }
            if (sum >= k) {
                break;
            }
        }

        System.out.println(ans);
    }
}