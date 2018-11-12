/**
 * Radix sort.
 */
import java.util.Scanner;
/**
 * { item_description }.
 */
import java.util.Arrays;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //empty Constructor.
    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int lines = Integer.parseInt(scan.nextLine());
        LSD l = new LSD();
        String[] strarr = new String[lines];
        for (int i = 0; i < lines; i++) {
            strarr[i] = scan.nextLine();
        }
        l.sort(strarr, strarr[0].length());
        System.out.println(Arrays.toString(strarr));
    }
}







