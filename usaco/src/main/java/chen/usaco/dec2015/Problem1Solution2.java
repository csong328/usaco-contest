package chen.usaco.dec2015;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Solve problem 1 in object-oriented way.
 */
public class Problem1Solution2 {

    public int lengthPaint(int a, int b, int c, int d) {
        Range range1 = new Range(a, b);
        Range range2 = new Range(c, d);

        return range1.length() + range2.length() - range1.overlapLength(range2);
    }

    public static void main(String[] args) throws IOException {
        int a, b, c, d;
        Scanner in = new Scanner(new File("paint.in"));
        a = in.nextInt();
        b = in.nextInt();
        c = in.nextInt();
        d = in.nextInt();
        in.close();

        int length = new Problem1().lengthPaint(a, b, c, d);

        PrintStream out = new PrintStream(new File("paint.out"));
        out.println(length);
        out.close();
    }

    /**
     * The Range concept introduced make the problem much more clearer.
     */
    private static class Range {
        private final int begin, end;

        public Range(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        public int length() {
            return this.end - this.begin;
        }

        public boolean inRange(int point) {
            return point >= this.begin && point <= this.end;
        }

        public int overlapLength(Range other) {
            if (other.begin < this.begin && this.inRange(other.end)) {
                return other.end - this.begin;
            } else if (this.inRange(other.begin) && inRange(other.end)) {
                return other.end - other.begin;
            } else if (this.inRange(other.begin) && other.end > this.end) {
                return this.end - other.begin;
            } else {
                return 0;
            }
        }
    }
}
