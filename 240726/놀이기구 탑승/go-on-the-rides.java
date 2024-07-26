import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] likes;
    static int[][] rides;
    static List<Integer> students = new ArrayList<>();

    static int[] points = {0, 1, 10, 100, 1000};

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        likes = new int[N * N + 1][N * N + 1];
        rides = new int[N][N];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            students.add(x);
            for (int j = 0; j < 4; j++) {
                int y = Integer.parseInt(st.nextToken());
                likes[x][y] = 1;
            }
        }

        for (int student : students) {
            int x = -1, y = -1, like = -1, empty = -1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (rides[i][j] == 0) {
                        int l = 0, e = 0;
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                            if (likes[student][rides[nx][ny]] == 1) l++;
                            if (rides[nx][ny] == 0) e++;
                        }
                        if (l > like || (l == like && e > empty)) {
                            x = i;
                            y = j;
                            like = l;
                            empty = e;
                        }
                    }
                }
            }
            rides[x][y] = student;
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (likes[rides[i][j]][rides[nx][ny]] == 1) {
                        cnt++;
                    }
                }
                sum += points[cnt];
            }
        }
        System.out.println(sum);
    }
}