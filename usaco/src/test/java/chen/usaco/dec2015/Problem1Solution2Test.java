package chen.usaco.dec2015;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Problem1Solution2Test {
    private Problem1Solution2 sut = new Problem1Solution2();

    @Test
    public void test1() {
        assertEquals(6, sut.lengthPaint(7, 10, 4, 8));
    }

    @Test
    public void test2() {
        assertEquals(6, sut.lengthPaint(4, 8, 7, 10));
    }

    @Test
    public void test3() {
        assertEquals(5, sut.lengthPaint(7, 10, 4, 6));
    }

    @Test
    public void test4() {
        assertEquals(5, sut.lengthPaint(4, 6, 7, 10));
    }
}