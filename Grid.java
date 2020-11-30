package Solutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Grid {

    static boolean inBounds(int i, int j, int row, int col) {
        return i >= 0 && j >= 0 && j < col && i < row;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        int grid[][] = new int[rows][cols];
        int steps[][] = new int[rows][cols];
        boolean visited[][] = new boolean[rows][cols];

        sc.nextLine();
        for (int i = 0; i < rows; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Integer.parseInt(line.charAt(j) + "");
            }
        }
        for (int[] row : steps)
            Arrays.fill(row, 10000000);

        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(0, 0));
        steps[0][0] = 0;
        int dx[] = {1, -1, 0, 0};
        int dy[] = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Coordinate at = queue.poll();
            if (visited[at.r][at.c]) {
                if (at.r == rows - 1 && at.c == cols - 1)
                    break;
                continue;
            }
            visited[at.r][at.c] = true;
            int jumpDistance = grid[at.r][at.c];
            for (int d = 0; d < 4; d++) {
                int x = at.r + dx[d] * jumpDistance;
                int y = at.c + dy[d] * jumpDistance;
                if (inBounds(x, y, rows, cols) && (steps[x][y] > steps[at.r][at.c] + 1)) {
                    visited[x][y] = false;
                    queue.add(new Coordinate(x, y));
                    steps[x][y] = steps[at.r][at.c] + 1;
                }
            }
        }
        if (!visited[rows - 1][cols - 1]) {
            System.out.println(-1);
        } else {
            System.out.println(steps[rows - 1][cols - 1]);
        }
    }

    static class Coordinate {
        int r, c;

        Coordinate(int row, int col) {
            r = row;
            c = col;
        }
    }
}
