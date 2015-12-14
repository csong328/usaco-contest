package chen.usaco.dec2015;

import static org.junit.Assert.*;

import org.junit.Test;

import chen.usaco.dec2015.Problem1;

/**
 * Create a junit test case will save your time,
 * iron out bugs quickly.
 */
public class Problem1Test {
	private Problem1 sut = new Problem1();

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
