package com.joragupra.codekatas;

import junit.framework.TestCase;

public class BowlingGameTest extends TestCase {

	private Game g;

	protected void setUp() {
		g = new Game();
	}

	private void rollMany(int n, int pins) {
		for (int i = 0; i < n; i++) {
			g.roll(pins);
		}
	}

	public void testGutterGame() {
		rollMany(20, 0);
		assertEquals(0, g.score());
	}

	public void testAllOnes() {
		rollMany(20, 1);
		assertEquals(20, g.score());
	}

	public void testOneSpare() {
		rollSpare();
		g.roll(3);
		rollMany(17, 0);
		assertEquals(16, g.score());
	}

	public void testPerfectGame() {
		rollMany(12, 10);
		assertEquals(300, g.score());
	}

	public void testOneStrike() {
		rollStrike();
		g.roll(3);
		g.roll(4);
		rollMany(16, 0);
		assertEquals(24, g.score());
	}

	private void rollStrike() {
		g.roll(10);
	}

	private void rollSpare() {
		g.roll(5);
		g.roll(5);
	}
}
