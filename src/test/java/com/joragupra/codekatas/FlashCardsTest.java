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
		user.notify( (user.ask(question) == "Inferno") ? "Correct." : "Wrong. The right answer is 'Inferno'.");
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
}
