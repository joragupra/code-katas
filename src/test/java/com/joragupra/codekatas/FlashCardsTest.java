package com.joragupra.codekatas;

import junit.framework.TestCase;

/**
 * @author jagudo
 * @since 22.07.13
 */
public class FlashCardsTest extends TestCase {

	private User user;

	private static final String QUESTION_RACER_DEFENSE = "What tower is best against racers?";

	private static final String QUESTION_ON_GROUP_DEFENSE = "What tower is best against groups?";

	public void setUp() {
		this.user = new User();
	}

	public void testUserIsAskedQuestionAboutSwarms() {
		String question = "What tower is best against swarms?";
		play(question);
		testUserIsAskedQuestionOnTheFlashCard(question);
	}

	public void testUserIsAskedQuestionAboutRacers() {
		play(QUESTION_RACER_DEFENSE);
		testUserIsAskedQuestionOnTheFlashCard(QUESTION_RACER_DEFENSE);
	}

	private void play(String question) {
		play(question, this.user);
	}

	private void play(String question, User user) {
		play(user, new FlashCard(question, "Inferno"));
	}

	private void play(User user, FlashCard card) {
		card.play(user);
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
		play(user, new FlashCard(QUESTION_RACER_DEFENSE, "Laser"));
		assertEquals("Correct.", user.lastConfirmationReceived());
	}

	public void testAnotherWrongAnswerIsCorrected() {
		User user = User.whoAnswers("Inferno");
		play(user, new FlashCard(QUESTION_RACER_DEFENSE, "Laser"));
		assertEquals("Wrong. The right answer is 'Laser'.", user.lastConfirmationReceived());
	}

	public void testPlayTwoCards() {
		User user = User.whoAnswers("Laser");
		play(user, new FlashCard(QUESTION_RACER_DEFENSE, "Laser"), new FlashCard(QUESTION_ON_GROUP_DEFENSE, "Concussion"));
		assertEquals("Correct.", user.confirmationsReceived().get(0));
		assertEquals("Wrong. The right answer is 'Concussion'.", user.confirmationsReceived().get(1));
	}

	private void play(User user, FlashCard card1, FlashCard card2) {
		play(user, card1);
		play(user, card2);
	}
}
