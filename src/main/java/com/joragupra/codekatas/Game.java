package com.joragupra.codekatas;

/**
 * Hello world!
 */
public class Game {

	private int score = 0;

	public void roll(int pins) {
		score += pins;
	}

	public int score() {
		return score;
	}
}
