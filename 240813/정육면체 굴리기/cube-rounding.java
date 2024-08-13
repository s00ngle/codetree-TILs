import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int M;
    static int x;
    static int y;
    static int k;
    static int[][] map;

    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    static int[] dice = new int[6];
    // 0 : 앞면
    // 1 : 윗면
    // 2 : 오른쪽면
    // 3 : 왼쪽면
    // 4 : 뒷면
    // 5 : 아랫면

    static int max;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int dir = Integer.parseInt(st.nextToken());
            roll(dir);
        }


    }

    static void roll(int dir) {
        // 0 : 앞면
        // 1 : 윗면
        // 2 : 오른쪽면
        // 3 : 왼쪽면
        // 4 : 아랫면
        // 5 : 뒷면

        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
            return;
        }
        x = nx;
        y = ny;

        if (dir == 1) { // 동
            // 위 <- 왼쪽 <- 아래 <- 오른쪽 <- 위
            int temp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[4];
            dice[4] = dice[2];
            dice[2] = temp;
        }
        if (dir == 2) { // 서
            // 위 <- 오른쪽 <- 아래 <- 왼쪽 <- 위
            int temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[4];
            dice[4] = dice[3];
            dice[3] = temp;
        }
        if (dir == 3) { // 북
            // 위 <- 앞 <- 아래 <- 뒤 <- 위
            int temp = dice[1];
            dice[1] = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = temp;
        }
        if (dir == 4) { // 남
            // 위 <- 뒤 <- 아래 <- 앞 <- 위
            int temp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = dice[0];
            dice[0] = temp;
        }

        // 0 : 앞면
        // 1 : 윗면
        // 2 : 오른쪽면
        // 3 : 왼쪽면
        // 4 : 아랫면
        // 5 : 뒷면
        if (map[x][y] == 0) {
            map[x][y] = dice[4];
        } else {
            dice[4] = map[x][y];
            map[x][y] = 0;
        }
        System.out.println(dice[1]);
    }

}