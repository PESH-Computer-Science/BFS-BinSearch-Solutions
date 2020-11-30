package Solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Haybales {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("haybales.in"));
        int n = sc.nextInt();
        int queries = sc.nextInt();
        int bales[] = new int[n];

        for(int i = 0; i < bales.length; i++) bales[i] = sc.nextInt();
        Arrays.sort(bales);
        PrintWriter pw = new PrintWriter(new File("haybales.out"));

        while (queries-- > 0) {
            sc.nextLine();
            int l = sc.nextInt();
            int r = sc.nextInt();
            int lower_bound = n;
            int upper_bound = 0;
            int lo = 0, hi = n - 1;

            while(lo <= hi){
                int mid = (lo + hi)/2;
                if(bales[mid] >= l){
                    hi = mid - 1;
                    lower_bound = Math.min(mid, lower_bound);
                }else lo = mid + 1;
            }

            lo = 0; hi = n - 1;
            while(lo <= hi){
                int mid = (lo + hi)/2;
                if(bales[mid] <= r){
                    lo = mid + 1;
                    upper_bound = Math.max(mid, upper_bound);
                }else hi = mid -1;
            }
            pw.println(upper_bound - lower_bound + 1);
//            System.out.println(upper_bound - lower_bound + 1);
        }
        pw.close();

    }
}
