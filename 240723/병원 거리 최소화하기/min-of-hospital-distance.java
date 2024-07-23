import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] arr;
    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        List<Point> person = new ArrayList<>();
        List<Point> hospital = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    person.add(new Point(i, j));
                }
                if (arr[i][j] == 2) {
                    hospital.add(new Point(i, j));
                }
            }
        }

        answer = Integer.MAX_VALUE;
        DFS(person, hospital, 0, 0, new int[M]);
        System.out.println(answer);
    }

    static void DFS(List<Point> person, List<Point> hospital, int idx, int cnt, int[] selected) {
        if (cnt == M) {
            int sum = 0;
            for (Point p : person) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < M; i++) {
                    min = Math.min(min, diff(p, hospital.get(selected[i])));
                }
                sum += min;
            }
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = idx; i < hospital.size(); i++) {
            selected[cnt] = i;
            DFS(person, hospital, i + 1, cnt + 1, selected);
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

    static int diff(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}