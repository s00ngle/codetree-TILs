import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;
    static int x, y, direction; // 0: 북, 1: 동, 2: 남, 3: 서
    static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        direction = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        visited[x][y] = true;

        for (int i = 0 ; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 1;

        while (true) {
            visited[x][y] = true;
            boolean flag = true;

            for (int i = 0 ; i < 4 ; i++) {
                turnLeft();
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (map[nx][ny] == 0 && !visited[nx][ny]) {
                    turnLeft();
                    x = nx;
                    y = ny;
                    result++;
                    flag = false;
                    break;
                }
            }
            if (flag) { // 4방향 탐색 후 갈 수 없을 때
                int reverse = (direction + 2) % 4;
                int nx = x + dx[reverse];
                int ny = y + dy[reverse];
                if (map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                }
                else {
                    break;
                }
            }
        }

        System.out.println(result);
    }

    static void turnLeft() {
        direction = (direction + 3) % 4;
    }
}