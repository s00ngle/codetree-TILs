import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] trees;
    static List<Point> nutrients = new ArrayList<>();
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, 1, 1, 0, -1, -1, -1, 0, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trees = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                trees[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nutrients.add(new Point(N - 2, 0));
        nutrients.add(new Point(N - 2, 1));
        nutrients.add(new Point(N - 1, 0));
        nutrients.add(new Point(N - 1, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            moveNutrients(d, p);
            growTrees();
            growNearBy();
            reNutrients();
        }

//        for (int[] tree: trees) {
//            for (int t: tree) {
//                System.out.print(t + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println(nutrients);
        int sum = 0;
        for (int[] tree : trees) {
            for (int t : tree) {
                sum += t;
            }
        }
        System.out.println(sum);
    }

    static void moveNutrients(int d, int p) {
        for (int i = 0; i < nutrients.size(); i++) {
            Point cur = nutrients.get(i);
            int nx = ((cur.x + dx[d] * p) + N) % N;
            int ny = ((cur.y + dy[d] * p) + N) % N;

            nutrients.set(i, new Point(nx, ny));
        }
    }

    static void growTrees() {
        for (Point nutrient : nutrients) {
            trees[nutrient.x][nutrient.y]++;
        }
    }

    static void growNearBy() {
        int[][] temp = new int[N][N];
        for (Point nutrient : nutrients) {
            int x = nutrient.x;
            int y = nutrient.y;

            for (int i = 2; i < 9; i += 2) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (trees[nx][ny] > 0) temp[x][y]++;
            }
            trees[x][y] += temp[x][y];
        }
    }

    static void reNutrients() {
        boolean[][] temp = new boolean[N][N];
        for (Point nutrient : nutrients) {
            int x = nutrient.x;
            int y = nutrient.y;
            temp[x][y] = true;
        }
        nutrients.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (trees[i][j] >= 2 && !temp[i][j]) {
                    trees[i][j] -= 2;
                    nutrients.add(new Point(i, j));
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