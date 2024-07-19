import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Work[] works = new Work[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            works[i] = new Work(i, i + Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }

        dp[works[0].end] = works[0].money;

        for (int i = 1; i < n; i++) {
            if (works[i].end >= n) continue;
            dp[works[i].end] = Math.max(dp[works[i].start - 1] + works[i].money, dp[works[i].end]);
        }

        bw.write(Arrays.stream(dp).max().getAsInt() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Work {
    int start;
    int end;
    int money;

    Work(int start, int end, int money) {
        this.start = start;
        this.end = end;
        this.money = money;
    }

    @Override
    public String toString() {
        return start + " " + end + " " + money;
    }
}