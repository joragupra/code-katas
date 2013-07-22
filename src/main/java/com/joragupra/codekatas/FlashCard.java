package com.joragupra.codekatas;

/**
 * @author jagudo
 * @copyright safetynet Ltd.
 * @since 22.07.13
 */
public class FlashCard {

	private String question;

	private String answer;

	public FlashCard(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public void play(User user) {
		user.notify( (answer == user.ask(question)) ? "Correct." : String.format("Wrong. The right answer is '%s'.", answer));
		user.ask(question);
	}
}
