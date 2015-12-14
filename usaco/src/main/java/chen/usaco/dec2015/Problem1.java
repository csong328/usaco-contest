package chen.usaco.dec2015;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/*-
 * Several seasons of hot summers and cold winters have taken their toll on
 * Farmer John's fence, and he decides it is time to repaint it, along with the
 * help of his favorite cow, Bessie. Unfortunately, while Bessie is actually
 * remarkably proficient at painting, she is not as good at understanding Farmer
 * John's instructions. If we regard the fence as a one-dimensional number line,
 * Farmer John paints the interval between x=a and x=b. For example, if a=3 and
 * b=5, then Farmer John paints an interval of length 2. Bessie,
 * misunderstanding Farmer John's instructions, paints the interval from x=c to
 * x=d, which may possibly overlap with part or all of Farmer John's interval.
 * Please determine the total length of fence that is now covered with paint.
 * 
 * INPUT FORMAT (file paint.in):
 * 
 * The first line of the input contains the integers a and b, separated by a
 * space (a<b). The second line contains integers c and d, separated by a space
 * (c<d).
 * 
 * The values of a, b, c, and d all lie in the range 0…100, inclusive.
 * 
 * OUTPUT FORMAT (file paint.out):
 * 
 * Please output a single line containing the total length of the fence covered
 * with paint. 
 * 
 * SAMPLE INPUT:
 * 
 * 7 10 
 * 4 8 
 * 
 * SAMPLE OUTPUT:
 * 6
 */
public class Problem1 {

    /*-
     * Check the relationship between two ranges [a, b], [c, d]
     *
     * They can be
     * a < b <= c < d - no overlap, length = (b - a) + (d - c)
     * c < d <= a < d - no overlap, length = (b - a) + (d - c)
     *
     * a < c < b < d - overlap b -c, length = (b - a) + (d - c) - (b -c)
     * a < c < d <= b - [a, b] includes [c, d], length = (d - c)
     *
     * c < a < b < d - [c, d] includes [a, b], length = (b - a) + (d - c) - (d - a)
     * c < a < d < b - overlap (d - a), length = (d - a)
     */
    // define the problem to be solved.
    public int lengthPaint(int a, int b, int c, int d) {
        // [a, b] [c, d] not overlap
        if (b < c || a > d) {
            return (b - a) + (d - c);

            // Following are overlap cases.
        } else if (a < c) {
            // [a, b] includes [c, d]
            if (b >= d) {
                return (b - a);
            } else {
                // [b, c] overlaps
                return (b - a) + (d - c) - (b - c);
            }

        } else { // a >= c
            // [b, d] includes [a, b]
            if (b <= d) {
                return (d - c);
            } else {
                // [d, a] overlaps
                return (b - a) + (d - c) - (d - a);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int a, b, c, d;
        // reading from file, very similar to read from console
        // Remember this!
        Scanner in = new Scanner(new File("paint.in"));
        // Rest is pretty much same as reading from console.
        a = in.nextInt();
        b = in.nextInt();
        c = in.nextInt();
        d = in.nextInt();
        in.close(); // you need to close the Scanner, it reads from file.
        // close operation will free up resources allocation for file reading.

        int length = new Problem1().lengthPaint(a, b, c, d);

        // System.out (console output) type is java.io.PrintStream
        // Write to a file similar to print to console. Remember this!
        PrintStream out = new PrintStream(new File("paint.out"));
        // Rest is similar to output to console.
        out.println(length);
        out.close(); // you need to close the PrintStream. It will flush the
        // content to the disk, and free up the resource.
    }

}
