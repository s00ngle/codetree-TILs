import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        List<Point> person = new ArrayList<>();
        List<Hospital> hospital = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    person.add(new Point(i, j));
                }
                if (arr[i][j] == 2) {
                    hospital.add(new Hospital(new Point(i, j), new int[2 * N]));
                }
            }
        }

        for (Hospital value : hospital) {
            for (Point point : person) {
                int diff = diff(value.point, point);
                value.distance[diff]++;
            }
        }

        Collections.sort(hospital);

//        for (Hospital h : hospital) {
//            System.out.println(h);
//        }

        int ans = 0;

        for (Point p: person) {
            int min = Integer.MAX_VALUE;
            for (int i = 0 ; i < M; i++) {
                min = Math.min(min, diff(p, hospital.get(i).point));
            }
            ans += min;
        }

        System.out.println(ans);
    }

    static class Hospital implements Comparable<Hospital> {
        Point point;
        int[] distance;

        Hospital(Point point, int[] distance) {
            this.point = point;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "point: " + point + ", distance: " + Arrays.toString(distance);
        }

        @Override
        public int compareTo(Hospital h) {
            for (int i = 0; i < distance.length; i++) {
                if (distance[i] < h.distance[i]) {
                    return 1;
                } else if (distance[i] > h.distance[i]) {
                    return -1;
                }
            }
            return 0;
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