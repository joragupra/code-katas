package com.joragupra.codekatas;

import junit.framework.TestCase;

/**
 * @author jagudo
 * @since 22.07.13
 */
public class FlashCardsTest extends TestCase {

	private User user;

	public void setUp() {
		this.user = new User();
	}

	public void testUserIsAskedQuestionAboutSwarms() {
		String question = "What tower is best against swarms?";
		play(question);
		testUserIsAskedQuestionOnTheFlashCard(question);
	}

	public void testUserIsAskedQuestionAboutRacers() {
		String question = "What tower is best against racers?";
		play(question);
		testUserIsAskedQuestionOnTheFlashCard(question);
	}

	private void play(String question) {
		play(question, this.user);
	}

	private void play(String question, User user) {
		play(question, user, "Inferno");
	}

	private void play(String question, User user, String rightAnswer) {
		user.notify( (rightAnswer == user.ask(question)) ? "Correct." : "Wrong. The right answer is 'Inferno'.");
		user.ask(question);
	}

	private void testUserIsAskedQuestionOnTheFlashCard(String question) {
		assertEquals(question, this.user.lastQuestionAsked());
	}

	public void testACorrectAnswerIsConfirmed() {
		String question = "What tower is best against swarms?";
		play(question);
		assertEquals("Correct.", this.user.lastConfirmationReceived());
	}

	public void testAWrongAnswerIsCorrected() {
		User user = User.whoAnswers("laser");
		play("What tower is best against swarms?", user);
		assertEquals("Wrong. The right answer is 'Inferno'.", user.lastConfirmationReceived());
	}

	public void testAnotherCorrectAnswerIsConfirmed() {
		User user = User.whoAnswers("Laser");
		play("What tower is best against racers?", user, "Laser");
		assertEquals("Correct.", user.lastConfirmationReceived());
	}

	public void testAnotherWrongAnswerIsCorrected() {
		User user = User.whoAnswers("Inferno");
		play("What tower is best against racers?", user, "Laser");
		assertEquals("Wrong. The right answer is 'Laser'.", user.lastConfirmationReceived());
	}
}
