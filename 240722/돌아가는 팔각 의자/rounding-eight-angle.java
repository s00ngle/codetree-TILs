import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        String[] table = new String[4];
        for (int i = 0; i < 4; i++) {
            table[i] = br.readLine();
        }

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()); // 1: 시계방향, -1: 반시계방향

            int[] rotate = new int[4];
            rotate[t - 1] = dir;

            for (int j = t - 1; j < 3; j++) {
                if (table[j].charAt(2) != table[j + 1].charAt(6)) {
                    rotate[j + 1] = -rotate[j];
                }
            }
            for (int j = t - 1; j > 0; j--) {
                if (table[j].charAt(6) != table[j - 1].charAt(2)) {
                    rotate[j - 1] = -rotate[j];
                }
            }

            for (int j = 0; j < 4; j++) {
                table[j] = rotate(table[j], rotate[j]);
            }
        }

        int s1 = charToInt(table[0].charAt(0));
        int s2 = charToInt(table[1].charAt(0));
        int s3 = charToInt(table[2].charAt(0));
        int s4 = charToInt(table[3].charAt(0));
        System.out.println(s1 + s2 * 2 + s3 * 4 + s4 * 8);
    }

    public static String rotate(String s, int dir) {
        if (dir == 1) {
            return s.charAt(7) + s.substring(0, 7);
        }
        if (dir == -1){
            return s.substring(1, 8) + s.charAt(0);
        }
        return s;
    }

    public static int charToInt(char c) {
        return c - '0';
    }
}