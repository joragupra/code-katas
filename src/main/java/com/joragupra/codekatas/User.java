package com.joragupra.codekatas;

/**
 * @author jagudo
 * @since 22.07.13
 */
public class User {

	private String lastQuestionAsked;

	public String lastQuestionAsked() {
		return lastQuestionAsked;
	}

	public void ask(String question) {
		this.lastQuestionAsked = question;
	}

	public String lastConfirmationReceived() {
		return "Correct.";
	}

	public static User whoAnswers(String answer) {
		return null;
	}
}
