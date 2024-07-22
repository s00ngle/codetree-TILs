import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static int[] nums;
    static int min;
    static int max;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int plus = Integer.parseInt(st.nextToken());
        int minus = Integer.parseInt(st.nextToken());
        int mul = Integer.parseInt(st.nextToken());

        min = 1000000000;
        max = -1000000000;

        DFS(1, nums[0], plus, minus, mul);

        sb.append(min).append(" ").append(max);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static void DFS(int idx, int result, int plus, int minus, int mul) {
        if (idx >= n) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        int num = nums[idx];
        if (plus > 0) {
            DFS(idx + 1, result + num, plus - 1, minus, mul);
        }
        if (minus > 0) {
            DFS(idx + 1, result - num, plus, minus - 1, mul);
        }
        if (mul > 0) {
            DFS(idx + 1, result * num, plus, minus, mul - 1);
        }
    }
}