import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] customers = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            customers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int leader = Integer.parseInt(st.nextToken());
        int crew = Integer.parseInt(st.nextToken());

        long result = 0;
        for (int i = 0; i < n; i++) {
            customers[i] -= leader;
            result++;
            if (customers[i] > 0) {
                result += customers[i] / crew;
                if (customers[i] % crew != 0) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}