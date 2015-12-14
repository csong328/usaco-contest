package chen.usaco.dec2015;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/*-
 * Always the troublemaker, Bessie the cow has stolen Farmer John's tractor and
 * taken off down the road! The road is exactly 100 miles long, and Bessie
 * drives the entire length of the road before ultimately being pulled over by a
 * police officer, who gives Bessie a ticket for exceeding the speed limit, for
 * having an expired license, and for operating a motor vehicle while being a
 * cow. While Bessie concedes that the last two tickets are probably valid, she
 * questions whether the police officer was correct in issuing the speeding
 * ticket, and she wants to determine for herself if she has indeed driven
 * faster than the speed limit for part of her journey.
 * 
 * The road is divided into N segments, each described by a positive integer
 * length in miles, as well as an integer speed limit in the range 1…100 miles
 * per hour. As the road is 100 miles long, the lengths of all N segments add up
 * to 100. For example, the road might start with a segment of length 45 miles,
 * with speed limit 70, and then it might end with a segment of length 55 miles,
 * with speed limit 60.
 * 
 * Bessie's journey can also be described by a series of segments, M of them.
 * During each segment, she travels for a certain positive integer number of
 * miles, at a certain integer speed. For example, she might begin by traveling
 * 50 miles at a speed of 65, then another 50 miles at a speed of 55. The
 * lengths of all M segments add to 100 total miles. Farmer John's tractor can
 * drive 100 miles per hour at its fastest.
 * 
 * Given the information above, please determine the maximum amount over the
 * speed limit that Bessie travels during any part of her journey.
 * 
 * INPUT FORMAT (file speeding.in):
 * 
 * The first line of the input contains N and M, separated by a space. The next
 * N lines each contain two integers describing a road segment, giving its
 * length and speed limit.
 * 
 * The next M lines each contain two integers describing a segment in Bessie's
 * journey, giving the length and also the speed at which Bessie was driving.
 * 
 * OUTPUT FORMAT (file speeding.out):
 * 
 * Please output a single line containing the maximum amount over the speed
 * limit Bessie drove during any part of her journey. If she never exceeds the
 * speed limit, please output 0. 
 * 
 * SAMPLE INPUT:
 * 3 3 
 * 40 75 
 * 50 35 
 * 10 45 
 * 40 76 
 * 20 30 
 * 40 40 
 * 
 * SAMPLE OUTPUT:
 * 5 
 * 
 * In this example, the road contains three segments (40 miles at 75 miles per
 * hour, followed by 50 miles at 35 miles per hour, then 10 miles at 45 miles
 * per hour). Bessie drives for three segments (40 miles at 76 miles per hour,
 * 20 miles at 30 miles per hour, and 40 miles at 40 miles per hour). During her
 * first segment, she is slightly over the speed limit, but her last segment is
 * the worst infraction, during part of which she is 5 miles per hour over the
 * speed limit. The correct answer is therefore 5.
 * 
 * Problem credits: Austin Bannister and Brian Dean
 */
public class Problem2 {
    /*-
     * Algorithm:
     *
     * Sample speed limit
     * Length SpeedLimit  Length DrivingSpeed
     *     40 75              40 76
     *     50 35              20 30
     *     10 45              40 40
     *
     * Convert the index from length to mileage
     * Mile SpeedLimit
     *   40 75
     *   90 35
     *  100 45
     *
     * Mile DrivingSpeed
     *   40 76
     *   60 30
     *  100 40
     *
     * Mile SpeedLimit DrivingSpeed OverSpeed
     *   40        75            76         1
     *   90        35            30         5
     *   60        75            30         0
     *  100        45            40         5
     *
     * @return
     */
    public int getMaxOverSpeed(int[][] speedLimits, int[][] drivingSpeeds) {
        /**
         * Change from segment length to distance to the origin.
         */
        // Break up big chunk of code into smaller method,
        // and method name should be very descriptive.
        convertFromLengthToMilage(speedLimits);
        convertFromLengthToMilage(drivingSpeeds);

        // record points when either speed limit or driving speed changes.
        int[] mileagePoints = new int[speedLimits.length + drivingSpeeds.length + 1];
        int mileagePointsIndex = 0;
        mileagePoints[mileagePointsIndex++] = 0;

        for (int i = 0; i < speedLimits.length; i++) {
            // this is a typical idiom to append item to an array
            mileagePoints[mileagePointsIndex++] = speedLimits[i][0];
        }

        for (int i = 0; i < drivingSpeeds.length; i++) {
            mileagePoints[mileagePointsIndex++] = drivingSpeeds[i][0];
        }

        // Check each point when speed limit or driving speed changes, to check
        // the over speed.
        int maxOverSpeed = 0;
        for (int i = 0; i < mileagePoints.length; i++) {
            int overSpeed = getOverSpeed(mileagePoints[i], speedLimits, drivingSpeeds);
            if (overSpeed > maxOverSpeed) {
                maxOverSpeed = overSpeed;
            }
        }

        return maxOverSpeed;
    }

    // each method should be focus to handle just one thing.
    // if it handles more than one thing, try to break it up further.
    private void convertFromLengthToMilage(int[][] speeds) {
        for (int i = 1; i < speeds.length; i++) {
            speeds[i][0] += speeds[i - 1][0];
        }
    }

    /**
     * Calculate the over speed.
     */
    private int getOverSpeed(int mile, int[][] speedLimits, int[][] drivingSpeeds) {
        int speedDiff = getSpeed(mile, drivingSpeeds) - getSpeed(mile, speedLimits);
        return Math.max(speedDiff, 0);
    }

    /**
     * Get speed for given mileage point, either speed limit or driving speed.
     */
    // Break the code into methods, also help reuse the same code. In this case,
    // the method is used to get both speed limit and driving speed for the given mileage.
    private int getSpeed(int mile, int[][] speedsIndexedByMileage) {
        int i = 0;

        while (i < speedsIndexedByMileage.length && mile > speedsIndexedByMileage[i][0]) {
            i++;
        }

        return speedsIndexedByMileage[i][1];
    }

    public static void main(String[] args) throws IOException {
        int N, M;
        int[][] speedLimits, drivingSpeeds;

        Scanner scanner = new Scanner(new File("speeding.in"));
        N = scanner.nextInt();
        M = scanner.nextInt();

        speedLimits = new int[N][2];
        for (int i = 0; i < speedLimits.length; i++) {
            speedLimits[i][0] = scanner.nextInt();
            speedLimits[i][1] = scanner.nextInt();
        }

        drivingSpeeds = new int[M][2];
        for (int i = 0; i < drivingSpeeds.length; i++) {
            drivingSpeeds[i][0] = scanner.nextInt();
            drivingSpeeds[i][1] = scanner.nextInt();
        }
        scanner.close();

        int maxOverSpeed = new Problem2().getMaxOverSpeed(speedLimits, drivingSpeeds);

        PrintStream out = new PrintStream(new File("speeding.out"));
        out.println(maxOverSpeed);
        out.close();
    }
}
