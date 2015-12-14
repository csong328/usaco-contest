package chen.usaco.dec2015;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Problem2Test {
    private Problem2 sut = new Problem2();

    @Test
    public void test() {
        int[][] speedLimits = new int[][]{
                {40, 75},
                {50, 35},
                {10, 45}
        };

        int[][] drivingSpeeds = new int[][]{
                {40, 76},
                {20, 30},
                {40, 40}
        };

        int maxOverSpeed = sut.getMaxOverSpeed(speedLimits, drivingSpeeds);

        assertEquals(5, maxOverSpeed);
    }

}
