import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int max;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                DFS(i, j, 0, 0);
                checkCross(i, j);
            }
        }

        System.out.println(max);
    }

    static void DFS(int x, int y, int depth, int sum) {
        if (depth == 4) {
            max = Math.max(max, sum);
        }

        sum += map[x][y];
        visited[x][y] = true;

        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

            if (!visited[nx][ny]) {
                DFS(nx, ny, depth + 1, sum);
            }
        }

        visited[x][y] = false;
    }

    static void checkCross(int x, int y) {
        int sum = map[x][y];
        for (int i = 0 ; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            sum += map[nx][ny];
        }
        for (int i = 0 ; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                max = Math.max(max, sum);
                continue;
            }
            sum -= map[nx][ny];
            max = Math.max(max, sum);
            sum += map[nx][ny];
        }
    }
}