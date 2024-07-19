import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int sum;
    static int[][] arr;
    static int ans;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        sum = 0;
        ans = Integer.MAX_VALUE;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                sum += arr[i][j];
            }
        }
        
        DFS(0, new int[n / 2], 0, n);
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int depth, int[] selected, int start, int n) {
        if (depth == n / 2) {
            int[] notSelected = new int[n / 2];
            int idx = 0;
            for (int i = 0 ; i < n; i++) {
                boolean isSelected = false;
                for (int j = 0; j < selected.length; j++) {
                    if (i == selected[j]) {
                        isSelected = true;
                        break;
                    }
                }
                if (!isSelected) {
                    notSelected[idx++] = i;
                }
            }

            int sum1 = getSum(selected);
            int sum2 = getSum(notSelected);
            ans = Math.min(ans, Math.abs(sum1 - sum2));
            return;
        }

        for (int i = start; i < n; i++) {
            selected[depth] = i;
            DFS(depth + 1, selected, i + 1, n);
        }
    }

    public static int getSum(int[] selected) {
        int sum = 0;
        for (int i = 0; i < selected.length; i++) {
            for (int j = 0; j < selected.length; j++) {
                sum += arr[selected[i]][selected[j]];
            }
        }
        return sum;
    }
}