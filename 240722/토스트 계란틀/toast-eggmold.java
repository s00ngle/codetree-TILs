import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while (true) {
            boolean flag = true;

            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<Point> group = new ArrayList<>();
                        DFS(i, j, group);

                        if (group.size() >= 2) {
                            flag = false;
                        }

                        int sum = 0;
                        for (Point point : group) {
                            sum += map[point.x][point.y];
                        }
                        int avg = sum / group.size();
                        for (Point point : group) {
                            map[point.x][point.y] = avg;
                        }
                    }
                }
            }
            if (flag) {
                System.out.println(ans);
                break;
            }
            ans++;
        }
    }

    static void DFS(int x, int y, List<Point> group) {
        visited[x][y] = true;
        group.add(new Point(x, y));

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                continue;
            }
            if (!visited[nx][ny]) {
                int diff = Math.abs(map[x][y] - map[nx][ny]);
                if (diff >= L && diff <= R) {
                    DFS(nx, ny, group);
                }
            }
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}