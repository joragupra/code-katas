package com.joragupra.codekatas;

import junit.framework.TestCase;

/**
 * @author jagudo
 * @since 22.07.13
 */
public class FlashCardsTest extends TestCase {

	public void testUserIsAskedQuestionOnTheFlashCard() {
		User user = new User();
		String question = "What tower is best against swarms?";

		assertEquals(question, user.lastQuestionAsked());
	}
}
