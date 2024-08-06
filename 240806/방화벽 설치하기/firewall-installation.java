import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0,0, new Point[3]);

        System.out.println(result);
    }

    static void DFS(int idx, int depth, Point[] tempWall) {
        if (depth == 3) {
            BFS(tempWall);
            return;
        }

        for (int i = idx; i < N * M; i++) {
            int x = i / M;
            int y = i % M;

            if (map[x][y] == 0) {
                tempWall[depth] = new Point(x, y);
                DFS(i + 1, depth + 1, tempWall);
            }
        }
    }

    static void BFS(Point[] tempWall) {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        for (Point p: tempWall) {
            copyMap[p.x][p.y] = 1;
        }

        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 2) {
                    queue.add(new Point(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int x = now.x;
            int y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = 2;
                    queue.add(new Point(nx, ny));
                }
            }
        }

        int safeArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) {
                    safeArea++;
                }
            }
        }

        result = Math.max(result, safeArea);
    }

    static class Point {
        int x, y;
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